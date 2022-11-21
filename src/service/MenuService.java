package service;

import dto.Account;
import dto.Action;
import dto.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public interface MenuService extends LibraryServiceInt{
    default void openLibrary(Login login) throws IOException {
        do {
            Account account = login.entering();
            boolean doSomething = true;
            do {
                if (Role.ADMIN.equals(account.getRole())) {
                    switch (choosingAction()) {
                        case ADD -> addBook();
                        case READ -> readBook();
                        case DELETE -> deleteBook();
                    }
                } else {
                    readBook();
                }
                doSomething = isDoSomething(doSomething);
            }
            while (doSomething);
        }
        while (LibraryService.isIsLibraryOpen());
    }

    default void openOrCreateLibrary(Login login) throws IOException, ClassNotFoundException {
        if(!Files.exists(Path.of(PATH_OF_LIBRARY))){
            Files.createDirectory(Path.of(PATH_OF_LIBRARY));
            Files.createFile(Path.of(PATH_OF_LIBRARY + "save.ser"));
            LibraryService.setBooks(new ArrayList<>());
            login.setAccountList(new ArrayList<>());
        }
        else {
            FileInputStream fileInputStream = new FileInputStream(PATH_OF_LIBRARY + "save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SavedLibrary savedLibrary = (SavedLibrary) objectInputStream.readObject();
            LibraryService.setBooks(savedLibrary.getSavedBooks());
            login.setAccountList(savedLibrary.getSavedAccounts());
        }
    }

    default Action choosingAction() {
        System.out.println("Do you want to add book, delete or read? Write please ADD, DELETE, or READ");
        String action = scanner.nextLine();

        return Arrays.stream(Action.values())
                .filter(a -> a.getActionName().equalsIgnoreCase(action))
                .findFirst()
                .orElse(Action.READ);
    }


    default boolean isDoSomething(boolean doSomething) {
        System.out.println(" ");
        System.out.println("Do you want to do something else? Write YES or NO");
        String choosingAction = scanner.nextLine();
        while (!choosingAction.equalsIgnoreCase("yes") && !choosingAction.equalsIgnoreCase("no")) {
            System.out.println("Write YES or NO");
            choosingAction = scanner.nextLine();
        }
        if (choosingAction.equalsIgnoreCase("yes")) {
            doSomething = true;
        } else if (choosingAction.equalsIgnoreCase("no")) {
            doSomething = false;
            System.out.println("Do you want to close library");
            choosingAction = scanner.nextLine();
            if(choosingAction.equalsIgnoreCase("yes")){
                LibraryService.setLibraryOpen(false);
            }
            else {
                System.out.println("The next one");
            }
        }
        return doSomething;
    }

}
