package pages;

import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import Testscript.BaseClass;
import util.ActionUtil;

public class HomeTab_pages extends BaseClass

{

	@FindBy(xpath = "//android.widget.TextView[@text='Home']")

	private WebElement Hometab;

	@FindBy(id = "com.climate.farmrise:id/checkWeatherDetails")
	private WebElement Accessweatherdetails;

	@FindBy(id = "com.climate.farmrise:id/hourlyWeatherForecastScrollView")
	private WebElement Weatherelement;

	@FindBy(xpath = ("//android.widget.TextView[@text='Now']"))
	private WebElement hourElement;

	// This is a constructor, as every page need a base driver to find web
	// elements
	public HomeTab_pages() {

		PageFactory.initElements(driver, this);
	}

	public void homeScreen() {
		try {
			Hometab.isDisplayed();
			System.out.println("Hometab  Btn Element exists ");
		} catch (NoSuchElementException e) {
			System.out.println("Hometab Btn  Element not exists ");
		}
		Hometab.click();
	}

	public void weather_Details() {
		try {
			Accessweatherdetails.isDisplayed();
			System.out.println("Accessweatherdetails  Btn Element exists ");
		} catch (NoSuchElementException e) {
			System.out.println("Accessweatherdetails Btn  Element not exists ");
		}
		Accessweatherdetails.click();
	}

	public void checkweather_timings() throws InterruptedException {

		ActionUtil util_obj = new ActionUtil();

		util_obj.checkweather_details();
		Reporter.log(" check weather details from Now to 23hrs");

	}

}
