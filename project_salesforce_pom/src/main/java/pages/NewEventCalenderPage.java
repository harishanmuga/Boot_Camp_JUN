package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.BaseClass;

public class NewEventCalenderPage extends BaseClass {
	
	static int endDate;

	public NewEventCalenderPage(WebDriver browser) {

		this.driver = browser;

	}

	public NewEventCalenderPage enterSubject(String subject) throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Combo (New Window)']")).click();
		Thread.sleep(3000);
		switchToLatestWindow();
		driver.findElement(By.xpath(
				"//div[@class='choicesBox tertiaryPalette brandSecondaryBrd']/ul/li/a[text()='" + subject + "']"))
				.click();
		//switchToParentWindow();
		return this;

	}

	public NewEventCalenderPage selectStartDate() {
		
		driver.switchTo().window(parentWindow);
		driver.findElement(By.id("StartDateTime")).click();
		String today = driver.findElement(By.xpath("//td[contains(@class,'todayDate')]")).getText();
		int todayDate = Integer.parseInt(today);
		int tmwDate = todayDate + 1;
		System.out.println(tmwDate);
		endDate = tmwDate + 1;

		driver.findElement(By.xpath("//td[text()='" + tmwDate + "']")).click();
		return this;

	}

	public NewEventCalenderPage selectEndDate() {
		
		driver.findElement(By.id("EndDateTime")).click();
		WebElement endMonth = driver.findElement(By.id("calMonthPicker"));
		Select dd = new Select(endMonth);
		dd.selectByVisibleText("August");
		driver.findElement(By.xpath("//td[text()='" + endDate + "']")).click();
		return this;

	}
	
	public NameLookupPage selectName() {
		
		driver.findElement(By.xpath("//a[@title='Name Lookup (New Window)']")).click();
		switchToLatestWindow();
		return new NameLookupPage(driver);

	}
	
	public NewEventCalenderPage attachFile(String fileName) {
		
		driver.switchTo().window(parentWindow);
		WebElement fileUpload = driver.findElement(By.xpath("//input[@name='attachFile']"));
		builder.moveToElement(fileUpload).click().perform();
		Set<String> windowHandles3 = driver.getWindowHandles();
		List<String> listOfWindows3 = new ArrayList<String>(windowHandles3);
		int size3 = listOfWindows3.size();
		System.out.println(size3);
		driver.switchTo().window(listOfWindows3.get(size3 - 1));
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//input[@id='file']"))
				.sendKeys("C:\\Users\\haris\\Desktop\\" + fileName + ".docx");
		driver.findElement(By.xpath("//input[@id='Attach']")).click();
		driver.findElement(By.xpath("//input[@value=' Done ']")).click();
		driver.switchTo().window(listOfWindows3.get(0));
		System.out.println(driver.getTitle());
		return this;

	}
	
	public NewEventCalenderPage verifyFileUpload(String fileName) throws InterruptedException {
		
		driver.switchTo().window(parentWindow);
		WebElement filesColumn = driver.findElement(By.xpath("//th[text()='File Name']"));
		js.executeScript("arguments[0].scrollIntoView();", filesColumn);
		Thread.sleep(3000);
		WebElement attachResult = driver.findElement(By.xpath("//span[contains(text(),'" + fileName + "')]"));
		builder.moveToElement(attachResult).perform();
		boolean displayed = attachResult.isDisplayed();
		if (!displayed) {
			System.out.println("File upload failed");
		} else {
			System.out.println("File upload success");
		}
		
		return this;

	}
	
	public ClassicModeHomePage clickSave() {
		
		driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save']")).click();
		return new ClassicModeHomePage(driver);

	}

}
