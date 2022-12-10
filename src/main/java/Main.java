import service.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Properties properties = getProperties();
        final String libraryPath = properties.getProperty(isFileSystemIsUnix() ? "library.path.unix.pattern" : "library.path.windows.pattern");
        final String absolutePathToLibrary = new File("").getAbsolutePath() + libraryPath;

        createLibraryDirectory(absolutePathToLibrary);

        StorageService storageService = new StorageServiceImpl(absolutePathToLibrary);
        LibraryService libraryService = new LibraryServiceImpl(new Scanner(System.in), storageService);
        MenuService menuService = new MenuServiceImpl(new Scanner(System.in), libraryService);
        LoginService loginService = new LoginServiceImpl();

        menuService.run();
    }

    private static Properties getProperties() {
        final Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    private static void createLibraryDirectory(final String directoryPath) {
        try {
            Files.createDirectory(Path.of(directoryPath));
        } catch (IOException e) {
            return;
        }
    }

    private static boolean isFileSystemIsUnix() {
        final String absolutePath = new File("").getAbsolutePath();
        return absolutePath.contains("/");
    }
}








