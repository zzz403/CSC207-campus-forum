package com.imperial.academia.use_case.profile;

/**
 * Defines the output boundary for presenting results of profile-related operations within the application.
 * This interface ensures that the presentation logic is decoupled from the business logic,
 * enabling different implementations to handle how profile data and errors are displayed to the user.
 */
public interface ProfileOutputBoundary {

    /**
     * Presents the profile output data to the user or another component of the application.
     * This method is called when profile data is successfully fetched and ready to be displayed,
     * typically updating the user interface with the latest user and related data.
     *
     * @param profileOutputData the profile data to be presented, encapsulating all relevant user details and associated information.
     */
    void present(ProfileOutputData profileOutputData);

    /**
     * Presents an error message when an error occurs during the profile-related operations.
     * This method is responsible for handling all forms of errors, from data retrieval failures to database access issues,
     * ensuring that appropriate feedback is given to the user.
     *
     * @param error the error message to be presented, providing details about the issue encountered.
     */
    void presentError(String error);
}
