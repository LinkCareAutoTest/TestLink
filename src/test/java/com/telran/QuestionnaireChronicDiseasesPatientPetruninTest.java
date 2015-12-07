package com.telran;

import com.telran.pages.PageTraining.LoginVladimirPage;
import com.telran.pages.QuestionnaireChronicDiseasesPatientPetruninPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Petrunin on 01.12.2015.
 */
public class QuestionnaireChronicDiseasesPatientPetruninTest {

    public static String username = "hore4832@yopmail.com"; //Данные входа клеента (hore2966@yopmail.com был заполнен)
    public static String password = "LinkCare!!11";//Данные входа доктора
    public LoginVladimirPage loginVladimirPage;
    public QuestionnaireChronicDiseasesPatientPetruninPage questionnaireChronicDiseasesPatientPetruninPage;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() {
      driver = new FirefoxDriver(); //Запускаем Firefox
        loginVladimirPage = PageFactory.initElements(driver, LoginVladimirPage.class); //Обращаемся к классу LoginMobilePage и берем оттуда ссылку на страницу
        questionnaireChronicDiseasesPatientPetruninPage=PageFactory.initElements(driver, QuestionnaireChronicDiseasesPatientPetruninPage.class); //Последующие методы
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() { //Входим на страницу и ждем пока она загрузится
        loginVladimirPage.openLoginPage(driver); //Логи к данному методу добавлены на странице LoginMobilePage

        loginVladimirPage.waitUntilLoginPageIsLoaded();
        loginVladimirPage
                .fillUsernameField(username)
                .fillPasswordField(password);
        questionnaireChronicDiseasesPatientPetruninPage.clickAddAcceptField();
        loginVladimirPage.clickOnLoginButton();
    }

    @Test(groups = {"positive", "smoke"})
    public void testOpenPatientPage() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertTrue(questionnaireChronicDiseasesPatientPetruninPage.tableWasFilled(), "Table was Filled");
        questionnaireChronicDiseasesPatientPetruninPage.clickTableButtom();
        Thread.sleep(5000);
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        Assert.assertTrue(questionnaireChronicDiseasesPatientPetruninPage.isOnframe(), "We are not on frame page");
        questionnaireChronicDiseasesPatientPetruninPage.fillElements();
        Thread.sleep(3000);
        questionnaireChronicDiseasesPatientPetruninPage.clickSubmitButtom();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Reporter.log("Registration is validate!");

    }

    @AfterClass(alwaysRun = true)
   public void tearDown() {
        this.driver.quit();
    }
}

