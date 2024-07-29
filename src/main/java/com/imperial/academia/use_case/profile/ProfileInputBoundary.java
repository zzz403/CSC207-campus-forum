package com.imperial.academia.use_case.profile;

/**
 * Defines the input boundary interface for executing profile-related operations.
 * This interface is part of the clean architecture boundary that separates the use case interactor
 * implementation from its inputs, ensuring that use case interactors can accept profile data
 * from multiple sources (e.g., web, mobile, APIs) without being tightly coupled to them.
 */
public interface ProfileInputBoundary {

    /**
     * Executes a profile-related operation based on the provided profile input data.
     * This method is typically used to initiate actions like fetching, updating, or displaying
     * user profile data depending on the specific implementation of the use case.
     *
     * @param profileInputData The data required to perform the profile operation, encapsulating
     *                         all necessary user or profile identifiers and attributes.
     */
    void execute(ProfileInputData profileInputData);
}
