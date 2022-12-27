package service;

import dto.Action;
import exception.NoSuchActionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {
    private static final String ACTION_ADD = "ADD";
    private static final String ACTION_DELETE = "DELETE";
    private static final String ACTION_READ = "READ";
    private static final String ACTION_EXIT = "EXIT";

    private static final String ACTION_NOT_SUPPORTED = "ACTION_NOT_SUPPORTED";

    @Mock
    private Scanner scanner;

    @Mock
    private LibraryServiceImpl libraryService;
    private MenuServiceImpl menuService;

    @BeforeEach
    void beforeEach(){
        menuService = new MenuServiceImpl(scanner, libraryService);
    }

    @AfterEach
    void afterEach(){
        verifyNoMoreInteractions(scanner, libraryService);
    }

    @Test
    void shouldChooseAction() {
        when(scanner.nextLine()).thenReturn(ACTION_ADD);
        Action action = menuService.choosingAction();
        assertEquals(Action.ADD, action);
        verify(scanner).nextLine();
    }

    @Test
    void shouldThrowExceptionIfActionDoesNotSupport() {
        when(scanner.nextLine()).thenReturn(ACTION_NOT_SUPPORTED);
        assertThrows(NoSuchActionException.class, menuService::choosingAction);
        verify(scanner).nextLine();
    }


    @Test
    void shouldCheckAllActions() {
        when(scanner.nextLine()).thenReturn(ACTION_ADD, ACTION_DELETE, ACTION_READ, ACTION_EXIT);
        doNothing().when(libraryService).addBook();
        doNothing().when(libraryService).deleteBook();
        doNothing().when(libraryService).readBook();
        menuService.run();
        verify(scanner, times(4)).nextLine();
        verify(libraryService).addBook();
        verify(libraryService).readBook();
        verify(libraryService).deleteBook();
    }

    @Test
    void shouldThrowExceptionIfActionIsNotCorrect(){
        when(scanner.nextLine()).thenReturn(ACTION_NOT_SUPPORTED, ACTION_EXIT);
        menuService.run();
        verify(scanner, times(2)).nextLine();
    }
}