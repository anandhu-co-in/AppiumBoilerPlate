import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class helloworld {

    public static void main(String[] args) throws MalformedURLException {

        //SETUP
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "9");
        capabilities.setCapability("deviceName", "d4bad61f0404");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
        capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
        AndroidDriver driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //AUTOMATE
        WebElement two=driver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text"));
        two.sendKeys("IT WORKED!!");
    }


}
