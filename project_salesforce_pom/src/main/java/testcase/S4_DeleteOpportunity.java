package testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S4_DeleteOpportunity extends BaseClass {
	
	@BeforeTest
	public void setFileName() {
		excelFilename = "deleteOpportunity";
		
	}
	
	@Test(dataProvider="sendData")
	public void deleteOpportunity(String app,String name) throws InterruptedException, IOException {
		
		new LoginPage(driver).enterUsername().enterPassword().clickLogin()
		.clickAppLauncher().clickViewAll()
		.searchApplication(app)
		.clickOpportunityTab()
		.findOpportunityEntry(name)
		.clickOpportunityDropDown()
		.clickDelete()
		.confirmDelete()
		.confirmDeleteMessage();

	}
	
	
}
