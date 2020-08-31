package Common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper extends BaseClass {
    private static final int TIME_OUT_IN_SECONDS_MIN = 60;
    private static final int TIME_OUT_IN_SECONDS_MAX = 120;
    private static final int POLLING_INTERVAL_IN_MILLIS = 10;

    public static void waitAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    public static void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementVisibilityMaxTimeout(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS_MAX, POLLING_INTERVAL_IN_MILLIS);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void waitForTextToMatch(WebElement element, String textToMatch) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS_MIN, POLLING_INTERVAL_IN_MILLIS);
        wait.until(anyTextToBePresentInElement(element, textToMatch, true));
    }


    public static void waitForValueToMatch(WebElement element, String textToMatch) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS_MIN, POLLING_INTERVAL_IN_MILLIS);
        wait.until(anyTextToBePresentInElement(element, textToMatch, false));
    }

    private static ExpectedCondition<Boolean> anyTextToBePresentInElement(
            final WebElement element, final String textToMatch, final boolean isText) {

        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText;
                    if (isText)
                        elementText = element.getText().toLowerCase();
                    else
                        elementText = element.getAttribute("value").toLowerCase();
                    return elementText.contains(textToMatch.toLowerCase());
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("text to be present in element %s", element);
            }
        };
    }

    public static void waitPageForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition =
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS_MAX, POLLING_INTERVAL_IN_MILLIS);
        wait.until(pageLoadCondition);
    }

}
