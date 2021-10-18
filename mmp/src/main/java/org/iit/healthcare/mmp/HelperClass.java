package org.iit.healthcare.mmp;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class HelperClass
{

    WebDriver driver;
	
	public HelperClass(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void launchApplicationURL(String url)	
	{
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}	
	
	 public String login(String uName, String pWord)
	   {
		  			
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(uName);
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pWord);
			driver.findElement(By.xpath("//input[@name='submit']")).click();
			String actual = driver.findElement(By.tagName("h3")).getText();
			return actual;
	   }
	
	public void navigateToModule(String mName)
	   {
		   driver.findElement(By.xpath("//span[contains(text(),'"+mName+"')]")).click();
		   driver.findElement(By.xpath("//input[@type='submit']")).click();
	   }
	
	public void logout()
	   {
			
			driver.findElement(By.xpath("//span[contains(text(),'  Logout ')]")).click();
	   }
	
	 
		
		
	 
}
