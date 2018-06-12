package com.glooko.tests.home;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.glooko.excelclassutility.ReadExcelData;
import com.glooko.tests.basetest.BaseTest;
import com.glooko.views.home.HomeView;
import com.glooko.views.login.LoginView;
import com.glooko.views.welcome.WelcomeView;

public class HomeTest extends BaseTest{

	LoginView loginview;
	WelcomeView welcomeview;
	HomeView homeview;

	@BeforeMethod
	public void init(){

		loginview=new LoginView(driver);
		welcomeview=new WelcomeView(driver);
		homeview=new HomeView(driver);
		}

	@Test
	public void checkAndtapNotification(){

		welcomeview.clickLogin();
		loginview.loginToGlooko(ReadExcelData.list.get(0),ReadExcelData.list.get(1));
		homeview.checkNotification();

	}



}
