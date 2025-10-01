package mazesolver.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import mazesolver.logic.DeadEndFilling;
import mazesolver.logic.Maze;
import mazesolver.logic.WallFollower;
import mazesolver.util.ArrayList;
import mazesolver.util.Pair;
import mazesolver.util.PerformanceTest;

/**
 * Class responsible for the maze solver's user interface
 */
public class MazeSolverUI extends Application {

    private ArrayList<Integer> deadEndFillingPath;
    private ArrayList<Integer> wallFollowerPath;
    private ArrayList<Pair<Integer, Integer>> mazePath;
    private DeadEndFilling deadEndFilling;
    private Maze maze;
    private WallFollower wallFollower;

    private GridPane gridPane;
    private Rectangle endWall;
    private Rectangle startWall;
    private Stage window;
    private Timeline timeline;

    private int iteration;
    private int mazeSize;
    private int tileSize;

    private Parent createMainView(int mazeSize, int tileSize) {
        this.mazeSize = mazeSize;
        this.tileSize = tileSize;

        maze = new Maze(mazeSize);

        startWall = new Rectangle(2, tileSize - 2);
        startWall.setTranslateX(-((mazeSize + 2) * tileSize - tileSize + 2));
        startWall.setTranslateY(tileSize + 2);
        startWall.setFill(Color.BLACK);

        endWall = new Rectangle(2, tileSize - 2);
        endWall.setTranslateX(-tileSize - 4);
        endWall.setTranslateY((mazeSize + 1) * tileSize - tileSize + 2);
        endWall.setFill(Color.BLACK);

        HBox layout = new HBox(createControlPanel(), createGrid(mazeSize, tileSize), startWall, endWall);
        layout.setStyle("-fx-background-color: white");

        return layout;
    }

