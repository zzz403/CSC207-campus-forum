package com.imperial.academia.use_case.createpost;

import java.sql.SQLException;

/**
 * This interface represents the input boundary for the create post use case.
 * It defines the method required to update the list of board names.
 */
public interface CreatePostInputBoundary {

  /**
   * Updates the list of board names by fetching them from the board service
   * and passing them to the presenter.
   * 
   * @throws SQLException if a database access error occurs
   */
  void updateBoardsName() throws SQLException;

  /**
   * Save the post to the database
   * 
   * @param post
   * @return true if save success otherwise false
   * @throws SQLException
   */
  boolean submitPost(String titile, String content, String boardName);

  /**
   * Enhance the input content using LLM
   * 
   * @param content the content need to be enhance
   */
  void enhanceContent(String content);
}
