// package com.imperial.academia.app.usecase_factory;

// import com.imperial.academia.app.UsecaseFactory;
// import com.imperial.academia.interface_adapter.common.ViewManagerModel;
// import com.imperial.academia.interface_adapter.postboard.PostBoardController;
// import com.imperial.academia.interface_adapter.postboard.PostBoardPresenter;
// import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
// import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
// import com.imperial.academia.use_case.changeview.ChangeViewInteractor;
// import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;
// import com.imperial.academia.view.PostBoardView;

// /**
//  * Factory class for creating instances related to the Post Board use case.
//  */
// public class PostBoardUseCaseFactory {

//     /** Prevents instantiation of this utility class. */
//     private PostBoardUseCaseFactory() {}

//     /**
//      * Creates a new instance of {@link PostBoardView} with the specified view manager model and post board view model.
//      * 
//      * @param viewManagerModel the view manager model
//      * @param posterViewModel the post board view model
//      * @return a new instance of {@link PostBoardView}
//      * @throws ClassNotFoundException if the class cannot be located
//      */
//     public static PostBoardView create(ViewManagerModel viewManagerModel, PostBoardViewModel posterViewModel) throws ClassNotFoundException {
//         PostBoardController posterController = createController(viewManagerModel);
//         return new PostBoardView(posterViewModel, posterController);
//     }

//     /**
//      * Creates a new instance of {@link PostBoardController} with the specified view manager model.
//      * 
//      * @param viewManagerModel the view manager model
//      * @return a new instance of {@link PostBoardController}
//      */
//     private static PostBoardController createController(ViewManagerModel viewManagerModel) {
//         return new PostBoardController();
//     }
// }
