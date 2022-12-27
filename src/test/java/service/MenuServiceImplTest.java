package service;

import dto.Action;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {
    private static final String ACTION_ADD = "ADD";
    private static final String ACTION_DELETE = "DELETE";
    private static final String ACTION_READ = "READ";
    private static final String ACTION_EXIT = "EXIT";

    @Mock
    Scanner scanner;

    @Mock
    LibraryServiceImpl libraryService;

    @Test
    void choosingAction() {
        when(scanner.nextLine()).thenReturn(ACTION_ADD);
        MenuServiceImpl menuService = new MenuServiceImpl(scanner, libraryService);
        Action action = menuService.choosingAction();
        assertEquals(Action.ADD, action);
        verify(scanner).nextLine();
    }


    @Test
    void run() {
        MenuServiceImpl menuService = new MenuServiceImpl(scanner, libraryService);
        when(scanner.nextLine()).thenReturn(ACTION_ADD, ACTION_DELETE, ACTION_READ, ACTION_EXIT);
        menuService.run();
    }
}