package util;

import org.testng.Reporter;

import Testscript.BaseClass;

public class select_Crop extends BaseClass {

	public void select_crop() throws Exception {
		try {

			boolean seeds = false;

			if (seeds == true) {

				driver.findElementByXPath("//android.widget.TextView[@text='Beetroot']").click();
				Reporter.log("Crop page Appeared and Crop has been selected");
				Thread.sleep(2000); // Proceed After Selecting a crop

				driver.findElementById("com.climate.farmrise:id/btn_Done").click();

				// Allow Access to device Location

				driver.findElementById("com.climate.farmrise:id/btn_allow_proceed").click();

			}

			else {
				// driver.findElementById("com.climate.farmrise:id/action_home").click();
				Reporter.log("Crop page doesnot appear. Looks like CROP is already selected on this device.");

			}
		}

		catch (Exception e) {

			System.out.println("--------Unable to Select crop---------------");
			throw e;
		}

	}

}
