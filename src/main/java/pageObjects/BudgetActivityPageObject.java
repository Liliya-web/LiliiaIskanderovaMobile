package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class BudgetActivityPageObject extends AbstractPageObject{
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[2]//android.widget.TextView")
    private WebElement budgetActivityTitle;

    public BudgetActivityPageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }
}
