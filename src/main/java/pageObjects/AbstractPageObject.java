package pageObjects;

import io.appium.java_client.AppiumDriver;

public abstract class AbstractPageObject {
    protected AppiumDriver appiumDriver;
    protected static final String PACKAGE_NAME = "platkovsky.alexey.epamtestapp:";
    protected static final String TYPE_STATIC_TEXT = "//XCUIElementTypeStaticText";
    protected static final String TYPE_SWITCH = "//XCUIElementTypeSwitch";
    protected static final String TYPE_TEXT_FIELD = "//XCUIElementTypeTextField";
    protected static final String TYPE_SECURE_TEXT_FIELD = "//XCUIElementTypeSecureTextField";
}
