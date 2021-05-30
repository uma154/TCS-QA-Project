package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.reusableFunctions.SeleniumUtilities;

public class Homepage {

	By searchbox = By.id("twotabsearchtextbox");
	By searchbutton = By.id("nav-search-submit-button");
	By signinframe = By.xpath("//div[@class='nav-right']//a[@id=\"nav-link-accountList\"]");
	By usernotsigned = By.xpath("//span[@id=\"nav-link-accountList-nav-line-1\"]");
	By mailid = By.id("ap_email");
	By passwordfield = By.id("ap_password");
	By continuebutton = By.id("continue");
	By signInSubmit = By.id("signInSubmit");
	
	String unsigneduser = "Hello, Sign in";
	SeleniumUtilities util;
	
	public Homepage(WebDriver driver, WebDriverWait wait) {
		util = new SeleniumUtilities(driver,wait);	
	}
	
	public void Login(String username, String password) {
	
		util.ElementClick(signinframe);		
		util.SetText(mailid, username);
		util.ElementClick(continuebutton);
		util.SetText(passwordfield, password);
		util.ElementClick(signInSubmit);
	}
	
	public void Login( String password) {
		
		util.SetText(passwordfield, password);
		util.ElementClick(signInSubmit);
	}
	
	public boolean IsLoggedin() {
		String signedinuser = util.GetEementText(usernotsigned);
		//user is not signed in, so call signin
		// test
		if (unsigneduser.equals(signedinuser)) {
			return false;
		}
		return true;
	}
	
	public void SearchProduct(String productname)
	{		
		util.SetText(searchbox,productname);
		util.ElementClick(searchbutton);
	}
	
	
}
