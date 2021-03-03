package org.practice.tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import automation.SeleniumHelper;

/**
 * Base test class
 * 
 * @author janine
 *
 */
public class TestBase {

	protected WebDriver driver;
	protected final Integer TIMEOUT_IN_SECONDS = 60;

	protected String url = "https://codepen.io/CalendlyQA/full/KKPQLmV";

	@BeforeEach
	public void setup() {

		driver = initializeChromeDriver();

		// load page
		driver.get(url);

		// switch to iFrame
		WebElement iFrame = SeleniumHelper.loadElement(driver,
				By.cssSelector("div#result-iframe-wrap > iframe#result"));
		driver.switchTo().frame(iFrame);

	}

	private WebDriver initializeChromeDriver() {

		String driverPath = Thread.currentThread().getContextClassLoader().getResource("chromedriver").getPath();

		// must change permission of chromedriver to a+x
		File chromedriver = new File(driverPath);
		chromedriver.setExecutable(true);
		System.setProperty("webdriver.chrome.driver", driverPath);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
		chromeOptions.addArguments("--start-maximized");

		WebDriver chromeDriver = new ChromeDriver(chromeOptions);

		chromeDriver.manage().timeouts().pageLoadTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		chromeDriver.manage().timeouts().setScriptTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		chromeDriver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);

		return chromeDriver;

	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
