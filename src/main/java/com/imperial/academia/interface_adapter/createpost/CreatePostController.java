package com.imperial.academia.interface_adapter.createpost;

import java.sql.SQLException;

import com.imperial.academia.use_case.createpost.CreatePostInputBoundary;

public class CreatePostController{
    
    private final CreatePostInputBoundary createPostInteractor;

    public CreatePostController(CreatePostInputBoundary createPostInteractor){
        this.createPostInteractor = createPostInteractor;
    }

    public void updateBoardName() throws SQLException{
        createPostInteractor.updateBoardsName();
    }
}
