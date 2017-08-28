package com.hifriends.auto;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Check online users. Some users is always online.
 * like Jet Family
 * @author  by aleksandrprendota on 27.08.17.
 */
public class СurrentUserValidateTest {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get("http://localhost:3131");
    }

    @Test
    @Ignore
    public void validateUserTest() throws Exception {

        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("google-signin-button")));

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//span[text()='Jet Family']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("9999")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//span[@id='apponent' and text()='Род Джонсон']")))
                .click();

    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
