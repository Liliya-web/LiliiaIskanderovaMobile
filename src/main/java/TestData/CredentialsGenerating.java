package TestData;

import com.github.javafaker.Faker;

public class CredentialsGenerating {
    Faker faker = new Faker();
    public final String email = faker.lorem().word() + "@mail.ru";
    public final String userName = faker.lorem().word();
    public final String password = faker.lorem().fixedString(10);
}
