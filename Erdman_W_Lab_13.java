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

  public double fieldWidth;
  public double fieldHeight;
  private Color ballColor;

  public Ball(double radius,
        double fieldWidth,
        double fieldHeight, Color ballColor) {

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
    //Random rand = new Random();
    //max = 5, min = -5 | random.nextDouble(max - min) + min
    this.y_speed = Math.random()*2+1;
    if (Math.random() < 0.5) {this.y_speed = this.y_speed*-1;}//allowing this to be negative
    this.x_speed = Math.random()*2+1;
    if (Math.random() < 0.5) {this.x_speed = this.x_speed*-1;}//allowing this to be negative

    //System.out.println("X SPEED: " + x_speed);
    //System.out.println("Y SPEED: " + y_speed);//dont need to print these anymore

    //Choose a color or randomly choose a color.
    //this is what the internet said to do: (more or less)
    //this is painful, but it looks nice in the end
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

class Neutrino extends Ball {
  double radius;
  double fieldWidth;
  double fieldHeight;

  public Neutrino(double radius, double fieldWidth, double fieldHeight, Color ballColor) {
    super(radius, fieldWidth, fieldHeight, ballColor);
    this.radius = radius;
    this.fieldWidth = fieldWidth;
    this.fieldHeight = fieldHeight;
  }
  public void nmove() {
    //This method moves an individual ball.  It changes the center of the ball and then it looks to see if the ball is colliding with any of the four walls.

    //Change the x coordinate of the center
    super.setCenterX(super.getCenterX() + this.x_speed);
    //Change the y coordinate of the center
    super.setCenterY(super.getCenterY() + this.y_speed);

    // Detect collision with left edge
    if (super.getCenterX() <= this.radius)
    {
      super.setCenterX(fieldWidth - this.radius - 1);//wouldn't work without the "-1". idk why, but it took me ages
      //this.x_speed = -this.x_speed;
    }

    // Detect collision with right edge
    if (super.getCenterX() >= (fieldWidth - this.radius))
    {
      super.setCenterX(this.radius); //setting the center to this.radius tps it to the other side
      //this.x_speed = -this.x_speed;
    }

    // Detect collision with top edge
    if (super.getCenterY() <= this.radius)
    {
      super.setCenterY(fieldHeight - this.radius - 1);//wouldn't work without the "-1". idk why, but it took me ages
      //this.y_speed = -this.y_speed;
    }

    // Detect collision with bottom edge
    if (super.getCenterY() >= (fieldHeight - this.radius))
    {
      super.setCenterY(this.radius); //setting the center to this.radius tps it to the other side
      //this.y_speed = -this.y_speed;
    }
  }//move
}//neutrino

public class Erdman_W_Lab_13 extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

  final private int WIDTH = 600;
  final private int HEIGHT = 500;
  final private int BALL_SIZE = 10;
  final private int N_SIZE = 5;

  Ball myball;
  Neutrino my_neutrino;

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
    //settingStage.show();//now 2 windows appear, on top of each other though, dont wanna fix rn, its 2am


    //BALLS:
    ArrayList<Ball> myballs = new ArrayList<Ball>();
    ArrayList<Neutrino> my_neutrinos = new ArrayList<Neutrino>();
    FlowPane root = new FlowPane();
    Pane pane = new Pane();
    root.getChildren().add(pane);
    
    double numOfBalls = (Math.random()*15+10);//ideally would be int, but cant be bothered to change it as it works

    for (int i = 0; i < numOfBalls; i++) {
      //Creating a random color for the normal balls: (could be placed in a func)
      float r = (float) Math.random();
      float g = (float) Math.random();
      //private float b = (float) Math.random();
      Color randColor = Color.color(r, g, 0);//blue is set to 0 as to not interfere with neutrinos


      myball = new Ball(BALL_SIZE, WIDTH, HEIGHT, randColor);
      my_neutrino = new Neutrino(N_SIZE, WIDTH, HEIGHT, Color.BLUE);
      myballs.add(i, myball);
      my_neutrinos.add(i, my_neutrino);
 		  pane.getChildren().addAll(myball, my_neutrino);
    }

    Scene scene = new Scene(root, WIDTH, HEIGHT);
    primaryStage.setTitle("Bouncy Balls");
    primaryStage.setScene(scene);
    primaryStage.show();

    KeyFrame k = new KeyFrame(Duration.millis(10),
    e ->{
      for (int i = 0; i < numOfBalls;i++) {
        myball = myballs.get(i);//pretty sure this can be one step
        my_neutrino = my_neutrinos.get(i);
        my_neutrino.nmove();
        myball.move();
      } 
    });
 
    Timeline t = new Timeline(k);
    t.setCycleCount(Timeline.INDEFINITE);
    t.play();
    //t.stop();
  }
}
