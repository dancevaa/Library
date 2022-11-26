package service;
import exception.*;


public interface StorageService {
    void openLibrary(LoginServiceImpl loginServiceImpl, LibraryServiceImpl libraryServiceImpl, MenuServiceImpl menuServiceImpl) throws NoSuchDirectoryOrFile;

    void openOrCreateLibrary(LoginServiceImpl loginServiceImpl) throws FileIsAlreadyExist, NoSuchDirectoryOrFile;

    void saving(LoginServiceImpl loginServiceImpl, LibraryServiceImpl libraryServiceImpl) throws NoSuchDirectoryOrFile;
}
