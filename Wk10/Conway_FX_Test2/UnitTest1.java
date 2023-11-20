// UnitTest1.java - Fall 2023
// ----------------------------------------------------------------------------------------
// @author B.Cornish
// For testing purposes only.  This file is not part of the project deliverables.
//
// 

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
// import javafx.animation.RotateTransition;
// import javafx.animation.ScaleTransition;
// import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class UnitTest1 extends Application {

    @Override
    public void start(Stage stage) {

        int width = 510;
        int height = width;
        int cellsWide = 30;
        int cellsHigh = cellsWide;
        int cellWidth = width / cellsWide;
        int cellHeight = height / cellsHigh;
        int cellXOffset;
        int cellYOffset;

        Group root = new Group();
        Scene scene = new Scene(root, width, height, Color.WHITE);

        Rectangle rectArray[][] = new Rectangle[cellsWide][cellsHigh];
        for (int i = 0; i < cellsWide; i++) {
            for (int j = 0; j < cellsHigh; j++) {
                cellXOffset = cellHeight * i ;
                cellYOffset = cellWidth * j ;

                System.out.println("x: " + cellXOffset + " y: " + cellYOffset);
                rectArray[i][j] = new Rectangle(cellXOffset, cellYOffset, cellWidth-1, cellHeight-1);
                rectArray[i][j].setFill(Color.GREEN);
                root.getChildren().add(rectArray[i][j]);
            }
        }

        for (int i = 0; i < cellsWide; i++) {
            for (int j = 0; j < cellsHigh; j++) {
                FillTransition fill = new FillTransition(Duration.millis(Math.random() * 500 + 500));
                
                // fill to random color
                fill.setToValue(new Color(Math.random(), Math.random(), Math.random(), 1));

                // fill.setToValue(Color.WHITE);
                ParallelTransition transition = new ParallelTransition(rectArray[i][j], fill);
                transition.setCycleCount(Timeline.INDEFINITE);
                transition.setAutoReverse(true);
                transition.play();
            }
        }

        stage.setTitle("JavaFX Scene - Cell Test");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        launch(args);

        // canvas size
        int cols = 15;
        int rows = 15;

        // render options
        boolean inChars = true;
        boolean wrap = false;

        // setup
        int[][] arr = setup2DArray(cols, rows);
        System.out.println();

        print2DArray(arr, inChars);

        // run
        for (int i = 0; i < 200; i++) {

            arr = nextGeneration(arr, wrap);
            print2DArray(arr, inChars);
            System.out.println("Generation: " + i + "\n");

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

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
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr[0].length - 1; j++) {

                if (wrap) { // count neighbors with wrap around
                    neighbors = countNeighborsWrap(arr, i, j);
                } else { // count neighbors without wrap around
                    neighbors = countNeighbors(arr, i, j);
                }

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
        // loop through the 3x3 grid around the cell
        for (int i = x - 1; i <= x + 1; i++) {
            // wrap around the edges
            if (i < 0) {
                i = arr.length - 1;
            } else if (i >= arr.length) {
                i = 0;
            }

            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0) {
                    j = arr[0].length - 1;
                } else if (j >= arr[0].length) {
                    j = 0;
                }
                if (i == x && j == y) { // skip the cell itself and don't count it as a neighbor
                    continue;
                }
                neighbors += arr[i][j]; // add the value of the cell to the neighbor count
            }
        }
        return neighbors;
    }
}
