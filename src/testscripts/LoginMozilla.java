package testscripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import org.junit.*;
//import static org.junit.Assert.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginMozilla {
	private static WebDriver driverM;
	private static WebDriverEventListener myevent;

	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeSuite(alwaysRun = true)
	public static void suiteUp() {

	}

	@BeforeTest
	public static void testUp() {
		System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
		myevent = new MyEventListener();
		driverM = new EventFiringWebDriver(new FirefoxDriver()).register(myevent);
		// driverM = new FirefoxDriver();

	}

	@BeforeClass
	public static void classUp() {
	}

	@BeforeMethod
	public static void base() throws Exception {
		driverM.get(Parametrs.baseUrl + "/default.asp");
		driverM.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(/* groups = { "Critical", "Login" }, priority = 1, */ dataProvider = "myData", dataProviderClass = DataData2.class)
	public void testLogin(TestLoginParameter parameterObject) throws Exception {
		HelpMethods.insertNamePassword(parameterObject.login, parameterObject.passwword, driverM);
		Refine.screenShot(Parametrs.folder, Parametrs.format, driverM);

		switch (parameterObject.result) {
		case "1": {
			// if (result.equals("1")) {
			// Thread.sleep(3000);
			driverM.findElement(Locators.buttonSetup).click();
			driverM.switchTo().frame("popup");
			// ERROR: Caught exception [ERROR: Unsupported command [selectFrame | id=popup |
			// ]]
			assertEquals(parameterObject.login, driverM.findElement(Locators.fieldEmail).getAttribute("value"));
			driverM.findElement(Locators.buttonQuit).click();
			driverM.switchTo().defaultContent();
		}
			break;
		case "2": {
			// if (result.equals("2")) {
			Thread.sleep(3000);
			Alert al = driverM.switchTo().alert();
			String s = al.getText();
			al.accept();
			driverM.switchTo().defaultContent();
			assertEquals(s, "Your password has been emailed to you.");
		}
			break;
		case "3":
		case "4": {
			// Thread.sleep(3000);
			Alert al = driverM.switchTo().alert();
			String s = al.getText();
			al.accept();
			driverM.switchTo().defaultContent();
			assertEquals(s, "Your password has been emailed to you.");
		}
			break;
		case "5":
		case "6": {
			Thread.sleep(3000);
			Alert al = driverM.switchTo().alert();
			String s = al.getText();
			al.accept();
			driverM.switchTo().defaultContent();
			assertEquals(s, "Your password has been emailed to you.");
		}
			break;
		case "7": {
			Thread.sleep(3000);
			Alert al = driverM.switchTo().alert();
			String s = al.getText();
			al.accept();
			driverM.switchTo().defaultContent();
			assertEquals(s, "Missing Security code, please try again...");
		}
			break;
		default: {
			//throw new Exception("Check your data!!!");
			System.out.println("Check your data");
			Thread.sleep(3000);
			tearDown();
			Thread.sleep(3000);
		}
			break;
		}

	}

	// сбросит на уровень вниз
	// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]

	@AfterMethod
	public static void tearDown() throws Exception {
		// вернет лист веб-элементов
		List<WebElement> web = driverM.findElements(Locators.buttonLogOut);
		if (web.isEmpty()) {
			try {
				Alert al = driverM.switchTo().alert();
				al.accept();

			} catch (NoAlertPresentException e) {
				// просто проверка того, что мы на главной. файнд элемент не ложит страницу
				driverM.findElement(Locators.buttonLogin);
			}

		} else
			web.get(0).click();

		// driver.close();

	}

	@AfterClass
	public static void classDown() {
		// System.out.println("ending class Login");
	}

	@AfterTest
	public static void testDown() {
		driverM.quit();
		// System.out.println("Ending critical path testing (test) in Login");
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@AfterSuite(alwaysRun = true)
	public static void suiteDown() {
		// driver.quit();
	}
}
