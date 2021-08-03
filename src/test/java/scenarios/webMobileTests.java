package scenarios;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import setup.BaseTest;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that Google search results for key word EPAM are relevant")
    public void searchEPAMByGoogleTest() throws InterruptedException {
        final String textToSearch = "EPAM";
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
