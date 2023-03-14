package ToggleGame.backend;
import ToggleGame.frontend.ToggleGameInteraction;

import java.util.*;

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
    private static final int BOARD_SIZE = 9;
    private static final int[][] ADJACENT_INDEXES = {
            {1, 3}, {0, 2, 4}, {1, 5}, {0, 4, 6}, {1, 3, 5, 7}, {2, 4, 8}, {3, 7}, {4, 6, 8}, {5, 7}
    };

    /**
     * Initialize and return the game board for a ToggleGame (9x "1")
     *
     * @return the String "111111111" to start a game with all white squares
     */

    @Override
    public String initializeGame() {
        //starter code ... replace the below code with a string containing all 1's
        //int[] board = new int[BOARD_SIZE];
        //  Arrays.fill(board, 1);
        return ("111111111");
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
    @Override
    public String buttonClicked(String current, int button) {
        if (button < 0 || button > 8) {
            throw new IllegalArgumentException("Button out of bounds.");
        }
        int[][] adjacentSquares = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4, 6}, {1, 3, 5, 7}, {2, 4, 8}, {3, 7}, {4, 6, 8}, {5, 7}};
        StringBuilder sb = new StringBuilder(current);
        sb.setCharAt(button, (char) (49 - sb.charAt(button)));  // Flip the color of the clicked button
        for (int adjacent : adjacentSquares[button]) {
            sb.setCharAt(adjacent, (char) (49 - sb.charAt(adjacent)));  // Flip the color of the adjacent buttons
        }
        return sb.toString();
    }
    //starter code...replace the below code
    // return GameHelper.generateRandomBoard();


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
    public int [] movesToSolve(String current, String target) {
        if (current.equals(target)) {
            return new int[0]; // empty array if already in target state
        }

        Map<String, String> parentMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        parentMap.put(current, null);
        queue.offer(current);
        visited.add(current);

        while (!queue.isEmpty()) {
            String state = queue.poll();
            for (int button = 0; button < BOARD_SIZE; button++) {
                String nextState = buttonClicked(state, button);
                if (!visited.contains(nextState)) {
                    parentMap.put(nextState, state);
                    if (nextState.equals(target)) {
                        // reconstruct path and return as array of button clicks
                        List<Integer> path = new ArrayList<>();
                        String currState = nextState;
                        while (parentMap.get(currState) != null) {
                            String finalCurrState = currState;
                            String finalCurrState1 = currState;
                            int buttonClick = Arrays.asList(ADJACENT_INDEXES).indexOf(Arrays.stream(ADJACENT_INDEXES)
                                    .filter(a -> Arrays.asList(a).contains(getChangedButton(finalCurrState, parentMap.get(finalCurrState1))))
                                    .findFirst()
                                    .get());
                            path.add(buttonClick);
                            currState = parentMap.get(currState);
                        }
                        Collections.reverse(path);
                        return path.stream().mapToInt(i -> i).toArray();
                    }
                    visited.add(nextState);
                    queue.offer(nextState);
                }
            }
        }

        return new int[0]; // empty array if no path exists
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

        //starter code ... replace the below
       // return new int[] {new Random().nextInt(9)};




          ;


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
       // return new Random().nextInt(9);
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


