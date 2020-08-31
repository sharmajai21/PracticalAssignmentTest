package ApplicationPages;

import Common.UIBasePageFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorsPage extends UIBasePageFactory {
    //region UI Elements
    @FindBy(xpath = "//h1[contains(text(), 'Westpac KiwiSaver Scheme Risk Profiler and Retirement Calculator')]")
    private WebElement headerText;

    @FindBy(xpath = "//a[text()='Click here to get started.']")
    private WebElement kiwiSaverCalcBtn;

    //endregion

    //region UI Actions
    public boolean isCalculatorsPageDisplayed() {
        try {
            return headerText.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnGetStartedBtn() {
        kiwiSaverCalcBtn.click();
    }
    //endregion
}
