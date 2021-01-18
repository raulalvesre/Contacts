package org.raul.utils;

import org.raul.phoneBook.PhoneBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializator {

    public static PhoneBook deserializeContacts() {
        File file = new File("contacts.data");
        PhoneBook phoneBook = new PhoneBook();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            if (file.length() != 0) {
                phoneBook = (PhoneBook) ois.readObject();
            }
        } finally {
            return phoneBook;
        }
    }

}
