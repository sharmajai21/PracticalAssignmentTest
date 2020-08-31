package Common;

import ApplicationPages.CalculatorsPage;
import ApplicationPages.KiwiSaverRetirementCalculatorPage;
import ApplicationPages.PersonalBankingHomePage;
import org.openqa.selenium.support.PageFactory;

public class UIBasePageFactory extends BaseClass {
    static protected PersonalBankingHomePage homePage;
    static protected CalculatorsPage calculatorsPage;
    static protected KiwiSaverRetirementCalculatorPage kiwiSaverRetirementCalculatorPage;

    public void initializePages() {
        super.initializePages();
        homePage=PageFactory.initElements(driver, PersonalBankingHomePage.class);
        calculatorsPage=PageFactory.initElements(driver, CalculatorsPage.class);
        kiwiSaverRetirementCalculatorPage=PageFactory.initElements(driver, KiwiSaverRetirementCalculatorPage.class);

    }

}
