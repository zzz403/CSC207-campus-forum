package com.imperial.academia.config;

public class DatabaseConfig {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL_NAME = "jdbc:h2:./src/main/java/com/imperial/academia/database/academia_imperial";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    public static String getDbUrlName() {
        return DB_URL_NAME;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPass() {
        return PASS;
    }
}
