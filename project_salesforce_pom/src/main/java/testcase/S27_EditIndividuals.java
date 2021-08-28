package testcase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S27_EditIndividuals extends BaseClass {
	
	@BeforeTest
	public void setFileName() {
		excelFilename = "editIndividuals";

	}
	
	@Test(dataProvider="sendData")
	public void editIndividuals(String name, String slt, String fName,String fullName) {
		
		new LoginPage(driver).enterUsername().enterPassword().clickLogin().clickAppLauncher()
		.clickViewAll()
		.searchApps()
		.searchIndividual(name)
		.clickDropDown(name)
		.clickEdit()
		.selectSalutation(slt)
		.enterFirstname(fName)
		.clickSave()
		.editConfirmText(fullName);

	}

}
