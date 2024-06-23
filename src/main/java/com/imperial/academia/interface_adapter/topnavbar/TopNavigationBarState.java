package com.imperial.academia.interface_adapter.topnavbar;

public class TopNavigationBarState {
    private String avatarUrl = "";
    private int userId = 0;
    private String currentViewName = "";

    public TopNavigationBarState(){}

    public TopNavigationBarState(TopNavigationBarState copy){
        this.avatarUrl = copy.avatarUrl;
        this.userId = copy.userId;
        this.currentViewName = copy.currentViewName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarURL) {
        this.avatarUrl = avatarURL;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCurrentViewName() {
        return currentViewName;
    }

    public void setCurrentViewName(String currentViewName) {
        this.currentViewName = currentViewName;
    }
}
