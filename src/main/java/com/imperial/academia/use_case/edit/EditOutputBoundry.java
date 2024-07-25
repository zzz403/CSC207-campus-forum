package com.imperial.academia.use_case.edit;

public interface EditOutputBoundry {
    void prepareFailView(String error);
    void present(EditOutputData editOutputData);
}
