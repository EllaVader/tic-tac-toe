package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.SeleniumHelper;
import automation.TicTacToeMove;
import automation.WaitUtil;

/**
 * Represents the TicTacToe Page
 * 
 * @author janine
 *
 */
public class TicTacToePage {

	WebDriver driver;
	private String cellLoc = "tr > td[data-column='%d'][data-row='%d']";

	@FindBy(id = "number")
	private WebElement numberTextBox;

	@FindBy(id = "start")
	private WebElement playButton;

	@FindBy(id = "endgame")
	private WebElement endgameMessage;

	@FindBy(id = "table")
	private WebElement ticTacToeBoard;

	public TicTacToePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * enters in the size of the board
	 * 
	 * @param size of board (i.e. 3 = 3x3 board)
	 */
	public void setUpBoardSize(int size) {
		numberTextBox.click();
		numberTextBox.clear();
		numberTextBox.sendKeys(String.valueOf(size));
		playButton.click();
		// wait for the board to be displayed before continuing
		WaitUtil.waitForElementVisible(driver, ticTacToeBoard);
	}

	/**
	 * Make a move on the tic tac toe board by
	 * 
	 * @param move - A move consists of a row and column index
	 */
	public void makeMove(TicTacToeMove move) {
		// dynamically load in the cell we want to click
		String cssCellLoc = String.format(cellLoc, move.getRow(), move.getColumn());
		WebElement cell = SeleniumHelper.loadElement(driver, By.cssSelector(cssCellLoc));

		if (cell != null) {
			// click in the cell and wait for the cell to have an x or o
			cell.click();
			cell = SeleniumHelper.loadElement(driver, By.cssSelector(cssCellLoc));
			WaitUtil.waitForElementToHaveText(driver, cell);
		} else {
			System.err.print("unable to load element " + cssCellLoc);
		}
	}

	public boolean isEndgameMessageDisplayed() {
		return endgameMessage.isDisplayed();
	}

	public String getEndgameMessage() {
		return endgameMessage.getText().trim();
	}

}
