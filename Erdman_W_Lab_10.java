import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import java.util.*;

public class Erdman_W_Lab_10 extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	//these first two need to be global so that they're not reset every time codeCounter is called. I guess I could pass then through, but this is easier. 
	public static List<Integer> sequence = new ArrayList<Integer>();
	public static List<Integer> secretCode = new ArrayList<Integer>(Arrays.asList(3, 3, 3));//code is 333
	//making these global so that the can be "removed" after more buttons are pressed
	//and i can add labels from the codeCounter function
	public static Label success = new Label("Success! The correct code was 333.");
	public static Label failure = new Label("Failure. That code was incorrect. Please try again.");
	public static FlowPane flow = new FlowPane();
		
	public static void codeCounter(int input) {
		flow.getChildren().remove(failure);
		flow.getChildren().remove(success);
		sequence.add(input);
		if (sequence.equals(secretCode)) {
			flow.getChildren().add(success);
		} else if(sequence.size() >= secretCode.size()) {
			sequence.clear();	
			flow.getChildren().add(failure);
		}
	}
	
	public void start(Stage myStage) {
		myStage.setTitle("Button Combo Game");
		Scene myScene = new Scene(flow, 300, 200);
		myStage.setScene(myScene);
		//buttons
		Button btnOne = new Button("1");
		btnOne.setPrefSize(80, 100);
		Button btnTwo = new Button("2");
		btnTwo.setPrefSize(80, 100);
		Button btnThree = new Button("3");
		btnThree.setPrefSize(80, 100);
		flow.setHgap(20);
		flow.setAlignment(Pos.CENTER);
		flow.getChildren().addAll(btnOne, btnTwo, btnThree);
		
		//event handling
		//pretty much outsourcing the rest once buttons are pressed
		//btnOne
		btnOne.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				codeCounter(1);
			}
		});
		//btnTwo
		btnTwo.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				codeCounter(2);
			}
		});
		//btnThree
		btnThree.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				codeCounter(3);
			}
		});
		
		
		myStage.show();
	}	
}