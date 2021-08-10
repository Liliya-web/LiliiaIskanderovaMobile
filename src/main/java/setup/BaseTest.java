package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static propertyLoader.PropertyLoader.getProperties;

public class BaseTest implements IDriver {
    protected WebPageObject webPageObject;
    protected NativeLoginPageObject nativeLoginPageObject;
    protected NativeRegistrationPageObject nativeRegistrationPageObject;
    protected BudgetActivityPageObject budgetActivityPageObject;
    private static final Properties envProperties = getProperties();
    private static final String PROJECT_NAME = envProperties.getProperty("PROJECT.NAME");
    private static final String TOKEN = envProperties.getProperty("TOKEN");
    private static final String APPIUM_HUB = envProperties.getProperty("APPIUM.HUB");

    private static AppiumDriver appiumDriver; // singleton

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    @Parameters({"platformName", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
    }

    @Parameters("appType")
    @BeforeMethod(alwaysRun = true)
    public void setUp(String appType) throws Exception {
        System.out.println("Before: app type - " + appType);
        switch (appType) {
            case "web":
                webPageObject = new WebPageObject(appiumDriver);
                break;
            case "native":
                nativeLoginPageObject = new NativeLoginPageObject(appiumDriver);
                nativeRegistrationPageObject = new NativeRegistrationPageObject(appiumDriver);
                budgetActivityPageObject = new BudgetActivityPageObject(appiumDriver);
                break;
            default:
                throw new Exception("Can't create a page objects for " + appType);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void closeDriver() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName,
                                 String app, String appPackage, String appActivity, String bundleId) throws UnsupportedEncodingException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("ignoreHiddenApiPolicyError", "true");

        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);
        if (platformName.equals("iOS")) capabilities.setCapability("automationName", "XCUITest");

        String token = URLEncoder.encode(TOKEN, StandardCharsets.UTF_8.name());
        System.out.println("Before: setting up Appium Driver");
        try {
            appiumDriver = new AppiumDriver(new URL(
                    format("https://%s:%s@%s/wd/hub", PROJECT_NAME, token, APPIUM_HUB)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }
}
