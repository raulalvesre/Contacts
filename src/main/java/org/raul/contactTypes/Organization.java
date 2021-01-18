package org.raul.contactTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Organization extends Contact {

    private String address;

    public Organization(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.contactPhoneNumber = phoneNumber;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.whenCreated = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        this.whenLastEdited = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String[] getEditableFieldNames() {
        String[] editableFields = {"name", "address", "phone"};
        return editableFields;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
        updateLastEdited();
    }

    @Override
    public String whenListed() {
        return super.toString();
    }

    @Override
    public String toString() {
        return  "Organization name: " + name +
                "\nAddress: " + address +
                "\nNumber: " + contactPhoneNumber +
                "\nTime created: " + whenCreated +
                "\nTime last edit: " + whenLastEdited + "\n";
    }

}
