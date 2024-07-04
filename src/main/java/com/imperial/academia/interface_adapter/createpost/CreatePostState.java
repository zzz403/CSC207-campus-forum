package com.imperial.academia.interface_adapter.createpost;

import java.util.ArrayList;
import java.util.List;

public class CreatePostState {
    private String title;
    private String body;
    private List<String> boardName;

    public CreatePostState(CreatePostState other) {
        this.title = other.getTitle();
        this.body = other.getBody();
        this.boardName = other.getBoardName();
    }

    public CreatePostState() {
        this.title = "";
        this.body = "";
        this.boardName = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getBoardName() {
        return boardName;
    }

    public void setBoardName(List<String> boardName) {
        this.boardName = new ArrayList<>(boardName);
    }
}
