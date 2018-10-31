package com.glooko.views.welcome;

import org.openqa.selenium.support.PageFactory;

import com.glooko.views.BaseView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WelcomeView extends BaseView {
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Sync')]")
	MobileElement welcomeScreenTitleFirstPage;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Easily')]")
	MobileElement welcomeScreenCopyFirstPage;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Activation')]")
	MobileElement welcomeScreenActivationCodeLink;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'SIGN UP')]")
	MobileElement signUp;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'LOGIN')]")
	MobileElement login;

	public WelcomeView(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to click on login button in Welcome screen.
	 */
	public void clickLogin() {
		log.info("Click on Login button in Welcome screen");
		waitForElement(login, 40);
		login.click();
		log.info("Clicked on login button");
	}
}
