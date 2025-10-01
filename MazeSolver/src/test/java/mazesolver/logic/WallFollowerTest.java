package mazesolver.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import mazesolver.util.ArrayList;

public class WallFollowerTest {

    private ArrayList<ArrayList<Integer>> graph;
    private ArrayList<Integer> path;
    private Maze maze;
    private WallFollower wallFollower;
    private int n;

    @Before
    public void setUp() {
        n = 20;

        maze = new Maze(n);
        maze.generateMaze();

        graph = maze.getGraph();
        wallFollower = new WallFollower(graph, n);
        wallFollower.solve();

        path = wallFollower.getPath();
    }

    @Test
    public void wallFollowerFindsPathFromStartToEnd() {
        int start = 0;
        int end = n * n - 1;
        assertEquals(start, (int) path.get(0));
        assertEquals(end, (int) path.get(path.size() - 1));
    }

    @Test
    public void wallFollowerPathIsContinuous() {
        for (int i = 1; i < path.size(); i++) {
            boolean isNeighbor = false;
            int previous = path.get(i - 1);
            int current = path.get(i);

            for (int j = 0; j < graph.get(previous).size(); j++) {
                if (graph.get(previous).get(j) == current) {
                    isNeighbor = true;
                    break;
                }
            }

            assertTrue(isNeighbor);
        }
    }
}
