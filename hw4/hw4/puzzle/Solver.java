package hw4.puzzle;


import edu.princeton.cs.algs4.MinPQ;

import java.util.HashMap;
import java.util.*;


public class Solver {
    List<WorldState> path;
    int moves;
    Map<WorldState, Integer> map;
    private class SearchNode{
        WorldState state;
        int dist;
        SearchNode parent;

        public SearchNode(WorldState state, int dist, SearchNode node) {
            this.state = state;
            this.dist = dist;
            this.parent = node;
        }
    }


    public Solver(WorldState initial) {
        map = new HashMap<>();
        SearchNode destination = findDest(initial);
        if (destination == null) {
            throw new IllegalArgumentException("Do not have a solution!");
        }

        this.moves = destination.dist;
        path = new ArrayList<>();
        while (destination!= null) {
            path.add(destination.state);
            destination = destination.parent;
        }

        Collections.reverse(path);

    }

    private SearchNode findDest(WorldState initial) {

        MinPQ<SearchNode> pq = new MinPQ<>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                return o1.dist + getEst(o1.state) - o2.dist - getEst(o2.state);
            }
        });

        pq.insert(new SearchNode(initial, 0, null));

        while (!pq.isEmpty()) {
            SearchNode cur = pq.delMin();
            if (getEst(cur.state) == 0) return cur;
            for (WorldState state : cur.state.neighbors()) {
                if (cur.parent != null && state.equals(cur.parent.state)) {
                    continue;
                }
                pq.insert(new SearchNode(state, cur.dist + 1, cur));
            }
        }
        return  null;
    }




    private int getEst(WorldState state) {
        int est = map.getOrDefault(state, state.estimatedDistanceToGoal());
        map.put(state, est);
        return est;
    }



    public int moves(){
        return moves;
    }
    public Iterable<WorldState> solution() {
        return path;
    }

}
