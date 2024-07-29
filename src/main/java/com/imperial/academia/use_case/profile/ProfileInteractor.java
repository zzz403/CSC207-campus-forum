package com.imperial.academia.use_case.profile;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.PostService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.session.SessionManager;

/**
 * Implements profile-related operations defined by the ProfileInputBoundary.
 * This class is responsible for fetching user and post data from services,
 * preparing output data, and managing view transitions.
 */
public class ProfileInteractor implements ProfileInputBoundary {

    private final ChangeViewInputBoundary changeViewInteractor;
    private final ProfileOutputBoundary profilePresenter;
    private final UserService userService;
    private final PostService postService;

    /**
     * Constructor for dependency injection.
     * Utilizes default services to manage user and post data.
     *
     * @param profilePresenter the presenter for displaying profile data and errors.
     */
    public ProfileInteractor(ProfileOutputBoundary profilePresenter) {
        this(profilePresenter, ServiceFactory.getUserService(), UsecaseFactory.getChangeViewInteractor(),
                ServiceFactory.getPostService());
    }

    /**
     * Constructor for explicit dependency injection. Useful for testing.
     *
     * @param profilePresenter       the presenter for profile operations.
     * @param userService            the service handling user-related data operations.
     * @param changeViewInteractor   the interactor responsible for changing views.
     * @param postService            the service handling post-related data operations.
     */
    public ProfileInteractor(ProfileOutputBoundary profilePresenter, UserService userService,
                             ChangeViewInputBoundary changeViewInteractor, PostService postService) {
        this.profilePresenter = profilePresenter;
        this.userService = userService;
        this.changeViewInteractor = changeViewInteractor;
        this.postService = postService;
    }

    /**
     * Executes the operation to fetch and present profile data based on the provided input data.
     * Retrieves user and their posts, assembles profile output data, and directs the user interface to display it.
     *
     * @param profileInputData the data containing the user ID to fetch profile information.
     */
    @Override
    public void execute(ProfileInputData profileInputData) {
        try {
            User user = userService.get(profileInputData.getUserId());
            if (user == null) {
                profilePresenter.presentError("User not found");
                return;
            }
            List<Post> posts = postService.getAllByUserId(user.getId());
            ProfileOutputData profileOutputData = createProfileOutputData(user, posts);

            profilePresenter.present(profileOutputData);
            changeViewInteractor.changeView("profile");

        } catch (SQLException e) {
            profilePresenter.presentError("Database error: " + e.getMessage());
        }
    }

    /**
     * Creates ProfileOutputData from user and posts information.
     * Randomly assigns image URLs from a set resource path for demonstration purposes.
     *
     * @param user  the user for whom profile data is being created.
     * @param posts the posts associated with the user.
     * @return ProfileOutputData containing all necessary user and post details.
     */
    private ProfileOutputData createProfileOutputData(User user, List<Post> posts) {
        List<String> postTitles = new ArrayList<>();
        List<String> postContents = new ArrayList<>();
        List<Timestamp> postDates = new ArrayList<>();
        List<String> postImageUrls = new ArrayList<>();
        List<Integer> postIds = new ArrayList<>();

        Random rand = new Random();
        for (Post post : posts) {
            postTitles.add(post.getTitle());
            postContents.add(post.getContent());
            postDates.add(post.getCreationDate());
            int randomNum = rand.nextInt(9) + 1;  // Generates a number between 1 and 9
            postImageUrls.add("resources/test_image/test_image_" + randomNum + ".jpg");
            postIds.add(post.getId());
        }

        return new ProfileOutputData(
                user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getAvatarUrl(),
                user.getRegistrationDate(), SessionManager.getCurrentUser().getId() == user.getId(),
                postTitles, postContents, postDates, postImageUrls, postIds
        );
    }
}
