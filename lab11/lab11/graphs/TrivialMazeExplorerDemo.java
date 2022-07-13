package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class TrivialMazeExplorerDemo {
    public static void main(String[] args) {
        Maze maze = new Maze("lab11/graphs/maze.txt");
        MazeDepthFirstPaths tme = new MazeDepthFirstPaths(maze, 1 , 1, 5, 5);
        tme.solve();
    }
}
