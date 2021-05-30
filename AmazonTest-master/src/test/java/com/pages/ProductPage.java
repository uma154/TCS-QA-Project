package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.reusableFunctions.SeleniumUtilities;

public class ProductPage {

	By price = By.xpath("//li[@id=\"mediaTab_heading_1\"]//span[@class=\"a-size-base mediaTab_subtitle\"]");
	
	By addItem = By.id("add-to-cart-button");
	By proceedtocheckout = By.xpath("//a[@id=\"hlb-ptc-btn-native\"]");
	By cardtotalprice = By.xpath("//span[@class=\"a-color-price hlb-price a-inline-block a-text-bold\"]");

	By checkoutItemprice = By.xpath("./ parent :: div /following-sibling::div[2]");
	By select = By.xpath("./ parent :: div /following-sibling::div[3]//select");
	By title = By.id("title");

	String productname;
	String titleexpression = "//span[contains(text(),'Items shipped from')]/parent :: div / following-sibling :: div //span[contains(text(),\"";

	
	SeleniumUtilities util;
	
	public ProductPage(WebDriver driver, WebDriverWait wait) {
		util = new SeleniumUtilities(driver,wait);	
	}
	
	public String GetPrice()
	{
		return util.GetElement(price).getText();
	}
	
	public String GetCartTotalPrice()
	{
		return util.GetElement(cardtotalprice).getText();
	}
	
	public String GetProductName()
	{
		productname = util.GetElement(title).getText();
		return productname;
	}
	
	public void AddItemtoCart()
	{
		util.ElementClick(addItem);
	}
	
	
	
	public String ProceedtoCheckout()
	{
		util.ElementClick(proceedtocheckout);
		return util.getUrl();

	}
	
	public String GetCheckoutPrice()
	{
		titleexpression = titleexpression + productname  +"\")]";				
		return util.GetElement(By.xpath(titleexpression)).findElement(checkoutItemprice).getText();
	}
	
	
	
	public void Removeitemfromcart()
	{					
		util.SetSelectItem(util.GetElement(By.xpath(titleexpression)).findElement(select),"Delete");
		
	}
}
