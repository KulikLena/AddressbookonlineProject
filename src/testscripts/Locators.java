package testscripts;

import org.openqa.selenium.By;

public class Locators {
	public static By login = By.name("LEmail");
	public static By passsword = By.name("LPassword");
	public static By buttonLogin = By.name("Login");
	public static By fieldEmail = By.name("email");
	public static By buttonSetup = By.xpath("//img[@src='images/setup.gif']");
	public static By buttonQuit = By.cssSelector("img.style20");

	public static By buttonAddContact = By.xpath("//img[@onclick='n()']");

	public static By name = By.name("name");
	public static By surname = By.name("surname");
	public static By email = By.name("email1");
	public static By email1 = By.name("email2");
	public static By email2 = By.name("email3");
	public static By phone1 = By.name("phone1");
	public static By phone2 = By.name("phone2");
	public static By phone3 = By.name("phone3");
	public static By contactWWW = By.name("WWW");
	public static By category = By.name("Category");
	public static By xmascard = By.name("xmascard");
	public static By address = By.name("address");
	public static By notes = By.name("details");
	
	public static By contactDayOfBirth = By.name("Day");
	public static By contactMonthOfBirth = By.name("Month");
	public static By contactYearOfBirth = By.name("Year");
	
	
	public static By buttonSaveContact = By.cssSelector("input[type=\"submit\"]");

	public static By buttonSelectContact = By.cssSelector("tr[id=R0]>td:nth-child(2)");

	public static By buttonDeleteContact = By.name("B1");
	
	public static By fieldNotes = By.name("details");
	
	public static By buttonQuitContact = By.cssSelector("img.auto-style2");

	public static By buttonLogOut = By.xpath("//img[@onclick=\"window.open('logout.asp','_top');\"]");
	
	public static By textHaveNoContacts = By.cssSelector("span.s7");
	
	public static By tableContact = By.cssSelector("form[name=\"THEFORM\"]>table>tbody>tr");
	//tr[@id="R0"]/td[5]
	public static By tablenn = By.xpath("//tr//td[5]");
}
