package org.raul.ui;

import org.raul.phoneBook.PhoneBook;
import org.raul.utils.Serializator;

import java.util.Scanner;

public class RecordMenu {

    private static final PhoneBook contacts = MainMenu.contacts;
    private static final Scanner scanner = MainMenu.scanner;
    private static int indexOfRecord;

    static void start(int index) {
        indexOfRecord = index;
        while (true) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine();
            if (checkAction(action)) break;
        }
    }

    private static boolean checkAction(String action) {
        switch (action) {
            case "edit":
                Editor.edit(indexOfRecord);
                break;
            case "delete":
                contacts.remove(indexOfRecord);
                Serializator.serializeContacts();
                System.out.println("The record removed!\n");
                break;
            case "menu":
                System.out.println();
                break;
            default:
                System.out.println("No such action, try again!\n");
                return false;
        }

        return true;
    }

}
