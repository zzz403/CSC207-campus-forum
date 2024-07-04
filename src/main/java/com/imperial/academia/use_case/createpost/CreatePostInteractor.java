package com.imperial.academia.use_case.createpost;

import java.sql.SQLException;
import java.util.List;

import com.imperial.academia.service.BoardService;

/**
 * The interactor that handles the logic for creating a post.
 * It communicates with the board service and the output boundary (presenter).
 */
public class CreatePostInteractor implements CreatePostInputBoundary {
    
    /** The presenter */
    private final CreatePostOutputBoundary createPostPresenter;
    
    /** The service that provides access to board data. */
    private final BoardService boardService;

    /**
     * Constructs a new CreatePostInteractor with the specified presenter and board service.
     * 
     * @param createPostPresenter the presenter that updates the view model with the board names
     * @param boardService the service that provides access to board data
     */
    public CreatePostInteractor(CreatePostOutputBoundary createPostPresenter, BoardService boardService) {
        this.createPostPresenter = createPostPresenter;
        this.boardService = boardService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBoardsName() throws SQLException {
        List<String> boardsName = boardService.getAllBoardsName();
        createPostPresenter.updateBoardsName(boardsName);
    }
}
