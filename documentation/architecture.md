# Architecture

## Package structure

The project is divided into three main packages:

- `mazesolver.ui`: Contains the user interface classes.
- `mazesolver.logic`: Contains the application logic and algorithms
- `mazesolver.util`: Contains the necessary data structures and other utilities.

### UI

- `MazeSolverUI`: The main user interface class. Creates, draws and manages the user interface.

### Logic

- `Maze`: Generates and manages the maze. Random maze generation is done using a DFS algorithm.
- `WallFollower`: Solves the maze using the wall follower algorithm. Stores the solving path for animation in the UI.
- `DeadEndFilling`: Solves the maze using the dead-end filling algorithm. Stores the solving path for animation in the UI.

### Util

All data structures in this package are implemented from scratch for educational purposes.

- `ArrayList`: Simple dynamic array implementation.
- `Pair`: Simple pair data structure, mainly used to represent maze coordinates.
- `Stack`: LIFO data structure implemented using a linked list. Used only for the DFS algorithm.
- `Node`: Linked list node component used by the Stack class.
- `RandomNumberGenerator`: Custom random number generator based on the Linear Congruential Generator (LCG) formula.
- `PerformanceTest`: Benchmarks the execution time of the maze solving algorithms on mazes of different sizes. Results are displayed in the UI.

## Time and space complexities

The application generates mazes using an iterative DFS algorithm and solves them with the wall follower and dead-end filling algorithms. Both solving algorithms are ideal choices for this project because each generated maze has only one valid solution. All mazes are square, with dimensions n x n.

- Maze generation (DFS):
  - Time complexity: O(n²), where n is the width and height of the maze. Each cell is visited once.
  - Space complexity: O(n²), due to the grid and the stack used for iteration.

- Wall follower algorithm:
  - Time complexity: O(n²) in the worst case, as it may traverse the entire maze before reaching the exit.
  - Space complexity: O(1), since it only needs to track the current position and direction.

- Dead-end filling algorithm:
  - Time complexity: O(n²), as it iteratively fills dead ends until only the solution remains.
  - Space complexity: O(n²), since it must remember the location of every dead end and track visited cells.

Note: Storing the full solution path for animation/visualization purposes increases the space complexity for both solving algorithms to O(n²), but this is only required for UI features and does not affect the theoretical complexity of the algorithms themselves.