package org.raul.ui;

import org.raul.contactTypes.Contact;
import org.raul.phoneBook.PhoneBook;
import org.raul.utils.Serializator;

import java.util.Arrays;
import java.util.Scanner;

public class Editor {

    private static final PhoneBook contacts = MainMenu.contacts;
    private static final Scanner scanner = MainMenu.scanner;

    static void edit(int index) {
        Contact contact = contacts.getContact(index);
        String[] editableFields = contact.getEditableFieldNames();
        StringBuilder fields = new StringBuilder("");
        Arrays.stream(editableFields).forEach(f -> {
            fields.append(f + ", ");
        });
        fields.delete(fields.length() - 2, fields.length());


        while (true) {
            System.out.print("Select a field (" + fields + "): ");
            String fieldName = scanner.nextLine();

            if (Arrays.stream(editableFields).anyMatch(fieldName.toLowerCase()::equals)) {
                editField(index, fieldName);
                Serializator.serializeContacts();
                System.out.println("Saved");
                Informer.inform(contact);
                break;
            } else {
                System.out.println("No such field, try again!\n");
            }
        }
    }

    private static void editField(int index, String fieldName) {
        System.out.print("Enter " + fieldName + ": ");
        String newValue = scanner.nextLine();
        contacts.getContact(index).setField(fieldName, newValue);
    }

}
