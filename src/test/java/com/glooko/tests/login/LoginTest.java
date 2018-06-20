package com.glooko.tests.login;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.glooko.tests.basetest.BaseTest;
import com.glooko.utility.ReadExcelData;
import com.glooko.views.login.LoginView;
import com.glooko.views.welcome.WelcomeView;

public class LoginTest extends BaseTest {
	LoginView loginview;
	WelcomeView welcomeview;

	@Test
	public void doLogin() {
		try {
			welcomeview.clickLogin();
			ReadExcelData.getDataFromExcel("D://LoginFile.xlsx", "Sheet1");
			loginview.loginToGlooko(ReadExcelData.list.get(0), ReadExcelData.list.get(1));
		} catch (final InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {
		loginview = new LoginView(driver);
		welcomeview = new WelcomeView(driver);
	}
}
