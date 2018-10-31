package com.glooko.views.home;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.glooko.views.BaseView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomeView extends BaseView {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Patterns']")
	public MobileElement startElement;

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

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Profile']")
	public MobileElement Profile;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout/android.widget.TextView[0]")
	MobileElement allInjectionNames;

	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout")
	List<MobileElement> noOfLists;

	@AndroidFindBy(id = "title")
	MobileElement titleProfile;

	@AndroidFindBy(id = "medication_name")
	List<MobileElement> medicationNames;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='LOG OUT']")
	MobileElement logout;

	@AndroidFindBy(id = "button1")
	MobileElement yesBtnLogOut;

	List<String> list = new ArrayList<String>();

	public HomeView(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		log.info("Starting Home Test");
	}

	/**
	 * Method to Add Food,Insulin,Medication,Exercise events.
	 */
	public void addMultipleEvents() {
		log.info("Executing addMultipleEvents method");
		waitForElement(startElement, 70);
		clickFloatIconFromHomeScreen();
		log.info("Clicked on float icon");
		log.info("Entering text in food field");
		addFood.sendKeys("Chicken");
		log.info("Entering food Quantity");
		foodQuantityInCarbs.sendKeys("100");
		log.info("Clicking on AddInsulin");
		addInsulin.click();
		log.info("Selecting medication from the list");
		medicationNames.get(5).click();
		log.info("Entering insulin quantity");
		insulinQuantityInUnits.sendKeys("30");
		log.info("clicking on addMedication");
		addMedication.click();
		log.info("Selecting medication from the list");
		medicationNames.get(5).click();
		log.info("Entering medication quantity");
		medicationQuantityInMg.sendKeys("50");
		log.info("entering text in addExercise field");
		addExercise.sendKeys("Walk");
		log.info("Entering text in exercise field");
		exerciseInMins.sendKeys("30");
		log.info("Clicking on addbutton");
		addButton.click();
	}

	/**
	 * Method to check Reminder notification in notification tray.
	 */
	public void checkNotification() {
		log.info("Swiping down Notification tray");
		waitForElement(startElement, 80);
		swipeTopToBottom(50);
		log.info("Checking existence of Reminder Notification");
		try {
			if (reminderNotification.isDisplayed()) {

				reminderNotification.click();
			}
		} catch (final Exception e) {

			// true);
		}
		driver.navigate().back();
	}

	/**
	 * Method to click on Float icon from Home screen.
	 */
	public void clickFloatIconFromHomeScreen() {
		log.info("Tapping on Plus icon");
		tap(1188, 1951);
	}

	/**
	 * Method to get list of medications from Insulin dropdown field.
	 */
	public void getMedicationTextFromInsulinList() {
		getTextFromDropdownList(medicationNames, startElementInMedicationList);
	}

	public void logout() {

		try {
			Thread.sleep(3000);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Executing logout test");
		log.info("Swiping right side");
		swipeDirection("FromLeftEdgeSide", 60);
		waitForElement(Profile, 40);
		log.info("Clicking on Profile icon");
		Profile.click();
		waitForElement(titleProfile, 40);
		log.info("Scrolling up");
		swipeDirection("UP", 40);
		waitForElement(logout, 40);
		log.info("Clicking on logout button");
		logout.click();
		log.info("Tapping on "+"Yes"+" from confirmation alert");
		yesBtnLogOut.click();

	}

	/**
	 * Method to scrollDown in Home screen from one element to other element.
	 */
	public void scrollDown() {
		waitForElement(endElement, 100);
		log.info("Scroll down in Home screen");
		scrollUsingElements(startElement, endElement);
	}

	/**
	 * Method to tap on Insulin dropdown in Add event screen.
	 */
	public void tapInsulinDropDown() {
		addInsulin.click();
	}
}
