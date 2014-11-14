/**
 * 
 */
package com.springframework.nanotrader.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.By;
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
public class TradeTest extends TestBase {
	
	public TradeTest(String baseUrl, WebDriver driver){
		super(baseUrl, driver);
	}
	

//	public WebElement getQuote(String symbol) {
        public void getQuote(String symbol) {

                System.out.println("******************************************************");
                System.out.println("Running Test: Stock Lookup");
                System.out.println("Loading URL xxxx");

		//waitForElementById(NAVBAR_TRADE);
		clickElementById(NAVBAR_TRADE);
		waitForElementById(TRD_QUOTE_INPUT);
		typeTextById(TRD_QUOTE_INPUT, symbol);
		clickElementById(TRD_QUOTE_SUBMIT);

		// THIS IS NOT WORKING NOW
		// return waitForElementById(TRD_QUANTITY);
		//
		// REWRITING WITH:
		
		
                // ACTION - Look for quote result
                //int myAccount2 = driver.findElements((By.xpath(".//*[@id='quote-error']"))).size();
		String myErrorMessage = driver.findElement((By.xpath(".//*[@id='quote-error']"))).getText();
                boolean checkResult;
                if (myErrorMessage.contains("The symbol you specified does not exist. Please try again."))
                { System.out.println("Stock was NOT FOUND [bad]"); checkResult = false;}
                else
                { System.out.println("Stock was FOUND [good]"); checkResult = true;}
                assertTrue("FAILED: Quote lookup failed", checkResult);
	}

	public void buyStock(String quantity) {
	    try {

                System.out.println("******************************************************");
                System.out.println("Running Test: Stock buy");
                System.out.println("Loading URL xxxx");


		//driver.get(baseUrl+"#trade");
		File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile4, new File("/depot/selenium/screenshot4.png"));


 		clickElementById(NAVBAR_TRADE);
                waitForElementById(TRD_QUOTE_INPUT);
	

		File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile5, new File("/depot/selenium/screenshot5.png"));

		typeTextById(TRD_QUOTE_INPUT, "VMW");
		clickElementById(TRD_QUOTE_SUBMIT);

		File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile6, new File("/depot/selenium/screenshot6.png"));

		typeTextById(TRD_QUANTITY, quantity);
		clickElementById(TRD_BUY_SUBMIT);
		waitForElementByXpath(BUY_ORDER_MODAL_MESSAGE);

                File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile7, new File("/depot/selenium/screenshot7.png"));

                String myErrorMessage = driver.findElement((By.xpath(".//*[@id='myModal']/div[2]"))).getText();
                boolean checkResult;
                if (myErrorMessage.contains("Your order is submitted for processing"))
                { System.out.println("order submitted [good]"); checkResult = true;}
                else
                { System.out.println("order failed [bad]"); checkResult = false;}
		//System.out.println("Text is " + myErrorMessage);
                assertTrue("FAILED: buy order failed", checkResult);

		// this test isnt actually a very good one since the assert won't fail as spring trader
		// 	always accepts an order even if there isn't enough money



		clickElementByXpath(BUY_ORDER_MODAL_OK);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

	public void sellStock() {
            try {

                System.out.println("******************************************************");
                System.out.println("Running Test 5: Stock sell");
                System.out.println("Loading URL xxxx");

		//waitForElementById(NAVBAR_PORTFOLIO);
		clickElementById(NAVBAR_PORTFOLIO);
		//waitForElementByXpath(SELL_FIRST_PORTFOLIO);
		
                File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile8, new File("/depot/selenium/screenshot8.png"));

		clickElementByXpath(SELL_FIRST_PORTFOLIO);

                File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile9, new File("/depot/selenium/screenshot9.png"));
		

		waitForElementById(SELL_ORDER_OK);
		//waitForElementByXpath(SELL_ORDER_CANCEL);
		//clickElementByXpath(SELL_ORDER_CANCEL);
		/*waitForElementById(NAVBAR_PORTFOLIO);
		waitForElementByXpath(SELL_FIRST_PORTFOLIO);
		clickElementByXpath(SELL_FIRST_PORTFOLIO);
		waitForElementByXpath(SELL_ORDER_OK);
		clickElementByXpath(SELL_ORDER_CANCEL);*/
		clickElementById(SELL_ORDER_OK);
                }
                catch (Exception e) {
                        throw new RuntimeException(e);
                }

	}

}
