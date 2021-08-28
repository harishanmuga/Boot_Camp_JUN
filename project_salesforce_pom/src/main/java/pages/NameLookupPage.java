package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BaseClass;

public class NameLookupPage extends BaseClass {
	
	public NameLookupPage(WebDriver browser) {
		
		this.driver = browser;
	}
	
	public NewEventCalenderPage searchName(String nameContact) throws InterruptedException {
		
		driver.switchTo().frame("searchFrame");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[text()='Search']/following-sibling::input[@id='lksrch']"))
				.sendKeys(nameContact);
		driver.findElement(By.xpath("//input[@name='go']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("resultsFrame");
		driver.findElement(By.xpath("//a[@class=' dataCell ']")).click();
		//switchToParentWindow();
		return new NewEventCalenderPage(driver);

	}

}
