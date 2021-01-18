package org.raul.ui;

import org.raul.contactTypes.Contact;
import org.raul.phoneBook.PhoneBook;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher {

    private static final PhoneBook contacts = mainMenu.contacts;
    private static final Scanner scanner = mainMenu.scanner;

    static void search() {
        System.out.print("Enter search query: ");
        String searched = scanner.nextLine();

        ArrayList<Map.Entry<Contact, Integer>> contactsFound = new ArrayList<>();

        for (int i = 0; i < contacts.getSize(); i++) {
            if (checkIfFinds(searched, contacts.getContact(i).toString())) {
                contactsFound.add(new AbstractMap.SimpleEntry<Contact, Integer>(contacts.getContact(i), i));
            }
        }

        if (contactsFound.size() > 0) {
            listSelectableContacts(contactsFound);
            searchMenu(getSelectableContacts(contactsFound));
        } else {
            System.out.println("No records found!\n");
        }

    }

    private static boolean checkIfFinds(String query, String contact) {
        Matcher matcher = Pattern.compile(query, Pattern.CASE_INSENSITIVE).matcher(contact);
        return matcher.find();
    }

    private static void listSelectableContacts(ArrayList<Map.Entry<Contact, Integer>> contactsFound) {
        System.out.printf("Found %d results:%n", contactsFound.size());
        AtomicInteger i = new AtomicInteger(0);

        contactsFound.forEach(c -> {
            System.out.println(i.incrementAndGet() + ". " + c.getKey().whenListed());
        });
        System.out.println();
    }

    private static LinkedHashMap<Integer, Map.Entry<Contact, Integer>> getSelectableContacts(ArrayList<Map.Entry<Contact, Integer>> contactsFound) {
        LinkedHashMap<Integer, Map.Entry<Contact, Integer>> contactsThatCanBeSelected = new LinkedHashMap<>();
        AtomicInteger i = new AtomicInteger(0);

        contactsFound.forEach(p -> {
            contactsThatCanBeSelected.put(i.incrementAndGet(), p);
        });

        return contactsThatCanBeSelected;
    }

    private static void searchMenu(LinkedHashMap<Integer, Map.Entry<Contact, Integer>> selectableContacts) {
        while (true) {
            System.out.print("[search] Enter action ([number], back, again): ");
            String action = scanner.nextLine();
            if(checkAction(action, selectableContacts)) break;
        }
    }


    private static boolean checkAction(String action, LinkedHashMap<Integer, Map.Entry<Contact, Integer>> selectableContacts) {
        try {
            int selected = Integer.parseInt(action);
            if (selected >= 1 && selected <= selectableContacts.size()) {
                Contact contact = selectableContacts.get(selected).getKey();
                int indexOfContactOnPhoneBook = selectableContacts.get(selected).getValue();
                Informer.inform(contact);
                RecordMenu.start(indexOfContactOnPhoneBook);
            } else {
                System.out.println("No such record");
            }
        } catch (NumberFormatException e) {
            if (action.equals("back")) {
                System.out.println();
            } else if (action.equals("again")) {
                search();
            } else {
                System.out.println("No such action, try again!\n");
                return false;
            }
        }

        return true;
    }

}
