package org.raul.ui;

import org.raul.phoneBook.PhoneBook;

import java.util.Scanner;

public class Lister {

    private static final PhoneBook contacts = MainMenu.contacts;
    private static final Scanner scanner = MainMenu.scanner;

    static void list() {
        contacts.list();

        while (true) {
            System.out.print("[list] Enter action ([number], back): ");
            String action = scanner.nextLine();
            if (checkAction(action)) break;
        }
    }

    private static boolean checkAction(String action) {
        try {
            int selected = Integer.parseInt(action);
            if (selected >= 1 && selected <= contacts.getSize()) {
                System.out.println(contacts.getContact(selected - 1));
                RecordMenu.start(selected - 1);
            } else {
                System.out.println("No such record");
            }
        } catch (NumberFormatException e) {
            if (action.equals("back")) {
                System.out.println();
            } else {
                System.out.println("No such action, try again!\n");
                return false;
            }
        }

        return true;
    }

}
