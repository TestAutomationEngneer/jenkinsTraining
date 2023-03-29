package org.example.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.example.configuration.ConfigRetriever.getConfig;

public class BrowserEnvironment {

    private String browserName;
    private boolean headlessBrowser;
    private int webElementTimeout;
    private boolean attachScreenshot;
    private Logger logger;
    private WebDriver driver;

    public BrowserEnvironment() throws IOException {
        this.browserName = "firefox";
        this.headlessBrowser = false;
        this.webElementTimeout = 10;
        this.attachScreenshot = false;
        this.logger = LoggerFactory.getLogger(BrowserEnvironment.class);
        this.initBrowserSettings();
    }

    private void initBrowserSettings() throws IOException {
        this.browserName = getConfig().isSpecified("browser") ? getConfig().getProperty("browser").toString() : this.browserName;
        this.webElementTimeout = getConfig().isSpecified("timeout") ? Integer.parseInt(getConfig().getProperty("timeout").toString()) : this.webElementTimeout;
        this.attachScreenshot = getConfig().isSpecified("screenshot") ? Boolean.parseBoolean(getConfig().getProperty("screenshot").toString()) : this.attachScreenshot;
        this.headlessBrowser = getConfig().isSpecified("headless") ? Boolean.parseBoolean(getConfig().getProperty("headless").toString()) : this.headlessBrowser;
    }

    public WebDriver getDriver() {
        WebDriver driver;
        switch (this.browserName) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                driver.get(System.getProperty("url"));
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.addArguments("start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                driver.get(System.getProperty("url"));
            }
            default -> {
                InternetExplorerOptions defaultOptions = new InternetExplorerOptions();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(defaultOptions);
                driver.get(System.getProperty("url"));
            }
        }
        this.driver = driver;
        return this.driver;
    }
}
