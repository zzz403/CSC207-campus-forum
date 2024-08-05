package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.chat.ChatCoordinatorInputBoundary;
import com.imperial.academia.use_case.edit.EditInputBoundary;
import com.imperial.academia.use_case.post.PostInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputData;

/**
 * The ProfileController class handles user interactions related to profile operations.
 * It interacts with the use case layer to perform profile-related tasks such as showing a profile and initiating a chat.
 */
public class ProfileController {

    /**
     * The interactor for profile operations.
     */
    private final ProfileInputBoundary profileInteractor;

    /**
     * The interactor for chat coordination operations.
     */
    private final ChatCoordinatorInputBoundary chatCoordinatorInteractor;
    private final ChangeViewInputBoundary changeViewInteractor;
    private final EditInputBoundary editInteractor;
    private final PostInputBoundary postInteractor;

    /**
     * Constructs a new ProfileController and initializes the interactors for profile and chat operations.
     */
    public ProfileController() {
        this.profileInteractor = UsecaseFactory.getProfileInteractor();
        this.chatCoordinatorInteractor = UsecaseFactory.getChatCoordinatorInteractor();
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
        this.editInteractor = UsecaseFactory.getEditInteractor();
        this.postInteractor = UsecaseFactory.getPostInteractor();
    }

    /**
     * Shows the profile of the user with the specified user ID.
     *
     * @param userId the ID of the user whose profile is to be shown
     */
    public void showProfile(int userId) {
        ProfileInputData profileInputData = new ProfileInputData(userId);
        profileInteractor.execute(profileInputData);
    }

    /**
     * Initiates a chat with the user with the specified user ID.
     *
     * @param userId the ID of the user to chat with
     */
    public void chat(int userId) {
        chatCoordinatorInteractor.toPrivateChat(userId);
    }

    /**
     * Placeholder for the edit functionality.
     * This method is currently not implemented.
     */
    public void edit() {
        editInteractor.execute();
    }

    public boolean initPostById(int postID) {
        if (!postInteractor.initPostById(postID)) {
            return false;
        }
        changeViewInteractor.changeView("post");
        return true;
    }

    public void logout() {
        SessionManager.setCurrentUser(null);
        changeViewInteractor.changeView("log in");
    }
}
