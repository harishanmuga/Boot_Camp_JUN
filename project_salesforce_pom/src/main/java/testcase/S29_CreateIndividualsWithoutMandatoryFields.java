package testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S29_CreateIndividualsWithoutMandatoryFields extends BaseClass {
	
	@Test
	public void CreateIndividualsWithoutMandatoryFields() throws InterruptedException {
		
		new LoginPage(driver).enterUsername().enterPassword()
		.clickLogin()
		.clickAppLauncher()
		.clickViewAll()
		.searchApps()
		.clickIndividualsDropdown()
		.clickNewIndividuals()
		.selectSalutaion()
		.enterFirstname()
		.clickSave()
		.VerifyBlockMessage();

	}

}
