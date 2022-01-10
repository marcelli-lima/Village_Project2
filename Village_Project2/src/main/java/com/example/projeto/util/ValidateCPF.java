package com.example.projeto.util;

import java.util.regex.Pattern;

public class ValidateCPF {
	
	
	  public static boolean isValidCPF(final String cpf) {
	        if( null == cpf ) {
	            return false;
	        }

	        final Pattern pattern = Pattern.compile("(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)");
	        return pattern.matcher(cpf).matches();
	    }

	
}
