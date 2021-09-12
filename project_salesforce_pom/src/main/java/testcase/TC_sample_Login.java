package testcase;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class TC_sample_Login extends BaseClass {
	
	@BeforeTest
	public void setFileName() {
		testName = "Login testcase";
		description = "Login with valid credentials.";
		author = "Hari";
		category = "Functional";

	}
	
	@Test
	public void login() throws IOException {
		
		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin();

	}

}
