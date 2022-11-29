package service;

import dto.Action;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.Scanner;
@Log4j
public class MenuServiceImpl implements MenuService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Action choosingAction() {
        log.info("Do you want to add book, delete or read? Write please ADD, DELETE, or READ");
        String action = scanner.nextLine();

        return Arrays.stream(Action.values())
                .filter(a -> a.getActionName().equalsIgnoreCase(action))
                .findFirst()
                .orElse(Action.READ);
    }

    @Override
    public boolean isDoSomething(boolean doSomething) {
        log.info(" ");
        log.info("Do you want to do something else? Write YES or NO");
        String choosingAction = scanner.nextLine();
        while (!choosingAction.equalsIgnoreCase("yes") && !choosingAction.equalsIgnoreCase("no")) {
            log.info("Write YES or NO");
            choosingAction = scanner.nextLine();
        }
        if (choosingAction.equalsIgnoreCase("yes")) {
            doSomething = true;
        } else if (choosingAction.equalsIgnoreCase("no")) {
            doSomething = false;
            log.info("Do you want to close library");
            choosingAction = scanner.nextLine();
            if (choosingAction.equalsIgnoreCase("yes")) {
                StorageServiceImpl.setLibraryOpen(false);
            } else {
                log.info("The next one");
            }
        }
        return doSomething;
    }
}
