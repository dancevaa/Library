package service;


public class Menu {
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
        this.setChoosingAction(library.scanner.nextLine());
        while (!this.getChoosingAction().equalsIgnoreCase("yes") && !this.getChoosingAction().equalsIgnoreCase("no")) {
            System.out.println("Write YES or NO");
            this.setChoosingAction(library.scanner.nextLine());
        }
        if (this.getChoosingAction().equalsIgnoreCase("yes")) {
            doSomething = true;
        } else if (this.getChoosingAction().equalsIgnoreCase("no")) {
            doSomething = false;
            System.out.println("The next one");
        }
        return doSomething;
    }



    public void choosingAction(LibraryService library) {
        System.out.println("Do you want to add book, delete or read? Write please ADD, DELETE, or READ");
        this.setChoosingAction(library.scanner.nextLine());
        while (!this.getChoosingAction().equalsIgnoreCase("add") && !this.getChoosingAction().equalsIgnoreCase("delete") && !this.getChoosingAction().equalsIgnoreCase("read")) {
            System.out.println("Write correct action");
            this.setChoosingAction(library.scanner.nextLine());
        }

    }
}
