package scenarios;

import TestData.CredentialsGenerating;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.util.concurrent.TimeUnit;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Test checks an ability to register and log in")
    public void registerAndLoginNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        if(nativePopupsPageObject.getEmailAutofillPopup().isDisplayed()) {
//            nativePopupsPageObject.getEmailAutofillPopup().click();
//        }
//        System.out.println("pop up closed");
        nativeLoginPageObject.getRegisterNewAccountBtn().click();
        System.out.println("Register new account button at login page clicked");
        CredentialsGenerating credentialsGenerating = new CredentialsGenerating();
        nativeRegistrationPageObject.getRegistrationEmailField().sendKeys(credentialsGenerating.email);
        nativeRegistrationPageObject.getRegistrationUsernameField().sendKeys(credentialsGenerating.userName);
        nativeRegistrationPageObject.getRegistrationPasswordField().sendKeys(credentialsGenerating.password);
        nativeRegistrationPageObject.getRegistrationConfirmPasswordField().sendKeys(credentialsGenerating.password);
        System.out.println("Registration fields filled");
        nativeRegistrationPageObject.getRegisterNewAccountBtn().click();
        System.out.println("Register new account button at registration page clicked");
    }

}
