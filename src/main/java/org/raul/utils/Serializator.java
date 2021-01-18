package org.raul.utils;

import org.raul.App;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializator {

    public static void serializeContacts() {
        String fileName = "contacts.data";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName, false))) {
            oos.writeObject(App.contacts);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
