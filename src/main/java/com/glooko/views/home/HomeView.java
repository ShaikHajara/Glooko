package com.glooko.views.home;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.glooko.views.BaseView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomeView extends BaseView {
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Patterns']")
	MobileElement startElement;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Today']")
	MobileElement endElement;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Glooko Reminder']")
	MobileElement reminderNotification;

	public HomeView(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to check Reminder notification in notification tray
	 */

	public void checkNotification() {
		waitForElement(startElement, 80);
		swipeTopToBottom(60);
		try {
			if (reminderNotification.isDisplayed()) {
				Reporter.log("Reminder notification available in notification tray", true);
				reminderNotification.click();
			}
		} catch (final Exception e) {
			Reporter.log("Reminder notification doesn't exists in notification tray", true);
		}
		driver.navigate().back();
	}

	/**
	 * Method to scrollDown in Home screen from one element to other element
	 */

	public void scrollDown() {
		waitForElement(endElement, 100);
		log.info("Scroll down in Home screen");
		scrollUsingElements(startElement, endElement);
	}
}
