package design.methods;

import org.openqa.selenium.WebElement;

public interface Element {
	
	public void clickElement(WebElement element);
	
	public void typeText(WebElement element, String value);

}
