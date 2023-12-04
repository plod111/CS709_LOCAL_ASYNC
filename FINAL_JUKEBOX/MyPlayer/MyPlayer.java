// MyPlayer.java - Fall 2023 - A groovy, animated music player.
// ----------------------------------------------------------------------------------------

// Please note that a large portion of this code, specifically the graphical animation,
// has been taken from the Oracle documentation of JavaFX Scene Graphs.
// You may find this reference at:
// 	https://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm

// Please note that also the reference for the media package was relied upon for elements
// related to the playing of MP3s.
// You may find this reference at:
// 	https://docs.oracle.com/javafx/2/api/javafx/scene/media/package-summary.html

// This code may have outside copyrights and is provided for educational purposes only.

// Imports relevant to the graphical elements.
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// Imports relevant to the animation effects
import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;

import java.io.File;

import javafx.animation.FillTransition;
import javafx.util.Duration;

// Imports relevant to playing MP3 music.
import javafx.scene.media.*;
import javafx.scene.Node;

public class MyPlayer extends Application {

	@Override
	public void start(Stage stage) {
		// Builds the base window for the GUI (graphical user interface).
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500, Color.BLACK);

		// Builds the initial rectangle that will eventually be animated.
		Rectangle r = new Rectangle(25, 25, 250, 250);
		r.setFill(Color.BLUE);
		root.getChildren().add(r);

		// Media player code for the music.
		String mp3;
		Media med;
		MediaPlayer mdp;
		MediaView mdv;

		// mp3 = "https://....";
		// mp3 = "clearskies.mp3";
		mp3 = "file:/home/plod/Documents/CS-709/CS709_LOCAL_ASYNC/FINAL_JUKEBOX/MusicMP3/quotthis-wayquot-hip-hop-beat-113255.mp3";
		// note: you can find free, open-source, legally distributable music for this
		// project.
		// Examples:
		// https://pixabay.com/music/
		// https://freemusicarchive.org/about/

		// FileChooser fc = new FileChooser();
		// fc.setTitle("Choose a music file to play.");
		// File file = fc.showOpenDialog(stage);

		// String URL = file.toURI().toString();
		// System.out.println(URL);
		// med = new Media(URL);


		med = new Media(mp3);
		mdp = new MediaPlayer ( med );
		mdv = new MediaView ( mdp );
		root.getChildren().add(mdv);

		// Add the animation effects
		TranslateTransition translate = new TranslateTransition(Duration.millis(750));
		translate.setToX(390);
		translate.setToY(390);

		FillTransition fill = new FillTransition(Duration.millis(750));
		fill.setToValue(Color.RED);

		RotateTransition rotate = new RotateTransition(Duration.millis(750));
		rotate.setToAngle(360);

		ScaleTransition scale = new ScaleTransition(Duration.millis(750));
		scale.setToX(0.1);
		scale.setToY(0.1);

		ParallelTransition transition = new ParallelTransition(r,
				translate, fill, rotate, scale);
		transition.setCycleCount(Timeline.INDEFINITE);
		transition.setAutoReverse(true);
		transition.play();

		// Launch the application window
		stage.setTitle("Music Player Demo");
		stage.setScene(scene);
		stage.show();
		mdp.play();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
