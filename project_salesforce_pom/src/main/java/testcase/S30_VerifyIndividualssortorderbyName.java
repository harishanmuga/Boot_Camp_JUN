package testcase;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S30_VerifyIndividualssortorderbyName extends BaseClass {

	@Test
	public void verifyIndividualssortorderbyName() throws InterruptedException {
		
		new LoginPage(driver).enterUsername().enterPassword().clickLogin()
		.clickAppLauncher()
		.clickViewAll()
		.searchApps()
		.clickSortOption()
		.verifyAscendingOrder();
	}
	
}
