import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class Erdman_W_Lab_9 extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage myStage) {
		myStage.setTitle("Demonstrate A Simple Scence Graph");
		FlowPane rootNode = new FlowPane();
		Scene myScene = new Scene(rootNode, 300, 200);
		myStage.setScene(myScene);
		Label myLabel = new Label("A Simple JavaFX Label");
		rootNode.getChildren().add(myLabel);
		myStage.show();
	}	
}