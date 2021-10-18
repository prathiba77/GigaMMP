package org.iit;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility
{
	public static String selectFutureDate(int nofDate)
	   {
		   Calendar cal = Calendar.getInstance();
		   System.out.println(cal.getTime());
		   cal.add(Calendar.DAY_OF_MONTH, nofDate);
		   
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/YYYY");
		   String date = sdf.format(cal.getTime());
		   System.out.println(date);
		   return date;
		   
	   }
}
