package com.reusableFunctions;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import com.baseClasses.Library;

public class SeleniumUtilities {

	WebDriver driver;
	WebDriverWait wait ;
	
	public SeleniumUtilities(WebDriver driver,WebDriverWait wait) {
		
		this.driver = driver;
		this.wait = wait;
	}
	
	public void to_take_screenshot(String fileName) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source,new File("./src/test/resources/ScreenShots/" +fileName +".png" ));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getTitle() {
		
		return driver.getTitle();
	
	}
	
	public String getUrl() {
		return driver.getCurrentUrl();
	}	
	
	public WebElement GetElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	public void SetSelectItem(WebElement element,String value) {		
		Select selectElement = new Select(element);
		selectElement.selectByValue(value);
	}
	
	public void ExplicitwaitforElementClicked(By locator) {
		wait.until(ExpectedConditions.attributeContains(locator, "checked", "1"));
	}
	
	public void SetText(By locator, String value) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(value);
		
	}
	
	public void ElementClick(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
		
	}
	
	public String GetEementText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public WebElement ExplicitWaitForElementVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement ExplicitWaitForElementPresence(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void ExplicitWaitForUrltochange(String url) {
		 wait.until(ExpectedConditions.urlToBe(url));
	}
	
	
	public void Explicitlywait() {
		wait=new WebDriverWait(driver,2);		
	}

}