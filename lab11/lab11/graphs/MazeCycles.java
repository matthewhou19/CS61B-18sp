package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean cyclefind = false;

    private int s;
    private  int t;

    private int[] parent ;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        parent = new int[maze.V()];
        marked[0] = true;
        parent[0] = 0;
        dfs(0);
        sign( s);
        sign(t);
    }

    private void sign(int i ){
        while (i != 0) {
            if (edgeTo[i] == Integer.MAX_VALUE) {
                edgeTo[i] = i;
                i = parent[i];
            } else {
                edgeTo[i] = Integer.MAX_VALUE;
                i = parent[i];
            }

        }
    }

    private void dfs(int i) {
        announce();
        if (cyclefind) {
            return;
        }

        for (int a : maze.adj(i)) {
            if (marked[a]) {
                if (a != parent[i]) {
                    cyclefind = true;
                    s = a;
                    t = i;


                    break;
                }

            } else {
                parent[a] = i;
                marked[a] = true;
                dfs(a);
            }
        }
    }

    // Helper methods go here
}

