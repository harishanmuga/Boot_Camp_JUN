package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseClass;

public class ClassicModeHomePage extends BaseClass {
	
	public ClassicModeHomePage(WebDriver browser) {

		this.driver = browser;
		
	}
	
	public NewEventCalenderPage clickCreateEvent() throws InterruptedException {
		
		Thread.sleep(3000);
		driver.findElement(By.id("createNewButton")).click();
		driver.findElement(By.xpath("//div[@id='createNewMenu']/a[@class='menuButtonMenuLink firstMenuItem eventMru']"))
				.click();
		return new NewEventCalenderPage(driver);

	}
	
	public ClassicModeHomePage verifyEventCreation() {
		
		List<WebElement> findElements = driver.findElements(By.xpath("//div[contains(@id,'hoverItem')]/div/a/span"));

		for (WebElement each : findElements) {
			String text = each.getText();
			if (text.equalsIgnoreCase("Call")) {
				System.out.println("Test case passed");
			}
		}
		return this;

	}

}
