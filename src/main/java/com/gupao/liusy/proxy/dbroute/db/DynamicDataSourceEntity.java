package com.gupao.liusy.proxy.dbroute.db;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:02
 */
public class DynamicDataSourceEntity {
    private final static ThreadLocal<String> local = new ThreadLocal<String>();
    private final static String DEFAULT_DATASOURCE = null;

    private DynamicDataSourceEntity() {

    }

    private static String get() {
        return local.get();
    }

    private static void set(String datasource) {
        local.set(datasource);
    }

    public static void set(int year) {
        local.set("DB_" + year);
    }

    public static void restore(){
        local.set(DEFAULT_DATASOURCE);
        System.out.println("数据源还原为默认");
    }
}