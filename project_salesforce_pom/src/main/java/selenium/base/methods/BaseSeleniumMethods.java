package selenium.base.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import design.methods.Element;

public class BaseSeleniumMethods implements Element {

	public WebDriver driver;
	public static String parentWindow;

	public void clickElement(WebElement element) {
		element.click();
	}

	public void switchToLatestWindow() {

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listwindowHandles = new ArrayList<String>(windowHandles);

		driver.switchTo().window(listwindowHandles.get(listwindowHandles.size() - 1));

	}

	public void switchToParentWindow() {

		driver.switchTo().window(parentWindow);

	}

	public void typeText(WebElement element, String value) {
		
		element.sendKeys(value);
		
	}

}
