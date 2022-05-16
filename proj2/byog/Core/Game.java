package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;


import java.awt.*;
import java.util.Random;

public class Game {

    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 45;
    public static final int offX = 4;
    public static final int offY = 6;

    private static final int xOff = 1;

    private static final int yOff = 1;

    private   long seed = 0;

    private boolean beginning = true;

    private boolean gameStart = false;

    private TETile[][] finalWorldFrame;


    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH + offX , HEIGHT + offY, xOff, yOff);

        welcomePage();

        while (beginning) {
            while (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key != 'N' & key != 'n' & key != 'L' && key != 'l' & key != 'Q' && key != 'q' ) {
                    continue;
                } else {
                    beginning = false;
                    if (key == 'N' || key == 'n') {
                        gameStart = true;

                        showSeed();
                        getSeed();
                        WorldGenerator wg = new WorldGenerator(seed, WIDTH, HEIGHT);
                        wg.randomBSPWorld();
                        finalWorldFrame = wg.outputWorld();
                        while (gameStart) {
                            StdDraw.clear(Color.BLACK);



                            ter.renderFrame(finalWorldFrame);
                            trackMouse(finalWorldFrame);
                            StdDraw.pause(500);
                        }




                    }
                }
            }

        }




    }


    private void welcomePage(){
        int heading1Hight = HEIGHT - (HEIGHT + offY) / 5;
        int midHight = (HEIGHT + offY) / 2;
        int midWidth =(WIDTH + offX) / 2;

        Font smallFont = new Font("Monaco", Font.BOLD, 20);
        Font bigFont = new Font("Monaco", Font.BOLD, 30);

        StdDraw.setFont(bigFont);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(midWidth, heading1Hight, "CS61B : THE GAME");

        StdDraw.setFont(smallFont);
        int optionHight = midHight;
        StdDraw.text(midWidth, optionHight--, "New Game (N)");
        optionHight--;
        StdDraw.text(midWidth, optionHight--, "Load Game (L)");
        optionHight--;
        StdDraw.text(midWidth, optionHight, "Quit Game (Q)");

        StdDraw.show();

    }

    private void getSeed() {

        while (!StdDraw.hasNextKeyTyped()) {
        }
        char c = StdDraw.nextKeyTyped();
        if (c == 's' || c == 'S') return;
        if (c < '0' || c > '9') {
            getSeed();
            return;
        }

        seed = seed * 10 + (c - '0');

        showSeed();
        getSeed();
    }

    private void showSeed() {
        StdDraw.clear(Color.BLACK);
        Font bigFont = new Font("Monaco", Font.BOLD, 30);

        StdDraw.setFont(bigFont);
        StdDraw.setPenColor(Color.WHITE);
        int midHight = (HEIGHT + offY) / 2;
        int midWidth =(WIDTH + offX) / 2;
        StdDraw.text(midWidth, midHight, String.valueOf(seed));
        StdDraw.show();
    }

    private void trackMouse(TETile[][] finalWorldFrame) {
        boolean findMouse = false;
        while (!findMouse) {
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            if (x < WIDTH - xOff && x >= 0-xOff && y < HEIGHT -yOff && y >= 0-yOff) {
                findMouse = true;
                String s = finalWorldFrame[(int) x - xOff][(int) y - yOff].description();
                Font bigFont = new Font("Monaco", Font.BOLD, 30);

                StdDraw.setFont(bigFont);
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.text((WIDTH + offX) * 4/5 ,HEIGHT + offY - 1, s);
                StdDraw.show();

            }


        }
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

        WorldGenerator wg = new WorldGenerator(seed, WIDTH, HEIGHT);



        wg.randomBSPWorld();


        TETile[][] finalWorldFrame = wg.outputWorld();


        return finalWorldFrame;
    }
}
