package com.repair.manage.config.mybatis_plus;

/**
 * @author wuming
 * @description: 线程容器，存储当前线程处理的表名称
 */
public class TableNameContext {

    private static final ThreadLocal<String> TABLE_NAME_HOLDER = new ThreadLocal<>();

    // 设置当前线程的表名
    public static void set(String tableName) {
        TABLE_NAME_HOLDER.set(tableName);
    }

    // 获取当前线程的表名
    public static String get() {
        return TABLE_NAME_HOLDER.get();
    }

    // 清除当前线程的表名
    public static void clear() {
        TABLE_NAME_HOLDER.remove();
    }

}
