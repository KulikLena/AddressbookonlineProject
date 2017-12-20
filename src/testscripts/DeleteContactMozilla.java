package testscripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import org.junit.*;
//import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteContactMozilla {
	private static WebDriver driverM;

	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeTest(groups = { "Critical", "Login" })
	public static void suiteUp() {

		System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
		driverM = new FirefoxDriver();
	}

	@BeforeClass
	public static void classUp() {

	}

	@BeforeMethod
	public static void base() throws Exception {

		driverM.get(Parametrs.baseUrl + "/default.asp");
		driverM.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(groups = { "Critical", "AddDelete" }, priority = 3)
	public void testDeleteContact() throws Exception {

		HelpMethods.insertNamePassword(Parametrs.emmail, Parametrs.password, driverM);

		driverM.findElement(Locators.buttonAddContact).click();
		driverM.switchTo().frame("popup");

		String l = HelpMethods.AddContactSet(Parametrs.name, Parametrs.surname, driverM);

		driverM.get(Parametrs.baseUrl + "/main.asp");

		List<WebElement> web = driverM.findElements(Locators.tableContact);
		System.out.println(web.size());
		String[] a = new String[web.size()];

		if (web.isEmpty())
			tearDown();
		else

			for (Integer i = 0; i < web.size(); i++) {

				a[i] = driverM.findElement(By.xpath(".//*[@id='R" + i + "']/td[5]")).getText();
				// System.out.println(a[i].toString());
				if (a[i].equals(l)) {
					driverM.findElement(By.xpath(".//*[@id='R" + i + "']/td[5]")).click();
					Thread.sleep(3000);
					driverM.switchTo().frame("popup");

					driverM.findElement(Locators.buttonDeleteContact).click();

					break;
				}
			}
		driverM.get(Parametrs.baseUrl + "/main.asp");
		Thread.sleep(3000);

		List<WebElement> web1 = driverM.findElements(Locators.tableContact);
		System.out.println(web1.size());
		String[] a1 = new String[web1.size()];
		if (web1.isEmpty())
			tearDown();
		else {
			for (int j = 0; j < (web1.size() - 3); j++) {
				a1[j] = driverM.findElement(By.xpath(".//*[@id='R" + j + "']/td[5]")).getText();
				if (a1[j].equals(l)) {
					throw new Exception("was bound to fail!!!");
				}
			}

		}
	}

	@AfterMethod
	public static void tearDown() throws Exception {
		driverM.findElement(Locators.buttonLogOut).click();

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@AfterTest(groups = { "Critical", "Login" })
	public static void suiteDown() {
		driverM.quit();

	}

	private boolean isElementPresent(By by) {
		try {
			driverM.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverM.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverM.switchTo().alert();
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
