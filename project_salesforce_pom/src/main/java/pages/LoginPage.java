package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import base.BaseClass;

public class LoginPage extends BaseClass {
	

	public LoginPage(WebDriver browser) {
		this.driver = browser;
	}

	public LoginPage enterUsername() throws IOException {
		
		try {
			typeText(driver.findElement(By.id(prop.getProperty("LoginPage.username.id"))), prop.getProperty("username"));
			reportStep("Username is entered successfully", "pass");
		} catch (Exception e) {
			WebElement findElement = driver.findElement(By.id("username"));
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", findElement);
			long takeSnap = takeSnap();
			reportStepWithSnap("Username is not entered successfully", "fail",takeSnap);
		}
		
		return this;

	}
	
	public LoginPage enterPassword() throws IOException {
		
		try {
			typeText(driver.findElement(By.id(prop.getProperty("LoginPage.password.id"))), prop.getProperty("password"));
			long takeSnap = takeSnap();
			reportStepWithSnap("Password is entered successfully", "pass",takeSnap);
			
		} catch (Exception e) {
			long takeSnap = takeSnap();
			reportStepWithSnap("Password is not entered successfully", "pass",takeSnap);
		}
		
		return this;
	}

	public HomePage clickLogin() throws IOException {
		
		try {
			clickElement((driver.findElement(By.id(prop.getProperty("LoginPage.loginButton.id")))));
			long takeSnap = takeSnap();
			reportStepWithSnap("Login button is clicked successfully", "pass", takeSnap);
			//reportStep("Login button is clicked successfully", "pass");
		} catch (Exception e) {
			long takeSnap = takeSnap();
			reportStepWithSnap("Login button is not clicked successfully", "pass", takeSnap);
		}
		
		return new HomePage(driver);

	}
}
