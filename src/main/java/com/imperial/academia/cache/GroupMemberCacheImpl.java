package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.group_member.GroupMember;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroupMemberCacheImpl implements GroupMemberCache {
    private Cache<String, GroupMember> groupMemberCache;
    private Cache<String, List<GroupMember>> groupMembersCache;

    public GroupMemberCacheImpl() {
        groupMemberCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();

        groupMembersCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    @Override
    public void setGroupMember(String key, GroupMember groupMember) {
        groupMemberCache.put(key, groupMember);
    }

    @Override
    public GroupMember getGroupMember(String key) {
        return groupMemberCache.getIfPresent(key);
    }

    @Override
    public void deleteGroupMember(String key) {
        groupMemberCache.invalidate(key);
    }

    @Override
    public boolean existsGroupMember(String key) {
        return groupMemberCache.getIfPresent(key) != null;
    }

    @Override
    public void setGroupMembers(String key, List<GroupMember> groupMembers) {
        groupMembersCache.put(key, groupMembers);
    }

    @Override
    public List<GroupMember> getGroupMembers(String key) {
        return groupMembersCache.getIfPresent(key);
    }

    @Override
    public void deleteGroupMembers(String key) {
        groupMembersCache.invalidate(key);
    }

    @Override
    public boolean existsGroupMembers(String key) {
        return groupMembersCache.getIfPresent(key) != null;
    }
}
