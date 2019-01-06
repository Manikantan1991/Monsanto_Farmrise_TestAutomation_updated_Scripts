package pages;

import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import Testscript.BaseClass;
import util.ActionUtil;

public class MoreTab_Pages extends BaseClass {

	@FindBy(xpath = "//android.widget.TextView[@text='More']")

	private WebElement MoreTab;

	@FindBy(xpath = "//android.widget.TextView[@text='Government Schemes']")
	private WebElement GovtSchemes;

	// This is a constructor, as every page need a base driver to find web
	// elements
	public MoreTab_Pages() {

		PageFactory.initElements(driver, this);
	}

	// Click on Moretab
	public void MoreTab_btn() {
		try {
			MoreTab.isDisplayed();
			System.out.println("MoreTab  Btn Element exists ");
		} catch (NoSuchElementException e) {
			System.out.println("MoreTab Btn  Element not exists ");
		}
		MoreTab.click();
	}

	// Click on GovtScheme
	public void GovtScheme() {

		try {
			GovtSchemes.isDisplayed();
			System.out.println("GovtSchemes  Btn Element exists ");
		} catch (NoSuchElementException e) {
			System.out.println("GovtSchemes Btn  Element not exists ");
		}

		GovtSchemes.click();
	}

	public void Scrolling() {

		// swipeBottomToTop on Home Tab
		try {

			ActionUtil util_object = new ActionUtil();
			util_object.swipeBottomToTop();

			Reporter.log("Successfully Scrolled.");

		} catch (InterruptedException e) {
			Reporter.log("Scroll Unsuccesfull.");
		}

	}

}