package testscripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;
//import org.junit.*;
//import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
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
import org.testng.annotations.Test;

public class AddContactMozilla {
	private static WebDriver driverMozilla;

	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeTest
	public static void suiteUp() {

		System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
		driverMozilla = new FirefoxDriver();
	}

	@BeforeMethod
	public static void base() throws Exception {

		driverMozilla.get(Parametrs.baseUrl + "/default.asp");
		driverMozilla.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAddContact() throws Exception {

		HelpMethods.insertNamePassword(Parametrs.emmail, Parametrs.password, driverMozilla);

		driverMozilla.findElement(Locators.buttonAddContact).click();
		driverMozilla.switchTo().frame("popup");

		String l = HelpMethods.AddContactSet(Parametrs.name, Parametrs.surname, driverMozilla);

		driverMozilla.get(Parametrs.baseUrl + "/main.asp");
		List<WebElement> web = driverMozilla.findElements(Locators.tableContact);
		System.out.println(web.size());
		String[] a = new String[web.size()];

		if (web.isEmpty()) {
			throw new Exception("was bound to fail!!!");
		} else

			for (Integer i = 0; i < (web.size() - 3); i++) {

				a[i] = driverMozilla.findElement(By.xpath(".//*[@id='R" + i + "']/td[5]")).getText();
				// System.out.println(a[i].toString());
				if (a[i].equals(l)) {
					driverMozilla.findElement(By.xpath(".//*[@id='R" + i + "']/td[5]")).click();
					Thread.sleep(3000);
					driverMozilla.switchTo().frame("popup");
					Thread.sleep(3000);

					assertEquals(Parametrs.name, driverMozilla.findElement(Locators.name).getAttribute("value"));
					assertEquals(Parametrs.surname, driverMozilla.findElement(Locators.surname).getAttribute("value"));
					driverMozilla.findElement(Locators.buttonQuitContact).click();
				}
			}

		driverMozilla.get(Parametrs.baseUrl + "/main.asp");
		for (Integer i = 0; i < web.size(); i++) {

			a[i] = driverMozilla.findElement(By.xpath(".//*[@id='R" + i + "']/td[5]")).getText();
			// System.out.println(a[i].toString());
			if (a[i].equals(l)) {
				driverMozilla.findElement(By.xpath(".//*[@id='R" + i + "']/td[5]")).click();
				Thread.sleep(3000);
				driverMozilla.switchTo().frame("popup");
				Thread.sleep(3000);
				driverMozilla.findElement(Locators.buttonDeleteContact).click();
			}
		}

		driverMozilla.get(Parametrs.baseUrl + "/main.asp");

	}

	@AfterMethod
	public static void tearDown() throws Exception {
		driverMozilla.findElement(Locators.buttonLogOut).click();

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);

		}
	}

	@AfterTest
	public static void suiteDown() {
		driverMozilla.quit();

	}

	private boolean isElementPresent(By by) {
		try {
			driverMozilla.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverMozilla.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverMozilla.switchTo().alert();
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
