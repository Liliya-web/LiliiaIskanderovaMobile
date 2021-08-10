package propertyLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyLoader {
    private static final String PATH = "src/test/resources/environment.properties";
    private static Properties propertiesInstance;

    public static Properties getProperties() {
        if (propertiesInstance != null) {
            return propertiesInstance;
        }
        try (FileInputStream input = new FileInputStream(PATH)) {
            propertiesInstance = new Properties();
            propertiesInstance.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            return propertiesInstance;
        } catch (IOException e) {
            System.err.println("File with properties is not found");
        }
        return null;
    }
}
