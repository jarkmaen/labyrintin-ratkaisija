package mazesolver.logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mazesolver.util.ArrayList;
import mazesolver.util.Pair;
import mazesolver.util.Stack;

public class MazeTest {

    @Test
    public void mazeGraphHasCorrectNumberOfNodes() {
        Maze maze = new Maze(10);
        maze.generateMaze();
        assertEquals(100, maze.getGraph().size());
    }

    @Test
    public void mazeHasPathFromStartToEnd() {
        int n = 100;
        Maze maze = new Maze(n);
        maze.generateMaze();

        ArrayList<ArrayList<Integer>> graph = maze.getGraph();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n * n];

        int start = 0; // (0, 0)
        int end = n * n - 1; // (n-1, n-1)

        stack.push(start);
        visited[start] = true;

        while (!stack.empty()) {
            int node = stack.pop();

            for (int i = 0; i < graph.get(node).size(); i++) {
                int next = graph.get(node).get(i);

                if (!visited[next]) {
                    stack.push(next);
                    visited[next] = true;
                }
            }
        }

        assertEquals(true, visited[end]);
    }

    @Test
    public void mazePathVisitsAllCells() {
        int n = 5;
        Maze maze = new Maze(n);
        maze.generateMaze();

        ArrayList<Pair<Integer, Integer>> path = maze.getPath();
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < path.size(); i++) {
            Pair<Integer, Integer> cell = path.get(i);
            visited[cell.getKey()][cell.getValue()] = true;
        }

        int count = 0;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (visited[x][y]) {
                    count++;
                }
            }
        }

        assertEquals(n * n, count);
    }
}
