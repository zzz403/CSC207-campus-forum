package com.imperial.academia.entity.chat_group;

import java.util.Date;
import java.sql.Timestamp;

public class ChatGroupFactory {
    public ChatGroup createPrivateChatGroup() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return new ChatGroup(0, "Private Chat", false, timestamp, timestamp);
    }
}
