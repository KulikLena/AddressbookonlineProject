package testscripts;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Parametrs {
	public static String baseUrl =  "https://www.addressbookonline.com/";
	public static String emmail= "narybalke0@gmail.com";
	public static String password= "totalitarism";
	
	
	public static String name = "Aliaksei";
	public static String surname = "Borovik";
	public static String contactEmail = "bk1339@mail.ru";
	public static String contactEmail1 = "bk1339@mail.ru";
	public static String contactEmail2 = "bk1339@mail.ru";
	public static String mainPhone = "+375 44 676 22 22";
	public static String mobilePhone = "+375 44 676 22 22";
	public static String altPhone = "+375 44 676 22 22";
	public static String contactHTTP = "www.treasure.com";
	public static String category = "1";
	public static String address = "Minsk, Melnikaite";
	public static String contactBirthDay = "19";
	public static String contactMonth = "Jul";
	public static String contactYear = "1981";
	public static String mNotes () {
		GregorianCalendar calen = new GregorianCalendar();
		Date d= calen.getTime();
		String s = d.toString();
		return s;
	}
	
	public static String folder = "d:\\report\\";
	public static String format = ".png";
	
	/*public static String randomNotes () {
		Random r = new Random(100);
		Integer d= r.nextInt(); 
		//(int) Math.random()*1000;
		String s = d.toString();
		return s;
	}*/
	
	
}
