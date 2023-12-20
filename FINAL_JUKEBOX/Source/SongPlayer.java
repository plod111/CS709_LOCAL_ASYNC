/**
 * SongPlayer.java - Fall 2023 - A clunky JavaFX Jukebox that plays MP3 files.
 * 
 * CS709 Hunter Fall 2023 - Final Project
 * 
 * @author B.Cornish
 * @author P.Chu
 * @date Dec 2023
 * 
 * To compile and run:
 * >> javac --module-path <JavaFX sdk lib path> --add-modules javafx.controls,javafx.fxml,javafx.media *.java
 * >> java --module-path <JavaFX sdk lib path> --add-modules javafx.controls,javafx.fxml,javafx.media SongPlayer songDetails.txt
 */


// Imports relevant to the graphical elements.
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
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
import javafx.animation.FillTransition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

// Imports relevant to playing MP3 music.
import javafx.scene.media.*;
import javafx.scene.Node;

// ***************************************************************************//
public class SongPlayer extends Application {

	// GUI Group
	Group root;
	// Scene dimensions
	int sceneWidth = 1000;
	int sceneHeight = 700;

	// Media player elements
	String mp3;
	Media med;
	MediaPlayer mdp;
	MediaView mdv;

	Song song, introSong;
	SongList songList; // list of all available songs
	PurchaseQueue purchaseQueue; // dynamic list of purchased song for next playing
	ObservableList<String> queueItems;
	ListView<String> queueListView;
	ListView<String> listView;
	ObservableList<String> items;
	TextField textField; // Text field for the song title.

	// Payment system variables
	private Label fundsLabel;
	private Label creditFundsLabel;
	private String creditCardAmount = "";
	CoinPayments coinPayments = new CoinPayments();
	CreditPayments creditPayments = new CreditPayments();

	// Confetti Animation Elements
	int numRectangles = 250;
	Rectangle[] rectangles = new Rectangle[numRectangles];
	TranslateTransition[] translations = new TranslateTransition[numRectangles];
	FillTransition[] fills = new FillTransition[numRectangles];
	RotateTransition[] rotations = new RotateTransition[numRectangles];
	ScaleTransition[] scales = new ScaleTransition[numRectangles];
	ParallelTransition[] transitions = new ParallelTransition[numRectangles];

	////////////////////////////////////////////////////////////////////////////////
	// DRIVER /////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {

		// useage
		if (args.length != 1) {
			System.out.println(
					"Usage: java --module-path <local JavaFX sdk lib path> --add-modules javafx.controls,javafx.fxml,javafx.media SongPlayer songDetails.txt");
			System.exit(0);
		}

