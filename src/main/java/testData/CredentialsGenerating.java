package testData;

import com.github.javafaker.Faker;

public class CredentialsGenerating {
    private Faker faker = new Faker();
    public final String email = faker.lorem().word() + "@mail.ru";
    public final String userName = faker.lorem().word();
    public final String password = faker.lorem().fixedString(10);
}
