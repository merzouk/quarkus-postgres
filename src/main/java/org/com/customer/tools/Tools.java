package org.com.customer.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Tools {

    private static final String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static final boolean validateEmail(String emailValue){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailValue);
        return matcher.matches();
    }
}
