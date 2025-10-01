package mazesolver.logic;

import mazesolver.util.ArrayList;
import mazesolver.util.Pair;
import mazesolver.util.RandomNumberGenerator;
import mazesolver.util.Stack;

/**
 * Class responsible for the maze logic
 */
public class Maze {

    private ArrayList<Pair<Integer, Integer>> path;
    private ArrayList<ArrayList<Integer>> graph;
    private int n;

    public Maze(int n) {
        this.n = n;
    }

    /**
     * Generates a random maze using a depth-first search (DFS) algorithm
     */
    public void generateMaze() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        boolean[][] visited = new boolean[n][n];

        path = new ArrayList<>();
        graph = new ArrayList<>();

        for (int i = 0; i < n * n; i++) {
            graph.add(new ArrayList<>());
        }

        // Choose a random starting cell for the maze generation
        int startX = rng.generate(n);
        int startY = rng.generate(n);
        stack.push(new Pair<>(startX, startY));
        visited[startX][startY] = true;

        int currentNode = 0;
        int previousNode;

        while (!stack.empty()) {
            ArrayList<String> unvisitedNeighbors = new ArrayList<>();

            int x = stack.peek().getKey();
            int y = stack.peek().getValue();

            stack.pop();

            // N = North, E = East, S = South, W = West
            if (y != 0 && !visited[x][y - 1]) {
                unvisitedNeighbors.add("N");
            }

            if (x + 1 != n && !visited[x + 1][y]) {
                unvisitedNeighbors.add("E");
            }

            if (y + 1 != n && !visited[x][y + 1]) {
                unvisitedNeighbors.add("S");
            }

            if (x != 0 && !visited[x - 1][y]) {
                unvisitedNeighbors.add("W");
            }

            if (unvisitedNeighbors.size() != 0) {
                stack.push(new Pair<>(x, y));

                int directionIndex = rng.generate(unvisitedNeighbors.size());
                String direction = unvisitedNeighbors.get(directionIndex);

                int offsetX = 0;
                int offsetY = 0;

                if (direction.equals("N")) {
                    offsetY = -1;
                } else if (direction.equals("E")) {
                    offsetX = 1;
                } else if (direction.equals("S")) {
                    offsetY = 1;
                } else if (direction.equals("W")) {
                    offsetX = -1;
                }

                stack.push(new Pair<>(x + offsetX, y + offsetY));
                visited[x + offsetX][y + offsetY] = true;
            }

            previousNode = currentNode;

            // Convert 2D coordinates to a unique node index for the graph
            currentNode = x + y * n;

            if (path.size() != 0) {
                graph.get(previousNode).add(currentNode);
            }

            path.add(new Pair<>(x, y));
        }
    }

    public ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }

    public ArrayList<Pair<Integer, Integer>> getPath() {
        return path;
    }
}
