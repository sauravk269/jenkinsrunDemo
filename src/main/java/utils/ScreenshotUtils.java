package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtils {

    public static void TakeScreenshot(WebDriver driver, String testName){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile= ts.getScreenshotAs(OutputType.FILE);
        String destFile= System.getProperty("User.dir")+"/Screenshot"+testName+"_"+System.currentTimeMillis()+".png";
        try {
            Files.copy(srcFile.toPath(), Path.of(destFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
