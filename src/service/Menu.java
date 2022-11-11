package service;

public class Menu {
    private static String choosingAction;

    public static String getChoosingAction() {
        return choosingAction;
    }

    public static void setChoosingAction(String choosingAction) {
        Menu.choosingAction = choosingAction;
    }

    public static boolean isDoSomething(boolean doSomething) {
        System.out.println(" ");
        System.out.println("Do you want to do something else? Write YES or NO");
        Menu.setChoosingAction(LibraryService.scanner.nextLine());
        while (!Menu.getChoosingAction().equalsIgnoreCase("yes") && !Menu.getChoosingAction().equalsIgnoreCase("no")) {
            System.out.println("Write YES or NO");
            Menu.setChoosingAction(LibraryService.scanner.nextLine());
        }
        if (Menu.getChoosingAction().equalsIgnoreCase("yes")) {
            doSomething = true;
        } else if (Menu.getChoosingAction().equalsIgnoreCase("no")) {
            doSomething = false;
            System.out.println("The next one");
        }
        return doSomething;
    }



    public static void choosingAction() {
        System.out.println("Do you want to add book, delete or read? Write please ADD, DELETE, or READ");
        Menu.setChoosingAction(LibraryService.scanner.nextLine());
        while (!Menu.getChoosingAction().equalsIgnoreCase("add") && !Menu.getChoosingAction().equalsIgnoreCase("delete") && !Menu.getChoosingAction().equalsIgnoreCase("read")) {
            System.out.println("Write correct action");
            Menu.setChoosingAction(LibraryService.scanner.nextLine());
        }

    }
}
