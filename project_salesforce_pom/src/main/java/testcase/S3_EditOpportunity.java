package testcase;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S3_EditOpportunity extends BaseClass {
	
	@BeforeTest
	public void setFileName() {
		
		excelFilename = "editOpportunity";

	}
	
	@Test(dataProvider = "sendData")
	public void editOpportunity(String app, String name, String date, String stage, String status, String desc) throws InterruptedException, IOException {
		
		new LoginPage(driver).enterUsername().enterPassword().clickLogin()
		.clickAppLauncher()
		.clickViewAll()
		.searchApplication(app)
		.clickOpportunityTab()
		.findOpportunityEntry(name)
		.clickEdit()
		.chooseCloseDate(date)
		.selectStage(stage)
		.selectDeliveryStatus(status)
		.enterDescription(desc)
		.clickSave()
		.getStageText(stage);
		
	}
}
