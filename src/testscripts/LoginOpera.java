package testscripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
//import org.junit.*;
//import static org.junit.Assert.*;
import java.net.URL;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

public class LoginOpera {
	private static WebDriver driverOPera;

	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeSuite(alwaysRun = true)
	public static void suiteUp() {

	}

	@BeforeTest
	public static void testUp() throws MalformedURLException {
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\49.0.2725.39\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		driverOPera = new OperaDriver(operaOptions);

		// driver = new RemoteWebDriver (new
		// URL("http://localhost:4444/wd/hub"),operaOptions);

		// System.setProperty("webdriver.ie.driver", "d:\\IEDriverServer.exe");

	}

	@BeforeClass
	public static void classUp() {

	}

	@BeforeMethod
	public static void base() throws Exception {
		driverOPera.get(Parametrs.baseUrl + "/default.asp");
		driverOPera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(/* groups = { "Critical", "Login" }, priority = 1, */ dataProvider = "myData", dataProviderClass = DataData.class)
	public void testLogin(TestLoginParameter parameterObject) throws Exception {

		HelpMethods.insertNamePassword(parameterObject.login, parameterObject.passwword, driverOPera);

		// Refine.screenShot(Parametrs.folder, Parametrs.format, driverOPera);

		switch (parameterObject.result) {
		case "1": {
			Thread.sleep(3000);
			driverOPera.findElement(Locators.buttonSetup).click();
			driverOPera.switchTo().frame("popup");

			assertEquals(parameterObject.login, driverOPera.findElement(Locators.fieldEmail).getAttribute("value"));
			driverOPera.findElement(Locators.buttonQuit).click();
			driverOPera.switchTo().defaultContent();
		}
			break;
		case "2": {
			// if (result.equals("2")) {
			Thread.sleep(3000);
			Alert al = driverOPera.switchTo().alert();
			String s = al.getText();
			al.accept();
			driverOPera.switchTo().defaultContent();
			assertEquals(s, "Your password has been emailed to you.");
		}
			break;
		case "3":
		case "4": {
			Thread.sleep(3000);
			Alert al = driverOPera.switchTo().alert();
			String s = al.getText();
			al.accept();
			driverOPera.switchTo().defaultContent();
			assertEquals(s, "You are not registered with that Email address");
		}
			break;
		case "5":
		case "6": {
			Thread.sleep(3000);
			Alert al = driverOPera.switchTo().alert();
			String s = al.getText();
			al.accept();
			driverOPera.switchTo().defaultContent();
			assertEquals(s, "Your password has been emailed to you.");
		}
			break;
		case "7": {
			Thread.sleep(3000);
			Alert al = driverOPera.switchTo().alert();
			String s = al.getText();
			al.accept();
			
			driverOPera.switchTo().defaultContent();
			assertEquals(s, "Missing Security code, please try again...");
		}
			break;
		default: {
			Thread.sleep(3000);
			tearDown();
			Thread.sleep(3000);
		}
			break;
		}

	}

	@AfterMethod
	public static void tearDown() throws Exception {
		// вернет лист веб-элементов
		List<WebElement> web = driverOPera.findElements(Locators.buttonLogOut);
		if (web.isEmpty()) {
			try {
				Alert al = driverOPera.switchTo().alert();
				al.accept();

			} catch (NoAlertPresentException e) {
				// просто проверка того, что мы на главной. файнд элемент не ложит страницу
				driverOPera.findElement(Locators.buttonLogin);
			}

		} else
			web.get(0).click();

		// driver.close();

	}

	@AfterClass
	public static void classDown() {

	}

	@AfterTest
	public static void testDown() {
		driverOPera.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@AfterSuite(alwaysRun = true)
	public static void suiteDown() {

	}
}
