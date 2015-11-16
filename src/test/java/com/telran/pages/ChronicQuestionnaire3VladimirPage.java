package com.telran.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naryck on 2015.11.09.
 */
public class ChronicQuestionnaire3VladimirPage extends Page {
    Map<String, Integer> buttons = new HashMap<String, Integer>();
    ObjectOutputStream oos;

    @FindBy(id = "MainContent_contentHtml")
    WebElement mainDiv;

    @FindBy(id = "submit")
    WebElement submitButton;
    /*@FindBy(id = "ExportFrame")
    WebElement iFrame;*/

    public ChronicQuestionnaire3VladimirPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillElements() {
        try {
            oos = new ObjectOutputStream((new FileOutputStream("d:\\buttons3.tst")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(driver.findElement(By.tagName("iFrame")));
        //driver.switchTo().defaultContent();
        List<WebElement> tables = mainDiv.findElements(By.tagName("table"));
        for (WebElement element : tables) {
            System.out.println(element);
        }
        List<WebElement> rows, radioButtons;

        for (WebElement currentTable : tables) {
            rows = currentTable.findElements(By.tagName("tr"));
            for (WebElement currentRow : rows) {
                radioButtons = currentRow.findElements(By.tagName("input"));
                String rndValue = String.valueOf(1);
                for (WebElement currentRadioButton : radioButtons) {
                    if (currentRadioButton.getAttribute("value").equalsIgnoreCase(rndValue)) {
                        buttons.put(currentRadioButton.getAttribute("name"), Integer.parseInt(currentRadioButton.getAttribute("value")));
                        currentRadioButton.click();
                    }
                }
            }
            try {
                oos.writeObject(buttons);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clickElement(submitButton);
        try {
            wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().activeElement().sendKeys(Keys.RETURN);
    }

    public ChronicQuestionnaire3VladimirPage waitUntilTestPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(submitButton);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("test page IOException");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("test page interrupted exception");
        }
        System.out.println("test page loaded successfully");
        return this;
    }
}