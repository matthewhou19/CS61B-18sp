package lab11.graphs;

import java.util.*;


/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    Map<Integer, Integer> map = new HashMap<>();

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int a = maze.toX(v);
        int b = maze.toY(v);
        int c = maze.toX(t);
        int d = maze.toY(t);

        return Math.abs(a - c) + Math.abs(b - d);
    }

    /** Finds vertex estimated to be closest to target. */
    private void findMinimumUnmarked(Queue<Integer> q) {
        q.clear();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (Integer key : map.keySet()) {
            pq.add(key);
        }
        while (!pq.isEmpty()) {
            q.add(pq.remove());
        }

        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        announce();

        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        map.put(s, h(s));


        while (!q.isEmpty()) {
            update(q);


        }
    }

    private void update(Queue<Integer> q) {
        if (targetFound) {
            return;
        }
        int cur = q.poll();
        map.remove(cur);
        for (int a : maze.adj(cur)) {
            if (marked[a]) {
                continue;
            }
            if (a == t) {
                targetFound = true;
            }
            map.put(a, h(a));
            marked[a] = true;
            distTo[a] = distTo[cur] + 1;
            edgeTo[a] = cur;
            announce();
            if (targetFound) {
                return;
            }
        }

        findMinimumUnmarked(q);
    }

    @Override
    public void solve() {
        astar(s);
    }

}

