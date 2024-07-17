package com.imperial.academia.use_case.createpost;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.BoardService;
import com.imperial.academia.service.PostService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.LLM.ChatGPTInteractor;
import com.imperial.academia.use_case.LLM.LLMInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;

/**
 * The interactor that handles the logic for creating a post.
 * It communicates with the board service and the output boundary (presenter).
 */
public class CreatePostInteractor implements CreatePostInputBoundary {
    
    /** The interactor that handles the logic for changing the view */
    private final ChangeViewInputBoundary changeViewInteractor = UsecaseFactory.getChangeViewInteractor();

    /** The presenter */
    private final CreatePostOutputBoundary createPostPresenter;

    /** The database service */
    private final BoardService boardService = ServiceFactory.getBoardService();
    private final PostService postService = ServiceFactory.getPostService();

    /**
     * Constructs a new CreatePostInteractor with the specified presenter and board service.
     * 
     * @param createPostPresenter the presenter that updates the view model with the board names
     * @param boardService the service that provides access to board data
     */
    public CreatePostInteractor(CreatePostOutputBoundary createPostPresenter) {
        this.createPostPresenter = createPostPresenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBoardsName() throws SQLException {
        BoardService boardService = ServiceFactory.getBoardService();
        List<String> boardsName = boardService.getAllBoardsName();
        createPostPresenter.updateBoardsName(boardsName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean submitPost(String title, String content, String boardName) {
        User user = SessionManager.getCurrentUser();
        int authorId = user.getId();
        int boardId;
        try {
            boardId = boardService.getBoardIdByName(boardName);
        } catch (SQLException e) {
            System.out.println("board id not found when save post");
            return false;
        }

        Post post = Post.builder()
                        .setTitle(title)
                        .setContent(content)
                        .setAuthorId(authorId)
                        .setBoardId(boardId)
                        .setCreationDate(Timestamp.from(Instant.now()))
                        .setLastModifiedDate(Timestamp.from(Instant.now()))
                        .build();

        try {
            postService.insert(post);
        } catch (SQLException e) {
            System.out.println("submitPost unseccuss");
            return false;
        }

        createPostPresenter.submitSeccuss();
        changeViewInteractor.changeView("post board");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enhanceContent(String content) {
        LLMInputBoundary llmApi = new ChatGPTInteractor();
        String enhancedContent = llmApi.enhanceContent(content);
        createPostPresenter.updateContent(enhancedContent);
    }    
}
