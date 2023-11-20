// SimpleText.java - Fall 2023
// ----------------------------------------------------------------------------------------
// This code may have outside copyrights and is provided for educational purposes only.
//
// Taken directly from: https://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm

// Code also used from the other parts of the reference:
// https://docs.oracle.com/javafx/2/ui_controls/text-field.htm


// Recommended reading: https://docs.oracle.com/javafx/2/ui_controls/jfxpub-ui_controls.htm
// More good reading: https://docs.oracle.com/javafx/2/charts/jfxpub-charts.htm


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 
// For UI controls (e.g., textboxes and labels)
//import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.event.ActionEvent;


// To run:
// c:\Progra~1\Eclips~1\jdk-17.0.8.7-hotspot\bin\java -p javafx-sdk-21.0.1\lib --add-modules=javafx.fxml,javafx.media,javafx.controls SimpleText


public class SimpleText extends Application {
 
   @Override
   public void start(Stage stage) {
	Group root = new Group();
	Scene scene = new Scene(root, 350, 200, Color.WHITE);

	Label label1 = new Label("Number 1:");
	TextField textField1 = new TextField ();
	Label label2 = new Label("Number 2:");
	TextField textField2 = new TextField ();

	Label label3 = new Label(" ");

	HBox hb1 = new HBox();
	hb1.getChildren().addAll(label1, textField1);
	hb1.setSpacing(10);

	HBox hb2 = new HBox();
	hb2.getChildren().addAll(label2, textField2);
	hb2.setSpacing(10);

	Button b1 = new Button("Add");

	b1.setOnAction(new EventHandler<ActionEvent>() {
	    @Override public void handle(ActionEvent e) {
        	label1.setText("Number 1 was added.");
        	label2.setText("Number 2 was added.");
		int sum = Integer.parseInt( textField1.getCharacters().toString() ) 
			+ Integer.parseInt ( textField2.getCharacters().toString() );
		label3.setText( "The sum of the numbers is " + sum );
	    }
	});

	VBox vb1 = new VBox();
	vb1.getChildren().addAll(hb1,hb2, label3, b1);
	vb1.setSpacing(10);

	
	root.getChildren().add(vb1);
 
	stage.setTitle("A simple demo of user input");
	stage.setScene(scene);
	stage.show();
   }
 
   public static void main(String[] args) {
	launch(args);
   }
}
