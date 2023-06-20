package org.com.customer.tools;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Tools
{
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailValue)
    {
        if(Objects.isNull(emailValue)) return false;
        return VALID_EMAIL_ADDRESS_REGEX.matcher(emailValue).matches();
    }

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum)
    {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
