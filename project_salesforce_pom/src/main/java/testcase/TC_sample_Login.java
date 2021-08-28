package testcase;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class TC_sample_Login extends BaseClass {
	
	@Test
	public void login() {
		
		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin();

	}

}
