package com.glooko.views;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
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

	public void waitForElement(MobileElement ele,int timeOutInSeconds)
	{
		log.info("Waiting for element"+" " +ele.toString());
		WebDriverWait wait=new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}


	public void waitForElement(By by, int timeOutInSeconds){
		WebDriverWait wait=new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void swipeNotificationBar(){
		log.info("Swipe down notification bar");
		TouchAction action=new TouchAction(driver);
	    action.longPress(481, 37, Duration.ofSeconds(20)).moveTo(507, 2233).release().perform();
	}

	public void scrollUpOrDownUsingElements(MobileElement startElement,MobileElement endElement){
		TouchAction action=new TouchAction(driver);
		action.press(startElement).waitAction(Duration.ofSeconds(10)).moveTo(endElement).release().perform();
	}

	public void scrollUpOrDownUsingCoordinates(int startX,int startY,int endX,int endY,int duration){
		TouchAction action=new TouchAction(driver);
		action.longPress(startX,startY).waitAction(Duration.ofSeconds(duration)).moveTo(endX,endY).release().perform();
	}

}
