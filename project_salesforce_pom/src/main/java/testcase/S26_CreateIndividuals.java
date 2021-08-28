package testcase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S26_CreateIndividuals extends BaseClass {
	
	@BeforeTest
	public void setFileName() {
		excelFilename = "createIndividuals";

	}
	
	@Test(dataProvider="sendData")
	public void createIndividuals(String lName) throws InterruptedException {
		
		new LoginPage(driver).enterUsername().enterPassword().clickLogin().clickAppLauncher().clickViewAll().searchApps().
		clickIndividualsDropdown().clickNewIndividuals().enterLastname(lName).clickSave().verifyConfirmationText();

	}

}
