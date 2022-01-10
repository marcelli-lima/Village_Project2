package com.example.projeto.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.*;
import java.util.Date;
public class FilterAge {


    public static long age(Date birthDate) throws ParseException {

    	
        Instant instant = birthDate.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        Period period = Period.between(givenDate, LocalDate.now());
        return period.getYears() + period.getMonths()/12 + period.getDays()/365;
        
    }

	public static int age(BigDecimal income) {
		return 0;
	}
}
