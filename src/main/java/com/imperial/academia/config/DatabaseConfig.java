package com.imperial.academia.config;

/**
 * DatabaseConfig is a configuration class that provides
 * the necessary information for connecting to the H2 database.
 */
public class DatabaseConfig {
    // JDBC driver name
    private static final String JDBC_DRIVER = "org.h2.Driver";
    // Database URL
    private static final String DB_URL_NAME = "jdbc:h2:./database/academia_imperial";
    // Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";

    /**
     * Gets the JDBC driver name.
     * 
     * @return the JDBC driver name.
     */
    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    /**
     * Gets the database URL.
     * 
     * @return the database URL.
     */
    public static String getDbUrlName() {
        return DB_URL_NAME;
    }

    /**
     * Gets the database username.
     * 
     * @return the database username.
     */
    public static String getUser() {
        return USER;
    }

    /**
     * Gets the database password.
     * 
     * @return the database password.
     */
    public static String getPass() {
        return PASS;
    }
}
