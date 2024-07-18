package com.imperial.academia.use_case.profile;

/**
 * The ProfileInputBoundry interface provides a contract for executing profile-related operations.
 * Implementations of this interface should define the specific logic for handling profile input data.
 */
public interface ProfileInputBoundry {

    /**
     * Executes an operation with the given profile input data.
     *
     * @param profileInputData the data required to perform the profile operation
     */
    void excute(ProfileInputData profileInputData);
}
