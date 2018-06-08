package com.glooko.views.welcome;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.glooko.views.BaseView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WelcomeView extends BaseView {

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Sync')]")
	WebElement welcomeScreenTitleFirstPage;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Easily')]")
	WebElement welcomeScreenCopyFirstPage;

	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'Activation')]")
	WebElement welcomeScreenActivationCodeLink;

	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'SIGN UP')]")
	WebElement signUp;

	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'LOGIN')]")
	WebElement Login;

	public WelcomeView(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to click on login button in Welcome screen
	 */

	public void clickLogin(){
		Login.click();
	}


}
