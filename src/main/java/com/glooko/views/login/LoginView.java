package com.glooko.views.login;

import org.openqa.selenium.support.PageFactory;

import com.glooko.views.BaseView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginView extends BaseView {

	@AndroidFindBy(xpath = "//android.widget.EditText[@index='1']")
	MobileElement email;

	@AndroidFindBy(xpath = "//android.widget.EditText[@index='3']")
	MobileElement password;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'LOG')]")
	MobileElement loginBtn;

	public LoginView(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		log.info("Started login test");
	}

	/**
	 * Method to login into app.
	 *
	 * @param username-Getting data from excel.
	 * @param password-Getting data from excel.
	 */
	public void loginToGlooko(String Username, String Password) {

		log.info("Executing loginToGlooko method");
		log.info("Waiting for email element");
		waitForElement(email, 60);
		log.info("email value is" + Username);
		email.sendKeys(Username);
		log.info("Password value is" + Password);
		password.sendKeys(Password);
		log.info("Click on Login button in login screen");
		loginBtn.click();
	}
}
