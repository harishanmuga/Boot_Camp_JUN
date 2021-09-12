package testcase;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class S121_SalesForceClassicNewEvent extends BaseClass{
	
	@BeforeTest
	public void setFileName() {
		excelFilename = "classicmodetest";

	}

	@Test(dataProvider="sendData")
	public void SalesForceClassicNewEvent(String subject, String name, String fileName) throws InterruptedException, IOException {
		
		new LoginPage(driver).enterUsername()
		.enterPassword()
		.clickLogin()
		.clickToRecentUsed()
		.printAppDetails()
		.clickProfile()
		.navigateToClassicMode()
		.clickCreateEvent()
		.enterSubject(subject)
		.selectStartDate()
		.selectEndDate()
		.selectName()
		.searchName(name)
		.attachFile(fileName)
		.verifyFileUpload(fileName)
		.clickSave()
		.verifyEventCreation();
	}
}
