// UnitTest1.java - Fall 2023
// ----------------------------------------------------------------------------------------
// @author B.Cornish
// For testing purposes only.  This file is not part of the project deliverables.
//
// 

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
// import javafx.animation.RotateTransition;
// import javafx.animation.ScaleTransition;
// import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class GridPane_Test1 extends Application {

    private GridPane Grid = new GridPane(); // Layout

    private Label label = new Label(); // Label

    private int height = 600;
    private int width = 600;
    private int pixel = 30;

    @Override
    public void start(Stage stage) {

        int width = 510;
        int height = width;
        int cellsWide = 5;
        int cellsHigh = cellsWide;
        int cellWidth = width / cellsWide;
        int cellHeight = height / cellsHigh;
        int cellXOffset;
        int cellYOffset;

        Group root = new Group();

        Grid.setGridLinesVisible(true);

        /**
         * Rectangle rectArray[][] = new Rectangle[cellsWide][cellsHigh];
         * 
         * for (int i = 0; i < cellsWide; i++) {
         * for (int j = 0; j < cellsHigh; j++) {
         * 
         * // create a label
         * rectArray[i][j] = new Rectangle(cellWidth, cellHeight);
         * root.getChildren().add(rectArray[i][j]);
         * }
         * }
         * 
         * for (int i = 0; i < cellsWide; i++) {
         * for (int j = 0; j < cellsHigh; j++) {
         * FillTransition fill = new FillTransition(Duration.millis(Math.random() * 500
         * + 500));
         * 
         * // fill to random color
         * fill.setToValue(new Color(Math.random(), Math.random(), Math.random(), 1));
         * 
         * // fill.setToValue(Color.WHITE);
         * ParallelTransition transition = new ParallelTransition(rectArray[i][j],
         * fill);
         * transition.setCycleCount(Timeline.INDEFINITE);
         * transition.setAutoReverse(true);
         * transition.play();
         * }
         * }
         */

        Scene scene = new Scene(Grid, width, height); // Add Layout to scene

        FillingLayoutWithLabels(width, height);

        // set random label to green
        int randomRow = (int) (Math.random() * (height / pixel));
        int randomCol = (int) (Math.random() * (width / pixel));
        label = (Label) Grid.getChildren().get(randomRow * (width / pixel) + randomCol+1);
        label.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        stage.setTitle("JavaFX Scene - Cell Test");
        stage.setScene(scene);
        stage.show();
    }



    
    private void FillingLayoutWithLabels(int width, int height) {

        for (int i = 0; i < width / pixel; i++) {

            for (int j = 0; j < height / pixel; j++) {

                addLabel(i, j);
            }
        }
    }

    public void addLabel(int columnIndex, int rowIndex) {

        Label label = new Label();

        label.setMinWidth(pixel);

        label.setMinHeight(pixel);

        label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        GridPane.setColumnIndex(label, columnIndex);

        GridPane.setRowIndex(label, rowIndex);

        label.setId(rowIndex + " " + columnIndex);

        Grid.getChildren().add(label);
    }

    public static void main(String[] args) {

        launch(args);
    }

}
