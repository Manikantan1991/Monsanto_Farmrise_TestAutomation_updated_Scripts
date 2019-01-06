package Testscript;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import Generic.Constant;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pages.GovtScheme_Pages;
import pages.HomeTab_pages;
import pages.MoreTab_Pages;
import pages.onBoard_screenPages;
import util.select_Crop;

public class BaseClass implements Constant {

	public static AndroidDriver<MobileElement> driver;
	DesiredCapabilities cap;

	String sDirPath = System.getProperty("user.dir");

	@BeforeSuite

	public void _setup() throws InterruptedException {

		// set desired capabilities for FarmRise and Honor 7 device

		try {

			cap = new DesiredCapabilities();
			cap.setCapability("automationName", automationName);
			cap.setCapability("platformName", platformName);
			cap.setCapability("platformVersion", platformVersion);
			cap.setCapability("deviceName", deviceName);
			cap.setCapability("UDID", UDID);
			cap.setCapability("autoGrantPermissions", "true");
			// cap.setCapability("appPackage", "com.climate.farmrise");
			// cap.setCapability("appActivity",
			// "com.climate.farmrise.SplashScreen");

			cap.setCapability("app", new File(sDirPath + "/Apk-File/FarmRice.apk").getAbsolutePath());
			System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
			cap.setCapability("fullReset", "false");
			cap.setCapability("noReset", "false");

			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Reporter.log("----------------appium driver initialised----------------");

		}

		catch (Exception e) {
			System.out.println("----------Unable to launch app-------------");
		}

		String windowName = driver.currentActivity();
		Reporter.log("Window name is :" + windowName);

		try {

			if (windowName.contains("SplashScreen")) {
				Reporter.log("Successfully installed.");
			}

			else

			{
				Reporter.log("Installation failed.");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 0, groups = "OnBoarding_page")

	public void boarding_Testscript() throws Exception {

		// This is to Instantiate Onborading_Pages

		onBoard_screenPages start_up_page = new onBoard_screenPages();
		start_up_page.select_Language();
		start_up_page.proceed_Btn();
		start_up_page.terms_condition();
		select_Crop crop_selection = new select_Crop();
		crop_selection.select_crop();
		// select_Crop.select_crop(driver, "Cumin seeds");
		// start_up_page.select_crop("Beetroot");
		start_up_page.swipe_Operation();
		Reporter.log(" Boarding Screen is Sucess Full ");

	}

	@Test(priority = 1, groups = "Home_page", dependsOnMethods = "boarding_Testscript")

	public void homescreen_Testscript() throws InterruptedException, IOException {

		// This is to Instantiate Home Tab
		// HomeTab_pages H = new HomeTab_pages(driver);
		HomeTab_pages main_page = new HomeTab_pages();
		main_page.homeScreen();
		main_page.weather_Details();
		main_page.checkweather_timings();

	}

	@Test(priority = 2, groups = "More_page", dependsOnMethods = "boarding_Testscript")

	public void Morepage_Testscript() throws InterruptedException, IOException

	{

		// This is to Instantiate More Tab

		MoreTab_Pages more_page = new MoreTab_Pages();
		Thread.sleep(2000);
		more_page.MoreTab_btn();
		more_page.GovtScheme();
		more_page.Scrolling();

		// This is to Instantiate GovtScheme Tab

		GovtScheme_Pages Govt_page = new GovtScheme_Pages();
		Govt_page.SearchBtn();
		Govt_page.Searchbox();
		Govt_page.Tapon_SrchBx();

		if (Govt_page.VerifyResult("Kisan"))
			Reporter.log("PASSED Test");
		else
			Reporter.log("FAILED Test");

	}

	@AfterSuite

	public void tearDown() {
		if (driver != null) {

			driver.quit();
			Reporter.log("Close the Driver");
		}
	}

}
