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
import com.imperial.academia.use_case.LLM.LLMInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.post.PostInfoData;
import com.imperial.academia.use_case.post.PostInputBoundary;
import com.imperial.academia.use_case.post.PostOverviewInfo;
import com.imperial.academia.use_case.postBoard.PostBoardOutputBoundary;

/**
 * The interactor that handles the logic for creating a post.
 * It communicates with the board service and the output boundary (presenter).
 */
public class CreatePostInteractor implements CreatePostInputBoundary {

    /** The interactor that handles the logic for changing the view */
    private final ChangeViewInputBoundary changeViewInteractor;

    /** The interactor that handles the logic for update post view */
    private final PostInputBoundary postInteractor;

    /** The interactor that handles the logic for useing LLM */
    private final LLMInputBoundary llmInteractor;

    /** The presenter that updates the view model with the board names */
    private final CreatePostOutputBoundary createPostPresenter;

    /** The presenter that updates the view model with the board names */
    private final PostBoardOutputBoundary postBoardPresenter;

    /** The database service */
    private final BoardService boardService;
    private final PostService postService;

    /**
     * Constructs a new CreatePostInteractor with the specified presenter.
     * 
     * @param createPostPresenter the presenter that updates the view model with the
     *                            board names
     * @param postBoardPresenter  the presenter that updates the view model with the
     *                            board names
     */
    public CreatePostInteractor(CreatePostOutputBoundary createPostPresenter,
            PostBoardOutputBoundary postBoardPresenter) {
        this.createPostPresenter = createPostPresenter;
        this.postBoardPresenter = postBoardPresenter;
        changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
        postInteractor = UsecaseFactory.getPostInteractor();
        llmInteractor = UsecaseFactory.getLLMInteractor();
        boardService = ServiceFactory.getBoardService();
        postService = ServiceFactory.getPostService();
    }

    /**
     * For unit testing
     * 
     * @param changeViewInteractor
     * @param postInteractor
     * @param llmInteractor
     * @param createPostPresenter
     * @param boardService
     * @param postService
     */
    public CreatePostInteractor(ChangeViewInputBoundary changeViewInteractor, PostInputBoundary postInteractor,
            LLMInputBoundary llmInteractor, CreatePostOutputBoundary createPostPresenter, BoardService boardService,
            PostService postService, PostBoardOutputBoundary postBoardPresenter) {
        this.changeViewInteractor = changeViewInteractor;
        this.postInteractor = postInteractor;
        this.llmInteractor = llmInteractor;
        this.createPostPresenter = createPostPresenter;
        this.postBoardPresenter = postBoardPresenter;

        this.boardService = boardService;
        this.postService = postService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBoardsName() throws SQLException {
        List<String> boardsName = boardService.getAllBoardsName();
        createPostPresenter.updateBoardsName(boardsName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean submitPost(String title, String content, String boardName) {
        User user = SessionManager.getCurrentUser();
        if (user == null) {
            System.out.println("No user login");
            return false;
        } else if (title == null || content == null || boardName == null) {
            System.out.println("invalid input! Recheck!!");
            return false;
        } else if (title.equals("") || content.equals("") || boardName.equals("")) {
            System.out.println("Input is empty! recheck!!");
            return false;
        }
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

        System.out.println("submitPost seccuss: " + post.getId());
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(title)
                .setContent(content)
                .setUsername(user.getUsername())
                .setAvatarUrl(user.getAvatarUrl())
                .setDate(post.getLastModifiedDate())
                .setLikes(0)
                .setPostId(post.getId())
                .build();

        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder()
                .setPostID(post.getId())
                .setPostTitle(title)
                .setSummary(content)
                .setUserName(user.getUsername())
                .setAvatarURL(user.getAvatarUrl())
                .setLikes(0)
                .setPostID(post.getId())
                .build();
        postInteractor.initPostPage(postInfoData);
        postBoardPresenter.addPost(postOverviewInfo);
        changeViewInteractor.changeView("post");

        createPostPresenter.submitSeccuss();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enhanceContent(String content) {
        String enhancedContent = llmInteractor.enhanceContent(content);
        createPostPresenter.updateContent(enhancedContent);
    }
}
