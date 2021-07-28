package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class NativePopupsPageObject {

    private static final String packageName = "platkovsky.alexey.epamtestapp:";
    @AndroidFindBy(id = packageName + "id/autofill_dataset_list")
    private WebElement emailAutofillPopup;

    public NativePopupsPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }
}
