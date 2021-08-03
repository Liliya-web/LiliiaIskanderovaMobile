package pageObjects;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class WebPageObject extends AbstractPageObject {
    @FindBy(xpath = "//input[@name=\"q\"]")
    private WebElement googleSearchField;
    @FindBy(xpath = "//form[@name=\"gs\"]//li[1]/div[1]")
    private WebElement googleSearchButton;
    @FindBy(xpath = "//div[@id=\"rso\"]/div/div/div/div[1]/div/a/div[2]/div")
    private List<WebElement> googleSearchResults;

    public WebPageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }
}
