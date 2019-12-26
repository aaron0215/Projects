import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

import javafx.scene.paint.Color;

import java.util.Random;


public class KeyTester extends Application {
   private Button b,b2;
   private Pane pane;
   private Scene scene;
   private Circle circle;
   private Random random;
   @Override
   public void start(Stage primaryStage) {
       random = new Random();
       primaryStage.setTitle("Jackie's window");
       pane = new Pane();
       
       circle = new Circle(200,200,20);
       pane.getChildren().add(circle);
       circle.setOnMouseClicked(new EventHandler<MouseEvent>()
       {
            
            @Override
            public void handle(MouseEvent me) {
               circle.setFill(Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            }
        });
       b = new Button("Grow");
       pane.getChildren().add(b);
       b.setLayoutX(100);
       b.setOnAction( new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e)
         {
            double radius = circle.getRadius(); 
            circle.setRadius(radius*1.1);
         }
       });
       b2 = new Button("Shrink");
       pane.getChildren().add(b2);
       b2.setOnAction( new EventHandler<ActionEvent>() {
         public void handle(ActionEvent e)
         {
            double radius = circle.getRadius(); 
            circle.setRadius(radius*.9);
         }
       });
       pane.setFocusTraversable(true);
       
       scene = new Scene(pane,400,400);
       scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
               if (ke.getCode()==KeyCode.DOWN)
                  circle.setCenterY(circle.getCenterY()+1);
               else if (ke.getCode()==KeyCode.UP)
                  circle.setCenterY(circle.getCenterY()-1);
               else if (ke.getCode()==KeyCode.LEFT)
                  circle.setCenterX(circle.getCenterX()-1);
               else if (ke.getCode()==KeyCode.RIGHT)
                  circle.setCenterX(circle.getCenterX()+1);
                 
            }          
         
       });

       primaryStage.setScene(scene);
       primaryStage.show();
      
      
   }
   public static void main(String [] args) {
      launch(args);
   }
}
