package com.glooko.views.history;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;

import com.glooko.views.BaseView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HistoryView extends BaseView {
	public static List<MobileElement> TextFromList;

	public static int noOfTextViews;

	public static int findNoOfTextViews() {
		noOfTextViews = TextFromList.size();
		log.info("Total no of text views are" + noOfTextViews);
		return noOfTextViews;
	}

	@AndroidFindBy(id = "history_button")
	MobileElement historyTab;

	@AndroidFindBy(xpath = "//android.widget.ListView[@index='4']")
	MobileElement eventsList;

	@AndroidFindBy(id = "btnShowHideGraph")
	MobileElement hideGraph;

	@AndroidFindBy(id = "history_food_text")
	List<MobileElement> foodText;

	@AndroidFindBy(id = "carbs_string")
	MobileElement carbsText;

	@AndroidFindBy(id = "history_food_carbs")
	MobileElement carbsValue;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Insulin']")
	List<MobileElement> insulin;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Insulin']/android.widget.TextView")
	MobileElement insulinValue;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Medication']")
	MobileElement medication;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Medication']/android.widget.TextView")
	MobileElement medicationValue;

	@AndroidFindBy(id = "history_exercise_text")
	MobileElement exercise;

	@AndroidFindBy(id = "history_exercise_details")
	MobileElement exerciseDetails;

	@AndroidFindBy(id = "lblDateTime")
	MobileElement dateTime;

	@AndroidFindBy(id = "no_data_title")
	MobileElement noDataInHistory;

	@AndroidFindBy(id = "linShowHideGraph")
	MobileElement showGraphLine;

	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout")
	List<MobileElement> noOfLists;

	public List<String> LoggedEventsText = new ArrayList<String>();

	public HistoryView(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to click on History tab from Home screen
	 *
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public void clickHistoryTab() throws InvalidFormatException, IOException {
		waitForElement(historyTab, 100);
		historyTab.click();
	}

	/**
	 * Method to find text of all events without scrolling up
	 */

	public void findAllLoggedEventsText() {
		findNoOfTextViews();
		for (int i = 0; i < noOfTextViews; i++) {
			System.out.println(TextFromList.get(i).getText());
			LoggedEventsText.add(TextFromList.get(i).getText());
		}
	}

	public int findNoOfEventsLogged() {
		final int NoOfEvents = noOfLists.size();
		System.out.println(NoOfEvents);
		log.info("No of events logged" + NoOfEvents);
		return NoOfEvents;
	}

	/**
	 * Method to hide graph in History tab
	 */
	public void hideGraph() {
		waitForElement(hideGraph, 60);
		final int HideGraph_X = hideGraph.getLocation().getX();
		final int HideGraph_Y = hideGraph.getLocation().getY();
		waitForElement(hideGraph, 50);
		final TouchAction action = new TouchAction(driver);
		log.info("X coordinate of Hidegraph element is" + HideGraph_X);
		log.info("Y coordinate of Hidegraph element is" + HideGraph_Y);
		action.press(hideGraph, HideGraph_X, HideGraph_Y).release().perform();
		log.info("Clicked on graph");
	}

	/**
	 * Method to find text of all events after scrolling up
	 */

	public void scrollAndGetLoggedEventsText() {
		findAllLoggedEventsText();
		final int startElement_X = TextFromList.get(noOfTextViews - 1).getLocation().getX();
		final int startElement_Y = TextFromList.get(noOfTextViews - 1).getLocation().getY();
		final TouchAction action = new TouchAction(driver);
		action.longPress(startElement_X, startElement_Y).waitAction(Duration.ofSeconds(20)).moveTo(dateTime).release()
				.perform();
		new HistoryView(driver).scrollAndGetLoggedEventsText();
	}
}
