package com.imperial.academia.use_case.edit;

public interface EditOutputBoundary {
    void prepareFailView(String error, EditOutputData editOutputData);
    void present(EditOutputData editOutputData);
}
