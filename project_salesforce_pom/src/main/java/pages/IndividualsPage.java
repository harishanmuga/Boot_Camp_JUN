package pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	public IndividualsPage clickSortOption() {
		
		driver.findElement(By.xpath(prop.getProperty("IndividualsPage.sortIndividual.xpath"))).click();
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
	
	public IndividualsPage clickDelete() {
		
		WebElement findElement = driver.findElement(By.xpath(prop.getProperty("IndividualsPage.deleteIndividual.xpath")));
		js.executeScript("arguments[0].click();", findElement);
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		return this;

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
	
	public IndividualsPage moveToLastEntryOfIndividuals() {
		
		WebElement lastRow = driver
				.findElement(By.xpath(prop.getProperty("IndividualsPage.lastRowOfIndividuals.xpath")));
		Actions builder = new Actions(driver);
		builder.moveToElement(lastRow).perform();
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
	
	public void verifyNoResultFound() {
		
		boolean displayed = driver.findElement(By.xpath("//span[text()='No items to display.']")).isDisplayed();
		if (!displayed) {
			System.out.println("This individual has been deleted.");
		}else {
			System.out.println("Individual is still exists");
		}

	}
	
	public IndividualsPage verifyAscendingOrder() throws InterruptedException {
		
		Thread.sleep(2000);
		List<WebElement> findElements = driver
				.findElements(By.xpath(prop.getProperty("IndividualsPage.listOfIndividuals.xpath")));

		String[] nameArray = new String[findElements.size()];
		Thread.sleep(10000);
		for (int i = 0; i < findElements.size(); i++) {
			String text = findElements.get(i).getAttribute("title");
			nameArray[i] = text;
		}

		for (String string : nameArray) {
			System.out.println(string);
		}

		String[] dupNameArray = nameArray;
		System.out.println("dup array length" + dupNameArray.length);
		Arrays.sort(dupNameArray);
		int count = 0;
		for (int i = 0; i < nameArray.length; i++) {
			if (nameArray[i] != dupNameArray[i]) {
				count++;
			}
		}
		if (count > 0) {
			System.out.println("Entries have not been sorted in ascending order as expected.");
		} else {
			System.out.println("Entries have been sorted in ascending order as expected.");
		}
		return this;

	}
	
}
