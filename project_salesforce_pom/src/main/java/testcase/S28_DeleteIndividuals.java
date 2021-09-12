package testcase;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S28_DeleteIndividuals extends BaseClass {
	
	@BeforeTest
	public void setFileName() {
		
		excelFilename = "deleteindividuals";

	}
	
	@Test(dataProvider="sendData")
	public void DeleteIndividuals(String name) throws IOException {
		
		new LoginPage(driver).enterUsername().enterPassword().clickLogin().clickAppLauncher()
		.clickViewAll()
		.searchApps()
		.searchIndividual(name)
		.clickDropDown(name)
		.clickDelete()
		.verifyNoResultFound();

	}

}
