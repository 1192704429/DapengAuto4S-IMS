package com.repair.manage.config.mybatis_plus;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wuming
 * @description: 存储当前线程处理的表名称以及打印完整的sql
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SqlInterceptor implements InnerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SqlInterceptor.class);
    private static final Pattern FROM_PATTERN = Pattern.compile("FROM\\s+([^\\s,]+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern JOIN_PATTERN = Pattern.compile("JOIN\\s+([^\\s,]+)", Pattern.CASE_INSENSITIVE);
    private static final Set<String> IGNORE_KEYWORDS = new HashSet<>(Arrays.asList("FROM", "JOIN", "WHERE", "GROUP", "ORDER", "HAVING", "LIMIT", "OFFSET", "VALUES"));

    // 操作前置处理
    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        try {
            BoundSql boundSql = sh.getBoundSql();
            String originalSql = boundSql.getSql();
            if (originalSql != null && !originalSql.trim().isEmpty()) {
                String tableName = extractTableName(originalSql);
                if (tableName != null) {
                    TableNameContext.set(tableName);
                    log.info("sql handle tableName save success! tableName: {}", tableName);
                }
            }
        } catch (Exception e) {
            log.error("sql handle tableName error! ", e);
        }
    }

    // 查询前置处理
    @Override
    @SuppressWarnings("rawtypes")
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        // 获取SQL语句
        String sql = showSql(ms.getConfiguration(), boundSql);
        log.info("\n SQL: {}", sql);
    }

    // 操作前置处理
    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        BoundSql boundSql = ms.getBoundSql(parameter);
        // 获取SQL语句
        String sql = showSql(ms.getConfiguration(), boundSql);
        log.info("\n SQL: {}", sql);
    }

    // sql处理
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (!parameterMappings.isEmpty() && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    }
                }
            }
        }
        return sql;
    }

    // 获取参数值
    private static String getParameterValue(Object obj) {
        if (obj == null) {
            return "null";
        }
        
        if (obj instanceof String) {
            return "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            return "'" + formatter.format((Date) obj) + "'";
        } else {
            return obj.toString();
        }
    }

    // 从SQL中提取表名
    private String extractTableName(String sql) {
        // 简化版表名提取，适用于大多数情况
        String processedSql = sql.trim().toUpperCase();

        // 处理SELECT语句
        if (processedSql.startsWith("SELECT")) {
            return extractTableNameFromSelect(processedSql);
        }
        // 处理INSERT语句
        if (processedSql.startsWith("INSERT")) {
            return extractTableNameFromInsert(processedSql);
        }
        // 处理UPDATE语句
        if (processedSql.startsWith("UPDATE")) {
            return extractTableNameFromUpdate(processedSql);
        }
        // 处理DELETE语句
        if (processedSql.startsWith("DELETE")) {
            return extractTableNameFromDelete(processedSql);
        }
        return null;
    }

    // 从SELECT语句提取表名
    private String extractTableNameFromSelect(String sql) {
        // 处理FROM子句
        Matcher fromMatcher = FROM_PATTERN.matcher(sql);
        if (fromMatcher.find()) {
            String tableName = fromMatcher.group(1);
            return cleanTableName(tableName);
        }

        // 处理JOIN子句
        Matcher joinMatcher = JOIN_PATTERN.matcher(sql);
        if (joinMatcher.find()) {
            String tableName = joinMatcher.group(1);
            return cleanTableName(tableName);
        }

        return null;
    }

    // 从INSERT语句提取表名
    private String extractTableNameFromInsert(String sql) {
        int intoIndex = sql.indexOf("INTO");
        if (intoIndex == -1) {
            return null;
        }

        int startIndex = intoIndex + 4;
        int endIndex = findNextKeyword(sql, startIndex);

        if (endIndex > startIndex) {
            String tableName = sql.substring(startIndex, endIndex).trim();
            return cleanTableName(tableName);
        }

        return null;
    }

    // 从UPDATE语句提取表名
    private String extractTableNameFromUpdate(String sql) {
        int updateIndex = sql.indexOf("UPDATE");
        if (updateIndex == -1) {
            return null;
        }

        int startIndex = updateIndex + 6;
        int endIndex = findNextKeyword(sql, startIndex);

        if (endIndex > startIndex) {
            String tableName = sql.substring(startIndex, endIndex).trim();
            return cleanTableName(tableName);
        }

        return null;
    }

    // 从DELETE语句提取表名
    private String extractTableNameFromDelete(String sql) {
        int fromIndex = sql.indexOf("FROM");
        if (fromIndex == -1) {
            return null;
        }

        int startIndex = fromIndex + 4;
        int endIndex = findNextKeyword(sql, startIndex);

        if (endIndex > startIndex) {
            String tableName = sql.substring(startIndex, endIndex).trim();
            return cleanTableName(tableName);
        }

        return null;
    }

    // 查找下一个关键字位置
    private int findNextKeyword(String sql, int startIndex) {
        int endIndex = sql.length();

        for (String keyword : IGNORE_KEYWORDS) {
            int index = sql.indexOf(keyword, startIndex);
            if (index != -1 && index < endIndex) {
                endIndex = index;
            }
        }

        return endIndex;
    }

    // 清理表名（去除模式名、别名、引号等）
    private String cleanTableName(String tableName) {
        // 去除模式名（如 schema.table）
        if (tableName.contains(".")) {
            tableName = tableName.substring(tableName.lastIndexOf('.') + 1);
        }

        // 去除别名（如 table AS t）
        if (tableName.contains(" ")) {
            tableName = tableName.substring(0, tableName.indexOf(' '));
        }

        // 去除引号
        tableName = tableName.replaceAll("\"", "").replaceAll("'", "").replaceAll("`", "");

        return tableName;
    }

}