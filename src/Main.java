import service.LibraryServiceImpl;
import service.LoginServiceImpl;
import service.MenuServiceImpl;
import service.StorageServiceImpl;

import exception.*;

public class Main {
    public static void main(String[] args) throws NoSuchDirectoryOrFile, FileIsAlreadyExist {
        LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
        MenuServiceImpl menuServiceImpl = new MenuServiceImpl();
        LibraryServiceImpl library = new LibraryServiceImpl();
        StorageServiceImpl storageServiceImpl = new StorageServiceImpl(loginServiceImpl);
        storageServiceImpl.openLibrary(loginServiceImpl, library, menuServiceImpl);
        storageServiceImpl.saving(loginServiceImpl, library);
    }
}








