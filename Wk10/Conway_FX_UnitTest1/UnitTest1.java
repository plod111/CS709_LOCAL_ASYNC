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
    }
}
