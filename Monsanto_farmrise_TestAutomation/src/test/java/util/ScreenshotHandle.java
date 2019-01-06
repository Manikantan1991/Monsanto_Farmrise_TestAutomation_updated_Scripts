package util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import Testscript.BaseClass;

public class ScreenshotHandle extends BaseClass {

	public void takeScreenshot(String filename) throws IOException {
		// take Screenshot and store it in a File Format
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Now Copy the Screenshot into desired Location using copyFile method

		FileUtils.copyFile(srcfile,
				new File("E:\\Monsanto_farmrise_TestAutomation\\Screenshots\\" + filename + ".jpg"));

		Reporter.log("Screenshots Captured");
	}

}
