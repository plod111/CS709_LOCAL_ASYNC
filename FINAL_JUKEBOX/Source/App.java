
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


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

public class App extends Application {

    ////////////////////////////////////
    //SongPlayer Variables and Methods//
    ///////////////////////////////////

    String mp3;
	Media med;
	MediaPlayer mdp;
	MediaView mdv;

	Song song;
	ArrayList<Song> songs;

	int songToPlay = 0;

	TextField textField;

	Rectangle r, r2, r3, r4;


    
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



    /////////////////////////////////
    





    //Payment system variables 
     

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

    






    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Juke Box");

        Group root = new Group();

        // set up for the title
        Text scenetitle = new Text("JukeBox");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        scenetitle.setLayoutX(200);
        scenetitle.setLayoutY(50);
        root.getChildren().add(scenetitle);


        
        ///////////////////////////////////
        //Music Player buttons and labels//
        ///////////////////////////////////

        //play button
        Button playButton = new Button("Play");
		playButton.setLayoutX(50);
		playButton.setLayoutY(100);
		playButton.setOnAction(e -> {
			//playSong();
		});
		root.getChildren().add(playButton);

		// Create the button to pause the song.
		Button pauseButton = new Button("Pause");
		pauseButton.setLayoutX(50);
		pauseButton.setLayoutY(130);
		pauseButton.setOnAction(e -> {
			//pauseSong();
		});
		root.getChildren().add(pauseButton);

		// Create the button to stop the song.
		Button stopButton = new Button("Stop");
		stopButton.setLayoutX(50);
		stopButton.setLayoutY(160);
		stopButton.setOnAction(e -> {
			//stopSong();
		});
		root.getChildren().add(stopButton);

		// Create the button to select a new song.
		Button nextButton = new Button("Next Song");
		nextButton.setLayoutX(50);
		nextButton.setLayoutY(190);
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

		
		// SORT BUTTONS
		// Create the button to sort by title.
		Button titleButton = new Button("Sort by Title");
		titleButton.setLayoutX(50);
		titleButton.setLayoutY(220);
		titleButton.setOnAction(e -> {
			//sortSongsBy("title");
			System.out.println(songs);
		});
		root.getChildren().add(titleButton);

		// Create the button to sort by artist.
		Button artistButton = new Button("Sort by Artist");
		artistButton.setLayoutX(50);
		artistButton.setLayoutY(250);
		artistButton.setOnAction(e -> {
			//sortSongsBy("artist");
			System.out.println(songs);
		});
		root.getChildren().add(artistButton);
        
		// Create the button to sort by genre.
		Button genreButton = new Button("Sort by Genre");
		genreButton.setLayoutX(50);
		genreButton.setLayoutY(280);
		genreButton.setOnAction(e -> {
			//sortSongsBy("genre");
			System.out.println(songs);
		});
		root.getChildren().add(genreButton);

		// Create the button to sort by duration.
		Button durationButton = new Button("Sort by Duration");
		durationButton.setLayoutX(50);
		durationButton.setLayoutY(310);
		durationButton.setOnAction(e -> {
			//sortSongsBy("duration");
			System.out.println(songs);
		});
		root.getChildren().add(durationButton);

        


        

        /////////////////////////////////////////
        //labels and buttons for payment system//
        /////////////////////////////////////////

        // funds label
        fundsLabel = new Label();
        creditFundsLabel = new Label();
        fundsLabel.setLayoutX(50);
        fundsLabel.setLayoutY(700);
        creditFundsLabel.setLayoutX(400);
        creditFundsLabel.setLayoutY(600);
        root.getChildren().addAll(fundsLabel, creditFundsLabel);

       
        // coinPad Buttons
        Button pennyButton = createButton("1¢", 60, 445);
        pennyButton.setOnAction(e -> handleCoinButton(1));

        Button nickelButton = createButton("5¢", 90, 445);
        nickelButton.setOnAction(e -> handleCoinButton(5));

        Button dimeButton = createButton("10¢", 120, 445);
        dimeButton.setOnAction(e -> handleCoinButton(10));

        Button quarterButton = createButton("25¢", 155, 445);
        quarterButton.setOnAction(e -> handleCoinButton(25));

        Button halfDollarButton = createButton("50¢", 190, 445);
        halfDollarButton.setOnAction(e -> handleCoinButton(50));

        Button goldenDollarButton = createButton("$1", 225, 445);
        goldenDollarButton.setOnAction(e -> handleCoinButton(100));

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

        Button swipeCreditCardButton = createButton("Swipe Credit Card", 385, 565);
        swipeCreditCardButton.setOnAction(e -> {
            creditPayments.addFunds(Integer.parseInt(creditCardAmount));
            creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount + "¢ added to funds");
            creditCardAmount = "";
            displayUpdatedTotal();
        });

        root.getChildren().addAll(pennyButton, nickelButton, dimeButton, quarterButton, halfDollarButton,
                goldenDollarButton, returnFundsButton, oneButton, twoButton, threeButton, fourButton, fiveButton,
                sixButton, sevenButton, eightButton, nineButton, zeroButton, clearButton, swipeCreditCardButton);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /////////////////////////////////
    
    private Button createButton(String text, double layoutX, double layoutY) {
        Button button = new Button(text);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        return button;
    }

    private void handleCoinButton(int amount) {
        coinPayments.addFunds(amount);
        displayUpdatedTotal();
    }

    private void handleReturnFundsButton() {
        int totalReturnAmount = coinPayments.currencyBox.getTotalCoinsAmount()
                + creditPayments.creditCurrencyBox.getCreditAmountInt();
        fundsLabel.setText("Funds returned: " + totalReturnAmount + "¢" + "\n" + coinPayments.returnFunds() + "\n"
                + "Credit Card Amount: " + creditPayments.returnFunds() + "¢");

        creditPayments.creditCurrencyBox.resetCreditAmountInt();
        coinPayments.currencyBox.resetAllCoins();



    // Builds the initial rectangle that will eventually be animated.
		// r = new Rectangle(125, 125, 150, 150);
		// r.setFill(Color.PURPLE);
		// r2 = new Rectangle(80, 350, 100, 100);
		// r2.setFill(Color.RED);
		// r3 = new Rectangle(550, 80, 150, 150);
		// r3.setFill(Color.YELLOW);
		// r4 = new Rectangle(575, 105, 100, 100);
		// r4.setFill(Color.ORCHID);
		// root.getChildren().addAll(r, r2, r3, r4);
        
    
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
    
    
    
    }

    public static void main(String[] args) {
        launch(args);
    }
}
