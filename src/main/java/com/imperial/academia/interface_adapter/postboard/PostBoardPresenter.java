package com.imperial.academia.interface_adapter.postboard;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;

public class PostBoardPresenter {
    private final ViewManagerModel viewManagerModel;
    private final PostBoardViewModel posterViewModel;

    public PostBoardPresenter(ViewManagerModel viewManagerModel, PostBoardViewModel posterViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.posterViewModel = posterViewModel;
    }


}
