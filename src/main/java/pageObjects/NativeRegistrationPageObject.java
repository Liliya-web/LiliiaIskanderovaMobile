package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class NativeRegistrationPageObject extends AbstractPageObject {
    @AndroidFindBy(id = PACKAGE_NAME + "id/registration_email")
    @iOSXCUITFindBy(xpath = TYPE_TEXT_FIELD + "[@value='user@example.com']")
    private WebElement registrationEmailField;
    @AndroidFindBy(id = PACKAGE_NAME + "id/registration_username")
    @iOSXCUITFindBy(xpath = TYPE_TEXT_FIELD + "[@value='TimApple']")
    private WebElement registrationUsernameField;
    @AndroidFindBy(id = PACKAGE_NAME + "id/registration_password")
    @iOSXCUITFindBy(xpath = TYPE_SECURE_TEXT_FIELD + "[@value='Required']")
    private WebElement registrationPasswordField;
    @AndroidFindBy(id = PACKAGE_NAME + "id/registration_confirm_password")
    @iOSXCUITFindBy(xpath = TYPE_SECURE_TEXT_FIELD + "[@value='Repeat please']")
    private WebElement registrationConfirmPasswordField;
    @AndroidFindBy(xpath = "//android.widget.CheckedTextView")
    @iOSXCUITFindBy(xpath = TYPE_SWITCH + "[@label='I read agreaments and agree wit it']")
    private WebElement confirmationCheckbox;
    @AndroidFindBy(id = PACKAGE_NAME + "id/register_new_account_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Register new account']")
    private WebElement registerNewAccountBtn;

    public NativeRegistrationPageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }
}
