package org.raul.contactTypes;

import org.raul.checadores.ChecadorDataNasc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Person extends Contact {
    private String surname;
    private String birthDate;
    private String gender;

    public Person(String firstName, String surname, String birthDate, String gender, String phoneNumber) {
        this.name = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.contactPhoneNumber = phoneNumber;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.whenCreated = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        this.whenLastEdited = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        this.isPerson = true;
    }

    //getters

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        if (birthDate.isBlank()) {
            return "[no data]";
        }

        return birthDate;
    }

    public String getGender() {
        if (gender.isBlank()) {
            return "[no data]";
        }

        return gender;
    }

    @Override
    public String[] getEditableFieldNames() {
        String[] editableFields = {"name", "surname", "birth", "gender", "number"};
        return editableFields;
    }

    //setters

    public void setSurname(String surname) {
        this.surname = surname;
        updateLastEdited();
    }

    public void setBirth(String newBirthDate) {
        if (ChecadorDataNasc.checkIfIsValid(newBirthDate)) {
            birthDate = newBirthDate;
            updateLastEdited();
        } else {
            birthDate = "[no data]";
            System.out.println("Bad birth date!");
        }
    }

    public void setGender(String newGender) {
        if (newGender.toLowerCase().equals("m") || newGender.toLowerCase().equals("f")) {
            gender = newGender;
            updateLastEdited();
        } else {
            gender = "[no data]";
            System.out.println("Bad gender!");
        }
    }

    //others

    @Override
    public String whenListed() {
        return name + " " + surname;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nSurname: " + surname +
                "\nBirth date: " + birthDate +
                "\nGender: " + gender +
                "\nNumber: " + contactPhoneNumber +
                "\nTime created: " + whenCreated +
                "\nTime last edit: " + whenLastEdited + "\n";
    }

}
