package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;


import java.util.Random;

public class Game {

    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 60;
    public static final int seed = 3567;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        Random random = new Random(3567);






        // initialize tiles







        // draws the world to the screen

    }







    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        char f = input.charAt(0);
        if (f != 'n' && f != 'N') {
            throw new IllegalArgumentException("Input string must start with character N or n !");
        }
        long seed = 0;
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == 's' || c == 'S') {
                break;
            } else if (c <= '9' && c >= '0') {
                seed = seed * 10 + (c - '0');
            } else {
                throw new IllegalArgumentException("Input must be a string of the format “N#######S” !");
            }
        }


        System.out.println(seed);




        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
