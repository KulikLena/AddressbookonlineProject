package testscripts;

public class TestLoginParameter {
	public String login="";
	public String passwword="";
	public String result="";

	public TestLoginParameter(String str) {
		String[] st = str.split(",");
		switch (st[0]) {
		case "1": {
			this.result = st[1];
			this.login = st[2];
			this.passwword = st[3];
		} break;
		case "2": {
			this.result = st[1];
			this.login = st[2];
			this.passwword="";
		} break;
		case "3": {
			this.result = st[1];
			this.passwword = st[2];
			this.login="";
		} break;
		case "4": {
			this.result = st[1];
			this.login="";
			this.passwword="";
		}break;
		default: {
			this.login=Parametrs.emmail;
			this.passwword= Parametrs.password;
			
		}
		}

	}
}