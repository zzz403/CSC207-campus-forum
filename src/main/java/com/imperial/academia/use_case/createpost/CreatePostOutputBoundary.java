package com.imperial.academia.use_case.createpost;

import java.util.List;

public interface CreatePostOutputBoundary {
    
    /**
     * update all boards name to createPostViewModel
     * 
     * @param boardNames
     */
    void updateBoardsName(List<String> boardNames);
}
