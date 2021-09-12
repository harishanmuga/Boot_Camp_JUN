package testcase;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S2_CreateOpportunity extends BaseClass {
	
	@BeforeTest
	public void setFileName() {
		
		excelFilename = "createOpportunity";

	}
	
	@Test(dataProvider = "sendData")
	public void createOpportunity(String app, String name, String date, String stage) throws InterruptedException, IOException {
		
		new LoginPage(driver).enterUsername().enterPassword().clickLogin()
		.clickAppLauncher()
		.clickViewAll()
		.searchApplication(app)
		.clickOpportunityDropDown()
		.navigateToNewOpportunityPage()
		.enterOpportunityName(name)
		.chooseCloseDate(date)
		.selectStage(stage)
		.clickSave()
		.confirmNewOpportunityCreated(name);
		

	}

}
