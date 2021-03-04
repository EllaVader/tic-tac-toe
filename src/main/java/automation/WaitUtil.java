package automation;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * Helper class for dealing with Waits
 * 
 * @author janine
 *
 */
public class WaitUtil {

	private static FluentWait<WebDriver> wait = null;

	public static final int DEFAULT_WAIT_TIMEOUT = 10;
	private static final int POLLING_INTERVAL = 2;

	private WaitUtil() {
	}

	/**
	 * Wait for element visible
	 * 
	 * @param driver
	 * @param element          - element to locate
	 * @param timeoutInSeconds
	 * @return
	 */
	public static WebElement waitForElementVisible(final WebDriver driver, WebElement element,
			final long... timeoutInSeconds) {

		nullifyImplicitWait(driver);

		// set timeout value
		long timeout = (timeoutInSeconds.length > 0) ? timeoutInSeconds[0] : DEFAULT_WAIT_TIMEOUT;

		wait = initializeFluentWait(driver, timeout);
		WebElement foundElement = wait.until(ExpectedConditions.visibilityOf(element));

		setDefaultImplicitWait(driver);
		return foundElement;
	}

	/**
	 * Wait for the element to have text
	 * 
	 * @param driver
	 * @param element
	 * @param timeoutInSeconds
	 */
	public static void waitForElementToHaveText(final WebDriver driver, WebElement element,
			final long... timeoutInSeconds) {

		nullifyImplicitWait(driver);

		// set timeout value
		long timeout = (timeoutInSeconds.length > 0) ? timeoutInSeconds[0] : DEFAULT_WAIT_TIMEOUT;
		wait = initializeFluentWait(driver, timeout);
		wait.until(wd -> {
			String elementText = element.getText();
			return !elementText.isEmpty();
		});

		setDefaultImplicitWait(driver);
	}

	/**
	 * Set driver implicitlyWait() time back to default value
	 *
	 * @param driver - {@link WebDriver} the driver to act on
	 */
	public static void setDefaultImplicitWait(final WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIMEOUT, TimeUnit.SECONDS);
	}

	/**
	 * zeros out implicit wait time
	 *
	 * @param driver - {@link WebDriver} the driver to act on
	 */
	public static void nullifyImplicitWait(final WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/**
	 * Set driver implicitlyWait() time.
	 *
	 * @param driver             - {@link WebDriver} the driver to act on
	 * @param waitTime_InSeconds - time to wait
	 */
	public static void setImplicitWait(final WebDriver driver, final long waitTime_InSeconds) {
		driver.manage().timeouts().implicitlyWait(waitTime_InSeconds, TimeUnit.SECONDS);
	}

	private static FluentWait<WebDriver> initializeFluentWait(final WebDriver driver, long timeout) {
		return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(POLLING_INTERVAL))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
	}

}
