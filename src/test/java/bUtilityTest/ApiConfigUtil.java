package bUtilityTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * this has the classes for connection and other operations that are used as utilities
 */

public class ApiConfigUtil{

    private static final String CONFIG_FILE = "src/test/configuration/api-endpoints.properties";
    private static final Properties properties = new Properties();
    private static ApiConfigUtil instance;

    private ApiConfigUtil() {
        try (InputStream inputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            // Handle file loading errors here
        }
    }
    public static ApiConfigUtil getInstance() {
        if (instance == null) {
            instance = new ApiConfigUtil();
        }
        return instance;
    }

    public String getBaseUrl() {
        return properties.getProperty("api.endpoint.baseurl");
    }

    public String getEndpointForGet() {
        return getBaseUrl() + properties.getProperty("api.endpoint.get");
    }

    public String getEndpointForPost() {
        return getBaseUrl() + properties.getProperty("api.endpoint.post");
    }

    public String getEndpointForPut() {
        return getBaseUrl() + properties.getProperty("api.endpoint.put");
    }

    public String getEndpointForDelete() {
        return getBaseUrl() + properties.getProperty("api.endpoint.delete");
    }
}







