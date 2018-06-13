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
	@AndroidFindBy(id = "history_button")
	MobileElement HistoryTab;

	@AndroidFindBy(xpath = "//android.widget.ListView[@index='4']")
	MobileElement EventsList;

	@AndroidFindBy(id = "btnShowHideGraph")
	MobileElement HideGraph;

	@AndroidFindBy(id = "history_food_text")
	List<MobileElement> FoodText;

	@AndroidFindBy(id = "carbs_string")
	MobileElement CarbsText;

	@AndroidFindBy(id = "history_food_carbs")
	MobileElement CarbsValue;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Insulin']")
	List<MobileElement> Insulin;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Insulin']/android.widget.TextView")
	MobileElement InsulinValue;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Medication']")
	MobileElement Medication;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Medication']/android.widget.TextView")
	MobileElement MedicationValue;

	@AndroidFindBy(id = "history_exercise_text")
	MobileElement Exercise;

	@AndroidFindBy(id = "history_exercise_details")
	MobileElement ExerciseDetails;

	@AndroidFindBy(id = "lblDateTime")
	MobileElement DateTime;

	@AndroidFindBy(id = "no_data_title")
	MobileElement NoDataInHistory;

	@AndroidFindBy(id = "linShowHideGraph")
	MobileElement ShowGraphLine;

	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout")
	List<MobileElement> NoOfLists;

	public static List<MobileElement> TextFromList;
	public List<String> LoggedEventsText = new ArrayList<String>();
	public static int noOfTextViews;

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
		waitForElement(HistoryTab, 100);
		HistoryTab.click();
	}

	/**
	 * Method to hide graph in History tab
	 */
	public void hideGraph() {
		int HideGraph_X = HideGraph.getLocation().getX();
		int HideGraph_Y = HideGraph.getLocation().getY();
		waitForElement(HideGraph, 70);
		TouchAction action = new TouchAction(driver);
		log.info("X coordinate of Hidegraph element is"+ HideGraph_X);
		log.info("Y coordinate of Hidegraph element is"+ HideGraph_Y);
		action.tap(HideGraph, HideGraph_X, HideGraph_Y).release().perform();
		log.info("Clicked on graph");
	}

	public int findNoOfEventsLogged() {
		int NoOfEvents = NoOfLists.size();
		System.out.println(NoOfEvents);
		log.info("No of events logged" + NoOfEvents);
		return NoOfEvents;
	}

	public static int findNoOfTextViews() {
		noOfTextViews = TextFromList.size();
		log.info("Total no of text views are"+ noOfTextViews);
		return noOfTextViews;
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

	/**
	 * Method to find text of all events after scrolling up
	 */

	public void scrollAndGetLoggedEventsText() {
		findAllLoggedEventsText();
		int startElement_X = TextFromList.get(noOfTextViews - 1).getLocation().getX();
		int startElement_Y = TextFromList.get(noOfTextViews - 1).getLocation().getY();
		TouchAction action = new TouchAction(driver);
		action.longPress(startElement_X, startElement_Y).waitAction(Duration.ofSeconds(20)).moveTo(DateTime).release()
				.perform();
		new HistoryView(driver).scrollAndGetLoggedEventsText();
	}
}