		launch(args);
	}

	@Override
	public void start(Stage stage) throws FileNotFoundException {
		// Builds the base window for the GUI (graphical user interface).
		root = new Group();
		Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.BLACK);

		////////////////////////////////////////////////////////////////////////////////
		// Confetti Animation
		buildRectangles(numRectangles, root);
		buildTransitions(numRectangles, root, scene);

		// read in the song details file from the command line
		Parameters params = getParameters();
		List<String> argsList = params.getRaw();
		String[] argsArray = argsList.toArray(new String[argsList.size()]);

		// create the song list
		songList = new SongList(argsArray);
		// print the song list
		System.out.println(songList.toString());

		// Create the queue of songs to play.
		purchaseQueue = new PurchaseQueue();

		// Intro song - plays when the program is first run, and on empty purchase queue
		introSong = new Song("Intro", "WinampRipoff", "Intro", 5, "winamp-intro.mp3",
				"/home/plod/Documents/CS-709/CS709_LOCAL_ASYNC/FINAL_JUKEBOX/Source/winamp-intro.mp3");

		song = introSong;
		mp3 = "file:" + song.getPath(); //
		System.out.println("Intro wav: " + mp3);
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
		RadioButton sortByTitleButton = new RadioButton("By Title");
		sortByTitleButton.setToggleGroup(sortingRadioButtons);
		sortByTitleButton.setSelected(true);
		sortByTitleButton.setLayoutX(50);
		sortByTitleButton.setLayoutY(250);
		sortByTitleButton.setTextFill(Color.GRAY);
		sortByTitleButton.setOnAction(e -> {
			sortSongsBy("title");
		});

		RadioButton sortByArtistButton = new RadioButton("By Artist");
		sortByArtistButton.setToggleGroup(sortingRadioButtons);
		sortByArtistButton.setLayoutX(50);
		sortByArtistButton.setLayoutY(280);
		sortByArtistButton.setTextFill(Color.GRAY);
		sortByArtistButton.setOnAction(e -> {
			sortSongsBy("artist");

		});

		RadioButton sortByGenreButton = new RadioButton("By Genre");
		sortByGenreButton.setToggleGroup(sortingRadioButtons);
		sortByGenreButton.setLayoutX(50);
		sortByGenreButton.setLayoutY(310);
		sortByGenreButton.setTextFill(Color.GRAY);
		sortByGenreButton.setOnAction(e -> {
			sortSongsBy("genre");

		});

		RadioButton sortByDurationButton = new RadioButton("By Duration");
		sortByDurationButton.setToggleGroup(sortingRadioButtons);
		sortByDurationButton.setLayoutX(50);
		sortByDurationButton.setLayoutY(340);
		sortByDurationButton.setTextFill(Color.GRAY);
		sortByDurationButton.setOnAction(e -> {
			sortSongsBy("duration");

		});

		root.getChildren().addAll(sortByTitleButton, sortByArtistButton, sortByGenreButton, sortByDurationButton);

		////////////////////////////////////////////////////////////////////////////////
		// SongList SIMPLE LIST ///////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		Label listViewLabel = new Label("Song Selection");
		listViewLabel.setLayoutX(280);
		listViewLabel.setLayoutY(70);
		listViewLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
		listViewLabel.setTextFill(Color.GRAY);
		root.getChildren().add(listViewLabel);

		// simple list view that lists song titles
		listView = new ListView<String>();
		items = FXCollections.observableArrayList();
		for (Song song : songList.getSongs()) {
			items.add(song.getArtist() + " - " + song.getTitle());
		}
		listView.setItems(items);
		listView.setLayoutX(280);
		listView.setLayoutY(100);
		listView.setPrefSize(285, 240);
		root.getChildren().add(listView);

		////////////////////////////////////////////////////////////////////////////////
		// PurchaseQueue SIMPLE LIST ///////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		// header for the song queue list view
		Label songQueueLabel = new Label("Up Next");
		songQueueLabel.setLayoutX(645);
		songQueueLabel.setLayoutY(70);
		songQueueLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
		songQueueLabel.setTextFill(Color.GRAY);
		root.getChildren().add(songQueueLabel);

		// simple list view for songs in purchase queue
		queueListView = new ListView<String>();
		queueItems = FXCollections.observableArrayList();
		queueListView.setLayoutX(645);
		queueListView.setLayoutY(100);
		queueListView.setPrefSize(285, 240);
		root.getChildren().add(queueListView);

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
						displayUpdatedTotal();
						System.out.println(purchaseQueue.toString());
					}
				}
			}
			queueListView = new ListView<String>();
			queueItems = FXCollections.observableArrayList();
			queueListView.setLayoutX(645);
			queueListView.setLayoutY(100);
			queueListView.setPrefSize(280, 240);
			for (Song song : purchaseQueue.getQueue()) {
				queueItems.add(song.getArtist() + " - " + song.getTitle());
			}
			queueListView.setItems(queueItems);
			root.getChildren().add(queueListView);
		});
		root.getChildren().add(buySongButton);

		////////////////////////////////////////////////////////////////////////////////
		// BUY & ADD SONG PLAY NEXT BUTTON
		////////////////////////////////////////////////////////////////////////////////
		Button buySongPlayNextButton = new Button("Buy Song, Play Next");
		buySongPlayNextButton.setLayoutX(380);
		buySongPlayNextButton.setLayoutY(350);
		buySongPlayNextButton.setOnAction(e -> {
			if (listView.getSelectionModel().getSelectedItem() != null) {
				String selectedSong = listView.getSelectionModel().getSelectedItem();
				String[] songDetails = selectedSong.split(" - ");
				String artist = songDetails[0];
				// System.out.println("Artist: " + artist);
				String title = songDetails[1];
				// System.out.println("Title: " + title);

				for (Song song : songList.getSongs()) {
					if (song.getArtist().equals(artist) && song.getTitle().equals(title)) {
						purchaseQueue.addSongPlayNext(song, creditPayments.creditCurrencyBox, coinPayments.currencyBox);
						displayUpdatedTotal();
						System.out.println(purchaseQueue.toString());
					}
				}
			}
			queueListView = new ListView<String>();
			queueItems = FXCollections.observableArrayList();
			queueListView.setLayoutX(645);
			queueListView.setLayoutY(100);
			queueListView.setPrefSize(280, 240);
			for (Song song : purchaseQueue.getQueue()) {
				queueItems.add(song.getArtist() + " - " + song.getTitle());
			}
			queueListView.setItems(queueItems);
			root.getChildren().add(queueListView);
		});
		root.getChildren().add(buySongPlayNextButton);

		////////////////////////////////////////////////////////////////////////////////
		// PAYMENT - labels and buttons ///////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////

		// funds label
		fundsLabel = new Label();
		creditFundsLabel = new Label();
		fundsLabel.setLayoutX(50);
		fundsLabel.setLayoutY(600);
		fundsLabel.setTextFill(Color.GRAY);
		fundsLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
		creditFundsLabel.setLayoutX(380);
		creditFundsLabel.setLayoutY(600);
		creditFundsLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
		creditFundsLabel.setTextFill(Color.GRAY);

		root.getChildren().addAll(fundsLabel, creditFundsLabel);

		// coinPad Buttons
		Button nickelButton = createButton(" 5¢", 80, 445);
		nickelButton.setOnAction(e -> handleCoinButton(5));

		Button dimeButton = createButton("10¢", 120, 445);
		dimeButton.setOnAction(e -> handleCoinButton(10));

		Button quarterButton = createButton("25¢", 163, 445);
		quarterButton.setOnAction(e -> handleCoinButton(25));

		// return funds button
		Button returnFundsButton = createButton("Return Funds", 90, 500);
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


		////////////////////////////////////////////////////////////////////////////////
		// Marquee ////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////

		Text textNode = new Text("25 cents per song... 35 cents for premium play next... 25 cents per song... 35 cents for premium play next... 25 cents per song... 35 cents for premium play next... ");

    
        TranslateTransition marqueeTransition = new TranslateTransition(Duration.seconds(20), textNode);

        marqueeTransition.setByX(-2000); 

		textNode.setTranslateX(600);
		textNode.setTranslateY(695);
		textNode.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
		textNode.setFill(Color.WHITE);

		// Set the transition to continuously cycle
        marqueeTransition.setCycleCount(TranslateTransition.INDEFINITE);

        marqueeTransition.play();

        root.getChildren().add(textNode);

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
		// Neon effect /////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
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

		// Launch the application window
		stage.setTitle("SongPlayer - CS709 JukeIt!");
		stage.setScene(scene);
		stage.show();

	} // end start

	////////////////////////////////////////////////////////////////////////////////
	// VARIOUS PAYMENT METHODS /////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * Displays the updated total funds amount.
	 */
	private void displayUpdatedTotal() {
		int displayAmount = coinPayments.currencyBox.getTotalCoinsAmount()
				+ creditPayments.creditCurrencyBox.getCreditAmountInt();
		fundsLabel.setText("Funds: " + displayAmount + "¢");
	}

	/**
	 * Adds the amount to the credit card amount.
	 * 
	 * @param addedAmount
	 */
	private void ccAmountAdded(int addedAmount) {
		creditCardAmount = creditCardAmount + Integer.toString(addedAmount);
		creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount);
	}

	/**
	 * Adds the amount to the coin payments.
	 * 
	 * @param amount
	 */
	private void handleCoinButton(int amount) {
		coinPayments.addFunds(amount);
		displayUpdatedTotal();
	}

	/**
	 * Creates a button with the given text and layout.
	 * 
	 * @param text
	 * @param layoutX
	 * @param layoutY
	 * @return
	 */
	private Button createButton(String text, double layoutX, double layoutY) {
		Button button = new Button(text);
		button.setLayoutX(layoutX);
		button.setLayoutY(layoutY);
		return button;
	}

	/**
	 * Handles the return funds button.
	 */
	private void handleReturnFundsButton() {
		int totalReturnAmount = coinPayments.currencyBox.getTotalCoinsAmount()
				+ creditPayments.creditCurrencyBox.getCreditAmountInt();
		coinPayments.currencyBox.setRefundAmount();
		fundsLabel.setText("Funds returned: " + totalReturnAmount + "¢" + "\n" + coinPayments.returnFunds() + "\n"
				+ "Credit Card Amount: " + creditPayments.returnFunds() + "¢");

		creditPayments.creditCurrencyBox.resetCreditAmountInt();
		coinPayments.currencyBox.resetAllCoins();
	}

	////////////////////////////////////////////////////////////////////////////////
	// SONG PLAYBACK ///////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * Plays the song.
	 */
	public void playSong() {
		mdp.play();
		textField.setText(song.getTitle() + ": by " + song.getArtist());
	}

	/**
	 * Stops the song.
	 */
	public void stopSong() {
		mdp.stop();
	}

	/**
	 * Pauses the song.
	 */
	public void pauseSong() {
		mdp.pause();
	}

	/**
	 * Plays the next song in the queue.
	 */
	public void nextSong() {
		mdp.stop();
		root.getChildren().remove(mdv);

		if (!purchaseQueue.isEmpty()) {
			song = purchaseQueue.getFirst();
			System.out.println(purchaseQueue.toString());
		} else {
			System.out.println("No more songs in the queue");
			textField.setText("Song Queue is Empty!");
			song = introSong; // new Song("Intro", "WinampRipoff", "Intro", 5,
								// "winamp-intro.mp3","/home/plod/Documents/CS-709/CS709_LOCAL_ASYNC/FINAL_JUKEBOX/Source/winamp-intro.mp3");
			mp3 = "file:" + song.getPath();
			System.out.println(mp3);
			med = new Media(mp3);
			mdp = new MediaPlayer(med);
			mdv = new MediaView(mdp);
			root.getChildren().add(mdv);
			mdp.play();
			textField.setText(song.getTitle() + ": by " + song.getArtist());
			return;
		}

		mp3 = "file:" + song.getPath();
		System.out.println(mp3);
		med = new Media(mp3);
		mdp = new MediaPlayer(med);
		mdv = new MediaView(mdp);
		root.getChildren().add(mdv);
		mdp.play();
		textField.setText(song.getTitle() + ": by " + song.getArtist());
		System.out.println("Removing song from queue");
		purchaseQueue.removeFirst();

		queueListView = new ListView<String>();
		queueItems = FXCollections.observableArrayList();
		queueListView.setLayoutX(645);
		queueListView.setLayoutY(100);
		queueListView.setPrefSize(280, 240);
		for (Song song : purchaseQueue.getQueue()) {
			queueItems.add(song.getArtist() + " - " + song.getTitle());
		}
		queueListView.setItems(queueItems);
		root.getChildren().add(queueListView);
	}

	/**
	 * Sorts the songs by the given parameter.
	 * 
	 * @param sortBy
	 */
	public void sortSongsBy(String sortBy) {

		switch (sortBy) {
			case "title":
				Collections.sort(songList.getSongs(), Comparator.comparing(Song::getTitle));
				listView = new ListView<String>();
				items = FXCollections.observableArrayList();
				for (Song song : songList.getSongs()) {
					items.add(song.getArtist() + " - " + song.getTitle());
				}
				listView.setItems(items);
				listView.setLayoutX(280);
				listView.setLayoutY(100);
				listView.setPrefSize(280, 240);
				root.getChildren().add(listView);
				break;
			case "artist":
				Collections.sort(songList.getSongs(), Comparator.comparing(Song::getArtist));
				listView = new ListView<String>();
				items = FXCollections.observableArrayList();
				for (Song song : songList.getSongs()) {
					items.add(song.getArtist() + " - " + song.getTitle());
				}
				listView.setItems(items);
				listView.setLayoutX(280);
				listView.setLayoutY(100);
				listView.setPrefSize(280, 240);
				root.getChildren().add(listView);
				break;
			case "genre":
				Collections.sort(songList.getSongs(), Comparator.comparing(Song::getGenre));
				listView = new ListView<String>();
				items = FXCollections.observableArrayList();
				for (Song song : songList.getSongs()) {
					items.add(song.getArtist() + " - " + song.getTitle());
				}
				listView.setItems(items);
				listView.setLayoutX(280);
				listView.setLayoutY(100);
				listView.setPrefSize(280, 240);
				root.getChildren().add(listView);
				break;
			case "duration":
				Collections.sort(songList.getSongs(), Comparator.comparing(Song::getDuration));
				listView = new ListView<String>();
				items = FXCollections.observableArrayList();
				for (Song song : songList.getSongs()) {
					items.add(song.getArtist() + " - " + song.getTitle());
				}
				listView.setItems(items);
				listView.setLayoutX(280);
				listView.setLayoutY(100);
				listView.setPrefSize(280, 240);
				root.getChildren().add(listView);
				break;
			default:
				break;
		}
	}

	/**
	 * Builds the rectangles for the confetti.
	 * 
	 * @param numRectangles
	 * @param root
	 */
	private void buildRectangles(int numRectangles, Group root) {

		for (int i = 0; i < numRectangles; i++) {
			rectangles[i] = new Rectangle((int) (Math.random() * sceneWidth), (int) (Math.random() * sceneHeight),
					(int) (Math.random() * 20), (int) (Math.random() * 20));
			// System.out.println(rectangles[i]);
			// System.out.println((int) (Math.random() * sceneWidth));
			// random color
			rectangles[i].setFill(
					Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
			root.getChildren().add(rectangles[i]);
		}

	}

	/**
	 * Builds the animation effects for each rectangle.
	 * 
	 * @param numRectangles
	 * @param root
	 * @param scene
	 */
	private void buildTransitions(int numRectangles, Group root, Scene scene) {

		// for each rectangle
		for (int i = 0; i < numRectangles; i++) {
			// Add the animation effects
			int duration = (int) (Math.random() * 2750 + 1000);
			translations[i] = new TranslateTransition(Duration.millis(duration));
			translations[i].setToX((int) (Math.random() * 2 * sceneWidth - sceneWidth));
			translations[i].setToY((int) (Math.random() * 2 * sceneHeight - sceneHeight));

			fills[i] = new FillTransition(Duration.millis(duration));
			fills[i].setToValue(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),
					(int) (Math.random() * 255)));

			rotations[i] = new RotateTransition(Duration.millis(duration));
			rotations[i].setToAngle((int) Math.random() * 1720 - 360);

			scales[i] = new ScaleTransition(Duration.millis(duration));
			scales[i].setToX((int) (Math.random() * 2));
			scales[i].setToY((int) (Math.random() * 2));

			transitions[i] = new ParallelTransition(rectangles[i], translations[i], fills[i], rotations[i], scales[i]);
			transitions[i].setCycleCount(Timeline.INDEFINITE);
			transitions[i].setAutoReverse(true);
			transitions[i].play();
		}

	}

}
