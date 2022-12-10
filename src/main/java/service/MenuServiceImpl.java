package service;

import dto.Action;
import exception.NoSuchActionException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final Scanner scanner;
    private final LibraryService libraryService;

    @Override
    public Action choosingAction() {
        log.info("Do you want to add book, delete or read? Write please ADD, DELETE, READ, EXIT");
        final String actionName = scanner.nextLine();
        try {
            return Action.findAction(actionName);
        } catch (IllegalArgumentException e) {
            throw new NoSuchActionException(actionName);
        }

    }

    @Override
    public void run() {

        Action action = null;
        do {
            try {
                action = choosingAction();
                switch (action) {
                    case ADD ->  libraryService.addBook();
                    case READ ->  libraryService.readBook();
                    case DELETE ->  libraryService.deleteBook();
                }
            } catch (NoSuchActionException e) {
                log.warn(e.getMessage());
            }
        }
        while (!Action.EXIT.equals(action));
    }
}
