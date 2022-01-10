package com.example.projeto.util;

import java.util.regex.Pattern;

public class ValidateName {
	
	 public static boolean isValidateName(final String name) {
    if( null == name || name.trim().isEmpty()) {
        return false;
    }

    final Pattern pattern = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ]+(([',. -][A-Za-zÀ-ÖØ-öø-ÿ ])?[A-Za-zÀ-ÖØ-öø-ÿ]*)*$");
    return pattern.matcher(name).matches();
}

}
