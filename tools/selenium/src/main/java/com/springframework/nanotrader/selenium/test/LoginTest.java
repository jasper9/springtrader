/**
 * 
 */
package com.springframework.nanotrader.selenium.test;

import org.openqa.selenium.WebDriver;
//import java.util.concurrent.TimeUnit;


import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;


/**
 * @author Ilayaperumal Gopinathan
 * 
 */
public class LoginTest extends TestBase {
	
	public LoginTest(String baseUrl, WebDriver driver){
		super(baseUrl, driver);
	}

	public void register(String username) {
		try {
			driver.get(baseUrl);
			// Click create new registration
			clickElementById(SHOW_REGISTRATION);
			// Fill up registration form
			typeTextById(REG_FULLNAME, "Test User");
			// driver.findElement(By.id(REG_FULLNAME)).sendKeys("Test User");
			typeTextById(REG_USERNAME, username);
			typeTextById(REG_EMAIL, "testuser@vmware.com");
			typeTextById(REG_PWD, "test");
			typeTextById(REG_PWD_CONFIRM, "test");
			typeTextById(REG_OPEN_BALANCE, "100000");
			typeTextById(REG_CC_NUM, "1234567896543456");
			typeTextById(REG_ADDR, "3401 Hillview Ave, Palo Alto CA 94304");
			// Submit the registration form
			clickElementById(REGISTER);
			// Logout
			// Explicit wait for 5 seconds
			logout();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void loginUser(String username, String passw) {
		try {
			// This was rewritten by Josh Gray <jgray@vmware.com> November 2014
			//    yes i know none of this is very ideal or elegant but it works. Deal with it.
			//
			System.out.println("******************************************************");
			System.out.println("Running Test: User Login & Logout");
			System.out.println("Loading URL xxxx");
			driver.get(baseUrl+"#login");
			
			// Take screenshot #1 - user should not be logged in	
			File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile1, new File("/depot/selenium/screenshot1.png"));
			
			//attempt to login with default credentials of existing user
			System.out.println("Attempting to login");
			typeTextById("username-input", username);
			typeTextById("password-input", passw);
			clickElementById("loginBtn");

			// look for the portfolio for the user
			// 	this was the first assert i tried but think i found a better way
			//WebElement myAccount = driver.findElement(By.id("nc-portfolio"));
			//assertTrue("FAILED: user login", myAccount.isDisplayed());
			//Thread.sleep(1000);
			
			// ASSERT - look for the user menu which implies logged in
			// 	the xpath is the user menu in the top right
			int myAccount3 = driver.findElements((By.xpath("//li[2]/a/span[2]"))).size();
                        boolean checkResult3;
                        if (myAccount3 > 0)
                        { System.out.println("User menu was FOUND [good]"); checkResult3 = true;}
                        else
                        { System.out.println("User menu was NOT FOUND [bad]"); checkResult3 = false;}
                        // this will assert if the menu is NOT found, meaning user still logged in
                        //      yes it is kind of backward logic......but it works....i think....
                        assertTrue("FAILED: Login failed - menu is not shown", checkResult3);

			
			// Take screenshot #2 - user should be logged in
                        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(scrFile2, new File("/depot/selenium/screenshot2.png"));


			// big hack - only way I was able to get the logout button to be visible
			System.out.println("Attempting to logout");
			Actions builder = new Actions(driver);
			builder.moveToElement(driver.findElement(By.xpath("//li[2]/a/span[2]"))).click().perform();
			driver.findElement(By.xpath("//a[@id='logout']")).click();

			// Take screenshot #3 - user should be logged out
			File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile3, new File("/depot/selenium/screenshot3.png"));

			// ACTION - logout	
			driver.get(baseUrl+"#login");
			//xpath = login drop down
           		int myAccount2 = driver.findElements((By.xpath("//li[2]/a/span[2]"))).size(); 
			boolean checkResult;
			if (myAccount2 > 0)
			{ System.out.println("User menu was FOUND [bad]"); checkResult = false;}
			else
			{ System.out.println("User mennu was NOT FOUND [good]"); checkResult = true;}
			// this will assert if the menu is found, meaning user still logged in
			// 	yes it is kind of backward logic......but it works....i think....
                        assertTrue("FAILED: Logout - user is still logged in", checkResult);



		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

       public void loginUser_Fast(String username, String passw) {
                try {
	
                        File scrFileLogin = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(scrFileLogin, new File("/depot/selenium/screenshot_loginFast.png"));

                       
                        System.out.println("Logging in as xxxx");
                        driver.get(baseUrl+"#login");

                        File scrFileLogin2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(scrFileLogin2, new File("/depot/selenium/screenshot_loginFast2.png"));

                        typeTextById("username-input", username);
                        typeTextById("password-input", passw);
                        clickElementById("loginBtn");





                }
                catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }
						


}
