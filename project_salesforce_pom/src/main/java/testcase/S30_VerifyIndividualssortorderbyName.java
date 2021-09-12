package testcase;

import java.io.IOException;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S30_VerifyIndividualssortorderbyName extends BaseClass {

	@Test
	public void verifyIndividualssortorderbyName() throws InterruptedException, IOException {
		
		new LoginPage(driver).enterUsername().enterPassword().clickLogin()
		.clickAppLauncher()
		.clickViewAll()
		.searchApps()
		.clickSortOption()
		.verifyAscendingOrder();
	}
	
}
