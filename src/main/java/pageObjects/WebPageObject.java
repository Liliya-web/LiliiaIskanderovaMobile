package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject extends AbstractPageObject {

    public WebPageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);

    }


}
