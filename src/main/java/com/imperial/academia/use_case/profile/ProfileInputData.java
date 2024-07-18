package com.imperial.academia.use_case.profile;

/**
 * The ProfileInputData class encapsulates the data required for profile operations.
 * This class is immutable and provides a way to retrieve the user ID associated with a profile operation.
 */
public class ProfileInputData {

    /**
     * The ID of the user associated with the profile operation.
     */
    private final int userId;

    /**
     * Constructs a new ProfileInputData instance with the specified user ID.
     *
     * @param userId the ID of the user associated with the profile operation
     */
    public ProfileInputData(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the ID of the user associated with the profile operation.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }
}
