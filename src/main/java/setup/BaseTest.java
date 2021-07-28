package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {
    protected WebPageObject webPageObject;
    protected NativeLoginPageObject nativeLoginPageObject;
    protected NativeRegistrationPageObject nativeRegistrationPageObject;
    protected NativePopupsPageObject nativePopupsPageObject;

    private static AppiumDriver appiumDriver; // singleton
    IPageObject po;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "browserName", "app"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName, @Optional("") String browserName,
                      @Optional("") String app) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app);
        setPageObject(appType, appiumDriver);

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    @Parameters("appType")
    @BeforeMethod
    public void setUp(String appType) throws Exception {
        switch (appType) {
            case "web":
                webPageObject = new WebPageObject(appiumDriver);
                break;
            case "native":
                nativeLoginPageObject = new NativeLoginPageObject(appiumDriver);
                nativeRegistrationPageObject = new NativeRegistrationPageObject(appiumDriver);
                nativePopupsPageObject = new NativePopupsPageObject(appiumDriver);
                break;
            default:
                throw new Exception("Can't create a page objects for " + appType);
        }
    }

    private void setAppiumDriver(String platformName, String deviceName,
                                 String browserName, String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
//        capabilities.setCapability("ignoreHiddenApiPolicyError", "true");

        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }
}
