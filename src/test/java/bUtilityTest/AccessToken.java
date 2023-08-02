package bUtilityTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * this has the classes for connection and other operations that are used as utilities
 */

public class AccessToken {

    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "src/test/configuration/api-accesstokens.properties";
    private static AccessToken instance;

    private AccessToken() throws IOException {

        try (InputStream inputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            // Handle file loading errors here
            e.printStackTrace();
        }
    }

    public static AccessToken getInstance() throws IOException {
        if (instance == null) {
            instance = new AccessToken();
        }
        return instance;
    }

    public String getAccessToken() {
        return properties.getProperty("api.bearer_token");
    }


}
