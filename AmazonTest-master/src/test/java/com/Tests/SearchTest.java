package com.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseClasses.Library;
import com.pages.Homepage;
import com.pages.ProductPage;
import com.pages.Searchlistpage;


public class SearchTest extends Library {
	Homepage homepage ;
	Searchlistpage searchpage;
	ProductPage product;
	String firstItemPrice;
	String bookname;
	
	@BeforeClass
	public void launchApp() {
		browserSetUp();
		homepage = new Homepage(GetWebDriver(),GetWebDriverWait());
		searchpage = new Searchlistpage(GetWebDriver(),GetWebDriverWait());
		product = new ProductPage(GetWebDriver(),GetWebDriverWait());
		firstItemPrice = properties.getProperty("firstitemprice");
		
	}
	
	@Test(priority=1,description="Click on first item and verify price")
	  public void VerifyProductPrice() {
		boolean isloggedin = homepage.IsLoggedin();
		if (isloggedin==false)
		{
			homepage.Login(properties.getProperty("username"), properties.getProperty("password"));
		}
		homepage.SearchProduct("qa testing for beginners");
		searchpage.ClickFirstItem();		
		String price= product.GetPrice();
		Assert.assertEquals(price, firstItemPrice);
	  }

	
	@Test(priority=2,description="Add item to cart and verify price")
	public void VerifyAddItemtoCart()
	{
			product.GetProductName();
			product.AddItemtoCart();
			String price = product.GetCartTotalPrice();
			Assert.assertEquals(price, firstItemPrice);
	}
	
	@Test(priority=3,description="Proceed to checkout and verify price")
	public void VerifyProceedtoCheckout()
	{
		String url = product.ProceedtoCheckout();
		if (url.contains("signin?"))
		{
			homepage.Login(properties.getProperty("password"));
		}
		String price = product.GetCheckoutPrice();
		Assert.assertEquals(price, firstItemPrice);
		//clear data
		product.Removeitemfromcart();
	}
	
	@AfterClass
	public void QuitBrowser()
	{
		tearDown();
	}
	
}
