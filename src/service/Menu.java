package service;


import dto.Action;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    private String choosingAction;

    public  String getChoosingAction() {
        return this.choosingAction;
    }

    public void setChoosingAction(String choosingAction) {
        this.choosingAction = choosingAction;
    }

    public boolean isDoSomething(boolean doSomething, LibraryService library) {
        System.out.println(" ");
        System.out.println("Do you want to do something else? Write YES or NO");
        this.setChoosingAction(scanner.nextLine());
        while (!this.getChoosingAction().equalsIgnoreCase("yes") && !this.getChoosingAction().equalsIgnoreCase("no")) {
            System.out.println("Write YES or NO");
            this.setChoosingAction(scanner.nextLine());
        }
        if (this.getChoosingAction().equalsIgnoreCase("yes")) {
            doSomething = true;
        } else if (this.getChoosingAction().equalsIgnoreCase("no")) {
            doSomething = false;
            System.out.println("The next one");
        }
        return doSomething;
    }

    public Action choosingAction() {
        System.out.println("Do you want to add book, delete or read? Write please ADD, DELETE, or READ");
        String action = scanner.nextLine();

        return Arrays.stream(Action.values())
                .filter(a -> a.getActionName().equalsIgnoreCase(action))
                .findFirst()
                .orElse(Action.READ);
    }
}
