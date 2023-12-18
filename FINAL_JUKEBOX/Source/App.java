// SongPlayer.java - Fall 2023 - A clunky Jukebox that plays MP3 files.
// CS709 Hunter Fall 2023 - Final Project
// ----------------------------------------------------------------------------------------

// To compile and run:
// javac --module-path /home/plod/Documents/CS-709/JavaFX/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media *.java
// java --module-path /home/plod/Documents/CS-709/JavaFX/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media SongPlayer.java <song details text file>

// Imports relevant to the graphical elements.
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import javafx.animation.FillTransition;

// Imports relevant to playing MP3 music.
import javafx.scene.media.*;
import javafx.scene.Node;

////////////////////////////////////////////////////////////////////////////////
// ***************************************************************************//
// RENAME TO SongPlayer.java
////////////////////////////////////////////////////////////////////////////////
// ***************************************************************************//
public class App extends Application {

	Group root;

	// Media player code for the music.
	String mp3;
	Media med;
	MediaPlayer mdp;
	MediaView mdv;

	Song song, introSong;
	SongList songList;
	PurchaseQueue purchaseQueue;

	// int songToPlay = 0;

	TextField textField; // Text field for the song title.

	Rectangle r, r2, r3, r4;

	// Payment system variables
	private Label fundsLabel;
	private Label creditFundsLabel;
	private String creditCardAmount = "";

	// create instances of the classes
	CoinPayments coinPayments = new CoinPayments();
	CreditPayments creditPayments = new CreditPayments();

	// prints funds to the screen
	private void displayUpdatedTotal() {
		int displayAmount = coinPayments.currencyBox.getTotalCoinsAmount()
				+ creditPayments.creditCurrencyBox.getCreditAmountInt();
		fundsLabel.setText("Funds: " + displayAmount + "¢");
	}

