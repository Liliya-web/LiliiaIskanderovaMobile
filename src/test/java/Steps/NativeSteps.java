package Steps;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BudgetActivityPageObject;
import pageObjects.NativeLoginPageObject;
import pageObjects.NativeRegistrationPageObject;
import setup.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeSteps extends BaseTest {

    private NativeLoginPageObject nativeLoginPageObject;
    private NativeRegistrationPageObject nativeRegistrationPageObject;
    private BudgetActivityPageObject budgetActivityPageObject;

    public NativeSteps() {
        System.out.println("Init page objects");
        nativeLoginPageObject = new NativeLoginPageObject(getDriver());
        nativeRegistrationPageObject = new NativeRegistrationPageObject(getDriver());
        budgetActivityPageObject = new BudgetActivityPageObject(getDriver());
    }
    public void registerNewAccount(String email, String userName, String password) {
        System.out.println("Click Register new account button at login page");
        nativeLoginPageObject.getRegisterNewAccountBtn().click();
        System.out.println("Fill registration fields");
        nativeRegistrationPageObject.getRegistrationEmailField().sendKeys(email);
        nativeRegistrationPageObject.getRegistrationUsernameField().sendKeys(userName);
        nativeRegistrationPageObject.getRegistrationPasswordField().sendKeys(password);
        nativeRegistrationPageObject.getRegistrationConfirmPasswordField().sendKeys(password);
        nativeRegistrationPageObject.getConfirmationCheckbox().click();
        System.out.println("Register new account");
        nativeRegistrationPageObject.getRegisterNewAccountBtn().click();
        System.out.println("New account is registered");
    }

    public void login(String emailOrUserName, String password) {
        System.out.println("Logging in");
        nativeLoginPageObject.getEmailField().sendKeys(emailOrUserName);
        nativeLoginPageObject.getPasswordField().sendKeys(password);
        nativeLoginPageObject.getSignInBtn().click();
    }
    public void verifyThatUserIsOnBudgetActivityPage(String finalPageName) {
        System.out.println("User logged in\nVerifying that user is on Budget Activity page");
        (new WebDriverWait(getDriver(), 3)).until(ExpectedConditions
                .refreshed(ExpectedConditions.visibilityOf(budgetActivityPageObject.getBudgetActivityTitle())));
        assertThat(budgetActivityPageObject.getBudgetActivityTitle().getText())
                .as("User is not on" + finalPageName + "page").isEqualTo(finalPageName);
        System.out.println("Page verified");
    }
}
