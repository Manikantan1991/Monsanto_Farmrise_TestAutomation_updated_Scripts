package pages;

import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import Testscript.BaseClass;
import util.ActionUtil;

public class GovtScheme_Pages extends BaseClass {

	@FindBy(id = "android:id/search_button")

	private WebElement Srchbtn;

	@FindBy(id = "android:id/search_src_text")
	private WebElement SrchBox;

	@FindBy(id = "com.climate.farmrise:id/sorry_txt")
	public WebElement txtResult;

	public GovtScheme_Pages() {

		PageFactory.initElements(driver, this);
	}

	public void SearchBtn() {
		try {
			Srchbtn.isDisplayed();
			System.out.println("Srchbtn Tab  Btn Element exists ");
		} catch (NoSuchElementException e) {
			System.out.println("Srchbtn  Tab Btn  Element not exists ");
		}
		Srchbtn.click();

	}

	public void Searchbox() {
		try {
			SrchBox.isDisplayed();
			System.out.println("SearchBox Tab  Btn Element exists ");
		} catch (NoSuchElementException e) {
			System.out.println("SearchBox   Tab Btn  Element not exists ");
		}
		SrchBox.sendKeys("Kisan");
	}

	public void Tapon_SrchBx() {
		try {

			ActionUtil util_obj = new ActionUtil();
			util_obj.tapOnentrBtn();

			Reporter.log("Successfully Tapped.");

		} catch (InterruptedException e) {
			Reporter.log("Tap Unsuccesfull.");
		}
	}

	public boolean VerifyResult(String result) {
		if (txtResult.getText().equals(result))
			return true;
		else
			return false;
	}

}
