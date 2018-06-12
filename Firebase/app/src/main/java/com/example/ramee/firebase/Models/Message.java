package com.example.ramee.firebase.Models;

/**
 * Created by ramee on 09/04/2018.
 */

public class Message {

    private String content, name;

    public Message() {
    }

    public Message(String content, String name, String username) {
        this.content = content;
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
