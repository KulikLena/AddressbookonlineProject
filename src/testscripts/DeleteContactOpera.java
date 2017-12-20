package testscripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;
//import org.junit.*;
//import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteContactOpera {
	private static WebDriver driverOpera;

	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeTest 
	public static void suiteUp() {
		
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\49.0.2725.39\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		driverOpera = new OperaDriver(operaOptions);
	}
	
	@BeforeMethod
	public static void base() throws Exception {

		driverOpera.get(Parametrs.baseUrl + "/default.asp");
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testDeleteContact() throws Exception {

		HelpMethods.insertNamePassword(Parametrs.emmail, Parametrs.password, driverOpera);

		driverOpera.findElement(Locators.buttonAddContact).click();
		driverOpera.switchTo().frame("popup");
		
		HelpMethods.AddContactSet(Parametrs.name, Parametrs.surname, driverOpera);

		
		driverOpera.get(Parametrs.baseUrl + "/main.asp");
		driverOpera.findElement(Locators.buttonSelectContact).click();
		
		driverOpera.switchTo().frame("popup");
		
		driverOpera.findElement(Locators.buttonDeleteContact).click();
		
		driverOpera.get(Parametrs.baseUrl + "/main.asp");

		assertEquals("You currently have no contacts\n\n\nUse the   to add a contact !",
				driverOpera.findElement(Locators.textHaveNoContacts).getText());

	}

	@AfterMethod
	public static void tearDown() throws Exception {
		driverOpera.findElement(Locators.buttonLogOut).click();
		
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	@AfterClass
	public static void classDown() {
		
	}
	
	@AfterTest  
	public static void suiteDown() {
		driverOpera.quit();
		
	}
	
	private boolean isElementPresent(By by) {
		try {
			driverOpera.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverOpera.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverOpera.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}
