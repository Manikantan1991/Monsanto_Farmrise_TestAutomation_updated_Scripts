package pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import Testscript.BaseClass;
import util.ActionUtil;

public class onBoard_screenPages extends BaseClass {

	@FindBy(xpath = "//android.widget.TextView[@text='English']")

	private WebElement English;

	@FindBy(id = "com.climate.farmrise:id/btn_Proceed")
	private WebElement ProceedBtn;

	@FindBy(id = "com.climate.farmrise:id/btn_agree")
	private WebElement Termsbtn;

	// This is a constructor, as every page need a base driver to find web
	// elements
	public onBoard_screenPages() {

		PageFactory.initElements(driver, this);
	}

	// Select a English Language
	public void select_Language() {
		try {
			English.isDisplayed();
			System.out.println("English Button Element exists ");
			Reporter.log("English Button Element exists");

		} catch (NoSuchElementException e) {
			System.out.println(" English Button Element not exists ");
		}

		English.click();
	}

	// Proceed Button
	public void proceed_Btn() {

		try {
			ProceedBtn.isDisplayed();
			System.out.println("Proceed Btn Element exists ");
		} catch (NoSuchElementException e) {
			System.out.println("ProceedBtn Element not exists ");
		}
		ProceedBtn.click();
	}

	// Terms and Agree and condition

	public void terms_condition() {

		try {
			Termsbtn.isDisplayed();
			System.out.println("Terms and condition  Btn Element exists ");
		} catch (NoSuchElementException e) {
			System.out.println("Terms and condition Btn  Element not exists ");
		}
		Termsbtn.click();
	}

	public void swipe_Operation()

	{

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// SwipeRightToLeft on Home Tab
		try {

			ActionUtil util_obj = new ActionUtil();
			util_obj.swipeRightToLeft();
			Reporter.log("Successfully Swipped.");

		} catch (InterruptedException e) {
			Reporter.log("Swipe Unsuccesfull.");
		}
	}

}
