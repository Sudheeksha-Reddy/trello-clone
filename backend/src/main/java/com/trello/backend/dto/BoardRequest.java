package com.trello.backend.dto;

public class BoardRequest {
    private String name;

    public BoardRequest() {}

    public BoardRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
