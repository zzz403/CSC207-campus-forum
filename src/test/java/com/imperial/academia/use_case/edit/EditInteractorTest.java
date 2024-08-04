package com.imperial.academia.use_case.edit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.UpdateUserFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.FileService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputData;

class EditInteractorTest {
    private EditInteractor editInteractor;
    private EditOutputBoundary mockEditPresenter;
    private UpdateUserFactory mockUpdatedUserFactory;
    private UserService mockUserService;
    private ChangeViewInputBoundary mockChangeViewInteractor;
    private FileService mockFileService;
    private ProfileInputBoundary mockProfileInteractor;

    @BeforeEach
    public void init(){
        mockEditPresenter = mock(EditOutputBoundary.class);
        mockUpdatedUserFactory = mock(UpdateUserFactory.class);
        mockUserService = mock(UserService.class);
        mockChangeViewInteractor = mock(ChangeViewInputBoundary.class);
        mockFileService = mock(FileService.class);
        mockProfileInteractor = mock(ProfileInputBoundary.class);
        try (MockedStatic<UsecaseFactory> mockedStatic = Mockito.mockStatic(UsecaseFactory.class)){
            mockedStatic.when(UsecaseFactory::getChangeViewInteractor).thenReturn(mockChangeViewInteractor);
            try(MockedStatic<ServiceFactory> mockedStatic1 = Mockito.mockStatic(ServiceFactory.class)){
                mockedStatic.when(ServiceFactory::getUserService).thenReturn(mockUserService);
                mockedStatic.when(ServiceFactory::getFileService).thenReturn(mockFileService);

                editInteractor = new EditInteractor(mockEditPresenter, mockUpdatedUserFactory);


            }
        }
    }


