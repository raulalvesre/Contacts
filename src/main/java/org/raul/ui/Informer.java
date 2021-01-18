package org.raul.ui;

import org.raul.contactTypes.Contact;
import org.raul.phoneBook.PhoneBook;

public class Informer {

    private static final PhoneBook contacts = mainMenu.contacts;

    static void inform(Contact contact) {
        System.out.println(contact);
    }

}
