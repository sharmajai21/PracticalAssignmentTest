package StepDefinition;

import Common.UIBasePageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class KiwisaverCalculator extends UIBasePageFactory {
    public static Logger Log = LogManager.getLogger(KiwisaverCalculator.class.getName());

    @Given("^User is on the Westpac bank website home page$")
    public void iAmOnTheWestpacBankWebsiteHomePage() {
        log.info("Started kiwisaver test");
        setupDriver();
        getPage();
        Assert.assertTrue("Home page is displayed", homePage.isHomePageDisplayed());
    }

    @When("Select KiwiSaver calculators menu option under KiwiSaver menu")
    public void iSelectKiwiSvaerCalculatorsMenuOptionUnderKiwisaverMenu() {
        homePage.selectHeaderMenu("Personal").selectSubMenu("KiwiSaver").selectSubMenu("KiwiSaver calculators");
    }

    @Then("KiwiSaver calculators screen is displayed")
    public void iAmNavigatedToKiwiSaverCalculatorsPage() {
        Assert.assertTrue("KiwiSaver Calculators page is dispalyed", calculatorsPage.isCalculatorsPageDisplayed());
    }

    @And("User clicks on Click here to get started button on calculators page")
    public void iClickOnClickHereToGetStartedButtonOnCalculatorsPage() {
        calculatorsPage.clickOnGetStartedBtn();
    }


    @Then("^KiwiSaver Retirement Calculator screen is displayed$")
    public void kiwisaverRetirmentCalculatorScreenIsDisplayed() {
        Assert.assertTrue("KS Retirement calculator screen is displayed", kiwiSaverRetirementCalculatorPage.isKSCalculatorPageDisplayed());
    }

    @And("^User click on information icon next to Current age field$")
    public void iClickOnInformactionIconNextToCurrentAgeField() throws InterruptedException {
        Thread.sleep(3000);
        kiwiSaverRetirementCalculatorPage.clickONAgeHelpButton();
    }

    @Then("^Age limit validation message is displayed$")
    public void ageLimitValidationMessageIsDisplayed() {
        Assert.assertTrue("Age limit validation", kiwiSaverRetirementCalculatorPage.isAgeHelpTextDispalyed());
    }

    @When("User specify  values for {string}, {string}, {string}, {string} and {string}")
    public void userSpecifyValuesForAnd(String currAge, String empStatus, String salarPA, String contribPercent, String riskProf) throws InterruptedException {
        kiwiSaverRetirementCalculatorPage.enterAge(currAge)
                .selectEmpStatus(empStatus)
                .enterSalary(salarPA)
                .selectKiwiSaverContrib(contribPercent)
                .selectRiskProfile(riskProf);
    }

    @Then("View Your KiwiSaver retirement projections button becomes enabled")
    public void viewYourKiwiSaverRetirementProjectionsButtonBecomesEnabled() {
        Assert.assertTrue("KiwiSaver projection button enabled", kiwiSaverRetirementCalculatorPage.isViewProjBtnEnabled());

    }


    @When("User clicks on View Your KiwiSaver retirement projections button")
    public void userClicksOnViewYourKiwiSaverRetirementProjectionsButton() {
        kiwiSaverRetirementCalculatorPage.clickViesRetProj();
    }

    @Then("Estimated projected kiwisaver balance at age of {int} years is dispalyed on the calculator screen")
    public void estimatedKiwisaverBalanceAtAgeOfYearsIsDispalyedOnTheCalculatorScreen(int arg0) {
        kiwiSaverRetirementCalculatorPage.isEstimatedProjectedBalanceDisplayed();
    }


    @When("User specify  values for {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void userSpecifyValuesForAnd(String currAge, String empStatus, String currBal, String volContrib, String volContribFreq, String riskProf, String savingGoals) throws InterruptedException {
        kiwiSaverRetirementCalculatorPage.enterAge(currAge)
                .selectEmpStatus(empStatus)
                .enterCurrBalance(currBal)
                .enterVolunContrib(volContrib, volContribFreq)
                .selectRiskProfile(riskProf)
                .enterSavGoals(savingGoals);
    }
}
