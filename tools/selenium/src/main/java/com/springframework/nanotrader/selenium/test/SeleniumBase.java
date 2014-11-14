/**
 * 
 */
package com.springframework.nanotrader.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Ilayaperumal Gopinathan
 *
 */
public class SeleniumBase {
	
	public static String baseUrl = "http://localhost:8080/spring-nanotrader-web";

	public static WebDriver driver = new FirefoxDriver();
	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

}
