package com.imperial.academia.use_case.edit;

/**
 * Interface defining the output boundary for edit operations.
 * This interface facilitates the interaction between the use case layer and the presentation layer,
 * allowing for the presentation of results and error handling during user profile edits.
 */
public interface EditOutputBoundary {

    /**
     * Prepares the view to display an error message along with the current state of the edit data.
     * This method is called when an error occurs during the edit process.
     *
     * @param error A string containing the error message to be displayed.
     * @param editOutputData The current data of the edit operation, which may be partially completed
     *                       or unchanged depending on where the error occurred.
     */
    void prepareFailView(String error, EditOutputData editOutputData);

    /**
     * Presents the results of the edit operation to the user.
     * This method is called when the edit operation completes successfully,
     * allowing the presentation layer to update the UI with the new user profile data.
     *
     * @param editOutputData The data resulting from the successful edit operation, containing updated user details.
     */
    void present(EditOutputData editOutputData);
}
