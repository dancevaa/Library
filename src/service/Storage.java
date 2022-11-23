package service;

import dto.Account;
import dto.Role;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage implements Serializable, StorageService {
    private final String PATH_OF_LIBRARY = "/Users/annann/Desktop/Library/";

    private static final long serialVersionUID = 1L;
    private static boolean isLibraryOpen = true;

    public Storage(Login login) throws IOException, ClassNotFoundException {
        openOrCreateLibrary(login);
    }

    public void openLibrary(Login login, LibraryService libraryService, Menu menu) throws IOException {
        do {
            Account account = login.entering();
            boolean doSomething = true;
            do {
                if (Role.ADMIN.equals(account.getRole())) {
                    switch (menu.choosingAction()) {
                        case ADD -> libraryService.addBook();
                        case READ -> libraryService.readBook();
                        case DELETE -> libraryService.deleteBook();
                    }
                } else {
                    libraryService.readBook();
                }
                doSomething = menu.isDoSomething(doSomething);
            }
            while (doSomething);
        }
        while (isLibraryOpen);
    }

    public void openOrCreateLibrary(Login login) throws IOException, ClassNotFoundException {
        if (!Files.exists(Path.of(PATH_OF_LIBRARY))) {
            Files.createDirectory(Path.of(PATH_OF_LIBRARY));
            Files.createFile(Path.of(PATH_OF_LIBRARY + "saved.JSON"));
            LibraryService.setBooks(new ArrayList<>());
            login.setAccountList(new ArrayList<>());
        } else {
            FileInputStream fileInputStream = new FileInputStream(PATH_OF_LIBRARY + "saved.JSON");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SavedLibrary savedLibrary = (SavedLibrary) objectInputStream.readObject();
            LibraryService.setBooks(savedLibrary.getSavedBooks());
            login.setAccountList(savedLibrary.getSavedAccounts());
        }
    }

    public void saving(Login login, LibraryService libraryService) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("/Users/annann/Desktop/Library/saved.JSON");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(new SavedLibrary(login.getAccountList(), libraryService.getBooks()));
        objectOutputStream.close();
    }

    public static void setLibraryOpen(boolean libraryOpen) {
        isLibraryOpen = libraryOpen;
    }

}
