package org.raul;

import org.raul.phoneBook.PhoneBook;
import org.raul.ui.MainMenu;
import org.raul.utils.Deserializator;
import org.raul.utils.Serializator;

public class App {

    public static PhoneBook contacts;

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                Serializator.serializeContacts();
            }
        }));

        contacts = Deserializator.deserializeContacts();
        MainMenu.start();
    }
}
