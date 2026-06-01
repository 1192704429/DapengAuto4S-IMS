package com.repair.manage.config;

import cn.hutool.core.util.StrUtil;
import com.repair.manage.common.Result;
import com.repair.manage.config.mybatis_plus.TableNameContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuming
 * @description: 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionConfig {

    @Autowired
    private DataSource dataSource;

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException ex) {
        log.error("数据库唯一主键异常");
        // 获取原始SQL异常
        String resultStr = "数据库字段不能重复，请检查数据!";
        Throwable cause = ex.getRootCause();
        if (cause instanceof SQLException) {
            SQLException sqlException = (SQLException) cause;
            String errorMessage = sqlException.getMessage();
            // 解析唯一索引冲突的列名
            String columnName = getColumnNameByIndexName(extractIndexName(errorMessage));
            if (StrUtil.isNotEmpty(columnName)) {
                // 根据列名获取注释
                String columnComment = getColumnCommentByColumnName(columnName);
                if (StrUtil.isNotEmpty(columnComment)) {
                    resultStr = columnComment + "不能重复，请检查数据!";
                }
            }
        }
        return Result.error(resultStr);
    }

    @ExceptionHandler(Exception.class)
    public Result handleGlobalException(Exception ex) {
        ex.printStackTrace();
        return Result.error("系统繁忙，请稍后重试！");
    }
	
	@ExceptionHandler(NoResourceFoundException.class)
    public Result handleNoResourceFoundException(NoResourceFoundException ex) {
        String resourcePath = ex.getResourcePath() != null ? ex.getResourcePath() : ex.getMessage();
        String msg = "没有静态资源 " + resourcePath;
        log.warn(msg);
        return Result.error(msg);
    }

    /**
     * 从 MySQL 唯一索引冲突错误信息中解析出索引名
     * 示例输入: "Duplicate entry 'admin' for key 'uk_username'"
     * 示例输出: "uk_username"
     */
    public static String extractIndexName(String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            return null;
        }

        // 查找 "key '" 之后的内容
        int keyStartIndex = errorMessage.indexOf("key '");
        if (keyStartIndex == -1) {
            return null;
        }

        // 索引名开始位置
        int indexStart = keyStartIndex + 5; // "key '" 的长度是 5

        // 查找索引名结束位置的单引号
        int indexEnd = errorMessage.indexOf("'", indexStart);
        if (indexEnd == -1 || indexEnd <= indexStart) {
            return null;
        }

        // 提取索引名
        return errorMessage.substring(indexStart, indexEnd);
    }


    // 根据索引名获取列名（需要实现）
    private String getColumnNameByIndexName(String indexName) {
        // 这里可以通过数据库元数据查询或维护一个索引名到列名的映射表
        // 示例：假设索引名是uk_xxx格式，列名就是xxx
        if (indexName.startsWith("uk_")) {
            return indexName.substring(3);
        }
        return null;
    }


    // 根据列名获取注释
    private String getColumnCommentByColumnName(String columnName) {
        String tableName = TableNameContext.get();
        Map<String, String> columnComments = getColumnComments(tableName);
        return columnComments.get(columnName);
    }

    // 获取表中所有列的注释
    public Map<String, String> getColumnComments(String tableName) {
        Map<String, String> commentMap = new HashMap<>();
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            // 获取当前连接的schema(数据库名)
            String schema = connection.getCatalog();
            // 使用schema参数精确查询当前数据库的表
            ResultSet columns = metaData.getColumns(schema, null, tableName, null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnComment = columns.getString("REMARKS");
                commentMap.put(columnName, columnComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取表{}的列注释失败", tableName, e);
        }
        return commentMap;
    }

}
