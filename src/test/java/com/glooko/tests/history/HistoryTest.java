package com.glooko.tests.history;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.glooko.excelclassutility.ReadExcelData;
import com.glooko.tests.basetest.BaseTest;
import com.glooko.views.history.HistoryView;
import com.glooko.views.login.LoginView;
import com.glooko.views.welcome.WelcomeView;

public class HistoryTest extends BaseTest{

	HistoryView historyview;
	LoginView loginview;
	WelcomeView welcomeview;

	@BeforeMethod
	public void init(){
		loginview=new LoginView(driver);
		welcomeview=new WelcomeView(driver);
		historyview=new HistoryView(driver);
       }


	@Test
	public void clickHistory()
	{
		welcomeview.clickLogin();
		try {
			loginview.loginToGlooko(ReadExcelData.list.get(0),ReadExcelData.list.get(1));
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		historyview.findAllLoggedEventsText();
	}

}

