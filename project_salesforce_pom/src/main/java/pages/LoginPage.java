package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import base.BaseClass;

public class LoginPage extends BaseClass {
	

	public LoginPage(WebDriver browser) {
		this.driver = browser;
	}

	public LoginPage enterUsername() {
		
		typeText(driver.findElement(By.id(prop.getProperty("LoginPage.username.id"))), prop.getProperty("username"));
		return this;

	}
	
	public LoginPage enterPassword() {
		
		typeText(driver.findElement(By.id(prop.getProperty("LoginPage.password.id"))), prop.getProperty("password"));
		return this;
	}

	public HomePage clickLogin() {
		
		clickElement((driver.findElement(By.id(prop.getProperty("LoginPage.loginButton.id")))));
		return new HomePage(driver);

	}
}
