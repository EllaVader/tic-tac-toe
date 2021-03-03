package org.practice.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import automation.TicTacToeMove;
import automation.pages.TicTacToePage;

/**
 * Test for TicTacToe App
 * 
 * @author janine
 *
 */
public class TicTacToeTests extends TestBase {

	private final String oWinsMessage = "Congratulations player O! You've won.";
	private final String xWinsMessage = "Congratulations player X! You've won.";

	@Test
	public void testXWins() {
		// load test data
		List<TicTacToeMove> moves = createXWinsMoves();
		TicTacToePage ticTacToePage = new TicTacToePage(driver);
		ticTacToePage.setUpBoardSize(3);

		// play moves such that X will win
		for (TicTacToeMove move : moves) {
			ticTacToePage.makeMove(move);
		}

		// verify that an endgame message is displayed
		assertTrue(ticTacToePage.isEndgameMessageDisplayed(), "Endgame message should be displayed but isn't");
		String endgameMessage = ticTacToePage.getEndgameMessage();
		// verify that the endgame message is correct
		assertTrue(endgameMessage.contains(xWinsMessage), "X should be displayed as winner, but isn't");
	}

	@Test
	public void testOWins() {
		// load test data
		List<TicTacToeMove> moves = createOWinsMoves();
		TicTacToePage ticTacToePage = new TicTacToePage(driver);
		ticTacToePage.setUpBoardSize(3);

		// play moves such that O will win
		for (TicTacToeMove move : moves) {
			ticTacToePage.makeMove(move);
		}

		// verify that an endgame message is displayed
		assertTrue(ticTacToePage.isEndgameMessageDisplayed(), "Endgame message should be displayed but isn't");
		String endgameMessage = ticTacToePage.getEndgameMessage();
		// verify that the endgame message is correct
		assertTrue(endgameMessage.contains(oWinsMessage), "O should be displayed as winner, but isn't");

	}

	@Test
	public void testNoWinner() {
		// load test data
		List<TicTacToeMove> moves = createNoWinsMoves();
		TicTacToePage ticTacToePage = new TicTacToePage(driver);
		ticTacToePage.setUpBoardSize(3);

		// play moves such that no one wins
		for (TicTacToeMove move : moves) {
			ticTacToePage.makeMove(move);
		}

		// verify that no message displayed
		assertFalse(ticTacToePage.isEndgameMessageDisplayed(), "Endgame message should not be displayed");

	}

	/**
	 * Create simple x winning scenarios across
	 * 
	 * @return
	 */
	public List<TicTacToeMove> createXWinsMoves() {
		List<TicTacToeMove> moves = new ArrayList<>();
		moves.add(new TicTacToeMove(0, 0));
		moves.add(new TicTacToeMove(0, 1));
		moves.add(new TicTacToeMove(1, 0));
		moves.add(new TicTacToeMove(1, 1));
		moves.add(new TicTacToeMove(2, 0));

		return moves;
	}

	/**
	 * Create simple O winning scenario - down
	 * 
	 * @return
	 */
	public List<TicTacToeMove> createOWinsMoves() {
		List<TicTacToeMove> moves = new ArrayList<>();
		moves.add(new TicTacToeMove(0, 0));
		moves.add(new TicTacToeMove(1, 0));
		moves.add(new TicTacToeMove(2, 1));
		moves.add(new TicTacToeMove(1, 1));
		moves.add(new TicTacToeMove(0, 2));
		moves.add(new TicTacToeMove(1, 2));

		return moves;
	}

	/**
	 * Create game that no one wins m
	 * 
	 * @return
	 */
	public List<TicTacToeMove> createNoWinsMoves() {
		List<TicTacToeMove> moves = new ArrayList<>();
		moves.add(new TicTacToeMove(0, 0));
		moves.add(new TicTacToeMove(2, 0));
		moves.add(new TicTacToeMove(1, 2));
		moves.add(new TicTacToeMove(1, 0));
		moves.add(new TicTacToeMove(2, 1));
		moves.add(new TicTacToeMove(0, 1));
		moves.add(new TicTacToeMove(1, 1));
		moves.add(new TicTacToeMove(2, 2));
		moves.add(new TicTacToeMove(0, 2));

		return moves;
	}

	// Other tests
	// Test winning all across
	// Test winning all down
	// Test winning diagonally
	// Test various board dimensions

	// should not be able to add to existing game board once games starts
	// restart game
	// min and max board size

}
