package com.imperial.academia.interface_adapter.edit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.edit.EditInputBoundary;
import com.imperial.academia.use_case.edit.EditInputData;

class EditControllerTest {
private EditInputBoundary mockEditInteractor;
private ChangeViewInputBoundary mockChangeViewInteractor;
private EditController editController;

    @BeforeEach
    public void init(){
        mockEditInteractor = mock(EditInputBoundary.class);
        mockChangeViewInteractor = mock(ChangeViewInputBoundary.class);

        try (MockedStatic<UsecaseFactory> mockedStatic = Mockito.mockStatic(UsecaseFactory.class)){
            mockedStatic.when(UsecaseFactory::getEditInteractor).thenReturn(mockEditInteractor);
            mockedStatic.when(UsecaseFactory::getChangeViewInteractor).thenReturn(mockChangeViewInteractor);

            editController = new EditController();
        }
    }

    @Test
    void execute() {
        editController.execute();
        verify(mockEditInteractor).execute();
    }

    @Test
    void update() {
        editController.update("", "","","","");
        verify(mockEditInteractor).update(any(EditInputData.class));
    }

    @Test
    void cancel() {
        editController.cancel();
        verify(mockChangeViewInteractor).changeView("profile");
    }

    @Test
    void changeAvatar() {
        editController.changeAvatar(1, new File(""));
        verify(mockEditInteractor).changeAvatar(1,new File(""));
    }
}