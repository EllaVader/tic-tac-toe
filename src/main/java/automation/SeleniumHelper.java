package automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Some helper functions for Selenium
 * 
 * @author janine
 *
 */
public class SeleniumHelper {

	/**
	 * Get the list of elements. Returns an empty list if none found
	 *
	 * @param context - {@link SearchContext} context to search against
	 * @param locator - {@link By} locator to search on
	 * @return - {@link List} of {@link WebElement} found based on the locator and
	 *         context, or empty list if none found
	 */
	public static List<WebElement> loadElements(SearchContext context, By locator) {
		return context.findElements(locator);
	}

	/**
	 * Get the element. Returns null if none found
	 *
	 * @param context - {@link SearchContext} context to search against
	 * @param locator - {@link By} locator to search on
	 * @return - {@link WebElement} the element or null if not found
	 */
	public static WebElement loadElement(SearchContext context, By locator) {
		List<WebElement> items = loadElements(context, locator);
		return items.isEmpty() ? null : items.get(0);
	}

}
