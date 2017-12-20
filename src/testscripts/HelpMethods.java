package testscripts;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HelpMethods {
	
	public static void insertNamePassword(String email, String password, WebDriver dr) {

		dr.findElement(Locators.login).clear();
		dr.findElement(Locators.login).sendKeys(email);
		dr.findElement(Locators.passsword).clear();
		dr.findElement(Locators.passsword).sendKeys(password);
		dr.findElement(Locators.buttonLogin).click();
	}

	public static String AddContactSet(String contactName, String contactSurname, WebDriver drive) {
		drive.findElement(Locators.name).clear();
		drive.findElement(Locators.name).sendKeys(Parametrs.name);
		drive.findElement(Locators.surname).clear();
		drive.findElement(Locators.surname).sendKeys(Parametrs.surname);
		drive.findElement(Locators.email).clear();
		drive.findElement(Locators.email).sendKeys(Parametrs.contactEmail);
		drive.findElement(Locators.email1).clear();
		drive.findElement(Locators.email1).sendKeys(Parametrs.contactEmail1);
		drive.findElement(Locators.email2).clear();
		drive.findElement(Locators.email2).sendKeys(Parametrs.contactEmail2);
		drive.findElement(Locators.phone1).clear();
		drive.findElement(Locators.phone1).sendKeys(Parametrs.mainPhone);
		drive.findElement(Locators.phone2).clear();
		drive.findElement(Locators.phone2).sendKeys(Parametrs.altPhone);
		drive.findElement(Locators.contactWWW).clear();
		drive.findElement(Locators.contactWWW).sendKeys(Parametrs.contactHTTP);
		//drive.findElement(Locators.xmascard).clear();
		//drive.findElement(Locators.xmascard).submit();
		drive.findElement(Locators.address).clear();
		drive.findElement(Locators.address).sendKeys(Parametrs.address);
		//new Select(drive.findElement(Locators.contactDayOfBirth)).selectByVisibleText(Parametrs.contactBirthDay);
		//byIndex (c 0)
		//new Select(drive.findElement(Locators.contactMonthOfBirth)).selectByVisibleText(Parametrs.contactMonth);
		//new Select(drive.findElement(Locators.contactYearOfBirth)).selectByVisibleText(Parametrs.contactYear);
		drive.findElement(Locators.fieldNotes).clear();
		String k = Parametrs.mNotes();
		drive.findElement(Locators.fieldNotes).sendKeys(k);
		drive.findElement(Locators.buttonSaveContact).click();
		return k;
	}
	
}



