package Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class BaseClass {

    public static Logger log = LogManager.getLogger(BaseClass.class.getName());
    public static WebDriver driver;

    public WebDriver setupDriver() {
        try {
            initiateDriver();
            initializePages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static void setDriversSystemProperties() {
        if (System.getProperty("os.name").toLowerCase().contains("windows 10")) {
            System.setProperty("webdriver.edge.driver", getDriverPath("win", "MicrosoftWebDriver.exe"));
        }
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            System.setProperty("webdriver.chrome.driver", getDriverPath("win", "chromedriver.exe"));
            System.setProperty("webdriver.ie.driver", getDriverPath("win", "IEDriverServer.exe"));
            System.setProperty("webdriver.gecko.driver", getDriverPath("win", "geckodriver.exe"));
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", getDriverPath("mac", "chromedriver"));
            System.setProperty("webdriver.gecko.driver", getDriverPath("mac", "geckodriver"));
        } else {
            System.setProperty("webdriver.chrome.driver", getDriverPath("linux", "chromedriver"));
            System.setProperty("webdriver.gecko.driver", getDriverPath("linux", "geckodriver"));
        }
    }

    private static String getDriverPath(String os, String driverName) {
        String path = System.getProperty("user.dir").concat("/src/test/resources/drivers/");
        return path.concat(os).concat(File.separator).concat(driverName);
    }

    //@ConcurrentParameterized.Parameters
    public static void initiateDriver() throws Exception {
        log.info("Initializing selenium webdriver instance");
        String browser = getBrowser();
        setDriversSystemProperties();
        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions option = new ChromeOptions();
                option.setExperimentalOption("useAutomationExtension", false);
                option.setAcceptInsecureCerts(true);
                option.addArguments("--disable-infobars");
                driver = new ChromeDriver(option);
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                break;

            case "edge":
                if (System.getProperty("os.name").contains("Windows 10")) {
                    driver = new EdgeDriver();
                } else {
                    throw new Exception("Edge not supported on this OS");
                }
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                firefoxOptions.addArguments("--disable-infobars");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "ie":
                if (System.getProperty("os.name").contains("Win")) {
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    ieOptions.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
                    ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    driver = new InternetExplorerDriver(ieOptions);
                    driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
                } else {
                    throw new Exception("ie is only supported on windows");
                }
                break;

            default:
                log.info("No browser value configured. please configured browser to be executed in config file");
        }
        driver.manage().window().maximize();
        log.info("Driver Initiated");
    }

    public void initializePages() {
    }

    public void getPage() {
        log.info("Getting URL: " + CommonUtils.envConfigValue("base.url"));
        driver.get(CommonUtils.envConfigValue("base.url"));
    }

    private static String getBrowser() {
            String browser="";
            try {
                browser=CommonUtils.getConfigValue("browser");
                if (null != browser && !browser.isEmpty()) {
                    return browser;
                } else {
                    return  browser="chrome";
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.info("Could not fetch browser to be executed");
            }
        return browser;
    }


}
