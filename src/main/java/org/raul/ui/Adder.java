package org.raul.ui;

import org.raul.checadores.ChecadorDataNasc;
import org.raul.checadores.ChecadorNumeroTelefone;
import org.raul.contactTypes.Organization;
import org.raul.contactTypes.Person;
import org.raul.phoneBook.PhoneBook;
import org.raul.utils.Serializator;

import java.util.Scanner;

public class Adder {

    private static final PhoneBook contacts = mainMenu.contacts;
    private static final Scanner scanner = mainMenu.scanner;

    static void add() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();

        if (type.equals("person")) {
            addPerson();
        } else if (type.equals("organization")) {
            addOrganization();
        } else {
            System.out.println("No such type!\n");
            return;
        }

        Serializator.serializeContacts();
    }

    private static void addPerson() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the birth date: ");
        String dataNasc = scanner.nextLine();
        if (!ChecadorDataNasc.checkIfIsValid(dataNasc)) {
            System.out.println("Bad birth date!");
            dataNasc = "[no data]";
        }

        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")) {
            System.out.println("Bad gender!");
            gender = "[no data]";
        }

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();
        if (!ChecadorNumeroTelefone.checkIfIsValid(phoneNumber)) {
            System.out.println("Wrong number format!");
            phoneNumber = "[no data]";
        }

        Person person = new Person(name, surname, dataNasc, gender, phoneNumber);
        contacts.add(person);
        System.out.println("The record added.\n");
    }

    private static void addOrganization() {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();
        if (!ChecadorNumeroTelefone.checkIfIsValid(phoneNumber)) {
            System.out.println("Wrong number format!");
            phoneNumber = "";
        }

        Organization organization = new Organization(name, address, phoneNumber);
        contacts.add(new Organization(name, address, phoneNumber));
        System.out.println("The record added.\n");
    }

}