    @Test
    void execute() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            editInteractor.execute();
            verify(mockChangeViewInteractor).changeView("edit");
        }
    }

    @Test
    void executeNullUser() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(null);
            editInteractor.execute();
            verify(mockChangeViewInteractor).changeView("login");
        }
    }

    @Test
    void updateNullUser() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(null);
            editInteractor.update(new EditInputData("","","","",""));
            verify(mockChangeViewInteractor).changeView("login");
        }
    }


    @Test
    void update() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );

            editInteractor.update(new EditInputData("","","","",""));
            verify(mockEditPresenter).prepareFailView(anyString(), any(EditOutputData.class));
        }
    }





    @Test
    void updateUserExists() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
           EditInputData editInputData = new EditInputData("hhhjkhhlkjhkjh","","","","");
           when(mockUserService.existsByUsername("hhhjkhhlkjhkjh")).thenReturn(true);

            editInteractor.update(editInputData);
            verify(mockEditPresenter).prepareFailView(anyString(), any(EditOutputData.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateUserSameName() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "hhhjkhhlkjhkjh",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            EditInputData editInputData = new EditInputData("hhhjkhhlkjhkjh","","","","");
            when(mockUserService.existsByUsername("hhhjkhhlkjhkjh")).thenReturn(true);

            editInteractor.update(editInputData);
            verify(mockEditPresenter).prepareFailView(anyString(), any(EditOutputData.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void updatePasswordShort() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            EditInputData editInputData = new EditInputData("hngfckjgfkjf","","","","");
            when(mockUserService.existsByUsername("")).thenReturn(false);

            editInteractor.update(editInputData);
            verify(mockEditPresenter).prepareFailView(anyString(), any(EditOutputData.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updatePasswordNotMatch() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            EditInputData editInputData = new EditInputData("hngfckjgfkjf","sadasdsadsad","","","");
            when(mockUserService.existsByUsername("")).thenReturn(false);

            editInteractor.update(editInputData);
            verify(mockEditPresenter).prepareFailView(anyString(), any(EditOutputData.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void updateEmailFalse() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            EditInputData editInputData = new EditInputData("hngfckjgfkjf","sadasdsadsad","sadasdsadsad","","");
            when(mockUserService.existsByUsername("")).thenReturn(false);

            editInteractor.update(editInputData);
            verify(mockEditPresenter).prepareFailView(anyString(), any(EditOutputData.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateEmailExist() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            User updatedUser = new User(
                    1,
                    "hngfckjgfkjf",
                    "sadasdsadsad",
                    "asdaskj02@gmail.com",
                    "",
                    "",
                    new Timestamp(0),
                    new Timestamp(1)
            );

            EditInputData editInputData = new EditInputData("hngfckjgfkjf","sadasdsadsad","sadasdsadsad","","asdaskj02@gmail.com");
            when(mockUserService.existsByUsername("")).thenReturn(false);
            when(mockUserService.existsByEmail("asdaskj02@gmail.com")).thenReturn(true);
            when(mockUpdatedUserFactory.create(anyInt(), anyString(),anyString(),anyString(),anyString(),anyString(), any(Timestamp.class),any(LocalDateTime.class))).thenReturn(updatedUser);

            editInteractor.update(editInputData);
            verify(mockEditPresenter).prepareFailView(anyString(), any(EditOutputData.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void updateGoodInputSameEmail() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "asdaskj02@gmail.com",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            User updatedUser = new User(
                    1,
                    "hngfckjgfkjf",
                    "sadasdsadsad",
                    "asdaskj02@gmail.com",
                    "",
                    "",
                    new Timestamp(0),
                    new Timestamp(1)
            );
            when(mockUpdatedUserFactory.create(anyInt(), anyString(),anyString(),anyString(),anyString(),anyString(), any(Timestamp.class),any(LocalDateTime.class))).thenReturn(updatedUser);

            EditInputData editInputData = new EditInputData("hngfckjgfkjf","sadasdsadsad","sadasdsadsad","","asdaskj02@gmail.com");
            when(mockUserService.existsByUsername("")).thenReturn(false);
            when(mockUserService.existsByEmail("")).thenReturn(true);



            try (MockedStatic<UsecaseFactory> mockedStatic1 = Mockito.mockStatic(UsecaseFactory.class)) {
                mockedStatic1.when(UsecaseFactory::getProfileInteractor).thenReturn(mockProfileInteractor);
                editInteractor.update(editInputData);
                verify(mockProfileInteractor).execute(any(ProfileInputData.class));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @Test
    void updateGoodInput() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            User updatedUser = new User(
                    1,
                    "hngfckjgfkjf",
                    "sadasdsadsad",
                    "asdaskj02@gmail.com",
                    "",
                    "",
                    new Timestamp(0),
                    new Timestamp(1)
            );
            when(mockUpdatedUserFactory.create(anyInt(), anyString(),anyString(),anyString(),anyString(),anyString(), any(Timestamp.class),any(LocalDateTime.class))).thenReturn(updatedUser);

            EditInputData editInputData = new EditInputData("hngfckjgfkjf","sadasdsadsad","sadasdsadsad","","asdaskj02@gmail.com");
            when(mockUserService.existsByUsername("")).thenReturn(false);
            when(mockUserService.existsByEmail("")).thenReturn(false);



            try (MockedStatic<UsecaseFactory> mockedStatic1 = Mockito.mockStatic(UsecaseFactory.class)) {
                mockedStatic1.when(UsecaseFactory::getProfileInteractor).thenReturn(mockProfileInteractor);
                editInteractor.update(editInputData);
                verify(mockProfileInteractor).execute(any(ProfileInputData.class));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    void updateSQLExceptions() {
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)){
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            User updatedUser = new User(
                    1,
                    "hngfckjgfkjf",
                    "sadasdsadsad",
                    "asdaskj02@gmail.com",
                    "",
                    "",
                    new Timestamp(0),
                    new Timestamp(1)
            );
            when(mockUpdatedUserFactory.create(anyInt(), anyString(),anyString(),anyString(),anyString(),anyString(), any(Timestamp.class),any(LocalDateTime.class))).thenReturn(updatedUser);

            EditInputData editInputData = new EditInputData("hngfckjgfkjf","sadasdsadsad","sadasdsadsad","","asdaskj02@gmail.com");
            when(mockUserService.existsByUsername("")).thenReturn(false);
            when(mockUserService.existsByEmail("")).thenReturn(false);

            Mockito.doThrow(new SQLException()).when(mockUserService).update(any(User.class));

            try (MockedStatic<UsecaseFactory> mockedStatic1 = Mockito.mockStatic(UsecaseFactory.class)) {
                mockedStatic1.when(UsecaseFactory::getProfileInteractor).thenReturn(mockProfileInteractor);
                editInteractor.update(editInputData);
            }


        } catch (SQLException e) {
            Assert.assertEquals("Database error: " + (new SQLException()).getMessage(), e.getMessage());
        }
    }



    @Test
    void changeAvatarFileNotImage() {
        File file = new File("Document.pdf");
        editInteractor.changeAvatar(0, file);
        verify(mockEditPresenter).prepareFailView("File format not supported. Supported formats: jpg, jpeg, png, gif, bmp.", null);
    }

    @Test
    void changeAvatarFileNull() {
        File file = new File("");
        editInteractor.changeAvatar(0, file);
        verify(mockEditPresenter).prepareFailView("File format not supported. Supported formats: jpg, jpeg, png, gif, bmp.", null);
    }


    @Test
    void changeAvatarImageFile(){
        File file = new File("Document.gif");
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)) {
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(
                    new User(
                            0,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Timestamp(0),
                            new Timestamp(1)
                    )
            );
            User updatedUser = new User(
                    1,
                    "",
                    "",
                    "",
                    "",
                    "path",
                    new Timestamp(0),
                    new Timestamp(1)
            );
            when(mockFileService.getOutputFilePath()).thenReturn("path");
            when(mockUpdatedUserFactory.create(anyInt(), anyString(),anyString(),anyString(),anyString(),anyString(), any(Timestamp.class),any(LocalDateTime.class))).thenReturn(updatedUser);


            editInteractor.changeAvatar(0, file);
            verify(mockFileService).saveAvatar(0, file);

        }




    }
}