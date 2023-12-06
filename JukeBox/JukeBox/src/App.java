import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private GridPane grid;
    private TextField initializationField;
    private TextField conditionField;
    private TextField incrementField;
    private Label loopStatusLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("For Loop Demo");

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

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //text fields for the loop parameters
        // initializationField = new TextField();
        // conditionField = new TextField();
        // incrementField = new TextField();
        // loopStatusLabel = new Label();

        coinPayments coinPayments = new coinPayments();
        
    
        //button for 1 cent
        Button pennyButton = new Button("1¢");
        pennyButton.setOnAction(e -> addPennies());

        private void addPennies() {
        coinPayments.addFunds(1);
        System.out.println("Penny added");
        System.out.println("Pennies: " + coinPayments.box.pennies);
        }
       

        //button for 5 cents
        Button nickelButton = new Button("5¢");
        nickelButton.setOnAction(e -> runLoop());

        //button for 10 cents
        Button dimeButton = new Button("10¢");
        dimeButton.setOnAction(e -> runLoop());

        //button for 25 cents
        Button quarterButton = new Button("25¢");
        quarterButton.setOnAction(e -> runLoop());

        //button for 50 cents
        Button halfDollarButton = new Button("50¢");
        halfDollarButton.setOnAction(e -> runLoop());

        //button for golden dollar
        Button goldenDollarButton = new Button("$1");
        goldenDollarButton.setOnAction(e -> runLoop());

      

        //set up for  all the elements to the grid pane
        grid.add(new Label("Initialization:"), 0, 1);
        grid.add(initializationField, 2, 1);
        grid.add(new Label("Condition:"), 1, 2);
        grid.add(conditionField, 2, 2);
        grid.add(new Label("Increment:"), 1, 3);
        grid.add(incrementField, 2, 3);
        coinPad.add(pennyButton, 0, 8);
        coinPad.add(nickelButton, 1, 8);
        coinPad.add(dimeButton, 2, 8);
        coinPad.add(quarterButton, 0, 9);
        coinPad.add(halfDollarButton, 1, 9);
        coinPad.add(goldenDollarButton, 2, 9);
        
        

        grid.add(loopStatusLabel, 2, 4);

        //add the grid pane to the scene

        grid.add(coinPad, 0, 5, 3, 1);
        Scene scene = new Scene(grid, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void runLoop() {
        String initialization = initializationField.getText();
        String condition = conditionField.getText();
        String increment = incrementField.getText();

        Label loopStatusLabel2 = loopStatusLabel;
        loopStatusLabel2.setText(""); // Clear previous status

        try {
            int initionalzationInt = Integer.parseInt(initialization);
            int conditionInt = Integer.parseInt(condition);
            int incrementInt = Integer.parseInt(increment);
            int j = 1;
            for (int i = initionalzationInt; i <= conditionInt; i += incrementInt) {
                loopStatusLabel2.setText(loopStatusLabel2.getText() + "Iteration " + j + " : " + + i + "\n");
                j++;
            }
        } catch (NumberFormatException e) {
            loopStatusLabel2.setText("Invalid input. Please enter valid integers.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
