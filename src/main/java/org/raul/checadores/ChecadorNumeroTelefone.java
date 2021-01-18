package org.raul.checadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChecadorNumeroTelefone {

    public static boolean checkIfIsValid(String telephoneNumberBlueprint) {
        String firstGroupRegex = "[+]?[(]\\w{1,}[)][ -]{1,}|[+]?\\w{1,}[ -]{1,}|[+]?[(]\\w{1,}[)]|[+]?\\w{1,}";
        String secondGroupRegex = "[(]\\w{2,}[)][ -]?|\\w{2,}[ -]{1,}|[(]\\w{2,}[)]|\\w{2,}";
        String normalGroupRegex = "\\w{2,}[ -]?";

        Pattern padraoNTelefone = Pattern.compile("(" + firstGroupRegex + "){1}(" + secondGroupRegex + ")?(" + normalGroupRegex + ")*");
        Matcher matcherPadraoNTelefone = padraoNTelefone.matcher(telephoneNumberBlueprint);

        return matcherPadraoNTelefone.matches();
    }

}
