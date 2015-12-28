package com.aurotech.workfront.sharepoint.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TestSamples {

	public static void main(String[] args) throws ParseException {
		
		System.out.println(EncryptionUtil.decrypt("P3/bMt0sFx0nhpfJOWRMkQ=="));
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("c", "cv");
		params.put("s", "sv");
		
		for(String p : params.keySet()){
			System.out.println(p +" - "+ params.get(p));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String start = "12/16/2015";
		String end = "12/19/2015";
		
		Date startDate = sdf.parse(start);
		Date endDate = sdf.parse(end);
		Date today = new Date();
		
		System.out.println(today+" --- "+startDate+" -- "+endDate);
		System.out.println(today.equals(startDate));
		
		System.out.println(sdf.format(today)+" -- "+sdf.format(startDate)+" -- "+sdf.format(endDate));
		
	    DateTime today1 = new DateTime();

	    DateTime tomorrow = today1.plusDays(1);
	    
	    System.out.println(today1.toString("MM/dd/yyyy"));

	    System.out.println(tomorrow.toString("MM/dd/yyyy"));

	   
	    DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
	 // Parsing the date
	    DateTime jodaStart = dtf.parseDateTime(start);
	    DateTime jodaEnd = dtf.parseDateTime(end);
	    System.out.println(jodaStart+" --- "+jodaEnd);
	    
	    System.out.println(jodaEnd.isBeforeNow());
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}

}
