import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    
    private GridPane grid;
    private Label fundsLabel;
    private Label creditFundsLabel;
    private String creditCardAmount = "";


    //create instances of the classes
    coinPayments coinPayments = new coinPayments();
    currencyBox currencyBox = new currencyBox();
    creditPayments creditPayments = new creditPayments();
        
    //prints funds to the screen
    private void displayUpdatedTotal() {
        int displayAmount = coinPayments.currencyBox.getTotalCoinsAmount() + creditPayments.creditCurrencyBox.getCreditAmountInt();
        fundsLabel.setText("Funds: " + displayAmount + "¢");
        
    }

    private void ccAmountAdded(int addedAmount) {
            creditCardAmount = creditCardAmount + Integer.toString(addedAmount);
            creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount);
            System.out.println(creditCardAmount);
        }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Juke Box");

        //set up for the grid pane
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));


        //GridPane for coin pad
        GridPane coinPad = new GridPane();
        coinPad.setHgap(10);
        coinPad.setVgap(10);
        coinPad.setPadding(new Insets(20));

        //gridPane for return funds button
        GridPane returnFunds = new GridPane();
        returnFunds.setHgap(10);
        returnFunds.setVgap(10);
        returnFunds.setPadding(new Insets(20));

        //gridPane for credit card payments
        GridPane creditCardPayments = new GridPane();
        creditCardPayments.setHgap(10);
        creditCardPayments.setVgap(10);
        creditCardPayments.setPadding(new Insets(20));


        //set up for the title
        Text scenetitle = new Text("JukeBox");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        grid.add(scenetitle, 5, 0, 2, 1);

        //text fields for the loop parameters
        
        fundsLabel = new Label();
        creditFundsLabel = new Label();
        

       /*
        coinPad Buttons
        */

        //button for 1 cent
        Button pennyButton = new Button("1¢   ");

        pennyButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                coinPayments.addFunds(1);
                displayUpdatedTotal();                
            }
        });

          
        //button for 5 cents
        Button nickelButton = new Button("5¢  ");

        nickelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                coinPayments.addFunds(5);
                displayUpdatedTotal();                
            }
        });


        //button for 10 cents
        Button dimeButton = new Button("10¢  ");

        dimeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                coinPayments.addFunds(10);
                displayUpdatedTotal();                
            }
        });

        //button for 25 cents
        Button quarterButton = new Button("25¢");

        quarterButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                coinPayments.addFunds(25);
                displayUpdatedTotal();                
            }
        });

        //button for 50 cents
        Button halfDollarButton = new Button("50¢");

        halfDollarButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                coinPayments.addFunds(50);
                displayUpdatedTotal();                
            }
        });

        //button for 100 cents
        Button goldenDollarButton = new Button("$1  ");

        goldenDollarButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                coinPayments.addFunds(100);
                displayUpdatedTotal();                
            }
        });

        //return funds button
        Button returnFundsButton = new Button("Return Funds");

        returnFundsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                coinPayments.returnFunds();
                int totalReturnAmount = coinPayments.currencyBox.getTotalCoinsAmount() + creditPayments.creditCurrencyBox.getCreditAmountInt();
                fundsLabel.setText("Funds returned: " + totalReturnAmount + "¢" + "\n" + coinPayments.returnFunds() + "\n" + "Credit Card Amount: " + creditPayments.returnFunds() + "¢");

                creditPayments.creditCurrencyBox.resetCreditAmountInt();
                coinPayments.currencyBox.resetAllCoins();
            }
        });


         /*
        creditCardPayment Buttons
        */
        

        //button for 1
        Button oneButton = new Button("1");
        oneButton.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(1);
                }
            });

    
        //button for 2
        Button twoButton = new Button("2");
        twoButton.setOnAction(new EventHandler<ActionEvent>() {
                    
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(2);
                }
                });

        //button for 3
        Button threeButton = new Button("3");
        threeButton.setOnAction(new EventHandler<ActionEvent>() {
                        
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(3);
                }
                });

        //button for 4
        Button fourButton = new Button("4");
        fourButton.setOnAction(new EventHandler<ActionEvent>() {
                            
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(4);
                }
                });

        //button for 5
        Button fiveButton = new Button("5");
        fiveButton.setOnAction(new EventHandler<ActionEvent>() {
                                
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(5);
                }
                });

        //button for 6
        Button sixButton = new Button("6");
        sixButton.setOnAction(new EventHandler<ActionEvent>() {
                                    
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(6);
                }
                });

        //button for 7
        Button sevenButton = new Button("7");
        sevenButton.setOnAction(new EventHandler<ActionEvent>() {
                                        
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(7);
                }
                });

        //button for 8
        Button eightButton = new Button("8");
        eightButton.setOnAction(new EventHandler<ActionEvent>() {
                                            
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(8);
                }
                });

        //button for 9
        Button nineButton = new Button("9");
        nineButton.setOnAction(new EventHandler<ActionEvent>() {
                                                
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(9);
                }
                });

        //button for 0
        Button zeroButton = new Button("0");
        zeroButton.setOnAction(new EventHandler<ActionEvent>() {
                                                    
                @Override
                public void handle(ActionEvent e) {
                    ccAmountAdded(0);
                }
                });

        //button for clear
        Button clearButton = new Button("C");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
                                                        
                @Override
                public void handle(ActionEvent e) {
                    creditCardAmount = "";
                    creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount);
                }
                });
        
        //button for swipe credit card
        Button swipeCreditCardButton = new Button("Swipe Credit Card");
        swipeCreditCardButton.setOnAction(new EventHandler<ActionEvent>() {
                                                                
                @Override
                public void handle(ActionEvent e) {
                    creditPayments.addFunds(Integer.parseInt(creditCardAmount));
                    creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount +"¢ added to funds");
                    // try {
                    //     Thread.sleep(2000);
                    // } catch (InterruptedException e1) {
                    //     e1.printStackTrace();
                    // }
                    creditCardAmount = "";
                    displayUpdatedTotal();
                    // creditFundsLabel.setText("Credit Card Amount: " + creditCardAmount);
                    //fundsLabel.setText("Funds: " + Integer.toString(coinPayments.currencyBox.getTotalCoinsAmount()) + "¢");
                }
                });

     
        

        //set up for all the elements to the coin pad grid pane
        coinPad.add(pennyButton, 0, 8);
        coinPad.add(nickelButton, 1, 8);
        coinPad.add(dimeButton, 2, 8);
        coinPad.add(quarterButton, 0, 9);
        coinPad.add(halfDollarButton, 1, 9);
        coinPad.add(goldenDollarButton, 2, 9);
        returnFunds.add(returnFundsButton, 1, 0);

        //set up for all the elements to the credit card payments grid pane
        grid.add(creditFundsLabel, 6, 7, 1, 3);
        coinPad.add(oneButton, 7, 8);
        coinPad.add(twoButton, 8, 8);
        coinPad.add(threeButton, 9, 8);
        coinPad.add(fourButton, 7, 9);
        coinPad.add(fiveButton, 8, 9);
        coinPad.add(sixButton, 9, 9);
        coinPad.add(sevenButton, 7, 10);
        coinPad.add(eightButton, 8, 10);
        coinPad.add(nineButton, 9, 10);
        coinPad.add(zeroButton, 8, 11);
        coinPad.add(clearButton, 9, 11);
        coinPad.add(swipeCreditCardButton, 7, 12, 3, 1);
        
        
        grid.add(fundsLabel, 2, 4);
        

        //add the grid pane to the scene
        grid.add(coinPad, 0, 6, 3, 1);
        Scene scene = new Scene(grid, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        //add the return funds button to the scene
        grid.add(returnFunds, 0, 10, 3, 1);

        //add the credit card payments to the scene to the right of the coin pad
        grid.add(creditCardPayments, 0, 5, 3, 1);

    
    }


   

    public static void main(String[] args) {
        launch(args);
    }
}
 