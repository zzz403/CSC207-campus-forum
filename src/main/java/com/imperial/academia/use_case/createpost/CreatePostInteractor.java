package com.imperial.academia.use_case.createpost;

import java.sql.SQLException;
import java.util.List;

import com.imperial.academia.service.BoardService;

public class CreatePostInteractor implements CreatePostInputBoundary {
    private final CreatePostOutputBoundary createPostPresenter;
    private final BoardService boardService;

    public CreatePostInteractor(CreatePostOutputBoundary createPostPresenter, BoardService boardService) {
        this.createPostPresenter = createPostPresenter;
        this.boardService = boardService;
    }

    @Override
    public void updateBoardsName() throws SQLException {
        List<String> boardsName = boardService.getAllBoardsName();
        createPostPresenter.updateBoardsName(boardsName);
    }
}
