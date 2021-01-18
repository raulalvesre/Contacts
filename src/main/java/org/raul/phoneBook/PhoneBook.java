package org.raul.phoneBook;

import org.raul.contactTypes.Contact;

import java.io.Serializable;
import java.util.ArrayList;

public class PhoneBook implements Serializable {

    private ArrayList<Contact> phoneBookDatabase;

    public PhoneBook() {
        this.phoneBookDatabase = new ArrayList<>();
    }

    public Contact getContact(int index) {
        return phoneBookDatabase.get(index);
    }

    public void add(Contact contact) {
        phoneBookDatabase.add(contact);
    }

    public void remove(int index) {
        phoneBookDatabase.remove(index);
    }

    public void list() {
        if (phoneBookDatabase.size() > 0) {
            for (int i = 0; i < phoneBookDatabase.size(); i++) {
                System.out.println(i + 1 + ". " + phoneBookDatabase.get(i).whenListed());
            }
        } else {
            System.out.println("No records to list!");
        }

        System.out.println();
    }

    public int getSize() {
        return phoneBookDatabase.size();
    }

}
