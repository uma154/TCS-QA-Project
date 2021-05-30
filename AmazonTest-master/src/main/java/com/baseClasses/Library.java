package com.baseClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Library
{
	
	public  WebDriver driver;
	public  WebDriverWait wait;
	public static Properties properties;
	public static Logger logger;


	public  void browserSetUp() {
	properties = new Properties();
	try {
		InputStream inputStream = new FileInputStream("./src/test/resources/ConfigurationProperties/Config.property");
		try {
				properties.load(inputStream);
		}
		catch (IOException e)
		{
				e.printStackTrace();
		}
	} 
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
	}

	logger = Logger.getLogger(Library.class);
	PropertyConfigurator.configure("./src/test/resources/Log4jProperties/Log4j.property");
	//PropertyConfigurator.configure("user.dir"+"log4j.properties");

	logger.info("*******Starting with Browser Set Up*********");
	String browser = properties.getProperty("browser");
	String url = properties.getProperty("url");
	System.out.println("url::::::"+url);
	System.out.println(browser);

	switch (browser.toLowerCase()) {
	
	case "chrome":
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("user-data-dir=D:/temp/");
	driver=new ChromeDriver();
	logger.info(String.format("Identified the browser as %s. Launching the browser", browser));
	break;
	case "firefox":
	WebDriverManager.firefoxdriver().setup();
	driver = new FirefoxDriver();
	logger.info(String.format("Identified the browser as %s. Launching the browser", browser));
	break;
	
	case "ie":
	WebDriverManager.iedriver().setup();
	driver = new InternetExplorerDriver();
	logger.info(String.format("Identified the browser as %s. Launching the browser", browser));
	break;
	case "headlessbrowser":
	WebDriverManager.chromedriver().setup();
	ChromeOptions headlessoptions=new ChromeOptions();
	headlessoptions.setHeadless(true);
	driver=new ChromeDriver(headlessoptions);
	
	default:		
		logger.info(String.format("Could not identify the browser as %s. Please specify correct browser", browser));
	break;
	}
	wait = new WebDriverWait(driver, 5);
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	logger.info("Launched the Amazon Application");
}	
	
	
	public WebDriver GetWebDriver() {
		return this.driver;
	}
	
	public WebDriverWait GetWebDriverWait() {
		return this.wait;
	}
		

	public  void tearDown() {
		driver.close();
		driver.quit();
		driver = null;
		wait = null;
		logger.info("Exiting the application and closing the browser");
	}

}