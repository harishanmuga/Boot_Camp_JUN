package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseClass;

public class OpportunityPage extends BaseClass {

	public OpportunityPage(WebDriver browser) {

		this.driver = browser;
	}

	public OpportunityPage clickOpportunityDropDown() throws InterruptedException {
		
		Thread.sleep(3000);
		WebElement optLink = driver.findElement(By.xpath(prop.getProperty("HomePage.opportunityDropdown.xpath")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", optLink);
		return this;

	}

	public OpportunityPage clickOpportunityTab() {

		WebElement oppTab = driver.findElement(By.xpath(prop.getProperty("OpportunityPage.opportunityTab.xpath")));
		wait.until(ExpectedConditions.elementToBeClickable(oppTab));
		clickUsingJavascript(oppTab);
		return this;

	}

	public OpportunityPage navigateToNewOpportunityPage() {

		WebElement element = driver
				.findElement(By.xpath(prop.getProperty("HomePage.navigateNewOpportunityOption.xpath")));
		clickElement(element);
		return this;

	}

	public OpportunityPage findOpportunityEntry(String name) {

		wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(By.xpath("(//a[@title='" + name + "']/ancestor::th/following-sibling::td)[last()]//div"))))
				.click();
		return this;

	}

	public OpportunityPage enterOpportunityName(String name) {

		WebElement element = driver.findElement(By.xpath(prop.getProperty("OpportunityPage.opportunityName.xpath")));
		typeText(element, name);
		return this;

	}

	public OpportunityPage chooseCloseDate(String date) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("OpportunityPage.closeDate.xpath"))).click();
		driver.findElement(By.xpath("//span[text()='" + date + "']")).click();
		return this;

	}

	// need to analyse this xpath
	public OpportunityPage selectStage(String stage) {
		String title = driver.getTitle();
		if (title.contains("New")) {
			driver.findElement(By.xpath(prop.getProperty("OpportunityPage.stageDropdownNew.xpath"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='" + stage + "'])[last()]")))
			.click();
		}
		else if (title.contains("Edit")) {
			driver.findElement(By.xpath(prop.getProperty("OpportunityPage.stageDropdownEdit.xpath"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='" + stage + "'])[last()]")))
			.click();
		} 
		return this;

	}

	public OpportunityPage getStageText(String stage) throws InterruptedException {

		Thread.sleep(2000);
		String text = driver
				.findElement(By.xpath("//a[@title='" + stage + "']/ancestor::th/following-sibling::td[3]/span/span"))
				.getText();
		verifyText(stage, text);
		return this;

	}

	public OpportunityPage selectDeliveryStatus(String status) throws InterruptedException {

		WebElement deliverystatus = driver
				.findElement(By.xpath(prop.getProperty("OpportunityPage.deliveryStatus.xpath")));
		scrolldownUsingJavascript(deliverystatus);
		deliverystatus.click();
		Thread.sleep(5000);
		clickElement(driver.findElement(By.xpath(
				"//label[text()='Delivery/Installation Status']/parent::lightning-combobox/div//div/div[2]/lightning-base-combobox-item[@data-value='"
						+ status + "']")));
		return this;

	}

	public OpportunityPage enterDescription(String desc) {

		WebElement element = driver.findElement(By.xpath(prop.getProperty("OpportunityPage.description.xpath")));
		typeText(element, desc);
		return this;

	}

	public OpportunityPage clickEdit() {

		WebElement editOpt = driver.findElement(By.xpath(prop.getProperty("OpportunityPage.editButton.xpath")));
		clickUsingJavascript(editOpt);
		return this;

	}
	
	public OpportunityPage clickDelete() {
		
		WebElement deleteOpt = driver.findElement(By.xpath("//div[text()='Delete']"));
		wait.until(ExpectedConditions.elementToBeClickable(deleteOpt));
		clickUsingJavascript(deleteOpt);
		return this;

	}
	
	public OpportunityPage confirmDelete() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Delete']"))).click();
		//driver.findElement(By.xpath("//span[text()='Delete']")).click();
		return this;

	}

	public OpportunityPage clickSave() {

		clickElement(driver.findElement(By.xpath(prop.getProperty("OpportunityPage.saveButton.xpath"))));
		return this;

	}

	public OpportunityPage confirmNewOpportunityCreated(String name) {

		String text = driver.findElement(By.xpath(prop.getProperty("OpportunityPage.confirmOpportunityCreated.xpath")))
				.getText();
		if (text.equalsIgnoreCase(name)) {
			System.out.println("Opportunity has been created successfully");
		} else {
			System.out.println("Opportunity has not been created successfully");
		}
		return this;

	}

	public void confirmDeleteMessage() {
		
		boolean displayed = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).isDisplayed();
		if (!displayed) {
			System.out.println("Deleted successfully");
		}else {
			System.out.println("Not deleted");
		}
		

	}
	
	// display error message when unabale to create new opportunity
	public void displayErrorMessage() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(5000);
		WebElement errorTitle = driver.findElement(By.xpath("//h2[text()='We hit a snag.']"));
		System.out.println(errorTitle.getText());
		List<WebElement> fieldLevelErrors = driver.findElements(By.xpath("//div[@class='fieldLevelErrors']/ul/li"));
		for (WebElement webElement : fieldLevelErrors) {
			System.out.println(webElement.getText());
		}

	}
}
