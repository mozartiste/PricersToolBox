package com.mozartiste.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    
    public static Double nbDays(Date begin, Date end) {
	    	long difference = end.getTime() - begin.getTime();
	    	Double nbDays = new BigDecimal(difference / (1000*60*60*24)).doubleValue();
	    	return nbDays;
    }
}
