package Common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtils {

    public static String getConfigValue(String key) {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("target/classes/properties/config"));
            System.out.print("Returning config Value of key " + key.toUpperCase()  + "--" + config.getProperty(key));
            return config.getProperty(key);
        } catch (Throwable t) {
            System.out.print("Issue loading properties file --" + t.getMessage());
            return null;
        }
    }

    public static String envConfigValue(String key) {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("target/classes/EnvironmentProfiles/" +
                    getConfigValue("Environment")
                    + ".properties"));

        } catch (Throwable t) {
            t.printStackTrace();
            System.out.print("Issue loading properties file - loading default env");

            InputStream inputStream = CommonUtils.class.getResourceAsStream("/environmentProfiles/" +getConfigValue("defaultEnvironment")  + ".properties");

        }
        return config.getProperty(key);

    }


}
