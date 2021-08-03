package scenarios;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;
import testData.DataProvider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class nativeMobileTests extends BaseTest {
//    @Test(groups = {"native"}, description = "Test checks that user have not an ability to sign in without credentials",
//            dataProvider = "logInWithoutCredentialsDataProvider", dataProviderClass = DataProvider.class)
//    public void userCanNotSignInWithoutCredentialsNativeTest(String expectedMessage)
//            throws TesseractException, AWTException, IOException {
//        final String pathToScreenshot = "target/screenshots/screenshot.png";
//        System.out.println("Click Sign in button");
//        nativeLoginPageObject.getSignInBtn().click();
//        System.out.println("Sign in button clicked\nVerifying that message '" + expectedMessage + "' appears");
////        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
////        Tesseract tesseract = new Tesseract();
////        tesseract.setDatapath("src/main/resources/tessdata");
////        tesseract.setLanguage("eng");
////        assertThat(tesseract.doOCR(file))
////                .as("'" + expectedMessage + "' was not found on the page")
////                .contains(expectedMessage);
//
//        Rectangle screenRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//        BufferedImage image = new Robot().createScreenCapture(screenRectangle);
//        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath("src/main/resources/tessdata");
//        tesseract.setLanguage("eng");
//        assertThat(tesseract.doOCR(image))
//                .as("'" + expectedMessage + "' was not found on the page")
//                .contains(expectedMessage);
//        System.out.println("Message verified\nTest DONE");
//    }

    @Test(groups = {"native"}, description = "Test checks an ability to register and log in",
            dataProvider = "registerAndLoginDataProvider", dataProviderClass = DataProvider.class)
    public void registerAndLoginNativeTest(String email, String userName, String password)
            throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        final String budgetActivity = "BudgetActivity";
        System.out.println("Click Register new account button at login page");
        nativeLoginPageObject.getRegisterNewAccountBtn().click();

        System.out.println("Fill registration fields");
        nativeRegistrationPageObject.getRegistrationEmailField().sendKeys(email);
        nativeRegistrationPageObject.getRegistrationUsernameField().sendKeys(userName);
        nativeRegistrationPageObject.getRegistrationPasswordField().sendKeys(password);
        nativeRegistrationPageObject.getRegistrationConfirmPasswordField().sendKeys(password);
        System.out.println("Register new account");
        nativeRegistrationPageObject.getRegisterNewAccountBtn().click();

        System.out.println("New account registered\nLogging in");
        nativeLoginPageObject.getEmailField().sendKeys(email);
        nativeLoginPageObject.getPasswordField().sendKeys(password);
        nativeLoginPageObject.getSignInBtn().click();
        System.out.println("User logged in\nVerifying that user is on Budget Activity page");
        (new WebDriverWait(getDriver(), 3)).until(ExpectedConditions
                .refreshed(ExpectedConditions.visibilityOf(budgetActivityPageObject.getBudgetActivityTitle())));
        assertThat(budgetActivityPageObject.getBudgetActivityTitle().getText())
                .as("User is not on" + budgetActivity + "page").isEqualTo(budgetActivity);
        System.out.println("Page verified\nTest DONE");
    }
}
