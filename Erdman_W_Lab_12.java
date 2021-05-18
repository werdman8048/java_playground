import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.animation.*;
import javafx.util.*;
import java.util.*;


class Atom extends Circle
{
  public double x_speed;
  public double y_speed;
  public double radius;
  private double fieldWidth;
  private double fieldHeight;
  private Color atomcolor;

  public Atom(double radius,
        double fieldWidth,
        double fieldHeight)
  {
    super();
    this.radius = radius;
    this.fieldWidth = fieldWidth;
    this.fieldHeight = fieldHeight;
    super.setRadius(radius);
    //Set the center for the X coordinate
    super.setCenterX(Math.random() * (fieldWidth - this.radius) + 1);

    // Set the center for the Y coordinate

    Randomly set the x_speed and the y_speed to some number between 1 and 5

    //Choose a color or randomly choose a color.
    atomcolor = Color.RED;
    super.setFill(Color.RED);

  }

  public void move()
  {
    This method moves an individual atom.  It changes the
    center of the atom and then it looks to see if the atom
    is colliding with any of the four walls.

    //Change the x coordinate of the center
    super.setCenterX(super.getCenterX() + this.x_speed);
    //Change the y coordinate of the center


    // Detect collision with left edge
    if (super.getCenterX() <= this.radius)
    {
      super.setCenterX(this.radius);
      this.x_speed = -this.x_speed;
    }

    // Detect collision with right edge


    // Detect collision with top edge

    // Detect collision with bottom edge

  } //move
}//atom

public class Atomgutted extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

  final private int WIDTH = 600;
  final private int HEIGHT = 500;
  final private int ATOM_SIZE = 10;

  Atom myatoms;





  public void start(final Stage primaryStage)
    {
      FlowPane root = new FlowPane();
      Pane pane = new Pane();


			myatoms = new Atom(ATOM_SIZE, WIDTH, HEIGHT);



 		    pane.getChildren().add(myatoms);

    root.getChildren().add(pane);

    Scene scene = new Scene(root, WIDTH, HEIGHT);
    primaryStage.setTitle("Single Atoms");
    primaryStage.setScene(scene);
    primaryStage.show();

		KeyFrame k = new KeyFrame(Duration.millis(10),
			e ->
			{
        myatoms.move();
    });

        Timeline t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

}
