package com.springframework.nanotrader.selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import org.openqa.selenium.WebElement;

import com.springframework.nanotrader.selenium.test.LoginTest;
import com.springframework.nanotrader.selenium.test.SeleniumBase;
import com.springframework.nanotrader.selenium.test.TradeTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest extends SeleniumBase{
	
	private static String username; 
	
	private LoginTest loginTest = new LoginTest(baseUrl, driver);
	private TradeTest tradeTest = new TradeTest(baseUrl, driver);
	
	
	@BeforeClass
	public static void setImplicitTimeout(){
		username = "seleniumtestuser" + new Random().nextInt(1000);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
	
	@Test(timeout=30000)
	public void A_registerUser(){
		//System.out.println("*************************");
		//System.out.println("Registering new user");
		//loginTest.register(username);
		//System.out.println("*************************");
	}
	
	@Test(timeout=30000)	
	public void B_loginUser(){
		//System.out.println("*************************");
		//System.out.println("Logging In");
		//System.out.println("*************************");
		//loginTest.login(username, "test");
		loginTest.loginUser("admin", "admin");
		//loginTest.logout();
	}
	
	@Test(timeout=30000)
	public void C_getQuote(){
		loginTest.loginUser_Fast("admin", "admin"); // ok i'm an idiot and this should be better but meh
		tradeTest.getQuote("VMW");
		//tradeTest.logout();
	}
	
	@Test(timeout=30000)
	public void D_buyStock(){
		//loginTest.loginUser_Fast("admin", "admin");
		//tradeTest.login(username, "test");
		//WebElement quoteResult = tradeTest.getQuote("VMW");
		//if (quoteResult != null) {
			//System.out.println("Buying stock");
			//System.out.println("*************************");
		tradeTest.buyStock("5");
		//}
		//tradeTest.logout();
	}
	
	@Test(timeout=30000)
	public void E_sellStock(){
		//tradeTest.login(username, "test");
		//System.out.println("Selling stocks");
		//System.out.println("*************************");
		tradeTest.sellStock();
		//tradeTest.logout();
	}
	
	@AfterClass
	public static void quitDriver(){
		driver.quit();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SeleniumTest selenium = new SeleniumTest();
		try{
		setImplicitTimeout();
		selenium.A_registerUser();
		selenium.B_loginUser();
		selenium.C_getQuote();
		selenium.D_buyStock();
		selenium.E_sellStock();
		quitDriver();
		System.out.println("Tests completed Successfully");
		System.out.println("*************************");
		} catch (Exception e){
			System.out.println("*************************");
			System.out.println("Test failed with the below exception:");
			System.out.println("*************************");
			e.printStackTrace();
		}
		
	}
}

