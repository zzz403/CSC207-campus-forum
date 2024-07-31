package com.imperial.academia.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.imperial.academia.cache.UserCache;
import com.imperial.academia.data_access.UserDAI;
import com.imperial.academia.entity.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.mockito.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserServiceImplTest {
    @Mock
    private UserCache userCache;
    @Mock
    private UserDAI userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    private AutoCloseable closeable;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testInsert() throws SQLException {
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        userService.insert(user);
        verify(userDAO, times(1)).insert(user);
        verify(userCache, times(1)).setUser("user:1", user);
    }

    @Test
    public void testExistsByUsername_Cached() throws SQLException {
        when(userCache.exists("username:user1")).thenReturn(true);
        assertTrue(userService.existsByUsername("user1"));
        verify(userCache, times(1)).exists("username:user1");
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testExistsByUsername_NotCached() throws SQLException {
        when(userCache.exists("username:user1")).thenReturn(false);
        when(userDAO.existsByUsername("user1")).thenReturn(true);
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        when(userDAO.getByUsername("user1")).thenReturn(user);

        assertTrue(userService.existsByUsername("user1"));
        verify(userCache, times(1)).exists("username:user1");
        verify(userDAO, times(1)).existsByUsername("user1");
        verify(userDAO, times(1)).getByUsername("user1");
        verify(userCache, times(1)).setUser("username:user1", user);
    }

    @Test
    public void testExistsByEmail_Cached() throws SQLException {
        when(userCache.exists("email:email@example.com")).thenReturn(true);
        assertTrue(userService.existsByEmail("email@example.com"));
        verify(userCache, times(1)).exists("email:email@example.com");
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testExistsByEmail_NotCached() throws SQLException {
        when(userCache.exists("email:email@example.com")).thenReturn(false);
        when(userDAO.existsByEmail("email@example.com")).thenReturn(true);
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        when(userDAO.getByEmail("email@example.com")).thenReturn(user);

        assertTrue(userService.existsByEmail("email@example.com"));
        verify(userCache, times(1)).exists("email:email@example.com");
        verify(userDAO, times(1)).existsByEmail("email@example.com");
        verify(userDAO, times(1)).getByEmail("email@example.com");
        verify(userCache, times(1)).setUser("email:email@example.com", user);
    }

    @Test
    public void testGetByUsername_Cached() throws SQLException {
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        when(userCache.getUser("username:user1")).thenReturn(user);

        User result = userService.getByUsername("user1");
        assertNotNull(result);
        assertEquals("user1", result.getUsername());
        verify(userCache, times(1)).getUser("username:user1");
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testGetByUsername_NotCached() throws SQLException {
        when(userCache.getUser("username:user1")).thenReturn(null);
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        when(userDAO.getByUsername("user1")).thenReturn(user);

        User result = userService.getByUsername("user1");
        assertNotNull(result);
        assertEquals("user1", result.getUsername());
        verify(userCache, times(1)).getUser("username:user1");
        verify(userDAO, times(1)).getByUsername("user1");
        verify(userCache, times(1)).setUser("username:user1", user);
    }

    @Test
    public void testGet_Cached() throws SQLException {
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        when(userCache.getUser("user:1")).thenReturn(user);

        User result = userService.get(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(userCache, times(1)).getUser("user:1");
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testGet_NotCached() throws SQLException {
        when(userCache.getUser("user:1")).thenReturn(null);
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        when(userDAO.get(1)).thenReturn(user);

        User result = userService.get(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(userCache, times(1)).getUser("user:1");
        verify(userDAO, times(1)).get(1);
        verify(userCache, times(1)).setUser("user:1", user);
    }

    @Test
    public void testGetAll_Cached() throws SQLException {
        User user1 = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        User user2 = new User(2, "user2", "password", "email2@example.com", "user", "avatarUrl2", null, null);
        List<User> users = Arrays.asList(user1, user2);
        when(userCache.getUsers("users:all")).thenReturn(users);

        List<User> result = userService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userCache, times(1)).getUsers("users:all");
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testGetAll_NotCached() throws SQLException {
        when(userCache.getUsers("users:all")).thenReturn(null);
        User user1 = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        User user2 = new User(2, "user2", "password", "email2@example.com", "user", "avatarUrl2", null, null);
        List<User> users = Arrays.asList(user1, user2);
        when(userDAO.getAll()).thenReturn(users);

        List<User> result = userService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userCache, times(1)).getUsers("users:all");
        verify(userDAO, times(1)).getAll();
        verify(userCache, times(1)).setUsers("users:all", users);
    }

    @Test
    public void testUpdate() throws SQLException {
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", null, null);
        userService.update(user);
        verify(userDAO, times(1)).update(user);
        verify(userCache, times(1)).setUser("user:1", user);
    }

    @Test
    public void testDelete() throws SQLException {
        userService.delete(1);
        verify(userDAO, times(1)).delete(1);
        verify(userCache, times(1)).deleteUser("user:1");
    }
}
