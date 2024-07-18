package com.imperial.academia.use_case.profile;

/**
 * The ProfileOutputBoundry interface provides a contract for presenting the results of profile-related operations.
 * Implementations of this interface should define how to present successful profile data and handle errors.
 */
public interface ProfileOutputBoundry {

    /**
     * Presents the profile output data.
     *
     * @param profileOutputData the data to be presented
     */
    void present(ProfileOutputData profileOutputData);

    /**
     * Presents an error message.
     *
     * @param error the error message to be presented
     */
    void presentError(String error);
}
