package org.raul.checadores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ChecadorDataNasc {

    public static boolean checkIfIsValid(String dataNascBlueprint) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            LocalDate.parse(dataNascBlueprint, formatter.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}
