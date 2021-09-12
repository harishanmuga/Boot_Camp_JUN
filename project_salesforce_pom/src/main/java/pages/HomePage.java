package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class HomePage extends BaseClass {

	public HomePage(WebDriver browser) {

		this.driver = browser;

	}

	public HomePage clickAppLauncher() {

		try {
			driver.findElement((By.xpath(prop.getProperty("HomePage.appLauncher.xpath")))).click();
			reportStep("App launcher clicked successfully", "pass");
		} catch (Exception e) {
			reportStep("App launcher is not clicked successfully", "fail");
		}

		return this;

	}

	public HomePage clickViewAll() {
		// wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElement(By.xpath(prop.getProperty("HomePage.viewAllButton.xpath"))))).click();
			reportStep("View All button clicked successfully", "pass");
		} catch (Exception e) {
			reportStep("View All button is not clicked successfully", "fail");
		}

		return this;

	}

	public IndividualsPage searchApps() {

		try {
			driver.findElement(By.xpath(prop.getProperty("HomePage.searchBox.xpath"))).sendKeys("indivi");
			WebElement individual = driver.findElement(By.xpath("//a[@class='al-tab-item']/span"));
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", individual);
			reportStep("Found the application", "pass");
		} catch (Exception e) {
			reportStep("Not Found the application", "fail");
		}

		return new IndividualsPage(driver);

	}

	public OpportunityPage searchApplication(String app) {

		try {
			driver.findElement(By.xpath("//label[text()='Search apps or items...']/parent::lightning-input//input"))
					.sendKeys(app);
			// list of search results
			List<WebElement> results = driver.findElements(By.xpath(
					"//div[@class='slds-grid slds-grid_pull-padded slds-wrap app-dnd-identifier']/one-app-launcher-app-tile"));
			// select sales from search results
			for (WebElement each : results) {
				String text = each.getAttribute("data-name");
				System.out.println(text);
				if (text.equalsIgnoreCase("sales")) {
					driver.findElement(By.xpath("//one-app-launcher-app-tile[@data-name='" + text + "']")).click();
				}
			}
			reportStep("Found the application", "pass");
		} catch (Exception e) {
			reportStep("Not Found the application", "fail");
		}

		return new OpportunityPage(driver);

	}

	public OpportunityPage navigateToNewOpportunityPage() {

		try {
			WebElement element = driver
					.findElement(By.xpath(prop.getProperty("HomePage.navigateNewOpportunityOption.xpath")));
			clickElement(element);
			reportStep("Navigated to New Opportunity task successfully", "pass");
		} catch (Exception e) {
			reportStep("Navigated to New Opportunity task not successfully", "fail");
		}
		
		return new OpportunityPage(driver);

	}

	public HomePage clickToRecentUsed() {

		try {
			driver.findElement(By.xpath("//a[text()='Community']")).click();
			reportStep("Clicked recently used tabs", "pass");
		} catch (Exception e) {
			reportStep("Not Clicked recently used tabs", "fail");
		}
		
		return this;

	}

	public HomePage printAppDetails() {

		try {
			List<WebElement> appName = driver
					.findElements(By.xpath("//div[text()='Lightning']/ancestor::td/preceding-sibling::th/span/span"));
			List<WebElement> devName = driver
					.findElements(By.xpath("//div[text()='Lightning']/ancestor::td/preceding-sibling::td[2]"));

			System.out.println(appName.size() + "  " + devName.size());

			for (int i = 0; i < appName.size(); i++) {
				String text = appName.get(i).getText();
				String text2 = devName.get(i).getText();
				System.out.println("Application Name: " + text + " | Developer Name: " + text2);
			}
			reportStep("Application details are displyed successfully", "pass");
		} catch (Exception e) {
			reportStep("Application details are not displyed successfully", "fail");
		}
		
		return this;

	}

	public HomePage clickProfile() {
		
		try {
			driver.findElement(By.xpath(prop.getProperty("HomePage.profile.xpath"))).click();
			reportStep("Clicked Profiles button successfully", "pass");
		} catch (Exception e) {
			reportStep("Unable to click Profiles button successfully", "fail");
		}
		
		return this;

	}

	public ClassicModeHomePage navigateToClassicMode() {

		try {
			driver.findElement(By.xpath(prop.getProperty("HomePage.switchToClassic.xpath"))).click();
			reportStep("Clicked ClassicMode button successfully", "pass");
		} catch (Exception e) {
			reportStep("Clicked ClassicMode button successfully", "fail");
		}
		
		return new ClassicModeHomePage(driver);

	}
}
