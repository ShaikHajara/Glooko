package com.glooko.tests.basetest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public static DesiredCapabilities cap;
	public static String service_url;
	public AppiumDriver<MobileElement> driver;
	public String Url = "http://127.0.0.1:4723/wd/hub";
	private CommandLine cmd;
	private DefaultExecuteResultHandler resulthandler;
	private DefaultExecutor executor;
	public AppiumServiceBuilder builder;
	public AppiumDriverLocalService service;

	public String appiumServerStart() throws InterruptedException {
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Shaik.Hajara\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723));
		service.start();
		Thread.sleep(5000);
		service_url = service.getUrl().toString();
		return service_url;
	}

	/**
	 * Method to assert two strings.Used to verify two strings whether it is
	 * equal or not.
	 *
	 * @param ele
	 *            - This is first parameter of assertEquals.
	 * @param Expected-
	 *            This is second parameter used to pass expected string.
	 */
	public void assertEquals(MobileElement ele, String Expected) {
		try {
			final String Actual = ele.getText();
			Assert.assertEquals(Actual, Expected);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void setup() {
		cap = new DesiredCapabilities();
		try {
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.VERSION, "7.0");
			cap.setCapability("appPackage", "com.glooko.logbook");
			cap.setCapability("appActivity", "com.glooko.glooko.activity.GlookoLaunchActivity");
			cap.setCapability("app-wait-activity", "com.glooko.glooko.activity.GlookoLaunchActivity");
			// cap.setCapability(MobileCapabilityType.NO_RESET, true);
			final String services_url = appiumServerStart();
			System.out.println(services_url);
			/*
			 * Runtime runtime = Runtime.getRuntime(); runtime.exec(
			 * "cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\""
			 * );
			 */
			waitForSeconds(3);
			driver = new AndroidDriver<MobileElement>(new URL(services_url), cap);
			System.out.println("Launched app");
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void stopServer() {
		service.stop();
	}

	/**
	 * Method to wait for page to load using thread sleep.
	 *
	 * @param timeOutInMilliSeconds-
	 *            This is timeout parameter used to wait for page to load.
	 */
	public void waitForSeconds(int timeOutInSeconds) {
		try {
			Thread.sleep(timeOutInSeconds * 1000);
		} catch (final InterruptedException e) {
		}
	}
}
