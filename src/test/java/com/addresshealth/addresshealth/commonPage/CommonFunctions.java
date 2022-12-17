package com.addresshealth.addresshealth.commonPage;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.functions.ExpectedCondition;
import net.serenitybdd.core.pages.PageObject;

public class CommonFunctions extends PageObject{
	
	
	
	/********************************************************************
	 * Description:
	 * Param: Web element
	 * Returns: Boolean Visibility
	 * Status: Complete
	 ********************************************************************/ 

	
	public void waitSeconds(float seconds)
    {
        try {
            Thread.sleep((long) seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
	
	/********************************************************************
	 * Description:
	 * Param: Web element
	 * Returns: Boolean Visibility
	 * Status: Complete
	 ********************************************************************/
	
	public JavascriptExecutor getJavascriptExecutor() 
    {
        return (JavascriptExecutor) getDriver();           //pageobject.class
    }
	
	/********************************************************************
	 * Description: Waits for the element to appear
	 * Param: Web element
	 * Returns: Boolean Visibility
	 * Status: Complete
	 ********************************************************************/  
	
	public Boolean waitForElementToAppear(By ele) 
	{
		long end = System.currentTimeMillis() + 30000;
		WebElement element = null;
		boolean flag = true;
		
		while (System.currentTimeMillis() < end)
		{
			try 
			{
				//System.out.println("In Try block");
				element = element(ele);
				System.out.println(element(ele).isCurrentlyVisible());
				if (!element.isDisplayed()) 
				{
					this.wait(3000);
					System.out.println("Waiting for " + element + " to appear.");
				} 
				else 
				{
					//System.out.println("In else block");
					if (element.isDisplayed()) 
					{
						return true;
					}
				}
			} 
			catch (NoSuchElementException nse) 
			{
				if (flag)
				{
					this.waitSeconds(3000);
					System.out.println("No such element exception. Waiting for " + element + " to appear.");
					flag = false;
				}
			} 
			catch (Exception e) 
			{
				
			}
		
		}
		return false;
	}
		
		/********************************************************************
		 * Description: Waits for JavaScript readyState to be complete
		 * Param: Web element
		 * Returns: Boolean Visibility
		 * Status: In Progress
		 ********************************************************************/ 
		
		public void waitForPageToLoad() 
		{
			new WebDriverWait(getDriver(), 60).until((ExpectedCondition<Boolean>) wd ->
			((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		}
		
		
		
		
		/********************************************************************
		 * Description: Waits for element to disappear
		 * Param: Web element
		 * Returns: Boolean Visibility
		 * Status: Complete
		 ********************************************************************/ 	
		
		public void waitForElementToDisappear(By element)
		{
		System.out.println("Waiting for element to disappear");
		new WebDriverWait(getDriver(), 300).until(ExpectedConditions.invisibilityOfElementLocated(element));
		}
		
		/********************************************************************
		 * Description: Waits for element to load
		 * Param: Web element
		 * Returns: Boolean Visibility
		 * Status: Complete
		 ********************************************************************/ 	
		
		public void waitForElementToLoad(By element)
		{
		System.out.println("Waiting for element to load");
		new WebDriverWait(getDriver(), 180).until(ExpectedConditions.visibilityOfElementLocated(element));
		}
	
	
	
}
