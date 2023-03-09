package ToggleGame.backend;
import ToggleGame.frontend.ToggleGameInteraction;

import java.util.Random;

/**
 * Buttons have the following order / placement on the screen
 *
 *  0 1 2
 *  3 4 5
 *  6 7 8
 *
 * For the Colors: BLACK is 0 and WHITE = 1
 *
 * This file is incomplete and should be completed
 * Once it is completed please update this message
 */
public class ToggleGameEngine implements ToggleGameInteraction {

    /**
     * Initialize and return the game board for a ToggleGame (9x "1")
     * @return the String "111111111" to start a game with all white squares
     */
    @Override
    public String initializeGame() {
        //starter code ... replace the below code with a string containing all 1's
        return GameHelper.generateRandomBoard();
    }

    /**
     * Update the game board for the given button that was clicked.
     * Squares marked as 0 are black and 1 is white
     *
     * @param button the game board square button that was clicked (between 0 and 8)
     *
     * @return the updated game board as a String giving the button colors in order
     *         with "0" for black and "1" for white.
     *
     * @throws IllegalArgumentException when button is outside 0-8
     */
    @Override
    public String buttonClicked(String current, int button) {
        //starter code...replace the below code
        return GameHelper.generateRandomBoard();
    }


    /**
     * Return a sequence of moves that leads in the minimum number of moves
     * from the current board state to the target state
     *
     * @param current the current board state given as a String of 1's (white square)
     *                and 0's (black square)
     * @param target the target board state given as a String of 1's (white square)
     *               and 0's (black square)
     * @return the sequence of moves to advance the board from current to target.
     *         Each move is the number associated with a button on the board. If no moves are
     *         required to advance the currentBoard to the target an empty array is returned.
     */
    @Override
    public int[] movesToSolve(String current, String target) {
        //starter code ... replace the below
        return new int[] {new Random().nextInt(9)};
    }

    /**
     * Return the minimum required number of required moves (button clicks)
     * to advance the current board to the target board.
     *
     * @param current the current board state given as a String of 1's (white square)
     *                and 0's (black square)
     * @param target the target board state given as a String of 1's (white square)
     *               and 0's (black square)
     * @return the minimum number of moves to advance the current board
     * to the target
     */
    @Override
    public int minNumberOfMoves(String current, String target) {
        //starter code ... replace the below
        return new Random().nextInt(9);
    }
}
