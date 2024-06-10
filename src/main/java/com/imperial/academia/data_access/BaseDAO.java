package com.imperial.academia.data_access;

import com.imperial.academia.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    void insert(T t) throws SQLException;
    T get(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    void update(T t) throws SQLException;
    void delete(int id) throws SQLException;

    default Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseConfig.JDBC_DRIVER);
        return DriverManager.getConnection(DatabaseConfig.DB_URL_NAME, DatabaseConfig.USER, DatabaseConfig.PASS);
    }
}

