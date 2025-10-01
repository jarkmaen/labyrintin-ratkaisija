package mazesolver.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mazesolver.util.ArrayList;

public class DeadEndFillingTest {

    private ArrayList<ArrayList<Integer>> graph;
    private DeadEndFilling deadEndFilling;
    private Maze maze;
    private boolean[] filled;
    private int end;
    private int n;
    private int start;

    @Before
    public void setUp() {
        n = 50;

        start = 0; // (0, 0)
        end = n * n - 1; // (n-1, n-1)

        maze = new Maze(n);
        maze.generateMaze();

        graph = maze.getGraph();
        deadEndFilling = new DeadEndFilling(graph, n);
        deadEndFilling.solve();
        filled = new boolean[n * n];

        ArrayList<Integer> filledNodes = deadEndFilling.getPath();

        for (int i = 0; i < filledNodes.size(); i++) {
            filled[filledNodes.get(i)] = true;
        }
    }

    @Test
    public void onlyOnePathRemainsAfterDeadEndFilling() {
        // All non-filled nodes (except start and end) should have exactly 2 neighbors
        for (int i = 0; i < graph.size(); i++) {
            if (!filled[i] && i != start && i != end) {
                int neighborCount = 0;

                for (int j = 0; j < graph.get(i).size(); j++) {
                    if (!filled[graph.get(i).get(j)]) {
                        neighborCount++;
                    }
                }

                assertEquals(2, neighborCount);
            }
        }
    }

    @Test
    public void solutionPathConnectsStartToEnd() {
        boolean[] visited = new boolean[n * n];
        int curretNode = start;
        visited[curretNode] = true;

        while (curretNode != end) {
            int nextNode = -1;

            for (int i = 0; i < graph.get(curretNode).size(); i++) {
                int neighbor = graph.get(curretNode).get(i);

                if (!filled[neighbor] && !visited[neighbor]) {
                    nextNode = neighbor;
                    break;
                }
            }

            if (nextNode == -1) {
                break;
            }

            curretNode = nextNode;
            visited[curretNode] = true;
        }

        assertEquals(end, curretNode);
    }
}
