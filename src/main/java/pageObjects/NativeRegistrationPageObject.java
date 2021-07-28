package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class NativeRegistrationPageObject extends AbstractPageObject {
    private static final String packageName = "platkovsky.alexey.epamtestapp:";
    @AndroidFindBy(id = packageName + "id/registration_email")
    private WebElement registrationEmailField;
    @AndroidFindBy(id = packageName + "id/registration_username")
    private WebElement registrationUsernameField;
    @AndroidFindBy(id = packageName + "id/registration_password")
    private WebElement registrationPasswordField;
    @AndroidFindBy(id = packageName + "id/registration_confirm_password")
    private WebElement registrationConfirmPasswordField;
    @AndroidFindBy(id = packageName + "id/register_new_account_button")
    private WebElement registerNewAccountBtn;

    public NativeRegistrationPageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }
}
