package com.imperial.academia.data_access;

import com.imperial.academia.entity.user.User;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private static Connection connection;
    private static UserDAO userDAO;

    @BeforeAll
    static void setUpClass() throws SQLException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        ds.setUser("sa");
        ds.setPassword("sa");
        connection = ds.getConnection();
        userDAO = new UserDAO(connection);

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE users (" +
                    "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) NOT NULL, " +
                    "password VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL, " +
                    "role VARCHAR(50) NOT NULL, " +
                    "avatar_url VARCHAR(255), " +
                    "registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")");
        }
    }

    @AfterAll
    static void tearDownClass() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE users");
        }
        connection.close();
    }

    @BeforeEach
    void setUp() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users");
        }
    }

    @Test
    void testInsertAndGetUser() throws SQLException {
        User user = new User(0, "testuser", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user);

        User retrievedUser = userDAO.get(user.getId());
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getUsername());
        assertEquals("test@example.com", retrievedUser.getEmail());
    }

    @Test
    void testInsertDuplicateUsername() {
        User user1 = new User(0, "testuser", "password", "test1@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        User user2 = new User(0, "testuser", "password", "test2@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        assertDoesNotThrow(() -> userDAO.insert(user1));
        assertThrows(SQLException.class, () -> userDAO.insert(user2));
    }

    @Test
    void testInsertDuplicateEmail() {
        User user1 = new User(0, "testuser1", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        User user2 = new User(0, "testuser2", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        assertDoesNotThrow(() -> userDAO.insert(user1));
        assertDoesNotThrow(() -> userDAO.insert(user2));
    }

    @Test
    void testUpdateUser() throws SQLException {
        User user = new User(0, "testuser", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user);

        user.setUsername("updateduser");
        user.setEmail("updated@example.com");
        userDAO.update(user);

        User updatedUser = userDAO.get(user.getId());
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    void testDeleteUser() throws SQLException {
        User user = new User(0, "testuser", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user);
        userDAO.delete(user.getId());

        User deletedUser = userDAO.get(user.getId());
        assertNull(deletedUser);
    }

    @Test
    void testExistsByUsername() throws SQLException {
        User user = new User(0, "testuser", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user);

        assertTrue(userDAO.existsByUsername("testuser"));
        assertFalse(userDAO.existsByUsername("nonexistentuser"));
    }

    @Test
    void testExistsByEmail() throws SQLException {
        User user = new User(0, "testuser", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user);

        assertTrue(userDAO.existsByEmail("test@example.com"));
        assertFalse(userDAO.existsByEmail("nonexistent@example.com"));
    }

    @Test
    void testGetAllUsers() throws SQLException {
        User user1 = new User(0, "user1", "password", "user1@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        User user2 = new User(0, "user2", "password", "user2@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user1);
        userDAO.insert(user2);

        List<User> users = userDAO.getAll();
        assertEquals(2, users.size());
    }

    @Test
    void testGetAllSince() throws SQLException, InterruptedException {
        User user1 = new User(0, "user1", "password", "user1@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user1);

        Thread.sleep(1000); // Ensure timestamp difference

        Timestamp timestamp = Timestamp.from(Instant.now());

        User user2 = new User(0, "user2", "password", "user2@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user2);

        List<User> users = userDAO.getAllSince(timestamp);
        assertEquals(1, users.size());
        assertEquals("user2", users.get(0).getUsername());
    }

    @Test
    void testInsertUserWithEmptyFields() {
        User user = new User(0, "", "", "", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        assertThrows(SQLException.class, () -> userDAO.insert(user));
    }

    @Test
    void testInsertUserWithLongFields() {
        String longString = "a".repeat(256);
        User user = new User(0, longString, "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        assertThrows(SQLException.class, () -> userDAO.insert(user));
    }

    @Test
    void testDeleteNonExistentUser() {
        assertDoesNotThrow(() -> userDAO.delete(999));
    }

    @Test
    void testGetByEmail() throws SQLException {
        // 创建一个用户并插入数据库
        User user = new User(0, "testuser", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user);

        // 根据email从数据库获取用户
        User retrievedUser = userDAO.getByEmail("test@example.com");

        // 验证获取的用户是否正确
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getUsername());
        assertEquals("test@example.com", retrievedUser.getEmail());

        // 测试获取一个不存在的email
        User nonExistentUser = userDAO.getByEmail("nonexistent@example.com");
        assertNull(nonExistentUser);
    }

    @Test
    void testGetByUsername() throws SQLException {
        // 创建一个用户并插入数据库
        User user = new User(0, "testuser", "password", "test@example.com", "user", null, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        userDAO.insert(user);

        // 根据用户名从数据库获取用户
        User retrievedUser = userDAO.getByUsername("testuser");

        // 验证获取的用户是否正确
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getUsername());
        assertEquals("test@example.com", retrievedUser.getEmail());

        // 测试获取一个不存在的用户名
        User nonExistentUser = userDAO.getByUsername("nonexistentuser");
        assertNull(nonExistentUser);
    }


}
