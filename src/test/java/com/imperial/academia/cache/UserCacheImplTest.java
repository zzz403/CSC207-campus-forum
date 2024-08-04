package com.imperial.academia.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.cache.Cache;
import com.imperial.academia.entity.user.User;

class UserCacheImplTest {

    private UserCacheImpl userCacheImpl;
    private Cache<String, User> mockUserCache;
    private Cache<String, List<User>> mockUsersCache;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        mockUserCache = Mockito.mock(Cache.class);
        mockUsersCache = Mockito.mock(Cache.class);

        userCacheImpl = new UserCacheImpl(mockUserCache, mockUsersCache);
    }

    @Test
    void testDefaultConstructor() {
        userCacheImpl = new UserCacheImpl();
        assertNotNull(userCacheImpl);
    }

    @Test
    void setUser_shouldPutUserInCache() {
        User user = new User(1, "JohnDoe", "password", "john.doe@example.com", "role", "avatarUrl", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        userCacheImpl.setUser("testKey", user);

        verify(mockUserCache, times(1)).put("testKey", user);
    }

    @Test
    void getUser_shouldReturnUserFromCache() {
        User user = new User(1, "JohnDoe", "password", "john.doe@example.com", "role", "avatarUrl", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockUserCache.getIfPresent("testKey")).thenReturn(user);

        User result = userCacheImpl.getUser("testKey");

        assertEquals(user, result);
        verify(mockUserCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteUser_shouldInvalidateUserInCache() {
        userCacheImpl.deleteUser("testKey");

        verify(mockUserCache, times(1)).invalidate("testKey");
    }

    @Test
    void exists_shouldReturnTrueIfUserExistsInCache() {
        User user = new User(1, "JohnDoe", "password", "john.doe@example.com", "role", "avatarUrl", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockUserCache.getIfPresent("testKey")).thenReturn(user);

        boolean result = userCacheImpl.exists("testKey");

        assertTrue(result);
        verify(mockUserCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void exists_shouldReturnFalseIfUserDoesNotExistInCache() {
        when(mockUserCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = userCacheImpl.exists("testKey");

        assertFalse(result);
        verify(mockUserCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setUsers_shouldPutUsersInCache() {
        List<User> users = new ArrayList<>();
        userCacheImpl.setUsers("testKey", users);

        verify(mockUsersCache, times(1)).put("testKey", users);
    }

    @Test
    void getUsers_shouldReturnUsersFromCache() {
        List<User> users = new ArrayList<>();
        when(mockUsersCache.getIfPresent("testKey")).thenReturn(users);

        List<User> result = userCacheImpl.getUsers("testKey");

        assertEquals(users, result);
        verify(mockUsersCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteUsers_shouldInvalidateUsersInCache() {
        userCacheImpl.deleteUsers("testKey");

        verify(mockUsersCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsUsers_shouldReturnTrueIfUsersExistInCache() {
        List<User> users = new ArrayList<>();
        when(mockUsersCache.getIfPresent("testKey")).thenReturn(users);

        boolean result = userCacheImpl.existsUsers("testKey");

        assertTrue(result);
        verify(mockUsersCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsUsers_shouldReturnFalseIfUsersDoNotExistInCache() {
        when(mockUsersCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = userCacheImpl.existsUsers("testKey");

        assertFalse(result);
        verify(mockUsersCache, times(1)).getIfPresent("testKey");
    }
}
