import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import java.util.Random;
import javafx.scene.paint.Color;

public class EventDemo extends Application {

   private Circle c;
   private HBox pane;
   private Button button1;
   private Button button2;
   private Random random;
   private Scene scene;
   @Override
   public void start(Stage primaryStage) {
       primaryStage.setTitle("Jackie's window");
       pane = new HBox();
       c = new Circle(100,100,10);
       random = new Random();
       c.setOnMouseClicked(new EventHandler<MouseEvent>()
       {
         @Override
         public void handle(MouseEvent me)
         {
            c.setFill(Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
         }
       });
       button1 = new Button("Bigger");
//       Handler handler = new Handler();
//       button1.setOnAction(handler); //connect with handler
       button1.setOnAction(new EventHandler<ActionEvent>()
       {
         @Override
         public void handle(ActionEvent e)
         {
//             pane.setStyle("-fx-background-color: red");
            c.setRadius(c.getRadius()*1.1);
         }
       });
       
       button2 = new Button("Smaller");
       button2.setOnAction(new EventHandler<ActionEvent>()
       {
         @Override
         public void handle(ActionEvent e)
         {
//             pane.setStyle("-fx-background-color: red");
            c.setRadius(c.getRadius()*0.9);
         }
       });

       scene = new Scene(pane,200,200);
       pane.getChildren().add(button1);
       pane.getChildren().add(button2);
       pane.getChildren().add(c);
       primaryStage.setScene(scene);
       primaryStage.show();
   }
   
//    public class Handler implements EventHandler<ActionEvent>
//    {
//       @Override
//       public void handle(ActionEvent e)
//       {
//          pane.setStyle("-fx-background-color: red");
//       }
//    }
   
   public static void main(String [] args) {
      launch(args);
   }
}
