package com.example.projeto.util;

import java.util.regex.Pattern;

public class ValidatePassword {

    public static boolean isValidatePassword(String password) {
        final Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        return pattern.matcher(password).matches();
    }
}