    private VBox createControlPanel() {
        VBox controlPanel = new VBox();

        Text mazeSizeText = new Text("Maze size:");

        ChoiceBox<String> dropdownMenu = new ChoiceBox<>();
        dropdownMenu.setPrefWidth(140);
        dropdownMenu.setValue(mazeSize + "x" + mazeSize);
        dropdownMenu.getItems().addAll("5x5", "10x10", "20x20", "30x30", "40x40", "50x50", "75x75", "100x100");
        dropdownMenu.valueProperty().addListener((observable) -> {
            initializeMaze();

            int index = dropdownMenu.getSelectionModel().getSelectedIndex();

            if (index == 0) {
                window.setScene(new Scene(createMainView(5, 100)));
            } else if (index == 1) {
                window.setScene(new Scene(createMainView(10, 58)));
            } else if (index == 2) {
                window.setScene(new Scene(createMainView(20, 32)));
            } else if (index == 3) {
                window.setScene(new Scene(createMainView(30, 22)));
            } else if (index == 4) {
                window.setScene(new Scene(createMainView(40, 17)));
            } else if (index == 5) {
                window.setScene(new Scene(createMainView(50, 18)));
            } else if (index == 6) {
                window.setScene(new Scene(createMainView(75, 12)));
            } else if (index == 7) {
                window.setScene(new Scene(createMainView(100, 9)));
            }
        });

        CheckBox animateGenerationCheckbox = new CheckBox("Animate generation");
        CheckBox animateAlgorithmCheckbox = new CheckBox("Animate algorithm");

        Separator separator1 = new Separator();
        separator1.setPadding(new Insets(20, 0, 20, 0));

        Separator separator2 = new Separator();
        separator2.setPadding(new Insets(20, 0, 20, 0));

        Button generateMazeButton = new Button("Generate maze");
        generateMazeButton.setPrefWidth(140);
        generateMazeButton.setOnAction(value -> {
            initializeMaze();

            maze.generateMaze();
            mazePath = maze.getPath();

            startWall.setFill(Color.WHITE);
            endWall.setFill(Color.WHITE);

            if (animateGenerationCheckbox.isSelected()) {
                animate("DFS", mazePath.size() - 1);
            } else {
                for (int i = 0; i < mazePath.size() - 1; i++) {
                    drawMaze(i);
                }

                initializeColors();
            }
        });

        Button wallFollowerButton = new Button("Wall follower");
        wallFollowerButton.setPrefWidth(140);
        wallFollowerButton.setOnAction(value -> {
            if (maze.getGraph() == null) {
                return;
            }

            initializeColors();

            wallFollower = new WallFollower(maze.getGraph(), mazeSize);
            wallFollower.solve();
            wallFollowerPath = wallFollower.getPath();

            if (animateAlgorithmCheckbox.isSelected()) {
                animate("WallFollower", wallFollowerPath.size());
            } else {
                for (int i = 0; i < wallFollowerPath.size(); i++) {
                    drawWallFollower(i);
                }
            }
        });

        Button deadEndFillingButton = new Button("Dead-end filling");
        deadEndFillingButton.setPrefWidth(140);
        deadEndFillingButton.setOnAction(value -> {
            if (maze.getGraph() == null) {
                return;
            }

            initializeColors();

            deadEndFilling = new DeadEndFilling(maze.getGraph(), mazeSize);
            deadEndFilling.solve();
            deadEndFillingPath = deadEndFilling.getPath();

            if (animateAlgorithmCheckbox.isSelected()) {
                animate("DeadEndFilling", deadEndFillingPath.size());
            } else {
                for (int i = 0; i < deadEndFillingPath.size(); i++) {
                    drawDeadEndFilling(i);
                }
            }
        });

        TextArea output = new TextArea();
        output.setEditable(false);
        output.setPrefHeight(tileSize * (mazeSize + 2) - 320);
        output.setStyle("-fx-faint-focus-color: transparent; -fx-focus-color: transparent");

        Button performanceTestButton = new Button("Performance test");
        performanceTestButton.setPrefWidth(140);
        performanceTestButton.setOnAction(value -> {
            PerformanceTest performanceTest = new PerformanceTest();

            String result = "Starting test...\n\n";
            result = result + performanceTest.wallFollowerPerformanceTest();
            result = result + "\n";
            result = result + performanceTest.deadEndFillingPerformanceTest();
            result = result + "\nTest completed!";

            output.setText(result);
        });

        controlPanel.getChildren().addAll(mazeSizeText, dropdownMenu, generateMazeButton, animateGenerationCheckbox,
                separator1, wallFollowerButton, deadEndFillingButton, animateAlgorithmCheckbox,
                separator2, performanceTestButton, output);
        controlPanel.setPadding(new Insets(5));
        controlPanel.setPrefWidth(150);
        controlPanel.setSpacing(5);
        controlPanel.setStyle("-fx-background-color: #f5f5f5");

        return controlPanel;
    }

    private GridPane createGrid(int mazeSize, int tileSize) {
        gridPane = new GridPane();
        gridPane.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox.setMargin(gridPane, new Insets(tileSize));

        for (int y = 0; y < mazeSize; y++) {
            for (int x = 0; x < mazeSize; x++) {
                Region tile = new Region();
                tile.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                tile.setPrefSize(tileSize, tileSize);

                gridPane.add(tile, x, y);
            }
        }

        return gridPane;
    }

