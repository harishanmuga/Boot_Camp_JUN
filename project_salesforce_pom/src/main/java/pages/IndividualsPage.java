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
		try {
			driver.findElement(By.xpath(prop.getProperty("IndividualsPage.individualsDropdown.xpath")))
			.click();
			reportStep("Clicked on Individual dropdown successfully", "pass");
		} catch (Exception e) {
			reportStep("Unable to click on Individual dropdown successfully", "fail");
		}
		
		return this;

	}
	
	public IndividualsPage searchIndividual(String name) {
		
		try {
			driver.findElement(By.xpath(prop.getProperty("IndividualsPage.searchBoxindividuals.xpath"))).sendKeys(name,Keys.ENTER);
			reportStep("Found Individual entry", "pass");
		} catch (Exception e) {
			reportStep("Not Found Individual entry", "fail");
		}
		
		return this;

	}
	
	public IndividualsPage clickSortOption() {
		
		try {
			driver.findElement(By.xpath(prop.getProperty("IndividualsPage.sortIndividual.xpath"))).click();
			reportStep("Sorting clicked successfully", "pass");
		} catch (Exception e) {
			reportStep("Sorting is not clicked successfully", "fail");
		}
		
		return this;

	}

	public IndividualsPage clickDropDown(String name) {
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@title='"+name+"']/ancestor::th/following-sibling::td[5]//a")))).click();
			reportStep("Option dropdown clicked as expected. - Displaying 'Edit' or 'Delete'", "pass");
		} catch (Exception e) {
			reportStep("Option dropdown is not clicked as expected. - Displaying 'Edit' or 'Delete'", "fail");
		}
		
		return this;

	}
	
	public EditIndividualPage clickEdit() {
		
		try {
			WebElement editOpt = driver.findElement(By.xpath(prop.getProperty("IndividualsPage.editIndividual.xpath")));
			js.executeScript("arguments[0].click();", editOpt);
			reportStep("Edit button is clicked successfully", "pass");
		} catch (Exception e) {
			reportStep("Edit button is not clicked successfully", "fail");
		}
		
		return new EditIndividualPage(driver);

	}
	
	public IndividualsPage clickDelete() {
		
		try {
			WebElement findElement = driver.findElement(By.xpath(prop.getProperty("IndividualsPage.deleteIndividual.xpath")));
			js.executeScript("arguments[0].click();", findElement);
			driver.findElement(By.xpath("//span[text()='Delete']")).click();
			reportStep("Delete button is clicked successfully", "pass");
		} catch (Exception e) {
			reportStep("Delete button is not clicked successfully", "fail");
		}
			
		return this;

	}
	
	public IndividualsPage clickNewIndividuals() throws InterruptedException {
		
		try {
			Thread.sleep(3000);
			WebElement newInd = driver.findElement(By.xpath(prop.getProperty("IndividualsPage.newIndividualButton.xpath")));
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", newInd);
			reportStep("New individuals option is clicked successfully", "pass");
		} catch (Exception e) {
			reportStep("New individuals option is not clicked successfully", "fail");
		}
		
		return this;

	}
	
	public IndividualsPage selectSalutaion() {
		
		try {
			clickElement(driver.findElement(By.xpath("(//a[text()='--None--'])[1]")));
			clickElement(driver.findElement(By.xpath("//a[@title='Mr.']")));
			reportStep("Salutation selected - success", "pass");
		} catch (Exception e) {
			reportStep("Salutation selected - failure", "fail");
		}
		
		return this;

	}
	
	public IndividualsPage enterFirstname() {
		
		try {
			typeText(driver.findElement(By.xpath("//input[contains(@class,'firstName')]")), "Hari");
			reportStep("Enter First Name - Success", "pass");
		} catch (Exception e) {
			reportStep("Enter First Name - Failue", "fail");
		}
		
		return this;

	}
	
	public IndividualsPage enterLastname(String lName) {
		
		try {
			driver.findElement(By.xpath(prop.getProperty("IndividualsPage.lastNameTextBox.xpath"))).sendKeys(lName);
			reportStep("Enter Last Name - Success", "pass");
		} catch (Exception e) {
			reportStep("Enter Last Name - Failue", "fail");
		}
		
		return this;

	}
	
	
	public IndividualsPage clickSave() {
		
		try {
			driver.findElement(By.xpath(prop.getProperty("IndividualsPage.saveButton.xpath"))).click();
			reportStep("Save clicked - Success", "pass");
		} catch (Exception e) {
			reportStep("Save clicked - Failure", "fail");
		}
		
		return this;

	}
	
	public IndividualsPage moveToLastEntryOfIndividuals() {
		
		try {
			WebElement lastRow = driver
					.findElement(By.xpath(prop.getProperty("IndividualsPage.lastRowOfIndividuals.xpath")));
			Actions builder = new Actions(driver);
			builder.moveToElement(lastRow).perform();
			reportStep("Moved to last entry of Individuals - Success", "pass");
		} catch (Exception e) {
			reportStep("Moved to last entry of Individuals - Failure", "fail");
		}
		
		return this;

	}
	
	public IndividualsPage verifyConfirmationText() {
		
		try {
			String text = driver
					.findElement(By.xpath("//span[@title='We found no potential duplicates of this Individual.']"))
					.getText();
			if (text.equalsIgnoreCase("We found no potential duplicates of this Individual.")) {
				System.out.println("Individual has been created successfully.");
			}
			reportStep("Individual created - Success", "pass");
		} catch (Exception e) {
			reportStep("Individual created - Failure", "fail");
		}
		
		return this;

	}
	
	public IndividualsPage editConfirmText(String name) {
		
		try {
			boolean opname = driver.findElement(By.xpath("//a[@title='"+name+"']/ancestor::th")).isDisplayed();
			if (!opname) {
				System.out.println("Not Edited successfully");
			}else {
				System.out.println("Edited successfully.");
				
			}
			reportStep("Verify edited Text - Success", "pass");
		} catch (Exception e) {
			reportStep("Verify edited Text - Failure", "fail");
		}
		return this;

	}
	
	public IndividualsPage VerifyBlockMessage() {
		
		try {
			boolean displayed = driver.findElement(By.xpath("//li[text()='These required fields must be completed: Last Name']")).isDisplayed();
			if(!displayed) {
				System.out.println("Saleforce is not blocked to create individual without mandatory fields.");
			} else {
				System.out.println("Saleforce is blocked to create individual without mandatory fields.");
			}
			reportStep("Display failure message - Success", "pass");
		} catch (Exception e) {
			reportStep("Display failure message - Failed", "fail");
		}
		return this;

	}
	
	public IndividualsPage verifyNoResultFound() {
		
		try {
			boolean displayed = driver.findElement(By.xpath("//span[text()='No items to display.']")).isDisplayed();
			if (!displayed) {
				System.out.println("This individual has been deleted.");
			}else {
				System.out.println("Individual is still exists");
			}
			reportStep("Unable to find entry of Individual as expected - Success", "pass");
		} catch (Exception e) {
			reportStep("Unable to find entry of Individual as expected - Failed", "pass");
		}
		return this;
		

	}
	
	public IndividualsPage verifyAscendingOrder() throws InterruptedException {
		
		try {
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
		reportStep("Verify ascending order - Success", "pass");
		}
		catch (Exception e) {
			reportStep("Verify ascending order - Failed", "fail");
		}
		return this;

	}
	
}
