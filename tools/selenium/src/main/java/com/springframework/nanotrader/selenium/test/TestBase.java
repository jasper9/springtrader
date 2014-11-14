/**
 * 
 */
package com.springframework.nanotrader.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.springframework.nanotrader.selenium.model.DashboardElement;
import com.springframework.nanotrader.selenium.model.LoginElement;
import com.springframework.nanotrader.selenium.model.PortfolioElement;
import com.springframework.nanotrader.selenium.model.TradeElement;
import com.springframework.nanotrader.selenium.model.UserElement;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ilayaperumal Gopinathan
 * 
 */
public class TestBase implements LoginElement, UserElement, TradeElement, PortfolioElement, DashboardElement {

	protected String baseUrl;
	
	protected WebDriver driver;

	public TestBase(String baseUrl, WebDriver driver){
		this.baseUrl = baseUrl;
		this.driver = driver;
	}

	public void login(String username, String password) {
		try {
			// Go to login page
			driver.get(baseUrl + "/#login");
			// Fill in username & password
			typeTextById(USERNAME, username);
			typeTextById(PASSWORD, password);
			// Submit Login
			clickElementById(LOGIN);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void logout(){
		System.out.println("*************************");
		System.out.println("In Logout");
		WebElement userNameNav = waitForElementById("nb-username");
		//WebElement userNameNav = waitForElementById("fat-menu");
		userNameNav.click();
		//clickElementById(LOGOUT);
		//
		//clickElementById("nb-username"); // not working
		//clickElementById("logout");
		//WebElement myAccount = driver.findElement(By.id("nc-portfolio"));
	}

	public void typeTextById(String idLocator, String value) {
		driver.findElement(By.id(idLocator)).sendKeys(value);
	}

	public void clickElementById(String idLocator) {
		driver.findElement(By.id(idLocator)).click();
	}

	public void clickElementByXpath(String xpathLocator) {
		driver.findElement(By.xpath(xpathLocator)).click();
	}

	public WebElement waitForElementById(final String idLocator) {
		return new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.id(idLocator));
			}
		});
	}

	public WebElement waitForElementByXpath(final String xpathLocator) {
		return new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath(xpathLocator));
			}
		});
	}


}
