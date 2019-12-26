import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Ellipse;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class FXShell extends Application {
   @Override
   public void start(Stage primaryStage) {
       primaryStage.setTitle("Jackie's window");
       Pane pane = new Pane();
       
       Polygon ploy = new Polygon();
       ploy.getPoints().addAll(new Double[] {10.0,10.0,20.0,20.0,20.0,50.0,200.0,100.0});
       pane.getChildren().add(ploy);
       
//        Label l = new Label("Hello World");
//        Font f1 = Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,48);
//        // pane.getChildren().add(l);
//        l.setFont(f1);
//        Rectangle r = new Rectangle(100,150,50,50);
//        // r.setFill(Color.GREEN);
//        r.setStyle("-fx-fill:red; -fx-stroke:black");
//        pane.getChildren().add(r);
//        r.setRotate(45);
//        
//        Circle c = new Circle(100,100,50);
//        c.setFill(Color.AZURE);
//        c.setStroke(Color.BLACK); //boarder
//        pane.getChildren().add(c);
//        
//        Circle c2 = new Circle(150,200,100);
//        c2.setFill(Color.YELLOW);
//        c2.setStroke(Color.BLACK); //boarder
//        c2.setOpacity(0.5);
//        pane.getChildren().add(c2);
//        
//        Text text = new Text(12,36,"hello world");
//        text.setFont(Font.font("Courier",48));
//        text.setUnderline(true);
//        text.setStrikethrough(true);
//        pane.getChildren().add(text); //use text for the content not associated with anything
//        text.setFill(Color.BLUE);
// 
//        Line line = new Line(100,100,140,180);
//        line.setFill(Color.RED);
//        pane.getChildren().add(line);
// 
//        Ellipse e1 = new Ellipse(200,200,50,100);
//        e1.setFill(Color.TRANSPARENT);
//        e1.setStroke(Color.GREEN);
//        pane.getChildren().add(e1);
//        
//        Ellipse e2 = new Ellipse(200,200,100,50);
//        e2.setFill(Color.TRANSPARENT);
//        e2.setStroke(Color.RED);
//        e2.setRotate(20);//degrees
//        pane.getChildren().add(e2);
// 
       Scene scene = new Scene(pane,400,400);
       primaryStage.setScene(scene);
       primaryStage.show();
      
      
   }
   public static void main(String [] args) {
      launch(args);
   }
}
