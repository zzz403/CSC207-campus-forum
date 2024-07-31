package com.imperial.academia.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigTest {

    @Test
    void getJdbcDriver_shouldReturnJdbcDriver() {
        String expected = "org.h2.Driver";
        String actual = DatabaseConfig.getJdbcDriver();
        assertEquals(expected, actual);
    }

    @Test
    void getDbUrlName_shouldReturnDbUrlName() {
        String expected = "jdbc:h2:./database/academia_imperial";
        String actual = DatabaseConfig.getDbUrlName();
        assertEquals(expected, actual);
    }

    @Test
    void getUser_shouldReturnUser() {
        String expected = "sa";
        String actual = DatabaseConfig.getUser();
        assertEquals(expected, actual);
    }

    @Test
    void getPass_shouldReturnPass() {
        String expected = "";
        String actual = DatabaseConfig.getPass();
        assertEquals(expected, actual);
    }
}
