package org.raul.contactTypes;

import org.raul.checadores.ChecadorNumeroTelefone;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public abstract class Contact implements Serializable {
    protected String name;
    protected String contactPhoneNumber;
    protected boolean isPerson = false;
    protected LocalDateTime whenCreated;
    protected LocalDateTime whenLastEdited;
    private static final long serialVersionUID = 1L;

    //getters

    public String getName() {
        return name;
    }

    public String getContactPhoneNumber() {
        if (contactPhoneNumber.isBlank()) {
            return "[no data]";
        }

        return contactPhoneNumber;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public LocalDateTime getWhenCreated() {
        return whenCreated;
    }

    public LocalDateTime getWhenLastEdited() {
        return whenLastEdited;
    }

    public Method[] getSetters() {
        Method[] thisMethods = this.getClass().getDeclaredMethods();
        Method[] parentMethods = this.getClass().getSuperclass().getDeclaredMethods();
        Method[] setters = new Method[thisMethods.length + parentMethods.length];

        Arrays.setAll(setters, i ->
                (i < parentMethods.length ? parentMethods[i] : thisMethods[i - parentMethods.length]));

        setters = Arrays.stream(setters).filter(m -> m.getName().toLowerCase().matches("set\\w+")).toArray(Method[]::new);


        return setters;
    }

    public String[] getEditableFieldNames() {
        String[] editableFields = {"name", "phone"};
        return editableFields;
    }

    //setters

    public void setName(String name) {
        this.name = name;
        updateLastEdited();
    }

    public void setNumber(String phoneNumber) {
        if (phoneNumber.equals("+(123) (123)")) {
            System.out.println("Wrong number format!");
            this.contactPhoneNumber = "";
        } else if (ChecadorNumeroTelefone.checkIfIsValid(phoneNumber)) {
            this.contactPhoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.contactPhoneNumber = "[no data]";
        }
    }

    public void setField(String fieldName, String newValue) {
        Method[] setters = getSetters();

        for (Method method : setters) {
            if (method.getName().toLowerCase().equals("set" + fieldName.toLowerCase())) {
                try {
                    method.invoke(this, newValue);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    //others

    public void updateLastEdited() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        whenLastEdited = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
    }

    abstract public String whenListed();

    @Override
    public String toString() {
        return this.name;
    }

}