    private void initializeMaze() {
        if (timeline != null) {
            timeline.stop();
        }

        for (int i = 0; i < mazeSize * mazeSize; i++) {
            Region tile = (Region) gridPane.getChildren().get(i);

            tile.setBorder(new Border(
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            tile.setStyle("-fx-background-color: white");
        }
    }

    private void initializeColors() {
        if (timeline != null) {
            timeline.stop();
        }

        for (int i = 0; i < mazeSize * mazeSize; i++) {
            Region tile = (Region) gridPane.getChildren().get(i);
            tile.setStyle("-fx-background-color: white");
        }
    }

    private void drawMaze(int i) {
        int x1 = mazePath.get(i).getKey();
        int y1 = mazePath.get(i).getValue();
        int x2 = mazePath.get(i + 1).getKey();
        int y2 = mazePath.get(i + 1).getValue();

        Region currentTile = (Region) gridPane.getChildren().get(x1 + y1 * mazeSize);
        currentTile.setStyle("-fx-background-color: white");
        BorderStroke border1 = currentTile.getBorder().getStrokes().get(0);

        Region nextTile = (Region) gridPane.getChildren().get(x2 + y2 * mazeSize);
        nextTile.setStyle("-fx-background-color: gray");
        BorderStroke border2 = nextTile.getBorder().getStrokes().get(0);

        if (x1 == x2) {
            if (y1 > y2) {
                currentTile.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                        BorderStrokeStyle.NONE, border1.getRightStyle(), border1.getBottomStyle(),
                        border1.getLeftStyle(), border1.getRadii(), border1.getWidths(), border1.getInsets())));
                nextTile.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                        border2.getTopStyle(), border2.getRightStyle(), BorderStrokeStyle.NONE, border2.getLeftStyle(),
                        border2.getRadii(), border2.getWidths(), border2.getInsets())));
            } else {
                currentTile.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                        border1.getTopStyle(), border1.getRightStyle(), BorderStrokeStyle.NONE, border1.getLeftStyle(),
                        border1.getRadii(), border1.getWidths(), border1.getInsets())));
                nextTile.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                        BorderStrokeStyle.NONE, border2.getRightStyle(), border2.getBottomStyle(),
                        border2.getLeftStyle(), border2.getRadii(), border2.getWidths(), border2.getInsets())));
            }
        } else if (y1 == y2) {
            if (x1 > x2) {
                currentTile.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                        border1.getTopStyle(), border1.getRightStyle(), border1.getBottomStyle(),
                        BorderStrokeStyle.NONE, border1.getRadii(), border1.getWidths(), border1.getInsets())));
                nextTile.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                        border2.getTopStyle(), BorderStrokeStyle.NONE, border2.getBottomStyle(), border2.getLeftStyle(),
                        border2.getRadii(), border2.getWidths(), border2.getInsets())));
            } else {
                currentTile.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                        border1.getTopStyle(), BorderStrokeStyle.NONE, border1.getBottomStyle(), border1.getLeftStyle(),
                        border1.getRadii(), border1.getWidths(), border1.getInsets())));
                nextTile.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                        border2.getTopStyle(), border2.getRightStyle(), border2.getBottomStyle(),
                        BorderStrokeStyle.NONE, border2.getRadii(), border2.getWidths(), border2.getInsets())));
            }
        }
    }

    private void drawWallFollower(int i) {
        Region tile = (Region) gridPane.getChildren().get(wallFollowerPath.get(i));
        tile.setStyle("-fx-background-color: #ffcccb");
    }

    private void drawDeadEndFilling(int i) {
        Region tile = (Region) gridPane.getChildren().get(deadEndFillingPath.get(i));
        tile.setStyle("-fx-background-color: gray");
    }

    private void animate(String algorithm, int iterationCount) {
        iteration = 0;

        timeline = new Timeline(new KeyFrame(Duration.millis(25), (ActionEvent event) -> {
            if (algorithm.equals("DFS")) {
                drawMaze(iteration++);
            } else if (algorithm.equals("WallFollower")) {
                drawWallFollower(iteration++);
            } else if (algorithm.equals("DeadEndFilling")) {
                drawDeadEndFilling(iteration++);
            }
        }));

        timeline.setCycleCount(iterationCount);
        timeline.play();

        if (algorithm.equals("DFS")) {
            timeline.setOnFinished(event -> initializeColors());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        window = stage;

        stage.setResizable(false);
        stage.setScene(new Scene(createMainView(10, 58)));
        stage.setTitle("Maze Solver");
        stage.show();
    }
}
