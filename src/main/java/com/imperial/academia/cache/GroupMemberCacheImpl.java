package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.group_member.GroupMember;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the GroupMemberCache interface using Guava Cache.
 */
public class GroupMemberCacheImpl implements GroupMemberCache {
    private final Cache<String, GroupMember> groupMemberCache;
    private final Cache<String, List<GroupMember>> groupMembersCache;

    /**
     * Constructs a new GroupMemberCacheImpl with specific cache configurations.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupMember(String key, GroupMember groupMember) {
        groupMemberCache.put(key, groupMember);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupMember getGroupMember(String key) {
        return groupMemberCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteGroupMember(String key) {
        groupMemberCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsGroupMember(String key) {
        return groupMemberCache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupMembers(String key, List<GroupMember> groupMembers) {
        groupMembersCache.put(key, groupMembers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupMember> getGroupMembers(String key) {
        return groupMembersCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteGroupMembers(String key) {
        groupMembersCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsGroupMembers(String key) {
        return groupMembersCache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getChatGroupId(int userId1, int userId2) {
        for (List<GroupMember> groupMembers : groupMembersCache.asMap().values()) {
            boolean foundPerson1 = false;
            boolean foundPerson2 = false;
            for (GroupMember member : groupMembers) {
                if (member.getUserId() == userId1) {
                    foundPerson1 = true;
                }
                if (member.getUserId() == userId2){
                    foundPerson2 = true;
                }
                if (foundPerson1 && foundPerson2) {
                    return member.getGroupId();
                }
            }
        }
        return -1;
    }
}
