package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.reusableFunctions.SeleniumUtilities;

public class Searchlistpage {
	SeleniumUtilities util;
	
//	By firstList = By.xpath("//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]/div");
//	By wholeprice = By.xpath("//span[@class='a-price-whole']");
//	By fraction = By.xpath("//span[@class='a-price-fraction']");
	By firstListAnchor = By.xpath("//div[@data-component-type=\"s-search-result\"]//a");
	By bookname = By.xpath("//div[@data-component-type=\"s-search-result\"]//a/span");
	

	public Searchlistpage(WebDriver driver, WebDriverWait wait) {
		util = new SeleniumUtilities(driver,wait);	
	}
	
	public void ClickFirstItem()
	{
		util.ElementClick(firstListAnchor);
		

		
	}
	
	

}
