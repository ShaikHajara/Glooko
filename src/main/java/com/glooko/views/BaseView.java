package com.glooko.views;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class BaseView {

	public AppiumDriver<MobileElement> driver;
	public static Logger log;


	public BaseView(AppiumDriver<MobileElement> driver){
		this.driver=driver;
		log = Logger.getLogger("devpinoyLogger");
		PropertyConfigurator.configure("C:\\Users\\Shaik.Hajara\\workspace\\glookoapp\\src\\main\\resources\\logs\\log4j.properties");

	}

	public void waitForElement(MobileElement ele,int time)

	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void swipeNotificationBar(){
		TouchAction action=new TouchAction(driver);
	    action.longPress(481, 37, Duration.ofSeconds(20)).moveTo(507, 2233).release().perform();

	}


}
