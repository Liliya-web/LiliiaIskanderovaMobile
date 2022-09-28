package scenarios;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import setup.BaseTest;
import testData.DataProvider;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class nativeMobileTests extends BaseTest {
    @AfterMethod(groups = {"native"}, alwaysRun = true)
    public void postConditions() {
        System.out.println("After method: navigate back and clear fields");
        getDriver().navigate().back();
        nativeLoginPageObject.getEmailField().clear();
        nativeLoginPageObject.getPasswordField().clear();
    }

    @Test(groups = {"native"}, description = "Test checks an ability to register and log in with email and password",
            dataProvider = "registerAndLoginDataProvider", dataProviderClass = DataProvider.class)
    public void registerAndLoginWithEmailAndPasswordNativeTest(String email, String userName, String password,
                                                               String finalPageName) {
        registerNewAccount(email, userName, password);
        System.out.println("Logging in");
        nativeLoginPageObject.getEmailField().sendKeys(email);
        nativeLoginPageObject.getPasswordField().sendKeys(password);
        nativeLoginPageObject.getSignInBtn().click();
        System.out.println("User logged in\nVerifying that user is on Budget Activity page");
        (new WebDriverWait(getDriver(), 3)).until(ExpectedConditions
                .refreshed(ExpectedConditions.visibilityOf(budgetActivityPageObject.getBudgetActivityTitle())));
        assertThat(budgetActivityPageObject.getBudgetActivityTitle().getText())
                .as("User is not on " + finalPageName + " page").isEqualTo(finalPageName);
        System.out.println("Page verified\nTest DONE");
    }

    @Test(groups = {"native"}, description = "Test checks an ability to register and log in with username and password",
            dataProvider = "registerAndLoginDataProvider", dataProviderClass = DataProvider.class)
    public void registerAndLoginWithUsernameAndPasswordNativeTest(String email, String userName, String password,
                                                                  String finalPageName) {
        registerNewAccount(email, userName, password);
        System.out.println("Logging in");
        nativeLoginPageObject.getEmailField().sendKeys(userName);
        nativeLoginPageObject.getPasswordField().sendKeys(password);
        nativeLoginPageObject.getSignInBtn().click();
        System.out.println("User logged in\nVerifying that user is on Budget Activity page");
        (new WebDriverWait(getDriver(), 3)).until(ExpectedConditions
                .refreshed(ExpectedConditions.visibilityOf(budgetActivityPageObject.getBudgetActivityTitle())));
        assertThat(budgetActivityPageObject.getBudgetActivityTitle().getText())
                .as("User is not on" + finalPageName + "page").isEqualTo(finalPageName);
        System.out.println("Page verified\nTest DONE");
    }

//    @Test(groups = {"native"}, description = "Test checks that user have not an ability to sign in without credentials",
//            dataProvider = "logInWithoutCredentialsDataProvider", dataProviderClass = DataProvider.class)
//    public void userCanNotSignInWithoutCredentialsNativeTest(String expectedMessage)
//            throws TesseractException, AWTException, IOException {
//        final String pathToScreenshot = "target/screenshots/screenshot.png";
//        System.out.println("Click Sign in button");
//        nativeLoginPageObject.getSignInBtn().click();
//        System.out.println("Sign in button clicked\nVerifying that message '" + expectedMessage + "' appears");
//        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
////        String filePath = "target/screenshots/screenshot.png";
////        FileUtils.copyFile(file, new File(filePath));
//        System.out.println("Screenshot took");
//        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath("src/main/resources/tessdata");
//        tesseract.setLanguage("eng");
//        System.out.println("Doing OCR");
//        assertThat(tesseract.doOCR(file))
//                .as("'" + expectedMessage + "' was not found on the page")
//                .contains(expectedMessage);
////        assertThat(tesseract.doOCR(new File(filePath)))
////                .as("'" + expectedMessage + "' was not found on the page")
////                .contains(expectedMessage);
//        System.out.println("Message verified\nTest DONE");
//
////        Rectangle screenRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
////        System.out.println("Screen size got");
////        BufferedImage image = new Robot().createScreenCapture(screenRectangle);
////        System.out.println("Screenshot took");
////        Tesseract tesseract = new Tesseract();
////        tesseract.setDatapath("src/main/resources/tessdata");
////        tesseract.setLanguage("eng");
////        System.out.println("Doing OCR");
////        assertThat(tesseract.doOCR(image))
////                .as("'" + expectedMessage + "' was not found on the page")
////                .contains(expectedMessage);
////        System.out.println("Message verified\nTest DONE");
//    }

    private void registerNewAccount(String email, String userName, String password) {
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
}
