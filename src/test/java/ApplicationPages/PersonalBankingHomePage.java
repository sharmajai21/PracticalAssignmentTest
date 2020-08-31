package ApplicationPages;

import Common.UIBasePageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalBankingHomePage extends UIBasePageFactory {

    //region UI Elements
    @FindBy(xpath = "//div[@class='sw-banner-hero banner1 light-image']")
    private WebElement homepageBanner;


    //endregion

    //region UI Actions
    public boolean isHomePageDisplayed() {
        try {
            return homepageBanner.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public PersonalBankingHomePage selectHeaderMenu(String headName) {
        WebElement headMenu = driver.findElement(By.xpath("//ul[@class='sw-header-menu']//descendant::a[text()='"+ headName +"']"));
        headMenu.click();
        return homePage;
    }

    public PersonalBankingHomePage selectSubMenu(String subMenu) {
        WebElement subMenuItem = driver.findElement(By.xpath("//nav[@id='ubermenu-ps']//descendant::a[text()='" + subMenu + "']"));
        subMenuItem .click();
        return homePage;
    }

    public void selectMenuOption(String menuOptionName) {
        WebElement subMenuItem = driver.findElement(By.xpath("//a[text()='" + menuOptionName  +"']"));
        subMenuItem .click();
    }

    //endregion


}
