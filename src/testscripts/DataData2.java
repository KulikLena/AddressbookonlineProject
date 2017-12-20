package testscripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataData2 {

	@DataProvider
	public static Iterator<Object[]> myData() {
		List<Object[]> list = new ArrayList<Object[]>();
		File file = new File("ImportantData.txt");
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(file));
			String s = br.readLine();
			while (s != null) {
				String[] str = s.split(",");
				s = br.readLine();
				list.add(new Object[] { str[0], str[1], str[2] });
				// a = Integer.valueOf(str[2]);

			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return list.iterator();
	}
}
