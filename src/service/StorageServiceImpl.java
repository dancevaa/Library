package service;

import dto.Account;
import dto.Role;
import Exception.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class StorageServiceImpl implements Serializable, StorageService {
    private final String PATH_OF_LIBRARY = "/Users/annann/Desktop/Library/";

    private static final long serialVersionUID = 1L;
    private static boolean isLibraryOpen = true;

    public StorageServiceImpl(LoginServiceImpl loginServiceImpl) throws NoSuchDirectoryOrFile, FileIsAlreadyExist {
        openOrCreateLibrary(loginServiceImpl);
    }

    public void openLibrary(LoginServiceImpl loginServiceImpl, LibraryServiceImpl libraryServiceImpl, MenuServiceImpl menuServiceImpl) throws NoSuchDirectoryOrFile {
        do {
            Account account = loginServiceImpl.entering();
            boolean doSomething = true;
            do {
                if (Role.ADMIN.equals(account.getRole())) {
                    switch (menuServiceImpl.choosingAction()) {
                        case ADD -> libraryServiceImpl.addBook();
                        case READ -> libraryServiceImpl.readBook();
                        case DELETE -> libraryServiceImpl.deleteBook();
                    }
                } else {
                    libraryServiceImpl.readBook();
                }
                doSomething = menuServiceImpl.isDoSomething(doSomething);
            }
            while (doSomething);
        }
        while (isLibraryOpen);
    }

    public void openOrCreateLibrary(LoginServiceImpl loginServiceImpl) throws FileIsAlreadyExist, NoSuchDirectoryOrFile{
        if (!Files.exists(Path.of(PATH_OF_LIBRARY))) {
            try {
                Files.createDirectory(Path.of(PATH_OF_LIBRARY));
                Files.createFile(Path.of(PATH_OF_LIBRARY + "saved.JSON"));
            }
            catch (Exception e){
                throw new FileIsAlreadyExist(PATH_OF_LIBRARY);
            }
            LibraryServiceImpl.setBooks(new ArrayList<>());
            loginServiceImpl.setAccountList(new ArrayList<>());
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(PATH_OF_LIBRARY + "saved.JSON");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                SavedLibrary savedLibrary = (SavedLibrary) objectInputStream.readObject();
                LibraryServiceImpl.setBooks(savedLibrary.getSavedBooks());
                loginServiceImpl.setAccountList(savedLibrary.getSavedAccounts());
            }
            catch (Exception e){
                throw new NoSuchDirectoryOrFile(PATH_OF_LIBRARY + "saved.JSON");
            }

        }
    }

    public void saving(LoginServiceImpl loginServiceImpl, LibraryServiceImpl libraryServiceImpl) throws NoSuchDirectoryOrFile {
        String path = "/Users/annann/Desktop/Library/saved.JSON";
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(new SavedLibrary(loginServiceImpl.getAccountList(), libraryServiceImpl.getBooks()));
            objectOutputStream.close();
        }
        catch (Exception e){
            throw new NoSuchDirectoryOrFile(path);
        }

    }

    public static void setLibraryOpen(boolean libraryOpen) {
        isLibraryOpen = libraryOpen;
    }

}
