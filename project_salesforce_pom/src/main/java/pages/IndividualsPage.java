package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseClass;

public class IndividualsPage extends BaseClass {
	
	public IndividualsPage(WebDriver browser) {
		
		this.driver = browser;
		
	}

	public IndividualsPage clickIndividualsDropdown() {
		
		driver.findElement(By.xpath(prop.getProperty("IndividualsPage.individualsDropdown.xpath")))
		.click();
		return this;

	}
	
	public IndividualsPage searchIndividual(String name) {
		
		driver.findElement(By.xpath(prop.getProperty("IndividualsPage.searchBoxindividuals.xpath"))).sendKeys(name,Keys.ENTER);
		return this;

	}

	public IndividualsPage clickDropDown(String name) {
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@title='"+name+"']/ancestor::th/following-sibling::td[5]//a")))).click();
		return this;

	}
	
	public EditIndividualPage clickEdit() {
		
		WebElement editOpt = driver.findElement(By.xpath(prop.getProperty("IndividualsPage.editIndividual.xpath")));
		js.executeScript("arguments[0].click();", editOpt);
		return new EditIndividualPage(driver);

	}
	
	public IndividualsPage clickNewIndividuals() throws InterruptedException {
		
		Thread.sleep(3000);
		WebElement newInd = driver.findElement(By.xpath(prop.getProperty("IndividualsPage.newIndividualButton.xpath")));
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", newInd);
		return this;

	}
	
	public IndividualsPage selectSalutaion() {
		
		clickElement(driver.findElement(By.xpath("(//a[text()='--None--'])[1]")));
		clickElement(driver.findElement(By.xpath("//a[@title='Mr.']")));
		return this;

	}
	
	public IndividualsPage enterFirstname() {
		
		typeText(driver.findElement(By.xpath("//input[contains(@class,'firstName')]")), "Hari");
		return this;

	}
	
	public IndividualsPage enterLastname(String lName) {
		
		driver.findElement(By.xpath(prop.getProperty("IndividualsPage.lastNameTextBox.xpath"))).sendKeys(lName);
		return this;

	}
	
	public IndividualsPage clickSave() {
		
		driver.findElement(By.xpath(prop.getProperty("IndividualsPage.saveButton.xpath"))).click();
		return this;

	}
	
	public IndividualsPage verifyConfirmationText() {
		
		String text = driver
				.findElement(By.xpath("//span[@title='We found no potential duplicates of this Individual.']"))
				.getText();
		if (text.equalsIgnoreCase("We found no potential duplicates of this Individual.")) {
			System.out.println("Individual has been created successfully.");
		}
		return this;

	}
	
	public void editConfirmText(String name) {
		
		boolean opname = driver.findElement(By.xpath("//a[@title='"+name+"']/ancestor::th")).isDisplayed();
		if (!opname) {
			System.out.println("Not Edited successfully");
		}else {
			System.out.println("Edited successfully.");
			
		}

	}
	
	public void VerifyBlockMessage() {
		
		boolean displayed = driver.findElement(By.xpath("//li[text()='These required fields must be completed: Last Name']")).isDisplayed();
		if(!displayed) {
			System.out.println("Saleforce is not blocked to create individual without mandatory fields.");
		} else {
			System.out.println("Saleforce is blocked to create individual without mandatory fields.");
		}

	}
	
}
