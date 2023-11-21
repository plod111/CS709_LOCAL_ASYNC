
/**
 * GoL_FX1.java - Fall 2023
 * CS709 - Hunter College
 * 
 * Game Of Life
 * 
 * Render options
 * - inChars: boolean flag to render as chars or ints
 * - wrap: boolean flag to wrap around edges or not
 * 
 * @author B.Cornish
 * @date Nov 20, 2023
 */

import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GoL_FX1 extends Application {

    private GridPane Grid = new GridPane(); // Layout

    private Label label = new Label(); // Label

    int cellArray[][]; // Array of 'cells''

    // render options
    private int height = 600;
    private int width = 600;
    private int pixel = 15;
    private int cellsWide = width / pixel;
    private int cellsHigh = cellsWide;

    // generation counter
    int genCount = 0;

    @Override
    public void start(Stage stage) {

        // render options
        boolean inChars = true;
        boolean wrap = true;

        Grid.setGridLinesVisible(true); // Show grid lines
        Scene scene = new Scene(Grid, width, height); // Add Layout to scene

        // setup
        cellArray = new int[cellsWide][cellsHigh];
        cellArray = setup2DArray(cellsWide, cellsHigh);
        FillingLayoutWithLabels(width, height);

        // new timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), new EventHandler() {
            @Override
            public void handle(Event event) {
                cellArray = nextGeneration(cellArray, wrap);
                print2DArray(cellArray, inChars);
                System.out.println("Generation: " + genCount + "\n");
                updateCells(cellArray);
                genCount++;
            }
        }));

        // set cycle count indefinite
        timeline.setCycleCount(Timeline.INDEFINITE);

        // play timeline
        timeline.play();

        stage.setTitle("Game Of Life, YO!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Updates the cells in the grid with label colors
     * @param cellArray
     */
    private void updateCells(int[][] cellArray) {
        // System.out.println("Updating cells");
        // loop through array and set label to red or green
        for (int i = 0; i < cellsWide; i++) {
            for (int j = 0; j < cellsHigh; j++) {

                if (cellArray[i][j] == 0) {
                    label = (Label) Grid.getChildren().get(j * cellsWide + i + 1);
                    label.setBackground(
                            new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                } else {
                    label = (Label) Grid.getChildren().get(j * cellsWide + i + 1);
                    label.setBackground(new Background(
                            new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }

    /**
     * Sets up the grid with labels
     * @param width
     * @param height
     */
    private void FillingLayoutWithLabels(int width, int height) {

        for (int i = 0; i < width / pixel; i++) {
            for (int j = 0; j < height / pixel; j++) {
                addLabel(i, j);
            }
        }
    }

    /**
     * Adds a label to the grid
     * @param columnIndex
     * @param rowIndex
     */
    public void addLabel(int columnIndex, int rowIndex) {

        Label label = new Label();
        label.setMinWidth(pixel);
        label.setMinHeight(pixel);
        label.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        GridPane.setColumnIndex(label, columnIndex);
        GridPane.setRowIndex(label, rowIndex);

        label.setId(rowIndex + " " + columnIndex);
        Grid.getChildren().add(label);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Driver /////////////////////////////////////////////////////////////////
    public static void main(String[] args) {

        launch(args);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Methods of GameOfLife1.java ////////////////////////////////////////////

    /**
     * Sets up a 2D array of 0s and 1s
     * 
     * @param cols
     * @param rows
     * @return
     */
    public static int[][] setup2DArray(int cols, int rows) {
        int[][] arr = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                arr[i][j] = (int) Math.floor(Math.random() * 2);
            }
        }
        return arr;
    }

    /**
     * Prints a 2D array of 0s and 1s
     * 
     * @param arr
     * @param inChars
     */
    public static void print2DArray(int[][] arr, boolean inChars) {
        for (int[] row : arr) {
            for (int cell : row) {
                if (inChars) {
                    System.out.print(cell == 1 ? "X " : "  ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Calculates the next generation of a 2D array of 0s and 1s
     * 
     * @param arr
     * @param wrap
     * @return
     */
    public static int[][] nextGeneration(int[][] arr, boolean wrap) {
        // create a new array to store the next generation
        int[][] next = new int[arr.length][arr[0].length];

        // loop through the array
        int neighbors;
        if (wrap) { // count neighbors with wrap around
            // System.out.println(arr.length + " " + arr[0].length);
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    // System.out.println("i: " + i + " j: " + j);
                    neighbors = countNeighborsWrap(arr, i, j);
                    // System.out.println("neighbors: " + neighbors);
                    // apply the rules of the game
                    if (arr[i][j] == 1 && neighbors < 2) {
                        next[i][j] = 0;
                    } else if (arr[i][j] == 1 && neighbors > 3) {
                        next[i][j] = 0;
                    } else if (arr[i][j] == 0 && neighbors == 3) {
                        next[i][j] = 1;
                    } else {
                        next[i][j] = arr[i][j];
                    }
                }
            }
            
        } else {
            // count neighbors without wrap around
            for (int i = 1; i < arr.length - 1; i++) {
                for (int j = 1; j < arr[0].length - 1; j++) {

                    neighbors = countNeighbors(arr, i, j);
                    // apply the rules of the game
                    if (arr[i][j] == 1 && neighbors < 2) {
                        next[i][j] = 0;
                    } else if (arr[i][j] == 1 && neighbors > 3) {
                        next[i][j] = 0;
                    } else if (arr[i][j] == 0 && neighbors == 3) {
                        next[i][j] = 1;
                    } else {
                        next[i][j] = arr[i][j];
                    }
                }
            }
        }

        return next;
    }

    /**
     * Counts the number of neighbors of a cell in a 2D array of 0s and 1s
     * bounded by the edges
     * 
     * @param arr
     * @param x
     * @param y
     * @return
     */
    public static int countNeighbors(int[][] arr, int x, int y) {
        int neighbors = 0;
        // loop through the 3x3 grid around the cell
        for (int i = x - 1; i <= x + 1; i++) {
            // skip if the cell is outside the array
            if (i < 0 || i >= arr.length) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                // skip if the cell is outside the array
                if (j < 0 || j >= arr[0].length) {
                    continue;
                }
                // skip the cell itself and don't count it as a neighbor
                if (i == x && j == y) {
                    continue;
                }
                // add the value of the cell to the neighbor count
                neighbors += arr[i][j];
            }
        }
        return neighbors;
    }

    /**
     * Counts the number of neighbors of a cell in a 2D array of 0s and 1s
     * wrapping around the edges
     * 
     * @param arr
     * @param x
     * @param y
     * @return
     */
    public static int countNeighborsWrap(int[][] arr, int x, int y) {
        int neighbors = 0;
        int xx=0;
        int yy=0;
        // loop through the 3x3 grid around the cell
        for (int i = x - 1; i <= x + 1; i++) {
            xx = i;
            // wrap around the edges
            if (i < 0) {
                xx = arr.length - 1;
            } else if (i >= arr.length) {
                xx = 0;
            }

            for (int j = y - 1; j <= y + 1; j++) {
                yy=j;
                // System.out.println("j: " + j );

                if (j < 0) {
                    yy = arr[0].length - 1;
                } else if (j >= arr[0].length) {
                    yy = 0;
                }

                if (i == x && j == y) { // skip the cell itself and don't count it as a neighbor
                    continue;
                }
                // System.out.println("i: " + i + " j: " + j);
                neighbors += arr[xx][yy]; // add the value of the cell to the neighbor count
                // System.out.println("neighbors: " + neighbors);
            }
        }
        // System.out.println("neighbors: " + neighbors);
        return neighbors;
    }
}
