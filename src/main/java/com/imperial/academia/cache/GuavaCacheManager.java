package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GuavaCacheManager {
    private Cache<String, List<String>> cache;

    public GuavaCacheManager() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
    }

    public void set(String key, List<String> value) {
        cache.put(key, value);
    }

    public List<String> get(String key) {
        return cache.getIfPresent(key);
    }

    public void delete(String key) {
        cache.invalidate(key);
    }

    public boolean exists(String key) {
        return cache.getIfPresent(key) != null;
    }
}
