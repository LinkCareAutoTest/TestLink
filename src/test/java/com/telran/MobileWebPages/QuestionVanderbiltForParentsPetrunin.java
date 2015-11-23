package com.telran.MobileWebPages;

import com.telran.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Iakov Volf
 */
public class QuestionVanderbiltForParentsPetrunin extends Page {

    //private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    //fields
    @FindBy(xpath = "//div[@id='MainPage']//label[contains(text(), 'שאלון וונדרבילט להורה- אבחון מעקב - דוח ממתין למילוי')]/../..//div[contains(text(), 'כעת')]")
    WebElement mainPage;

    @FindBy(xpath = "//*[@id='listWaitingReports']//div[@class='NextImgButton']")
    WebElement nextImgButton;

    @FindBy(xpath = "//*[@id='form1']")
    WebElement form1;

   /* @FindBy(xpath = "/*//*[@id='form1']*//*[@class='sectionq']")
    WebElement point;*/


    @FindBy(id = "LeftBtnHeader")
    WebElement leftBtnHeader;


    //public ProfilePage profilePage;

    public QuestionVanderbiltForParentsPetrunin(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://dhclinicamobileppstg.item-soft.co.il/index.htm";
        PageFactory.initElements(driver, this);
    }


    public QuestionVanderbiltForParentsPetrunin openLoginPage(WebDriver driver) {
        driver.get(PAGE_URL);
        return this;
    }


    public void clickMainPage() {
        clickElement(mainPage);

    }

    public void checkNextImgButton() {
        clickElement(nextImgButton);

    }

    public void fillElements() {
        List<WebElement> sectionqs = form1.findElements(By.tagName("sectionq"));
        List<WebElement> rows, radioButtons;

        for (WebElement currentTable : sectionqs) {
            rows = currentTable.findElements(By.tagName("tr"));
            for (WebElement currentRow : rows) {
                radioButtons = currentRow.findElements(By.tagName("input"));
                String rndValue = String.valueOf((int) (Math.random() * 3));
                for (WebElement currentRadioButton : radioButtons) {
                    if (currentRadioButton.getAttribute("value").equalsIgnoreCase(rndValue)) {
                        currentRadioButton.click();
                    }
                }
            }
        }
    }

    public void checkLeftBtnHeader() {
        clickElement(leftBtnHeader);

    }
}



   /* public void loginOnMobilePage(String username, String pass) {
        openLoginPage(driver);
        waitUntilLoginPageIsLoaded();
        fillUsernameField(username);
        fillPasswordField(pass);
        checkAgreeChebox();
        clickOnLoginButton();
    }

    public boolean isOnLoginPage() {
        return exists(loginButton);
    }


    //check alert presence

    public boolean alertMessageNotValidUserName() {
        return exists(wrongUserNameAlert);
    }

    public boolean alertMessageNotValidFirsrName() {
        return exists(wrongPasswordAlert);
    }
*/


