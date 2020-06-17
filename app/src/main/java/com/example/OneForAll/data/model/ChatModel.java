package com.example.OneForAll.data.model;

public class ChatModel {

    public static final int TEXT_TYPE=0;
    public static final int MAP_TYPE=1;

    public int typeFor;

    String message;
    String userId;
    String time;
    String type;

    public ChatModel() {
    }

    public int getTypeFor() {
        return typeFor;
    }

    public void setTypeFor(int typeFor) {
        this.typeFor = typeFor;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
