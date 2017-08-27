package com.hifriends.auto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author  by aleksandrprendota on 28.08.17.
 */
public class SendingMessageTest {
    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get("http://localhost:3131");
    }

    @Test
    public void googleFormValidateTest() throws Exception {

        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("google-signin-button")));


        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("9999")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("message")))
                .sendKeys("Hello Jet!");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("send")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[text()='Hello Jet!']")))
                .click();

    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
