package ApplicationPages;

import Common.UIBasePageFactory;
import Common.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KiwiSaverRetirementCalculatorPage extends UIBasePageFactory {

    //Region UI Elements
    @FindBy(xpath = "//h1[text()='KiwiSaver Retirement Calculator']")
    private WebElement headerText;

    @FindBy(xpath = "//h3[text()='About the Calculator']")
    private WebElement ftrText;

    @FindBy(xpath = "//div[@model='ctrl.data.CurrentAge']//descendant::input")
    private WebElement ageTxt;

    @FindBy(xpath = "//div[@help-id='CurrentAge' and @class='ng-isolate-scope']/button")
    WebElement ageHelpTextButton;

    @FindBy(xpath = "//p[text()='This calculator has an age limit of 18 to 64 years old.']")
    WebElement ageHelpText;

    @FindBy(xpath="//div[@selected-content='ctrl.data.EmploymentStatusHTML']")
    WebElement  empStatusDropDown;

    @FindBy(xpath = "//div[@model='ctrl.data.AnnualIncome']//descendant::input")
    private WebElement salaryOrWadgeTxt;

    @FindBy(xpath = "//div[@model='ctrl.data.KiwiSaverBalance']//descendant::input")
    private WebElement currBalanceTxt;

    @FindBy(xpath = "//div[@model='ctrl.data.VoluntaryContributions']//descendant::input")
    private WebElement volContribTxt;

    @FindBy(xpath = "//div[@model='ctrl.data.SavingsGoal']//descendant::input")
    private WebElement retireSavGoalTxt;

    @FindBy(xpath="//span[text()='Frequency']/..")
    WebElement  volunContFreqDropDown;

    //endregion

    //Region UI Actions
    public boolean isKSCalculatorPageDisplayed() {
        try {
            WaitHelper.waitPageForLoad();
            return headerText.isDisplayed() && ftrText.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;

        } catch (Exception e) {
            return false;
        }

    }

    public KiwiSaverRetirementCalculatorPage clickONAgeHelpButton(){
        driver.switchTo().frame(0);
        ageHelpTextButton.click();
        return kiwiSaverRetirementCalculatorPage;

    }

    public boolean isAgeHelpTextDispalyed(){
        try {
            return ageHelpText.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean isViewProjBtnEnabled() {
        WebElement viewRetBalanceBtn = driver.findElement(By.xpath("//span[text()='Complete the form to see your KiwiSaver retirement projections.']/.."));
        return viewRetBalanceBtn.isDisplayed();
    }

    public KiwiSaverRetirementCalculatorPage enterCurrBalance(String currBal) {
            currBalanceTxt.sendKeys(currBal.replace("$","").replace(",", "").replace(" ", "").trim());
            return kiwiSaverRetirementCalculatorPage;
    }

    public KiwiSaverRetirementCalculatorPage enterVolunContrib(String volContrib, String strFreq) {
        volContribTxt.sendKeys(volContrib);
        volunContFreqDropDown.click();
        driver.findElement(By.xpath("//span[text()='" + strFreq + "']/..")).click();
        return kiwiSaverRetirementCalculatorPage;
    }

    public KiwiSaverRetirementCalculatorPage enterSavGoals(String savGoals) {
        retireSavGoalTxt.sendKeys(savGoals.replace("$","").replace(",", "").replace(" ", "").trim());
        return kiwiSaverRetirementCalculatorPage;
    }

    public void clickViesRetProj() {
        WebElement viewRetBalanceBtn = driver.findElement(By.xpath("//span[text()='Complete the form to see your KiwiSaver retirement projections.']/.."));
        viewRetBalanceBtn.click();

    }

    public boolean  isEstimatedProjectedBalanceDisplayed () {
        WebElement projBalHeader = driver.findElement(By.xpath("//span['At age 65, your KiwiSaver balance is estimated to be:']//parent::div[@class='results-heading']"));
        WebElement projBal = driver.findElement(By.xpath("//span['At age 65, your KiwiSaver balance is estimated to be:']//parent::div[@class='results-heading']//descendant::span[@class='result-value result-currency ng-binding']"));
        return projBalHeader.isDisplayed() && projBal.isDisplayed() && !(projBal.getText().toString().isEmpty());
    }

    public KiwiSaverRetirementCalculatorPage enterAge(String age) {
        driver.switchTo().frame(0);
       ageTxt.sendKeys(age);
       return kiwiSaverRetirementCalculatorPage;
    }

    public KiwiSaverRetirementCalculatorPage enterSalary(String salary) {
        salaryOrWadgeTxt.clear();
        salaryOrWadgeTxt.sendKeys(salary.replace("$", "").trim());
        return kiwiSaverRetirementCalculatorPage;
    }

    public KiwiSaverRetirementCalculatorPage selectEmpStatus(String empStatus) throws InterruptedException {
        Thread.sleep(2000);
        empStatusDropDown.click();
        WebElement webElement = driver.findElement(By.xpath("//span[@class='ng-scope' and text()='" + empStatus + "']"));
        WaitHelper.waitForElementVisibility(webElement);
        webElement.click();
        return kiwiSaverRetirementCalculatorPage;
    }

    public KiwiSaverRetirementCalculatorPage selectKiwiSaverContrib(String currContrib) {
        WebElement webElement = driver.findElement(By.xpath("//span[text()='" + currContrib + "']"));
        webElement.click();
        return kiwiSaverRetirementCalculatorPage;
    }

    public KiwiSaverRetirementCalculatorPage selectRiskProfile(String riskProf) {
        WebElement webElement = driver.findElement(By.xpath("//span[@class='input-label']/span[text()='" + riskProf + "']"));
        webElement.click();
        return kiwiSaverRetirementCalculatorPage;
    }
    //endregion
}
