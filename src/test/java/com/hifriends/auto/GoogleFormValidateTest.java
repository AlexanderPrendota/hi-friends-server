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
 * Test for correct google form view
 * @author  by aleksandrprendota on 27.08.17.
 */
public class GoogleFormValidateTest {

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

    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }

}
