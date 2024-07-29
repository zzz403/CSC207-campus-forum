package com.imperial.academia.use_case.edit;

import java.io.File;

public interface EditInputBoundry {

    void execute();
    void update(EditInputData editInputData);
    void changeAvatar(int userId, File selectedFile);
}
