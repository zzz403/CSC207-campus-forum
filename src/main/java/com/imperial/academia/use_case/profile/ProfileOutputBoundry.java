package com.imperial.academia.use_case.profile;

public interface ProfileOutputBoundry {
    void present(ProfileOutputData profileOutputData);
    void presentError(String error);
}
