package ToggleGame.backend;
import ToggleGame.frontend.ToggleGameInteraction;

import java.util.*;

// All the methods are done with the assistance of chat gpt

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
    private static final int BOARD_SIZE = 9;    // initializing BOARD_SIZE to 9
    private static final int[][] NEIGHBOR_INDICES = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    /**
     * Initialize and return the game board for a ToggleGame (9x "1")
     *
     * @return the String "111111111" to start a game with all white squares
     */

    @Override
    public String initializeGame() {
        return ("111111111");    // Initialize the game board with all white squares
    }

    /**
     * Update the game board for the given button that was clicked.
     * Squares marked as 0 are black and 1 is white
     *
     * @param button the game board square button that was clicked (between 0 and 8)
     * @return the updated game board as a String giving the button colors in order
     * with "0" for black and "1" for white.
     * @throws IllegalArgumentException when button is outside 0-8
     */

    public String buttonClicked(String board, int button) {
        char[] boardChars = board.toCharArray();    // Converting the game board to char array
        flipButton(boardChars, button);            // Flip the clicked button
        flipNeighbors(boardChars, button);        //  Flip the neighbouring buttons
        return new String(boardChars);           //Return the updated game board
    }
    // Flip the color of the clicked button
    private void flipButton(char[] board, int button) {
        board[button] = board[button] == '0' ? '1' : '0'; // // If the color of the button is black, change it to white, and vice versa.

    }
    // Flip the color of the neighboring buttons
    private void flipNeighbors(char[] board, int button) {
        int row = button / 3;
        int col = button % 3;
        for (int[] neighborIndex : NEIGHBOR_INDICES) {
            int neighborRow = row + neighborIndex[0];
            int neighborCol = col + neighborIndex[1];
            if (isValidIndex(neighborRow, neighborCol)) {      // Check if the neighbor button is a valid button
                int neighborButton = neighborRow * 3 + neighborCol;
                flipButton(board, neighborButton);    // Flip the color of the neighbor button
            }
        }
    }

    // Check if the given indices are a valid button
    private boolean isValidIndex(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;  // Return true if the indices are within the button range, false otherwise

    }

    /**
     * Return a sequence of moves that leads in the minimum number of moves
     * from the current board state to the target state
     *
     * @param current the current board state given as a String of 1's (white square)
     *                and 0's (black square)
     * @param target  the target board state given as a String of 1's (white square)
     *                and 0's (black square)
     * @return the sequence of moves to advance the board from current to target.
     * Each move is the number associated with a button on the board. If no moves are
     * required to advance the currentBoard to the target an empty array is returned.
     */

    @Override
    public int[] movesToSolve(String current, String target) {
        if (current.equals(target)) {
            return new int[0];
        }
        Map<String, Integer> buttonToMove = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(current);
        buttonToMove.put(current, -1); // we don't want to count the initial board as a move
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            for (int button = 0; button < 9; button++) {
                String next = buttonClicked(curr, button);
                if (!buttonToMove.containsKey(next)) {
                    buttonToMove.put(next, button);
                    if (next.equals(target)) {
                        // we found the target, construct the move sequence and return
                        List<Integer> moves = new ArrayList<>();
                        while (button != -1) {
                            moves.add(button);
                            button = buttonToMove.get(curr);
                            curr = button == -1 ? current : buttonClicked(curr, button);
                        }
                        Collections.reverse(moves);
                        return moves.stream().mapToInt(Integer::intValue).toArray();
                    }
                    queue.offer(next);
                }
            }
        }
        return new int[0]; // unreachable code since there's always a solution
    }

    // helper method to get the button that changed between two states
    private int getChangedButton(String state1, String state2) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (state1.charAt(i) != state2.charAt(i)) {
                return i;
            }
        }
        return -1; // should not happen
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

            if (current.equals(target)) {
                return 0;
            }
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(current);
            visited.add(current);
            int moves = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String curr = queue.poll();
                    if (curr.equals(target)) {
                        return moves;
                    }
                    for (int j = 0; j < 9; j++) {
                        String next = buttonClicked(curr, j);
                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
                moves++;
            }
            return -1;
        }
    }


