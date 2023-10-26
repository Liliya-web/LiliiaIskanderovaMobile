package scenarios;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.WebPageObject;
import setup.BaseTest;
import testData.DataProvider;

public class WebMobileTests extends BaseTest {

    private WebPageObject webPageObject;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.out.println("Init page objects");
        webPageObject = new WebPageObject(getDriver());
    }

    @Test(groups = {"web"}, description = "Make sure that Google search results for key word EPAM are relevant",
            dataProvider = "searchEPAMDataProvider", dataProviderClass = DataProvider.class)
    public void searchEPAMByGoogleTest(String textToSearch) throws InterruptedException {
        getDriver().get("http://google.com"); // open Google Search page
        webPageObject.getGoogleSearchField().sendKeys(textToSearch);
        webPageObject.getGoogleSearchButton().click();
        // Check Google search results
        System.out.println("Checking google search results");
        SoftAssertions softAssertions = new SoftAssertions();
        webPageObject.getGoogleSearchResults().forEach(searchResult ->
                softAssertions.assertThat(searchResult.getText())
                        .as("The search result '" + searchResult.getText() + "' not contains '" + textToSearch + "'")
                        .contains(textToSearch));
        softAssertions.assertAll();
        // Log that test finished
        System.out.println("Search results asserted");
    }

}
