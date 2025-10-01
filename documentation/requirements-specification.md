# Requirements specification

This application generates random mazes and solves them using two classic pathfindings algorithms (wall follower and dead-end filling). The core feature is the visualization and animation of the generation and solving processes.

## Basic functionality

### Main view

- The application opens to a main view with two sections:
  - A control panel on the left side.
  - A maze visualization area on the right side.

### Control panel

- The size of the maze can be selected using a drop-down menu.
    - The available dimensions are: 5x5, 10x10, 20x20, 30x30, 40x40, 50x50, 75x75 and 100x100.
- A randomly generated maze is created when the user presses the "Generate maze" button.
- There is a selection available for the two maze solving algorithms:
    - Wall follower.
    - Dead-end filling.
- Maze generation and solving can be animated, showing the algorithms executing step by step.
- A performance test can be run to benchmark the execution times of both algorithms across different maze sizes.
    - The benchmark results are outputted directly to the control panel.

### Maze

- The maze is generated using a DFS algorithm.
- The generated maze always has a single solution (a perfect maze).
- The entrance is fixed at the top left corner and the exit is at the bottom right corner.