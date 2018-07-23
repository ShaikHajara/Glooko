package com.glooko.views.home;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.glooko.views.BaseView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomeView extends BaseView {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Patterns']")
	MobileElement startElement;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Today']")
	MobileElement endElement;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Glooko Reminder']")
	MobileElement reminderNotification;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@clickable='true']")
	MobileElement floatIcon;

	@AndroidFindBy(id = "save_note_action")
	MobileElement addButton;

	@AndroidFindBy(id = "note_insulin_type")
	MobileElement addInsulin;

	@AndroidFindBy(id = "note_food_field")
	MobileElement addFood;

	@AndroidFindBy(id = "note_carbs_field")
	MobileElement foodQuantityInCarbs;

	@AndroidFindBy(id = "note_insulin_field")
	MobileElement insulinQuantityInUnits;

	@AndroidFindBy(id = "note_medication_field")
	MobileElement medicationQuantityInMg;

	@AndroidFindBy(id = "note_exercise_field")
	MobileElement exerciseInMins;

	@AndroidFindBy(id = "note_medication_type")
	MobileElement addMedication;

	@AndroidFindBy(id = "note_exercise_name")
	MobileElement addExercise;

	@AndroidFindBy(id = "note_date_text")
	MobileElement dateCalendar;

	@AndroidFindBy(id = "note_time_text")
	MobileElement time;

	@AndroidFindBy(id = "note_comment_text")
	MobileElement addComment;

	@AndroidFindBy(id = "alertTitle")
	public MobileElement startElementInMedicationList;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView")
	public MobileElement titleInAddEventScreen;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout/android.widget.TextView[0]")
	MobileElement allInjectionNames;

	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout")
	List<MobileElement> noOfLists;

	@AndroidFindBy(id = "medication_name")
	List<MobileElement> medicationNames;

	List<String> list = new ArrayList<String>();

	public HomeView(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Method to Add Food,Insulin,Medication,Exercise events
	 */

	public void addMultipleEvents() {

		addFood.sendKeys("Chicken");
		foodQuantityInCarbs.sendKeys("100");
		addInsulin.click();

		addMedication.click();

		addExercise.sendKeys("Walk");
		exerciseInMins.sendKeys("30");
		addButton.click();

	}

	/**
	 * Method to check Reminder notification in notification tray
	 */

	public void checkNotification() {
		waitForElement(startElement, 80);
		swipeTopToBottom(50);
		try {
			if (reminderNotification.isDisplayed()) {
				Reporter.log("Reminder notification available in notification tray", true);
				reminderNotification.click();
			}
		} catch (final Exception e) {
			Reporter.log("Reminder notification doesn't exists in notification tray", true);
		}
		driver.navigate().back();
	}

	/**
	 * Method to click on Float icon from Home screen
	 */

	public void clickFloatIconFromHomeScreen() {
		log.info("Tapping on Plus icon");
		// waitForElement(startElement, 80);
		final TouchAction action = new TouchAction(driver);
		// action.longPress(findXCoordinate(floatIcon),
		// findYCoordinate(floatIcon)).waitAction(Duration.ofMinutes(1))
		// .release().perform();
		action.tap(1188, 1951).release().perform();
	}

	/**
	 * Method to get list of medications from Insulin dropdown field
	 */
	public void getMedicationList() {
		for (int i = 0; i < medicationNames.size(); i++) {
			list.add(medicationNames.get(i).getText());
			log.info(medicationNames.get(i).getText());
		}
		log.info("Last elememt in list is" + list.get(list.size() - 1));
		final String lastElement = list.get(list.size() - 1);
		final String lastElementWithoutPrefix = lastElement.substring(0, lastElement.length() - 1);
		log.info(lastElementWithoutPrefix);
		if (!lastElementWithoutPrefix.equalsIgnoreCase("Afrezza")) {
			scrollUsingElements(medicationNames.get(12), startElementInMedicationList);
			new HomeView(driver).getMedicationList();
		} else {
			log.info("Cannot scroll further");
		}
	}

	/**
	 * Method to scrollDown in Home screen from one element to other element
	 */

	public void scrollDown() {
		waitForElement(endElement, 100);
		log.info("Scroll down in Home screen");
		scrollUsingElements(startElement, endElement);
	}

	/**
	 * Method to tap on Insulin dropdwon in Add event screen
	 */
	public void tapInsulinDropDown() {
		addInsulin.click();
	}
}
