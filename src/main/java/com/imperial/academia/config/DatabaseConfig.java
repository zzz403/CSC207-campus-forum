package com.imperial.academia.config;

public class DatabaseConfig {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/";
    public static final String DB_URL_NAME = "jdbc:mysql://localhost:3306/academia_imperial?useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASS = "123456";
    public static final String DB_NAME = "academia_imperial";
    private DatabaseConfig() {
        // wait, this is a utility class
    }
}
