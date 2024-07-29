package com.imperial.academia.use_case.profile;

/**
 * Encapsulates the data required for executing operations related to user profiles.
 * This class is designed to be immutable, ensuring that once an instance is created, the user ID it contains cannot be changed.
 * This immutability helps in maintaining the consistency and integrity of the data throughout the operation lifecycle.
 */
public class ProfileInputData {

    /**
     * The unique identifier of the user for whom the profile operation is intended.
     * This ID is used to fetch, update, or manage user profile information in the application.
     */
    private final int userId;

    /**
     * Constructs a new ProfileInputData instance using the provided user ID.
     * This constructor allows the setting of the user ID at the time of object creation, after which the ID cannot be changed.
     *
     * @param userId the unique identifier of the user associated with the profile operation.
     */
    public ProfileInputData(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the user ID associated with the profile operation.
     * This ID is critical for identifying which user profile to access or modify in the system.
     *
     * @return the unique identifier of the user.
     */
    public int getUserId() {
        return userId;
    }
}
