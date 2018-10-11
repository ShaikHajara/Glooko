package com.glooko.tests.home;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.glooko.tests.basetest.BaseTest;
import com.glooko.utility.ReadExcelData;
import com.glooko.views.home.HomeView;
import com.glooko.views.login.LoginView;
import com.glooko.views.welcome.WelcomeView;

public class HomeTest extends BaseTest {
	LoginView loginview;
	WelcomeView welcomeview;
	HomeView homeview;

	@Test
	public void checkAndtapNotification() {
		welcomeview.clickLogin();
		try {
			ReadExcelData.getDataFromExcel("D://LoginFile.xlsx", "Sheet1");
		} catch (final InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginview.loginToGlooko(ReadExcelData.list.get(0), ReadExcelData.list.get(1));
		homeview.checkNotification();
	}

	@Test
	public void getInsulinListText() {
		waitForSeconds(7);
		homeview.clickFloatIconFromHomeScreen();
		assertEquals(homeview.titleInAddEventScreen, "Add Event");
		homeview.tapInsulinDropDown();
		assertEquals(homeview.startElementInMedicationList, "Insulin");
		homeview.getMedicationList();
	}

	@BeforeMethod
	public void init() {
		loginview = new LoginView(driver);
		welcomeview = new WelcomeView(driver);
		homeview = new HomeView(driver);
	}
}
