import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WithAppInstallation {

    public static void main(String[] args) throws MalformedURLException {

        //SETUP
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "9");
        capabilities.setCapability("deviceName", "d4bad61f0404");
        capabilities.setCapability("platformName", "Android");

        //These two lines to prevent reinstall if app already installed. But not tested to confirm it
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);

        capabilities.setCapability("app", System.getProperty("user.dir")+"\\src\\test\\java\\demoApp3.apk");
        AndroidDriver driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

}
