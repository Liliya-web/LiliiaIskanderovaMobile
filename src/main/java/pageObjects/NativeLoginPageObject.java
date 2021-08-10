package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class NativeLoginPageObject extends AbstractPageObject {
    @AndroidFindBy(id = PACKAGE_NAME + "id/login_email")
    @iOSXCUITFindBy(xpath = TYPE_TEXT_FIELD + "[@value='user@example.com']")
    private WebElement emailField;
    @AndroidFindBy(id = PACKAGE_NAME + "id/login_pwd")
    @iOSXCUITFindBy(xpath = TYPE_SECURE_TEXT_FIELD + "[@value='Required']")
    private WebElement passwordField;
    @AndroidFindBy(id = PACKAGE_NAME + "id/email_sign_in_button")
    @iOSXCUITFindBy(xpath = TYPE_STATIC_TEXT + "[@value='Sign In']")
    private WebElement signInBtn;
    @AndroidFindBy(id = PACKAGE_NAME + "id/register_button")
    @iOSXCUITFindBy(xpath = TYPE_STATIC_TEXT + "[@value='Register new account']")
    private WebElement registerNewAccountBtn;

    public NativeLoginPageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }
}
