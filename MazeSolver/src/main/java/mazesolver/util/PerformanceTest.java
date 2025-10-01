package mazesolver.util;

import mazesolver.logic.DeadEndFilling;
import mazesolver.logic.Maze;
import mazesolver.logic.WallFollower;

/**
 * Benchmarks the performance of the maze solving algorithms implemented in this
 * project
 */
public class PerformanceTest {

    private Maze maze1;
    private Maze maze2;
    private Maze maze3;
    private Maze maze4;
    private Maze maze5;
    private Maze maze6;

    public PerformanceTest() {
        // Generate mazes before starting the tests
        maze1 = new Maze(100); // 100x100
        maze1.generateMaze();

        maze2 = new Maze(250); // 250x250
        maze2.generateMaze();

        maze3 = new Maze(500); // 500x500
        maze3.generateMaze();

        maze4 = new Maze(750); // 750x750
        maze4.generateMaze();

        maze5 = new Maze(1000); // 1000x1000
        maze5.generateMaze();

        maze6 = new Maze(2500); // 2500x2500
        maze6.generateMaze();
    }

    /**
     * Wall follower algorithm performance test
     * 
     * @return test results as a string
     */
    public String wallFollowerPerformanceTest() {
        String result = "Wall follower:\n";
        long timer;

        timer = System.nanoTime();
        WallFollower wallFollower1 = new WallFollower(maze1.getGraph(), 100);
        wallFollower1.solve();
        result = result + "100x100: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        WallFollower wallFollower2 = new WallFollower(maze2.getGraph(), 250);
        wallFollower2.solve();
        result = result + "250x250: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        WallFollower wallFollower3 = new WallFollower(maze3.getGraph(), 500);
        wallFollower3.solve();
        result = result + "500x500: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        WallFollower wallFollower4 = new WallFollower(maze4.getGraph(), 750);
        wallFollower4.solve();
        result = result + "750x750: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        WallFollower wallFollower5 = new WallFollower(maze5.getGraph(), 1000);
        wallFollower5.solve();
        result = result + "1000x1000: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        WallFollower wallFollower6 = new WallFollower(maze6.getGraph(), 2500);
        wallFollower6.solve();
        result = result + "2500x2500: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        return result;
    }

    /**
     * Dead-end filling algorithm performance test
     * 
     * @return test results as a string
     */
    public String deadEndFillingPerformanceTest() {
        String result = "Dead-end filling:\n";
        long timer;

        timer = System.nanoTime();
        DeadEndFilling deadEndFilling1 = new DeadEndFilling(maze1.getGraph(), 100);
        deadEndFilling1.solve();
        result = result + "100x100: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        DeadEndFilling deadEndFilling2 = new DeadEndFilling(maze2.getGraph(), 250);
        deadEndFilling2.solve();
        result = result + "250x250: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        DeadEndFilling deadEndFilling3 = new DeadEndFilling(maze3.getGraph(), 500);
        deadEndFilling3.solve();
        result = result + "500x500: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        DeadEndFilling deadEndFilling4 = new DeadEndFilling(maze4.getGraph(), 750);
        deadEndFilling4.solve();
        result = result + "750x750: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        DeadEndFilling deadEndFilling5 = new DeadEndFilling(maze5.getGraph(), 1000);
        deadEndFilling5.solve();
        result = result + "1000x1000: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        timer = System.nanoTime();
        DeadEndFilling deadEndFilling6 = new DeadEndFilling(maze6.getGraph(), 2500);
        deadEndFilling6.solve();
        result = result + "2500x2500: " + ((System.nanoTime() - timer) / 1000000) + "ms\n";

        return result;
    }
}
