package com.glooko.views;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class BaseView {
	public static Logger log;
	public AppiumDriver<MobileElement> driver;

	public BaseView(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		log = Logger.getLogger("devpinoyLogger");
		PropertyConfigurator.configure(
				"C:\\Users\\Shaik.Hajara\\workspace\\glookoapp\\src\\main\\resources\\logs\\log4j.properties");
	}

	public int findXCoordinate(MobileElement ele) {
		final int X = ele.getLocation().getX();
		return X;
	}

	public int findYCoordinate(MobileElement ele) {
		final int Y = ele.getLocation().getY();
		return Y;
	}

	public void scrollUsingCoordinates(int startX, int startY, int endX, int endY, int duration) {
		final TouchAction action = new TouchAction(driver);
		action.longPress(startX, startY).waitAction(Duration.ofSeconds(duration)).moveTo(endX, endY).release()
				.perform();
	}

	public void scrollUsingElements(MobileElement startElement, MobileElement endElement) {
		final TouchAction action = new TouchAction(driver);
		action.press(startElement).waitAction(Duration.ofSeconds(10)).moveTo(endElement).release().perform();
	}

	public int secondXCoordinate(MobileElement ele) {
		final int secondX = ele.getLocation().getX() + ele.getSize().getWidth();
		return secondX;
	}

	public int secondYCoordinate(MobileElement ele) {
		final int secondY = ele.getLocation().getY() + ele.getSize().getHeight();
		return secondY;
	}

	public void swipeNotificationBar() {
		log.info("Swipe down notification bar");
		final TouchAction action = new TouchAction(driver);
		action.longPress(481, 37, Duration.ofSeconds(20)).moveTo(507, 2233).release().perform();
	}

	public void swipeTopToBottom(int durationInSeconds) {
		final TouchAction action = new TouchAction(driver);
		final Dimension size = driver.manage().window().getSize();
		final int startX = size.getWidth() / 3;
		System.out.println(startX);
		final int endX = (int) (size.getWidth() * 0.50);
		System.out.println(endX);
		final int endY = (int) (size.getHeight() * 0.50);
		System.out.println(endY);
		action.longPress(startX, 2).waitAction(Duration.ofSeconds(durationInSeconds)).moveTo(endX, endY).release()
				.perform();
	}

	public void waitForElement(By by, int timeOutInSeconds) {
		final WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElement(MobileElement ele, int timeOutInSeconds) {
		log.info("Waiting for element" + " " + ele.toString());
		final WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
}
