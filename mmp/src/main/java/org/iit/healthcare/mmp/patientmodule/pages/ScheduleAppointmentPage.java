package org.iit.healthcare.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleAppointmentPage 

{
	WebDriver driver;	
	
	public ScheduleAppointmentPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public HashMap<String, String> bookAppointment(String drName, String time, String sName)
	   {
		   HashMap<String, String> appHMap = new HashMap<String, String>();
		  
		   driver.findElement(By.xpath("//h4[contains(text(),'"+drName+"')]/following::button[@id='opener'][1]")).click();
		   appHMap.put("doctor", drName);
		   
		   WebElement frame = driver.findElement(By.xpath("//iframe[@id='myframe']"));
		   driver.switchTo().frame(frame);
		   driver.findElement(By.xpath("//input[@id='datepicker']")).click();
			
			String dateofAppointment = Utility.selectFutureDate(20);
			
			
			String[] date = dateofAppointment.split("/");
			String appointmentDate = date[0];
			String appointmentMonth = date[1];			
			String appointmentYear = date[2];
			
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-datepicker-year']")));
			
			String calYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			
			while(!calYear.equals(appointmentYear))
			{
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
				calYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
				System.out.println(calYear);
			}
			
         String calMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			
			while(!calMonth.equals(appointmentMonth))
			{
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
				calMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
				System.out.println(calMonth);
			}
			
			driver.findElement(By.linkText(appointmentDate)).click();
			System.out.println(driver.findElement(By.id("datepicker")).getAttribute("value"));	
			appHMap.put("date", driver.findElement(By.id("datepicker")).getAttribute("value"));
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='time']")));		
			WebElement timeDropdown = driver.findElement(By.xpath("//select[@name='time']"));
			Select sel = new Select(timeDropdown);
			sel.selectByVisibleText(time);	
			appHMap.put("time", time);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='status']")));
					
			driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();
	   
	   
			driver.findElement(By.xpath("//textarea[@id='sym']")).sendKeys(sName);
			appHMap.put("sym", driver.findElement(By.xpath("//textarea[@id='sym']")).getAttribute("value"));
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			return appHMap ;
		}	 		
	   public HashMap<String, String> fetchPatientPortalDate()
	   {
		 HashMap<String, String> patHMap = new HashMap<String, String>();
		 patHMap.put("date", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText());
		 patHMap.put("time", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText());
		 patHMap.put("sym", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText());
		 patHMap.put("doctor", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText());
		 return patHMap;
		 
	   }	   

}
