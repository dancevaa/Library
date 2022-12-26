package service;

import dto.Action;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {
    private static final String ACTION_ADD = "ADD";
    @Mock
    Scanner scanner;

    @Mock
    LibraryServiceImpl libraryService;

    @Test
    void choosingActionAdd() {
        when(scanner.nextLine()).thenReturn(ACTION_ADD);
        MenuServiceImpl menuService = new MenuServiceImpl(scanner, libraryService);
        when(menuService.choosingAction()).thenReturn(Action.ADD);
    }

    @Test
    void run() {
    }
}