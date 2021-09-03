package selenium.base.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import design.methods.Element;

public class BaseSeleniumMethods implements Element {

	public WebDriver driver;
	public static JavascriptExecutor js;
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
	
	public void clickUsingJavascript(WebElement element) {
		
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}
	
	public void scrolldownUsingJavascript(WebElement element) {
		
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

	}
	
	public void verifyText(String exp, String act) {
		
		if (exp.equalsIgnoreCase(act)) {
			System.out.println("Text matched");
		} else {
			System.out.println("Text not matched");
		}

	}

}
