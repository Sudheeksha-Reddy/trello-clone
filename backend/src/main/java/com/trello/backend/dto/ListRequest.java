package com.trello.backend.dto;

public class ListRequest {
    private String title;

    public ListRequest() {}

    public ListRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
