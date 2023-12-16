// SongPlayer.java - Fall 2023 - A groovy, animated Jukebox that plays MP3 files.
// CS709 Hunter Fall 2023 - Final Project
// ----------------------------------------------------------------------------------------

// To compile and run:
// javac --module-path /home/plod/Documents/CS-709/JavaFX/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media SongPlayer.java
// java --module-path /home/plod/Documents/CS-709/JavaFX/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media SongPlayer.java *.mp3

// Imports relevant to the graphical elements.
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// Imports relevant to the animation effects
import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.animation.FillTransition;
import javafx.util.Duration;

// Imports relevant to playing MP3 music.
import javafx.scene.media.*;
import javafx.scene.Node;

public class SongPlayer extends Application {

	// Media player code for the music.
	String mp3;
	Media med;
	MediaPlayer mdp;
	MediaView mdv;

	Song song;
	ArrayList<Song> songs;

	int songToPlay = 0;

	TextField textField;

	Rectangle r, r2, r3, r4;

	@Override
	public void start(Stage stage) throws FileNotFoundException {
		// Builds the base window for the GUI (graphical user interface).
		Group root = new Group();
		Scene scene = new Scene(root, 800, 500, Color.BLACK);

		// read in the song details file from the command line
		Parameters params = getParameters();
		List<String> argsList = params.getRaw();
		String[] argsArray = argsList.toArray(new String[argsList.size()]);

		// print out the song details list file name
		// System.out.println(argsArray);

		SongList songList = new SongList(argsArray);
		
		System.out.println(songList.toString());
		// songs = songList.getSongs();

		////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		// get the first song from songs ArrayList
		// song = songs.get(songToPlay); //

		// mp3 = "file:" + song.getFileName(); //
		// System.out.println(mp3);
		// // textField.setText(song.getTitle() + ": by " + song.getArtist());

		// // Create the media player and media view.
		// med = new Media(mp3);
		// mdp = new MediaPlayer(med);
		// mdv = new MediaView(mdp);
		// root.getChildren().add(mdv);
		// mdp.play();

		////////////////////////////////////////////////////////////////////////////////
		// Create the button to play the song.
		Button playButton = new Button("Play");
		playButton.setLayoutX(50);
		playButton.setLayoutY(100);
		playButton.setOnAction(e -> {
			playSong();
		});
		root.getChildren().add(playButton);

		// Create the button to pause the song.
		Button pauseButton = new Button("Pause");
		pauseButton.setLayoutX(50);
		pauseButton.setLayoutY(150);
		pauseButton.setOnAction(e -> {
			pauseSong();
		});
		root.getChildren().add(pauseButton);

		// Create the button to stop the song.
		Button stopButton = new Button("Stop");
		stopButton.setLayoutX(50);
		stopButton.setLayoutY(200);
		stopButton.setOnAction(e -> {
			stopSong();
		});
		root.getChildren().add(stopButton);

		// Create the button to select a new song.
		Button nextButton = new Button("Next Song");
		nextButton.setLayoutX(50);
		nextButton.setLayoutY(250);
		nextButton.setOnAction(e -> {

			// stop the current song
			mdp.stop();
			songToPlay++;

			song = songs.get(songToPlay); //

			mp3 = "file:" + song.getPath();
			System.out.println(mp3);
			med = new Media(mp3);
			mdp = new MediaPlayer(med);
			mdv = new MediaView(mdp);
			root.getChildren().add(mdv);
			textField.setText(song.getTitle() + ": by " + song.getArtist());
		});
		root.getChildren().add(nextButton);

		////////////////////////////////////////////////////////////////////////////////
		// SORT BUTTONS
		// Create the button to sort by title.
		Button titleButton = new Button("Sort by Title");
		titleButton.setLayoutX(50);
		titleButton.setLayoutY(300);
		titleButton.setOnAction(e -> {
			sortSongsBy("title");
			System.out.println(songs);
		});
		root.getChildren().add(titleButton);

		// Create the button to sort by artist.
		Button artistButton = new Button("Sort by Artist");
		artistButton.setLayoutX(50);
		artistButton.setLayoutY(350);
		artistButton.setOnAction(e -> {
			sortSongsBy("artist");
			System.out.println(songs);
		});
		root.getChildren().add(artistButton);

		// Create the button to sort by genre.
		Button genreButton = new Button("Sort by Genre");
		genreButton.setLayoutX(50);
		genreButton.setLayoutY(400);
		genreButton.setOnAction(e -> {
			sortSongsBy("genre");
			System.out.println(songs);
		});
		root.getChildren().add(genreButton);

		// Create the button to sort by duration.
		Button durationButton = new Button("Sort by Duration");
		durationButton.setLayoutX(50);
		durationButton.setLayoutY(450);
		durationButton.setOnAction(e -> {
			sortSongsBy("duration");
			System.out.println(songs);
		});
		root.getChildren().add(durationButton);

		//////////////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////////////
		// Create the label for the song title.
		Text text = new Text(50, 50, "");
		text.setFont(Font.font("Cambria", 28));
		text.setStyle("-fx-font-weight: bold");
		textField = new TextField();
		textField.setText("Song Title");
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

		// Builds the initial rectangle that will eventually be animated.
		r = new Rectangle(125, 125, 150, 150);
		r.setFill(Color.PURPLE);
		r2 = new Rectangle(80, 350, 100, 100);
		r2.setFill(Color.RED);
		r3 = new Rectangle(550, 80, 150, 150);
		r3.setFill(Color.YELLOW);
		r4 = new Rectangle(575, 105, 100, 100);
		r4.setFill(Color.ORCHID);
		root.getChildren().addAll(r, r2, r3, r4);

		// Add the animation effects
		TranslateTransition translate = new TranslateTransition(Duration.millis(1750));
		translate.setToX(590);
		translate.setToY(290);

		TranslateTransition translate2 = new TranslateTransition(Duration.millis(1750));
		translate2.setToX(300);
		translate2.setToY(-390);

		FillTransition fill = new FillTransition(Duration.millis(1750));
		fill.setToValue(Color.BLUE);
		FillTransition fill2 = new FillTransition(Duration.millis(1750));
		fill2.setToValue(Color.BLUE);

		RotateTransition rotate = new RotateTransition(Duration.millis(1750));
		rotate.setToAngle(360);
		RotateTransition rotate2 = new RotateTransition(Duration.millis(1750));
		rotate2.setToAngle(-720);
		RotateTransition rotate3 = new RotateTransition(Duration.millis(2750), r3);
		rotate3.setToAngle(720);
		RotateTransition rotate4 = new RotateTransition(Duration.millis(2750), r4);
		rotate4.setToAngle(-720);
		rotate3.setCycleCount(Timeline.INDEFINITE);
		rotate4.setCycleCount(Timeline.INDEFINITE);
		rotate3.setAutoReverse(true);
		rotate4.setAutoReverse(true);
		rotate3.play();
		rotate4.play();

		ScaleTransition scale = new ScaleTransition(Duration.millis(1750));
		scale.setToX(0.1);
		scale.setToY(0.1);
		ScaleTransition scale2 = new ScaleTransition(Duration.millis(1750));
		scale2.setToX(0.1);
		scale2.setToY(0.1);

		ParallelTransition transition = new ParallelTransition(r, translate, fill, rotate, scale);
		transition.setCycleCount(Timeline.INDEFINITE);
		transition.setAutoReverse(true);
		transition.play();

		ParallelTransition transition2 = new ParallelTransition(r2, translate2, fill2, rotate2, scale2);
		transition2.setCycleCount(Timeline.INDEFINITE);
		transition2.setAutoReverse(true);
		transition2.play();

		// Launch the application window
		stage.setTitle("Music Player Demo");
		stage.setScene(scene);
		stage.show();

	}

	public void playSong() {
		mdp.play();
		textField.setText(song.getTitle() + ": by " + song.getArtist());
	}

	public void stopSong() {
		mdp.stop();
	}

	public void pauseSong() {
		mdp.pause();
	}

	public void nextSong() {
		// mdp.stop();
		// songToPlay++;

		// song = songs.get(songToPlay); //

		// mp3 = "file:" + song.getPath();
		// System.out.println(mp3);
		// med = new Media(mp3);
		// mdp = new MediaPlayer(med);
		// mdv = new MediaView(mdp);
		// root.getChildren().add(mdv);
		// mdp.play();
		// textField.setText(song.getTitle() + ": by " + song.getArtist());
	}

	public void sortSongsBy(String sortBy) {

		switch (sortBy) {
			case "title":
				Collections.sort(songs, Comparator.comparing(Song::getTitle));
				break;
			case "artist":
				Collections.sort(songs, Comparator.comparing(Song::getArtist));
				break;
			case "genre":
				Collections.sort(songs, Comparator.comparing(Song::getGenre));
				break;
			case "duration":
				Collections.sort(songs, Comparator.comparing(Song::getDuration));
				break;
			default:
				break;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
