//sliders and buttons have been added in a second window but are not yet functional, I just want to turn this in, my understanding is that lab 13 will start with this code
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;//sliders
import javafx.geometry.*;//for sliders
import javafx.animation.*;
import javafx.util.*;
import java.util.*;


class Ball extends Circle {
  public double x_speed;
  public double y_speed;
  public double radius;

  private double fieldWidth;
  private double fieldHeight;
  private Color ballColor;

  public Ball(double radius,
        double fieldWidth,
        double fieldHeight) {

    super();
    this.radius = radius;
    this.fieldWidth = fieldWidth;
    this.fieldHeight = fieldHeight;
    super.setRadius(radius);
    //Set the center for the X coordinate
    super.setCenterX(Math.random() * (fieldWidth - this.radius) + 1);

    // Set the center for the Y coordinate
    super.setCenterY(Math.random() * (fieldHeight - this.radius) + 1);

    //Randomly set the x_speed and the y_speed to some number between 1 and 5
    Random rand = new Random();
    this.y_speed = (Math.random()*4 + 1);
    this.x_speed = (Math.random()*4 + 1);

    //System.out.println("X SPEED: " + x_speed);
    //System.out.println("Y SPEED: " + y_speed);//dont need to print these anymore

    //Choose a color or randomly choose a color.
    //this is what the internet said to do: (more or less)
    float r = (float) Math.random();
    float g = (float) Math.random();
    float b = (float) Math.random();
    Color ballColor = Color.color(r, g, b);//this is painful, but it looks nice in the end
    super.setFill(ballColor);
  }

  public void move() {

    //This method moves an individual ball.  It changes the center of the ball and then it looks to see if the ball is colliding with any of the four walls.

    //Change the x coordinate of the center
    super.setCenterX(super.getCenterX() + this.x_speed);
    //Change the y coordinate of the center
    super.setCenterY(super.getCenterY() + this.y_speed);

    // Detect collision with left edge
    if (super.getCenterX() <= this.radius)
    {
      super.setCenterX(this.radius);
      this.x_speed = -this.x_speed;
    }

    // Detect collision with right edge
    if (super.getCenterX() >= (fieldWidth - this.radius))
    {
      super.setCenterX(fieldWidth - this.radius); //setting the center to this.radius tps it to the other side
      this.x_speed = -this.x_speed;
    }

    // Detect collision with top edge
    if (super.getCenterY() <= this.radius)
    {
      super.setCenterY(this.radius);
      this.y_speed = -this.y_speed;
    }

    // Detect collision with bottom edge
    if (super.getCenterY() >= (fieldHeight - this.radius))
    {
      super.setCenterY(fieldHeight - this.radius); //setting the center to this.radius tps it to the other side
      this.y_speed = -this.y_speed;
    }

  } //move
}//ball

public class Erdman_W_Lab_12 extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

  final private int WIDTH = 600;
  final private int HEIGHT = 500;
  final private int BALL_SIZE = 10;

  Ball myball;

  public void start(final Stage primaryStage) {

    //SLIDERS AND BUTTONS:
    FlowPane rootSupport = new FlowPane();
    Slider ball_slider = new Slider(0.1, 10, 1);//same as below
    Slider speed_slider = new Slider(0.1, 10, 1);//hardcoded: 1/10 speed min, 10x speed max, 1x (default)
    //speed_slider.setFill(Color.BLACK); couldnt figure this out but looks like i need to use css to change colors?
    //speed_slider.setAlignment(Pos.RIGHT); nor this
    speed_slider.setOrientation(Orientation.VERTICAL);

    Button btnReset = new Button("Reset");

    rootSupport.getChildren().addAll(speed_slider, ball_slider, btnReset);

    Scene sceneSupport = new Scene(rootSupport, 200, 150);
    Stage settingStage = new Stage();

    settingStage.setTitle("Setting Screen");
    settingStage.setScene(sceneSupport);
    settingStage.show();//now 2 windows appear, on top of each other though, dont wanna fix rn, its 2am


    //BALLS:
    ArrayList<Ball> myballs = new ArrayList<Ball>();//it legit took me 6 hours to realize I had to use an arraylist. actually i had thought about it earlier but didn't expect it to work. 
    FlowPane root = new FlowPane();
    Pane pane = new Pane();
    root.getChildren().add(pane);
    
    //i liked more balls so I've changed it. to fit the instuctions PERFECTLY it would be *40+10 this is 30 to 60 balls
    double numOfBalls = (Math.random()*30+30);//ideally would be int, but cant be bothered to change it as it works
    
    for (int i = 0; i < numOfBalls; i++) {
      myball = new Ball(BALL_SIZE, WIDTH, HEIGHT);
      myballs.add(i, myball);
 		  pane.getChildren().add(myball);
    }

    Scene scene = new Scene(root, WIDTH, HEIGHT);
    primaryStage.setTitle("Bouncy Balls");
    primaryStage.setScene(scene);
    primaryStage.show();

    KeyFrame k = new KeyFrame(Duration.millis(10),
    e ->{
      for (int i = 0; i < numOfBalls;i++) {
        myball = myballs.get(i);//pretty sure this can be one step
        myball.move();
      } 
    });
 
    Timeline t = new Timeline(k);
    t.setCycleCount(Timeline.INDEFINITE);
    t.play();
    //t.stop();
  }
}
