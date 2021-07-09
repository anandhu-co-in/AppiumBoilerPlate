import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class WithAppiumServerAutoStart {

    private static AppiumServiceBuilder builder;
    private static AppiumDriverLocalService appiumService;

    public static void main(String[] args) throws MalformedURLException {

                builder = new AppiumServiceBuilder();
//                builder.withAppiumJS(new File("C:\\Users\\anandhukrishnan.v\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
                builder.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"));
                builder.withIPAddress("0.0.0.0");
                builder.usingPort(4724);
                builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
                //builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error"); //<-- This line if disabled will show all appium logs in terminal
                appiumService = AppiumDriverLocalService.buildService(builder);
                appiumService.start();

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("BROWSER_NAME", "Android");
                capabilities.setCapability("VERSION", "9");
                capabilities.setCapability("deviceName", "d4bad61f0404");
                capabilities.setCapability("platformName", "Android");
                //These two lines to prevent reinstall if app already installed. But not tested to confirm it
                capabilities.setCapability("noReset", true);
                capabilities.setCapability("fullReset", false);
                capabilities.setCapability("app", System.getProperty("user.dir")+"\\src\\test\\java\\demoApp3.apk");

                System.out.println("Appium server URL is : "+appiumService.getUrl().toString());
                AndroidDriver driver = new AndroidDriver<>(appiumService.getUrl(), capabilities);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

                //END
                driver.quit();
                appiumService.stop();
    }

}

//NOTES
//1. Since i auto starting appium server, i just got the url of it using appiumService.getUrl(), Or you can give the url on your own there
//2. Note that the appium JS path is actually the path to main.js
//3. As per my finding, you can use the main.js, from either your systems nodejs nodemodules, or the nodemodules in appium desktop installlation (I have commended on of them above)
//4. Also i noticed, that if errored before appium.service.stop(), server wont be stope, next time youn run it will show address already use(Though automation works)
//5. But when you run like that, appiumservice.stop(), wont work further to stop server.. Guess i have to manually kill the port's process through admin cmd