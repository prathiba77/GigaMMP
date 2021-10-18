package org.iit.healthcare.mmp.patientmodule;

import java.util.HashMap;
import org.iit.TestBaseClass;
import org.iit.healthcare.mmp.HelperClass;
import org.iit.healthcare.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScheduleAppointmentTests extends TestBaseClass
{	
			
	   @Test
	   public void validateScheduleAppointment() throws InterruptedException
	   {		   
		    HelperClass helper = new HelperClass(driver);				   
			helper.launchApplicationURL(prop.getProperty("patienturl"));			
		    String actual = helper.login(prop.getProperty("patientUser"),prop.getProperty("patientPassword"));				
			String expected = "ria1";
			Assert.assertTrue(actual.contains(expected));			
			helper.navigateToModule(prop.getProperty("moduleName"));			
			
			ScheduleAppointmentPage spage = new ScheduleAppointmentPage(driver);			
			HashMap<String, String> expHMap	= spage.bookAppointment(prop.getProperty("doctorName"),prop.getProperty("appointmentTime"),prop.getProperty("symptomName"));						
			HashMap<String, String> actualHMap = spage.fetchPatientPortalDate();
			Assert.assertTrue(expHMap.equals(actualHMap));
			
			helper.logout();
	   }	   
	  	   
	   
	   
}
