package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class NativeLoginPageObject extends AbstractPageObject {
    private static final String packageName = "platkovsky.alexey.epamtestapp:";
    @AndroidFindBy(id = packageName + "id/login_email")
    private WebElement emailField;
    @AndroidFindBy(id = packageName + "id/login_pwd")
    private WebElement passwordField;
    @AndroidFindBy(id = packageName + "id/email_sign_in_button")
    private WebElement signInBtn;
    @AndroidFindBy(id = packageName + "id/register_button")
    private WebElement registerNewAccountBtn;

    public NativeLoginPageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }
}