	private void ccAmountAdded(int addedAmount) {
		creditCardAmount = creditCardAmount + Integer.toString(addedAmount);
		creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount);
	}

	private void handleCoinButton(int amount) {
		coinPayments.addFunds(amount);
		displayUpdatedTotal();
	}

	private Button createButton(String text, double layoutX, double layoutY) {
		Button button = new Button(text);
		button.setLayoutX(layoutX);
		button.setLayoutY(layoutY);
		return button;
	}

	private void handleReturnFundsButton() {
		int totalReturnAmount = coinPayments.currencyBox.getTotalCoinsAmount()
				+ creditPayments.creditCurrencyBox.getCreditAmountInt();
		coinPayments.currencyBox.setRefundAmount();
		fundsLabel.setText("Funds returned: " + totalReturnAmount + "¢" + "\n" + coinPayments.returnFunds() + "\n"
				+ "Credit Card Amount: " + creditPayments.returnFunds() + "¢");

		creditPayments.creditCurrencyBox.resetCreditAmountInt();
		coinPayments.currencyBox.resetAllCoins();
	}

	@Override
	public void start(Stage stage) throws FileNotFoundException {
		// Builds the base window for the GUI (graphical user interface).
		root = new Group();
		Scene scene = new Scene(root, 800, 800, Color.BLACK);

		// read in the song details file from the command line
		Parameters params = getParameters();
		List<String> argsList = params.getRaw();
		String[] argsArray = argsList.toArray(new String[argsList.size()]);

		// print out the song details list file name
		// System.out.println(argsArray);

		songList = new SongList(argsArray);

		System.out.println(songList.toString());
		// songs = songList.getSongs();

		// DELETE THIS LATER
		coinPayments.addFunds(100);

		////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		// get the first song from the purchaseQueue
		purchaseQueue = new PurchaseQueue();

		purchaseQueue.addSong(songList.getSongs().get(0), creditPayments.creditCurrencyBox, coinPayments.currencyBox);
		purchaseQueue.addSong(songList.getSongs().get(1), creditPayments.creditCurrencyBox, coinPayments.currencyBox);
		purchaseQueue.addSong(songList.getSongs().get(22), creditPayments.creditCurrencyBox, coinPayments.currencyBox);
		purchaseQueue.addSong(songList.getSongs().get(14), creditPayments.creditCurrencyBox, coinPayments.currencyBox);
		purchaseQueue.addSong(songList.getSongs().get(8), creditPayments.creditCurrencyBox, coinPayments.currencyBox);

		System.out.println(purchaseQueue.toString());
		// song = purchaseQueue.getFirst(); //

		//////////////////////////////////////////////////////////////////////////////
		// Intro song ////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////
		introSong = new Song("Intro", "WinampRipoff", "Intro", 5, "winamp-intro.mp3",
				"/home/plod/Documents/CS-709/CS709_LOCAL_ASYNC/FINAL_JUKEBOX/Source/winamp-intro.mp3");

		song = introSong;
		mp3 = "file:" + song.getPath(); //
		System.out.println(mp3);
		med = new Media(mp3);
		mdp = new MediaPlayer(med);
		mdv = new MediaView(mdp);
		root.getChildren().add(mdv);

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
		pauseButton.setLayoutY(130);
		pauseButton.setOnAction(e -> {
			pauseSong();
		});
		root.getChildren().add(pauseButton);

		// Create the button to stop the song.
		Button stopButton = new Button("Stop");
		stopButton.setLayoutX(50);
		stopButton.setLayoutY(160);
		stopButton.setOnAction(e -> {
			stopSong();
		});
		root.getChildren().add(stopButton);

		// Create the button to select a new song.
		Button nextButton = new Button("Next Song");
		nextButton.setLayoutX(50);
		nextButton.setLayoutY(190);
		nextButton.setOnAction(e -> {
			nextSong();
		});
		root.getChildren().add(nextButton);

		// Radio Buttons for sorting
		ToggleGroup sortingRadioButtons = new ToggleGroup();
		RadioButton button1 = new RadioButton("By Title");
		button1.setToggleGroup(sortingRadioButtons);
		button1.setSelected(true);
		RadioButton button2 = new RadioButton("By Artist");
		button2.setToggleGroup(sortingRadioButtons);
		RadioButton button3 = new RadioButton("By Genre");
		button3.setToggleGroup(sortingRadioButtons);
		RadioButton button4 = new RadioButton("By Duration");
		button4.setToggleGroup(sortingRadioButtons);
		button1.setLayoutX(50);
		button1.setLayoutY(350);
		button2.setLayoutX(50);
		button2.setLayoutY(380);
		button3.setLayoutX(50);
		button3.setLayoutY(410);
		button4.setLayoutX(50);
		button4.setLayoutY(440);
		root.getChildren().addAll(button1, button2, button3, button4);

		// need to add simple listener to the radio buttons

		////////////////////////////////////////////////////////////////////////////////
		// simple list view that lists song titles
		ListView<String> listView = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList();
		for (Song song : songList.getSongs()) {
			items.add(song.getArtist() + " - " + song.getTitle());
		}
		listView.setItems(items);
		listView.setLayoutX(280);
		listView.setLayoutY(100);
		listView.setPrefSize(240, 240);
		root.getChildren().add(listView);

		////////////////////////////////////////////////////////////////////////////////
		// BUY & ADD SONG BUTTON
		////////////////////////////////////////////////////////////////////////////////
		Button buySongButton = new Button("Buy Song");
		buySongButton.setLayoutX(280);
		buySongButton.setLayoutY(350);
		buySongButton.setOnAction(e -> {
			if (listView.getSelectionModel().getSelectedItem() != null) {
				String selectedSong = listView.getSelectionModel().getSelectedItem();
				String[] songDetails = selectedSong.split(" - ");
				String artist = songDetails[0];
				String title = songDetails[1];
				for (Song song : songList.getSongs()) {
					if (song.getArtist().equals(artist) && song.getTitle().equals(title)) {
						purchaseQueue.addSong(song, creditPayments.creditCurrencyBox, coinPayments.currencyBox);
						System.out.println(purchaseQueue.toString());
					}
				}
			}
		});
		root.getChildren().add(buySongButton);

		////////////////////////////////////////////////////////////////////////////////
		// BUY & ADD SONG PLAY NEXT BUTTON
		////////////////////////////////////////////////////////////////////////////////
		Button buySongPlayNextButton = new Button("Buy Song, Play Next");
		buySongPlayNextButton.setLayoutX(330);
		buySongPlayNextButton.setLayoutY(350);
		buySongPlayNextButton.setOnAction(e -> {
			if (listView.getSelectionModel().getSelectedItem() != null) {
				String selectedSong = listView.getSelectionModel().getSelectedItem();
				String[] songDetails = selectedSong.split(" - ");
				String artist = songDetails[0];
				System.out.println("Artist: " + artist);
				String title = songDetails[1];
				System.out.println("Title: " + title);

				for (Song song : songList.getSongs()) {
					if (song.getArtist().equals(artist) && song.getTitle().equals(title)) {
						purchaseQueue.addSongPlayNext(song, creditPayments.creditCurrencyBox, coinPayments.currencyBox);
						System.out.println(purchaseQueue.toString());
					}
				}
			}
		});
		root.getChildren().add(buySongPlayNextButton);

		////////////////////////////////////////////////////////////////////////////////
		// SORT BUTTONS
		// Create the button to sort by title.
		Button titleButton = new Button("Sort by Title");
		titleButton.setLayoutX(50);
		titleButton.setLayoutY(220);
		titleButton.setOnAction(e -> {
			sortSongsBy("title");
			System.out.println(song);
		});
		root.getChildren().add(titleButton);

		// Create the button to sort by artist.
		Button artistButton = new Button("Sort by Artist");
		artistButton.setLayoutX(50);
		artistButton.setLayoutY(250);
		artistButton.setOnAction(e -> {
			sortSongsBy("artist");
			System.out.println(song);
		});
		root.getChildren().add(artistButton);

		// Create the button to sort by genre.
		Button genreButton = new Button("Sort by Genre");
		genreButton.setLayoutX(50);
		genreButton.setLayoutY(280);
		genreButton.setOnAction(e -> {
			sortSongsBy("genre");
			System.out.println(song);
		});
		root.getChildren().add(genreButton);

		// Create the button to sort by duration.
		Button durationButton = new Button("Sort by Duration");
		durationButton.setLayoutX(50);
		durationButton.setLayoutY(310);
		durationButton.setOnAction(e -> {
			sortSongsBy("duration");
			System.out.println(song);
		});
		root.getChildren().add(durationButton);

		/////////////////////////////////////////
		// labels and buttons for payment system//
		/////////////////////////////////////////

		// funds label
		fundsLabel = new Label();
		creditFundsLabel = new Label();
		fundsLabel.setLayoutX(50);
		fundsLabel.setLayoutY(700);
		fundsLabel.setTextFill(Color.GRAY);
		creditFundsLabel.setLayoutX(400);
		creditFundsLabel.setLayoutY(600);
		root.getChildren().addAll(fundsLabel, creditFundsLabel);

		// coinPad Buttons
		Button nickelButton = createButton("5¢", 90, 445);
		nickelButton.setOnAction(e -> handleCoinButton(5));

		Button dimeButton = createButton("10¢", 120, 445);
		dimeButton.setOnAction(e -> handleCoinButton(10));

		Button quarterButton = createButton("25¢", 155, 445);
		quarterButton.setOnAction(e -> handleCoinButton(25));

		// return funds button
		Button returnFundsButton = createButton("Return Funds", 150, 500);
		returnFundsButton.setOnAction(e -> handleReturnFundsButton());

		// creditCardPayment Buttons
		Button oneButton = createButton("1", 400, 445);
		oneButton.setOnAction(e -> ccAmountAdded(1));

		Button twoButton = createButton("2", 430, 445);
		twoButton.setOnAction(e -> ccAmountAdded(2));

		Button threeButton = createButton("3", 460, 445);
		threeButton.setOnAction(e -> ccAmountAdded(3));

		Button fourButton = createButton("4", 400, 475);
		fourButton.setOnAction(e -> ccAmountAdded(4));

		Button fiveButton = createButton("5", 430, 475);
		fiveButton.setOnAction(e -> ccAmountAdded(5));

		Button sixButton = createButton("6", 460, 475);
		sixButton.setOnAction(e -> ccAmountAdded(6));

		Button sevenButton = createButton("7", 400, 505);
		sevenButton.setOnAction(e -> ccAmountAdded(7));

		Button eightButton = createButton("8", 430, 505);
		eightButton.setOnAction(e -> ccAmountAdded(8));

		Button nineButton = createButton("9", 460, 505);
		nineButton.setOnAction(e -> ccAmountAdded(9));

		Button zeroButton = createButton("0", 430, 535);
		zeroButton.setOnAction(e -> ccAmountAdded(0));

		Button clearButton = createButton("C", 460, 535);
		clearButton.setOnAction(e -> {
			creditCardAmount = "";
			creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount);
		});

		// "swipe credit card button" to load funds from credit card
		Button swipeCreditCardButton = createButton("Swipe Credit Card", 380, 565);
		swipeCreditCardButton.setOnAction(e -> {
			creditPayments.addFunds(Integer.parseInt(creditCardAmount));
			creditCardAmount = "";
			creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount);
			displayUpdatedTotal();
		});

		root.getChildren().addAll(nickelButton, dimeButton, quarterButton,
				returnFundsButton, oneButton, twoButton, threeButton, fourButton, fiveButton,
				sixButton, sevenButton, eightButton, nineButton, zeroButton, clearButton, swipeCreditCardButton);

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
		stage.setTitle("CS709 JukeIt!");
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
		mdp.stop();
		root.getChildren().remove(mdv);

		if (!purchaseQueue.isEmpty()) {
			System.out.println("Removing song from queue");
			purchaseQueue.removeFirst();
		}

		if (!purchaseQueue.isEmpty()) {
			song = purchaseQueue.getFirst();
			System.out.println(purchaseQueue.toString());
		} else {
			System.out.println("No more songs in the queue");
			textField.setText("Song Queue is Empty!");
			song = introSong; // new Song("Intro", "WinampRipoff", "Intro", 5,
								// "winamp-intro.mp3","/home/plod/Documents/CS-709/CS709_LOCAL_ASYNC/FINAL_JUKEBOX/Source/winamp-intro.mp3");
			// return;
		}

		mp3 = "file:" + song.getPath();
		System.out.println(mp3);
		med = new Media(mp3);
		mdp = new MediaPlayer(med);
		mdv = new MediaView(mdp);
		root.getChildren().add(mdv);
		mdp.play();
		textField.setText(song.getTitle() + ": by " + song.getArtist());
	}

	public void sortSongsBy(String sortBy) {

		switch (sortBy) {
			case "title":
				Collections.sort(songList.getSongs(), Comparator.comparing(Song::getTitle));
				break;
			case "artist":
				Collections.sort(songList.getSongs(), Comparator.comparing(Song::getArtist));
				break;
			case "genre":
				Collections.sort(songList.getSongs(), Comparator.comparing(Song::getGenre));
				break;
			case "duration":
				Collections.sort(songList.getSongs(), Comparator.comparing(Song::getDuration));
				break;
			default:
				break;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
