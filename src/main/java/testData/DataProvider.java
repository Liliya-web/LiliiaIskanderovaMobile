package testData;

public class DataProvider {
    @org.testng.annotations.DataProvider
    public static Object[][] registerAndLoginDataProvider() {
        CredentialsGenerating credentialsGenerating = new CredentialsGenerating();
        return new Object[][]{
                {credentialsGenerating.email, credentialsGenerating.userName,
                        credentialsGenerating.password, "BudgetActivity"}
        };
    }

    @org.testng.annotations.DataProvider
    public static Object[][] logInWithoutCredentialsDataProvider() {
        CredentialsGenerating credentialsGenerating = new CredentialsGenerating();
        return new Object[][]{
                {"Incorrect email or password"}
        };
    }
}
