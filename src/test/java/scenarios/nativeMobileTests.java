package scenarios;

import Steps.NativeSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BaseTest;
import testData.DataProvider;

public class nativeMobileTests extends BaseTest {

    NativeSteps nativeSteps;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.out.println("Init steps");
        nativeSteps = new NativeSteps();
    }

    @Test(groups = {"native"}, description = "Test checks an ability to register and log in with email/username and password",
            dataProvider = "registerAndLoginDataProvider", dataProviderClass = DataProvider.class)
    public void registerAndLoginWithEmailAndPasswordNativeTest(String emailOrUserName, String email, String userName, String password,
                                                               String finalPageName) {
        nativeSteps.registerNewAccount(email, userName, password);
        if (emailOrUserName == "email") {
            nativeSteps.login(email, password);
        } else {
            nativeSteps.login(userName, password);
        }
        nativeSteps.verifyThatUserIsOnBudgetActivityPage(finalPageName);
        System.out.println("Test DONE");
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
}
