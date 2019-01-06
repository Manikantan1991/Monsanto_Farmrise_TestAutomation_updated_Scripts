package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import Testscript.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class ActionUtil extends BaseClass {

	// Description: This method to scroll left side based on device height and
	// width

	public void swipeRightToLeft() throws InterruptedException

	{

		Reporter.log("Swipe operation starts ");

		Dimension size = driver.manage().window().getSize();

		int startx = (int) (size.width * 0.60);
		int endx = (int) (size.width * 0.40);

		int starty = size.height / 2;

		Thread.sleep(5000);

		for (int i = 1; i < 5; i++) {
			driver.swipe(startx, starty, endx, starty, 1000);

			Reporter.log("Swipped Right to Left Swipe starts");
		}

	}

	public void swipeElement_weather(WebElement el, int hourY)

	{
		String orientation = driver.getOrientation().value();
		int leftX = el.getLocation().getX();
		int rightX = leftX + el.getSize().getWidth();
		el.getLocation().getY();
		int middleY = hourY;
		if (orientation.equals("portrait")) {

			Reporter.log("Swiping in Portrait");
			driver.swipe(rightX - 5, middleY, leftX + 5, middleY, 2000);
		}

		else if (orientation.equals("landscape")) {
			Reporter.log("Swiping Landscape");
			driver.swipe(rightX - 5, middleY, leftX + 5, middleY, 500);
		}
	}

	// Swipe Botton to Top in government Schemes

	public void swipeBottomToTop() throws InterruptedException {

		Dimension size2 = driver.manage().window().getSize();

		size2 = driver.manage().window().getSize();
		int x_start = (int) (size2.width * 0.50);
		int x_end = (int) (size2.width * 0.50);
		int y_start = (int) (size2.height * 0.70);
		int y_end = (int) (size2.height * 0.20);

		for (int j = 0; j < 2; j++) {
			Thread.sleep(2000);
			driver.swipe(x_start, y_start, x_end, y_end, 2000);
			Reporter.log("Swipped /Scrolled from Bottom to Top");

		}
	}

	// Tapping on Element

	public void tapOnentrBtn() throws InterruptedException {

		// working Co-ordinate position with X and Y co-ordinates

		int x = 990;
		int y = 1900;
		TouchAction tapCoordinates = new TouchAction(driver);
		Thread.sleep(2000);
		tapCoordinates.tap(x, y).perform();
		Reporter.log("Search button is clicked");

	}

	// Verify Horizontal scroll from Now to 23hrs

	public void checkweather_details() throws InterruptedException

	{
		// get the weather timings and Add weather timings details in List and
		// retrive

		WebElement element = driver.findElementById("com.climate.farmrise:id/hourlyWeatherForecastScrollView");

		WebElement hourElement = driver.findElementByXPath("//android.widget.TextView[@text='Now']");

		List<String> timeList = new ArrayList<String>();
		List<MobileElement> list1 = driver.findElements(
				By.xpath("//android.widget.TextView[@resource-id='com.climate.farmrise:id/hourlyValue']"));
		System.out.println(list1.size());
		for (int j = 0; j < list1.size(); j++) {
			Reporter.log("----------------------" + list1.get(j).getText());
			timeList.add(list1.get(j).getText());
		}

		int hourY = hourElement.getLocation().getY();
		for (int i = 0; i < 6; i++) {
			Thread.sleep(2000);
			swipeElement_weather(element, hourY);
			list1 = driver.findElements(
					By.xpath("//android.widget.TextView[@resource-id='com.climate.farmrise:id/hourlyValue']"));
			System.out.println(list1.size());
			for (int j = 0; j < list1.size(); j++) {
				System.out.println("----------------------" + list1.get(j).getText());
				timeList.add(list1.get(j).getText());
			}

		}

		String[] tempTimeList = new String[timeList.size()];
		timeList.toArray(tempTimeList);
		for (int i = 0; i < timeList.size(); i++) {
			Reporter.log("----------------------++++++++++++++" + tempTimeList[i]);
		}

		List<String> finalTimeList = new ArrayList<String>();
		boolean duplicate = false;
		finalTimeList.add(tempTimeList[0]);
		for (int k = 0; k < tempTimeList.length; k++) {
			for (int m = 0; m < finalTimeList.size(); m++) {
				if (finalTimeList.get(m).equals(tempTimeList[k])) {
					// System.out.println("////////////////////////////////////////////////////////////////Duplicate
					// found"+ tempTimeList[k]);
					duplicate = true;
					break;
				}
			}

			if (!duplicate) {
				finalTimeList.add(tempTimeList[k]);
			}
			duplicate = false;
		}

		String time = driver.getDeviceTime();

		Reporter.log("Time displayed in device   is :" + time);

		Reporter.log("*****************************************************************");

		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("h a");
		String datetime = dateformat.format(c.getTime());
		System.out.println(datetime);

		String[] parts = datetime.split(" ");
		Integer currentTime = Integer.valueOf(parts[0]);
		String currentZone = parts[1];

		boolean checkResult = true;
		for (int i = 1; i < finalTimeList.size(); i++) {
			// System.out.println("Time : " + finalTimeList.get(i));

			if (currentTime == 11 && currentZone.equals("PM")) {
				currentZone = "AM";
			} else if (currentTime == 11 && currentZone.equals("AM")) {
				currentZone = "PM";
			}

			if (currentTime == 12) {
				currentTime = 1;
			} else {
				currentTime = currentTime + 1;
			}

			String nextTime = Integer.toString(currentTime) + " " + currentZone;

			if (!finalTimeList.get(i).equals(nextTime)) {
				checkResult = false;
				break;
			}
		}

		if (checkResult && (finalTimeList.size() == 24)) {
			Reporter.log("Time format is proper");
		} else {
			Reporter.log("Time format is not proper");
		}
	}

}
