package mazesolver.logic;

import mazesolver.util.ArrayList;

/**
 * Class responsible for the wall follower algorithm
 */
public class WallFollower {

    private ArrayList<ArrayList<Integer>> graph;
    private ArrayList<Integer> path;
    private int n;

    public WallFollower(ArrayList<ArrayList<Integer>> graph, int n) {
        this.graph = graph;
        this.n = n;
    }

    /**
     * Determines the cardinal direction label (N, E, S or W) corresponding to the
     * movement from the current node to the next node
     */
    private String determineDirectionLabel(int currentNode, int nextNode) {
        String direction = "";

        if (currentNode > nextNode) {
            if (currentNode - nextNode == 1) {
                direction = "W";
            } else {
                direction = "N";
            }
        } else if (nextNode > currentNode) {
            if (nextNode - currentNode == 1) {
                direction = "E";
            } else {
                direction = "S";
            }
        }

        return direction;
    }

    public void solve() {
        path = new ArrayList<>();

        int start = 0; // (0, 0)
        int end = n * n - 1; // (n-1, n-1)

        String direction = "E";
        int currentNode = start;
        int previousNode = 0;

        // Traverse the maze until the end is reached
        while (currentNode != end) {
            path.add(currentNode);

            int nextNode = -1;

            boolean rightAvailable = false;
            boolean forwardAvailable = false;
            boolean leftAvailable = false;

            // Follow the right-hand rule (check right -> forward -> left)
            // N = North, E = East, S = South, W = West
            if (direction.equals("N")) {
                for (int i = 0; i < graph.get(currentNode).size(); i++) {
                    int neighbor = graph.get(currentNode).get(i);

                    if (neighbor == currentNode + 1) {
                        rightAvailable = true;
                    }

                    if (neighbor == currentNode - n) {
                        forwardAvailable = true;
                    }

                    if (neighbor == currentNode - 1) {
                        leftAvailable = true;
                    }
                }

                if (rightAvailable) {
                    nextNode = currentNode + 1;
                } else if (forwardAvailable) {
                    nextNode = currentNode - n;
                } else if (leftAvailable) {
                    nextNode = currentNode - 1;
                } else {
                    // Turn 180 (backtrack)
                    nextNode = previousNode;
                }
            } else if (direction.equals("E")) {
                for (int i = 0; i < graph.get(currentNode).size(); i++) {
                    int neighbor = graph.get(currentNode).get(i);

                    if (neighbor == currentNode + n) {
                        rightAvailable = true;
                    }

                    if (neighbor == currentNode + 1) {
                        forwardAvailable = true;
                    }

                    if (neighbor == currentNode - n) {
                        leftAvailable = true;
                    }
                }

                if (rightAvailable) {
                    nextNode = currentNode + n;
                } else if (forwardAvailable) {
                    nextNode = currentNode + 1;
                } else if (leftAvailable) {
                    nextNode = currentNode - n;
                } else {
                    // Turn 180 (backtrack)
                    nextNode = previousNode;
                }

            } else if (direction.equals("S")) {
                for (int i = 0; i < graph.get(currentNode).size(); i++) {
                    int neighbor = graph.get(currentNode).get(i);

                    if (neighbor == currentNode - 1) {
                        rightAvailable = true;
                    }

                    if (neighbor == currentNode + n) {
                        forwardAvailable = true;
                    }

                    if (neighbor == currentNode + 1) {
                        leftAvailable = true;
                    }
                }

                if (rightAvailable) {
                    nextNode = currentNode - 1;
                } else if (forwardAvailable) {
                    nextNode = currentNode + n;
                } else if (leftAvailable) {
                    nextNode = currentNode + 1;
                } else {
                    // Turn 180 (backtrack)
                    nextNode = previousNode;
                }

            } else if (direction.equals("W")) {
                for (int i = 0; i < graph.get(currentNode).size(); i++) {
                    int neighbor = graph.get(currentNode).get(i);

                    if (neighbor == currentNode - n) {
                        rightAvailable = true;
                    }

                    if (neighbor == currentNode - 1) {
                        forwardAvailable = true;
                    }

                    if (neighbor == currentNode + n) {
                        leftAvailable = true;
                    }
                }

                if (rightAvailable) {
                    nextNode = currentNode - n;
                } else if (forwardAvailable) {
                    nextNode = currentNode - 1;
                } else if (leftAvailable) {
                    nextNode = currentNode + n;
                } else {
                    // Turn 180 (backtrack)
                    nextNode = previousNode;
                }
            }

            direction = determineDirectionLabel(currentNode, nextNode);

            previousNode = currentNode;
            currentNode = nextNode;
        }

        path.add(currentNode);
    }

    public ArrayList<Integer> getPath() {
        return path;
    }
}
