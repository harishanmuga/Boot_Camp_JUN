package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BaseClass;

public class EditIndividualPage extends BaseClass {
	
	public EditIndividualPage(WebDriver browser) {
		
		this.driver = browser;
	}

	public EditIndividualPage selectSalutation(String salutaion) {
		
		driver.findElement(By.xpath("(//a[text()='--None--'])[1]")).click();
		driver.findElement(By.xpath("//a[@title='"+salutaion+"']")).click();
		return this;
		
	}
	
	public EditIndividualPage enterFirstname(String fName) {
		
		driver.findElement(By.xpath("//input[contains(@class,'firstName')]")).sendKeys(fName);
		return this;

	}
	
	public IndividualsPage clickSave() {
		
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		return new IndividualsPage(driver);

	}
}
