package org.raul.ui;

import org.raul.App;
import org.raul.phoneBook.PhoneBook;
import org.raul.utils.Serializator;

import java.util.Scanner;

public class mainMenu {

    static Scanner scanner = new Scanner(System.in);
    static PhoneBook contacts = App.contacts;

    public static void start() {

        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String userInput = scanner.nextLine();

            if (userInput.equals("exit")) {
                Serializator.serializeContacts();
                break;
            }
            else {
                checkUserInput(userInput);
            }
        }

    }

    private static void checkUserInput(String userInput) {
        if (userInput.equals("add")) {
            Adder.add();
        } else if (userInput.equals("list")) {
            Lister.list();
        } else if (userInput.equals("search")) {
            Searcher.search();
        } else if (userInput.equals("count")) {
            System.out.println("The Phone Book has " + contacts.getSize() + " records.\n");
        } else {
            System.out.println("No such action, try again!!!\n");
        }
    }
}
