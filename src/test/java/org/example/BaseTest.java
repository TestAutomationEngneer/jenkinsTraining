package org.example;

import org.example.configuration.BrowserEnvironment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BaseTest {

    protected static WebDriver driver;
    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static BrowserEnvironment browserEnvironment;

    @BeforeAll
    static void setUp() throws IOException {
        browserEnvironment = new BrowserEnvironment();
        driver = browserEnvironment.getDriver();
        logger.debug("WebDriver initialized properly");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        logger.debug("Driver is closed");
    }
}
