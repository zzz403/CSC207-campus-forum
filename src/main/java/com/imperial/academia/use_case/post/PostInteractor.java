package com.imperial.academia.use_case.post;

import java.sql.SQLException;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.CommentService;
import com.imperial.academia.service.PostService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The PostInteractor class handles the interaction logic for posts.
 * It acts as a mediator between the input boundary and the output boundary.
 */
public class PostInteractor implements PostInputBoundary {
    private final PostOutputBoundary postPresenter;

    private final PostService postService;

    private final UserService userService;

    private final CommentService commentService;

    /**
     * Constructs a new PostInteractor with the specified PostOutputBoundary.
     * 
     * @param postPresenter the output boundary that will handle the presentation
     *                      logic.
     */
    public PostInteractor(PostOutputBoundary postPresenter) {
        this.postPresenter = postPresenter;
        this.postService = ServiceFactory.getPostService();
        this.userService = ServiceFactory.getUserService();
        this.commentService = ServiceFactory.getCommentService();
    }

    
    /**
     * Constructs a new PostInteractor with the specified PostOutputBoundary, PostService, UserService, and CommentService.
     * 
     * @param postPresenter the output boundary that will handle the presentation logic.
     * @param postService the service that will handle the post logic.
     * @param userService the service that will handle the user logic.
     * @param commentService the service that will handle the comment logic.
     */
    public PostInteractor(PostOutputBoundary postPresenter, PostService postService, UserService userService,
            CommentService commentService) {
        this.postPresenter = postPresenter;
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }



    /**
     * Initializes the post page desplay with the given post information data.
     * 
     * @param postInfoData the data required to initialize the post page.
     */
    @Override
    public void initPostPage(PostInfoData postInfoData) {
        postPresenter.initPostPage(postInfoData);
        initComments(postInfoData.getPostID(), SessionManager.getCurrentUser());
    }

    /**
     * Initializes the post page with the post information for the post with the
     * given post ID.
     * 
     * @param postID the ID of the post to initialize the post page with.
     * @return true if the post was fetched successfully, false otherwise.
     */
    @Override
    public Boolean initPostById(int postID) {
        try {
            Post post = postService.get(postID);
            int userID = post.getAuthorId();
            User user = userService.get(userID);

            String title = post.getTitle();
            String content = post.getContent();
            String username = user.getUsername();
            String avatarUrl = user.getAvatarUrl();
            Timestamp date = post.getLastModifiedDate();
            int postLikes = postService.getTotalLikesNumberByPostId(postID);

            User currentUser = SessionManager.getCurrentUser();
            boolean isLiked = postService.checkLiked(postID, currentUser.getId());

            PostInfoData postInfoData = PostInfoData.builder()
                    .setTitle(title)
                    .setContent(content)
                    .setUsername(username)
                    .setAvatarUrl(avatarUrl)
                    .setDate(date)
                    .setLikes(postLikes)
                    .setPostId(postID)
                    .setIsLiked(isLiked)
                    .build();

            postPresenter.initPostPage(postInfoData);

        } catch (SQLException e) {
            System.out.println("Can't find the post or user DNE when init post page");
            return false;
        }
        return true;
    }

    /**
     * Gets the comments for the post with the given post ID.
     * 
     * @param postID      the ID of the post to get the comments for.
     * @param currentUser the current user.
     * @throws SQLException if there is an error getting the comments.
     */
    public void initComments(int postID, User currentUser) {
        List<CommentData> commentDataList = new ArrayList<>();
        try {
            List<Comment> comments = commentService.getAllByPostId(postID);
            for (Comment comment : comments) {
                User tempUser = userService.get(comment.getAuthorId());
                String tempUsername = tempUser.getUsername();
                if (tempUser.getId() == currentUser.getId()) {
                    tempUsername = "Me";
                }
                CommentData commentData = CommentData.builder()
                        .setId(comment.getId())
                        .setContent(comment.getContent())
                        .setAuthorId(comment.getAuthorId())
                        .setUsername(tempUsername)
                        .setLastModified(comment.getLastModified())
                        .build();
                commentDataList.add(commentData);
            }
            postPresenter.initComments(commentDataList);
        } catch (SQLException e) {
            return;
        }
    }

    /**
     * Adds a like to the post with the given post ID.
     * 
     * @param postId the ID of the post to add a like to.
     * @return true if the like was added successfully, false otherwise.
     */
    @Override
    public boolean addLike(int postId, int userId) {
        try {
            postService.likePost(postId, userId);
        } catch (SQLException e) {
            System.out.println("Can't like the post");
            return false;
        }
        postPresenter.addLike(postId);
        return true;
    }

    /**
     * Removes a like from the post with the given post ID.
     * 
     * @param postId the ID of the post to remove a like from.
     * @return true if the like was removed successfully, false otherwise.
     */
    @Override
    public boolean removeLike(int postId, int userId) {
        try {
            postService.unlikePost(postId, userId);
        } catch (SQLException e) {
            System.out.println("Can't unlike the post");
            return false;
        }
        postPresenter.removeLike(postId);
        return true;
    }

    /**
     * Checks if the post with the given post ID is liked by the user with the given
     * user ID.
     * 
     * @param postId the ID of the post to check if liked.
     * @param userId the ID of the user to check if liked.
     * @return true if the post is liked by the user, false otherwise.
     */
    @Override
    public boolean checkLiked(int postId, int userId) {
        try {
            return postService.checkLiked(postId, userId);
        } catch (SQLException e) {
            System.out.println("Can't check if liked");
            return false;
        }

    }

    /**
     * Posts a comment to the post with the given post ID.
     * 
     * @param postId  the ID of the post to post a comment to.
     * @param userId  the ID of the user posting the comment.
     * @param content the content of the comment.
     */
    @Override
    public void postComment(int postId, int userId, String content) {
        try {
            Comment comment = Comment.builder()
                    .setContent(content)
                    .setPostId(postId)
                    .setAuthorId(userId)
                    .setCreationDate(Timestamp.from(Instant.now()))
                    .setLastModified(Timestamp.from(Instant.now()))
                    .build();
            commentService.insert(comment);

            CommentData commentData = CommentData.builder()
                    .setId(comment.getId())
                    .setContent(comment.getContent())
                    .setAuthorId(comment.getAuthorId())
                    .setUsername("Me")
                    .setLastModified(comment.getLastModified())
                    .build();

            postPresenter.addPost(commentData);

        } catch (SQLException e) {
            System.out.println("Can't summbit the comment");
        }
    }
}
