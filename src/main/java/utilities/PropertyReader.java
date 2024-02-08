package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String readProperty(String key) {
        String value = "";
        try (InputStream input = new FileInputStream("./src/main/resources/configuration.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            value = prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
