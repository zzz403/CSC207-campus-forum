package com.imperial.academia.data_access.group_member;

import com.imperial.academia.entity.group_member.GroupMember;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupMemberDAO implements GroupMemberDAI {
    private Connection conn;

    public GroupMemberDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(GroupMember groupMember) throws SQLException {
        String sql = "INSERT INTO group_members (group_id, user_id, role, last_modified) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupMember.getGroupId());
            pstmt.setInt(2, groupMember.getUserId());
            pstmt.setString(3, groupMember.getRole());
            pstmt.executeUpdate();
        }
    }

    @Override
    public GroupMember get(int groupId, int userId) throws SQLException {
        String sql = "SELECT * FROM group_members WHERE group_id = ? AND user_id = ?";
        GroupMember groupMember = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupId);
            pstmt.setInt(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    groupMember = new GroupMember(
                        rs.getInt("group_id"),
                        rs.getInt("user_id"),
                        rs.getString("role"),
                        rs.getTimestamp("joined_date"),
                        rs.getTimestamp("last_modified")
                    );
                }
            }
        }
        return groupMember;
    }

    @Override
    public List<GroupMember> getAll() throws SQLException {
        String sql = "SELECT * FROM group_members";
        List<GroupMember> groupMembers = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                groupMembers.add(new GroupMember(
                    rs.getInt("group_id"),
                    rs.getInt("user_id"),
                    rs.getString("role"),
                    rs.getTimestamp("joined_date"),
                    rs.getTimestamp("last_modified")
                ));
            }
        }
        return groupMembers;
    }

    @Override
    public List<GroupMember> getAllSince(Timestamp timestamp) throws SQLException {
        String sql = "SELECT * FROM group_members WHERE last_modified > ?";
        List<GroupMember> groupMembers = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, timestamp);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    groupMembers.add(new GroupMember(
                        rs.getInt("group_id"),
                        rs.getInt("user_id"),
                        rs.getString("role"),
                        rs.getTimestamp("joined_date"),
                        rs.getTimestamp("last_modified")
                    ));
                }
            }
        }
        return groupMembers;
    }

    @Override
    public void update(GroupMember groupMember) throws SQLException {
        String sql = "UPDATE group_members SET role = ?, last_modified = CURRENT_TIMESTAMP WHERE group_id = ? AND user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, groupMember.getRole());
            pstmt.setInt(2, groupMember.getGroupId());
            pstmt.setInt(3, groupMember.getUserId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int groupId, int userId) throws SQLException {
        String sql = "DELETE FROM group_members WHERE group_id = ? AND user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupId);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        }
    }
}
