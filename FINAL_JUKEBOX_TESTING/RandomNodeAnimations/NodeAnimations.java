// NodeAnimations.java - Fall 2023 - A confetti generator that uses random node animations.
// ----------------------------------------------------------------------------------------

// To compile and run:
// javac --module-path /home/plod/Documents/CS-709/JavaFX/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media NodeAnimations.java
// java --module-path /home/plod/Documents/CS-709/JavaFX/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media NodeAnimations.java

// Imports relevant to the graphical elements.
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Imports relevant to the animation effects
import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;

import java.io.FileNotFoundException;


import javafx.animation.FillTransition;
import javafx.util.Duration;



public class NodeAnimations extends Application {

	int numRectangles = 150;

	Rectangle[] rectangles = new Rectangle[numRectangles];
	TranslateTransition[] translations = new TranslateTransition[numRectangles];
	FillTransition[] fills = new FillTransition[numRectangles];
	RotateTransition[] rotations = new RotateTransition[numRectangles];
	ScaleTransition[] scales = new ScaleTransition[numRectangles];
	ParallelTransition[] transitions = new ParallelTransition[numRectangles];


	int sceneWidth = 1000;
	int sceneHeight = 800;

	@Override
	public void start(Stage stage) throws FileNotFoundException {
		// Builds the base window for the GUI (graphical user interface).
		Group root = new Group();
		Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.BLACK);

		buildRectangles(numRectangles, root);
		buildTransitions(numRectangles, root, scene);
		//////////////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////////////
		// Create the label for the song title.
		Text text = new Text(50, 50, "");
		text.setFont(Font.font("Cambria", 38));
		text.setStyle("-fx-font-weight: bold");
		TextField textField = new TextField();
		textField.setText("Confetti Generator 9000");
		text.textProperty().bind(textField.textProperty());

		////////////////////////////////////////////////////////////////////////////////
		// Neon effect
		Blend blend = new Blend();
		blend.setMode(BlendMode.MULTIPLY);

		DropShadow ds = new DropShadow();
		ds.setColor(Color.rgb(254, 235, 66, 0.3));
		ds.setOffsetX(5);
		ds.setOffsetY(5);
		ds.setRadius(5);
		ds.setSpread(0.2);

		blend.setBottomInput(ds);

		DropShadow ds1 = new DropShadow();
		ds1.setColor(Color.web("#f13a00"));
		ds1.setRadius(20);
		ds1.setSpread(0.2);

		Blend blend2 = new Blend();
		blend2.setMode(BlendMode.MULTIPLY);

		InnerShadow is = new InnerShadow();
		is.setColor(Color.web("#feeb42"));
		is.setRadius(9);
		is.setChoke(0.8);
		blend2.setBottomInput(is);

		InnerShadow is1 = new InnerShadow();
		is1.setColor(Color.web("#f13a00"));
		is1.setRadius(5);
		is1.setChoke(0.4);
		blend2.setTopInput(is1);

		Blend blend1 = new Blend();
		blend1.setMode(BlendMode.MULTIPLY);
		blend1.setBottomInput(ds1);
		blend1.setTopInput(blend2);

		blend.setTopInput(blend1);
		text.setEffect(blend);
		root.getChildren().add(text);

		////////////////////////////////////////////////////////////////////////////////

		

		// Launch the application window
		stage.setTitle("Random Node Demo");
		stage.setScene(scene);
		stage.show();

	}


	private void buildRectangles(int numRectangles, Group root) {

		for (int i = 0; i < numRectangles; i++) {
			rectangles[i] = new Rectangle((int) (Math.random() * sceneWidth), (int) (Math.random() * sceneHeight),
					(int) (Math.random() * 100), (int) (Math.random() * 100));
			// System.out.println(rectangles[i]);
			// System.out.println((int) (Math.random() * sceneWidth));
			// random color
			rectangles[i].setFill(
					Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
			root.getChildren().add(rectangles[i]);
		}

	}
	

	
	private void buildTransitions(int numRectangles, Group root, Scene scene) {

		// for each rectangle
		for (int i = 0; i < numRectangles; i++) {
			// Add the animation effects
			int duration = (int)(Math.random() * 1750+300);
			translations[i] = new TranslateTransition(Duration.millis(duration));
			translations[i].setToX((int)(Math.random() * 2*sceneWidth-sceneWidth));
			translations[i].setToY((int)(Math.random() * 2*sceneHeight-sceneHeight));

			fills[i] = new FillTransition(Duration.millis(duration));
			fills[i].setToValue(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),
					(int) (Math.random() * 255)));

			rotations[i] = new RotateTransition(Duration.millis(duration));
			rotations[i].setToAngle((int)Math.random() * 720 -360);

			scales[i] = new ScaleTransition(Duration.millis(duration));
			scales[i].setToX((int)(Math.random()*2));
			scales[i].setToY((int)(Math.random()*2));

			transitions[i] = new ParallelTransition(rectangles[i], translations[i], fills[i], rotations[i], scales[i]);
			transitions[i].setCycleCount(Timeline.INDEFINITE);
			transitions[i].setAutoReverse(true);
			transitions[i].play();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
