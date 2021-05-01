import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import java.util.*;

public class Erdman_W_Lab_11 extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	//global vars for calculation
	static float fl_fahrenheit;
	static float fl_celcius;
	static float fl_kelvin;
	
	//must be retuned as an int. so here:
	//NOTE: any decimal value (meaning X.00....1 --> X.999...) is rounded DOWN. this could be changed but i didn't consider it to be that much of an issue
	static int rt_fahrenheit;
	static int rt_celcius;
	static int rt_kelvin;
	
	//going to be 3 seperate cal functions
	public static void fcalc(float input) {
		rt_celcius = (int) ((input-32)/1.8);
		rt_kelvin = (int) (rt_celcius + 273.15);
	}
	
	public static void ccalc(float input) {
		rt_fahrenheit = (int) (input*1.8+32);
		rt_kelvin = (int) (input + 273.15);
	}
	
	public static void kcalc(float input) {
		rt_celcius = (int) (input - 273.15);
		rt_fahrenheit = (int) (rt_celcius*1.8+32);
	}
	
	
	public void start(Stage myStage) {
		myStage.setTitle("Temperature Calculator");
		
		//text fields for all units
		TextField tf_fahrenheit = new TextField();
		TextField tf_celcius = new TextField();
		TextField tf_kelvin = new TextField();//i know underscores aren't standard with java, but it's easier 
		
		//labels for text fields
		Label lbl_fahr = new Label("Fahrenheit");
		Label lbl_cel = new Label("Celcius");
		Label lbl_kel = new Label("Kelvin");
		
		//alightment
		//essentially "\n"
		Region p = new Region();
		p.setPrefSize(Double.MAX_VALUE, 0.0);
		Region p2 = new Region();
		p2.setPrefSize(Double.MAX_VALUE, 0.0);
		//^^ this was causing me issues. flowpane essentially shoves as much as possible onto one row, then wraps. i as having issues forcing it to wrap early. idk how to do that

		FlowPane flow = new FlowPane();
		Scene myScene = new Scene(flow, 300, 200);
		flow.setHgap(10);
		flow.setAlignment(Pos.CENTER);
		flow.setVgap(20);
		
		//setting the scene
		myStage.setScene(myScene);
		flow.getChildren().addAll(lbl_fahr, tf_fahrenheit, p, lbl_cel, tf_celcius, p2, lbl_kel, tf_kelvin);
		myStage.show();
		
		//event handling. documentation said event was sent when the ENTER key is pressed
		tf_fahrenheit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				fl_fahrenheit = Float.parseFloat(tf_fahrenheit.getText());
				fcalc(fl_fahrenheit);
				tf_celcius.setText("" + rt_celcius);
				tf_kelvin.setText("" + rt_kelvin);//there are other ways but this is simple
			}
		});
		
		tf_celcius.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				fl_celcius = Float.parseFloat(tf_celcius.getText());
				ccalc(fl_celcius);
				tf_fahrenheit.setText("" + rt_fahrenheit);
				tf_kelvin.setText("" + rt_kelvin);//there are other ways but this is simple
			}
		});
		
		tf_kelvin.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				fl_kelvin = Float.parseFloat(tf_kelvin.getText());
				kcalc(fl_kelvin);
				tf_celcius.setText("" + rt_celcius);
				tf_fahrenheit.setText("" + rt_fahrenheit);//there are other ways but this is simple
			}
		});
	}	
}