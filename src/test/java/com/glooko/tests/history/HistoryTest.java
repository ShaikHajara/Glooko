package com.glooko.tests.history;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.glooko.tests.basetest.BaseTest;
import com.glooko.utility.ReadExcelData;
import com.glooko.views.history.HistoryView;
import com.glooko.views.login.LoginView;
import com.glooko.views.welcome.WelcomeView;

public class HistoryTest extends BaseTest {

	HistoryView historyview;
	LoginView loginview;
	WelcomeView welcomeview;

	@Test
	public void clickHistory() {
		welcomeview.clickLogin();
		try {
			ReadExcelData.getDataFromExcel("C:\\Users\\Shaik.Hajara\\eclipse-workspace\\Glooko-master\\src\\main\\resources\\excelfiles\\LoginFile.xlsx", "Sheet1");
		} catch (final InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loginview.loginToGlooko(ReadExcelData.list.get(0), ReadExcelData.list.get(1));
		try {
			historyview.clickHistoryTab();
		} catch (final InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// historyview.findAllLoggedEventsText();
	}

	@Test
	public void hideGraphInHistoryView() {
		try {
			historyview.clickHistoryTab();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		historyview.hideGraph();
	}

	@BeforeMethod
	public void init() {
		loginview = new LoginView(driver);
		welcomeview = new WelcomeView(driver);
		historyview = new HistoryView(driver);
	}
}
