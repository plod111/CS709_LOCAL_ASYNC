// GridPane_Test3.java - Fall 2023
// ----------------------------------------------------------------------------------------
// @author B.Cornish
// For testing purposes only.  This file is not part of the project deliverables.
// ----------------------------------------------------------------------------------------
// Attempt1 at using TimeLine with GridPane
//
// 

//import java.beans.EventHandler;

import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
// import javafx.animation.RotateTransition;
// import javafx.animation.ScaleTransition;
// import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class GridPane_Test3 extends Application {

    private GridPane Grid = new GridPane(); // Layout

    private Label label = new Label(); // Label

    Button b1 = new Button("R"); // Button

    int cellArray[][]; // Array of 'cells''

    private int height = 600;
    private int width = 600;
    private int pixel = 30;
    private int cellsWide = width / pixel;
    private int cellsHigh = cellsWide;

    @Override
    public void start(Stage stage) {

        Group root = new Group();

        Grid.setGridLinesVisible(true);

        cellArray = new int[cellsWide][cellsHigh];

        Scene scene = new Scene(Grid, width, height); // Add Layout to scene

        FillingLayoutWithLabels(width, height);

        // new timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), new EventHandler() {
            @Override
            public void handle(Event event) {
                updateCells();
            }
        }));

        // set cycle count indefinite
        timeline.setCycleCount(Timeline.INDEFINITE);

        // play timeline
        timeline.play();

        stage.setTitle("JavaFX Scene - Cell Test");
        stage.setScene(scene);
        stage.show();
    }

    private void updateCells() {
        // fill array with random 0 or 1
        for (int i = 0; i < cellsWide; i++) {
            for (int j = 0; j < cellsHigh; j++) {
                cellArray[i][j] = (int) (Math.random() * 2);
            }
        }

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

        label.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

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
