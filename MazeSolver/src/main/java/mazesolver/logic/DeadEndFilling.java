package mazesolver.logic;

import mazesolver.util.ArrayList;

/**
 * Class responsible for the dead-end filling algorithm
 */
public class DeadEndFilling {

    private ArrayList<ArrayList<Integer>> graph;
    private ArrayList<Integer> path;
    private boolean visited[];
    private int n;

    public DeadEndFilling(ArrayList<ArrayList<Integer>> graph, int n) {
        this.graph = graph;
        this.n = n;
    }

    /**
     * Counts the number of visited neighbors for a node
     */
    private int countVisitedNeighbors(int node) {
        int visitedNeighbors = 0;

        for (int i = 0; i < graph.get(node).size(); i++) {
            if (visited[graph.get(node).get(i)]) {
                visitedNeighbors++;
            }
        }

        return visitedNeighbors;
    }

    /**
     * Iterates through the node's neighbors and returns the next unvisited node
     */
    private int findNextNode(int node) {
        int nextNode = -1;

        for (int i = 0; i < graph.get(node).size(); i++) {
            if (!visited[graph.get(node).get(i)]) {
                nextNode = graph.get(node).get(i);
                path.add(node);
                visited[node] = true;
                break;
            }
        }

        return nextNode;
    }

    public void solve() {
        ArrayList<Integer> deadEnds = new ArrayList<>();
        path = new ArrayList<>();
        visited = new boolean[n * n];

        int start = 0; // (0, 0)
        int end = n * n - 1; // (n-1, n-1)

        // Search for dead ends
        for (int i = 0; i < graph.size(); i++) {
            // If the node has only one neighbor, it's a dead end
            if (graph.get(i).size() == 1 && i != start && i != end) {
                deadEnds.add(i);
                path.add(i);
                visited[i] = true;
            }
        }

        // Process the dead ends
        for (int i = 0; i < deadEnds.size(); i++) {
            int node = graph.get(deadEnds.get(i)).get(0);

            // Move forward from the dead end until a junction is encountered or the next
            // node is the start/end node
            while (true) {
                if (node == start || node == end || visited[node]) {
                    break;
                }

                int neighbors = graph.get(node).size();
                int visitedNeighbors = countVisitedNeighbors(node);

                if (neighbors - visitedNeighbors == 0) {
                    // If all neighbors are visited, this node is now a dead end
                    path.add(node);
                    visited[node] = true;
                    break;
                } else if (neighbors - visitedNeighbors == 1) {
                    // If exactly one neighbor is unvisited, move forward to that neighbor
                    int nextNode = findNextNode(node);
                    node = nextNode;
                } else if (neighbors - visitedNeighbors > 1) {
                    // If more than one neighbor is unvisited, we've reached a junction
                    break;
                }
            }
        }
    }

    public ArrayList<Integer> getPath() {
        return path;
    }
}
