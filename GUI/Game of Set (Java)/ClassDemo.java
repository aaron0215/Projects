import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
   
public class ClassDemo extends Application {
   Pane pane = new Pane();
   private Card c = new Card(1,2,1,1);
   Card.Shadings fill;
   Card.Colors cardColor;
   Image hatch = new Image("hatch.png");
   public Pane createCardpane(Card c, int row, int col)
   {
      String symbol = c.symToString();
      switch (symbol){
          case "OVAL":
            Ellipse e = drawEllipse(pane,50,50,50,25,fill.HATCHED,cardColor.GREEN);
            pane.getChildren().add(e);
            break;
         case "SQUARE":
            Circle cl = drawCircle(pane,50,50,25,fill.HATCHED,cardColor.GREEN);
            pane.getChildren().add(cl);
            break;
          case "DIAMOND":
             Rectangle r = drawRectangle(pane,50,50,50,25,fill.HATCHED,cardColor.GREEN);
             pane.getChildren().add(r);
             break;
      }
      return pane;
   }
   
   public Rectangle drawRectangle(Pane pane, int centerX, int centerY,
            int heightX, int widthY, Card.Shadings fill, Card.Colors cardColor)
   {
      Rectangle rectangle = new Rectangle(centerX,centerY,heightX,widthY);
      if (fill == Card.Shadings.HATCHED){
         rectangle.setFill(new ImagePattern(hatch));
         rectangle.setStrokeWidth(2.0);
         if (cardColor == Card.Colors.GREEN){
            rectangle.setStroke(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            rectangle.setStroke(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            rectangle.setStroke(Color.PURPLE);
         }
      }
      
      else if (fill == Card.Shadings.SOLID){
         if (cardColor == Card.Colors.GREEN){
            rectangle.setFill(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            rectangle.setFill(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            rectangle.setFill(Color.PURPLE);
         }
      }
      else if (fill == Card.Shadings.OUTLINE){
         rectangle.setFill(Color.WHITE);
         if (cardColor == Card.Colors.GREEN){
            rectangle.setStroke(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            rectangle.setStroke(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            rectangle.setStroke(Color.PURPLE);
         }
      }          
      return rectangle;
   }

public Circle drawCircle(Pane pane, int centerX, int centerY,
            int radius, Card.Shadings fill, Card.Colors cardColor)
   {
      Circle circle = new Circle(centerX,centerY,radius);
      if (fill == Card.Shadings.HATCHED){
         circle.setFill(new ImagePattern(hatch));
         circle.setStrokeWidth(2.0);
         if (cardColor == Card.Colors.GREEN){
            circle.setStroke(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            circle.setStroke(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            circle.setStroke(Color.PURPLE);
         }
      }
      
      else if (fill == Card.Shadings.SOLID){
         if (cardColor == Card.Colors.GREEN){
            circle.setFill(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            circle.setFill(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            circle.setFill(Color.PURPLE);
         }
      }
      else if (fill == Card.Shadings.OUTLINE){
         circle.setFill(Color.WHITE);
         if (cardColor == Card.Colors.GREEN){
            circle.setStroke(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            circle.setStroke(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            circle.setStroke(Color.PURPLE);
         }
      }          
      return circle;
   }

   
   public Ellipse drawEllipse(Pane pane, int centerX, int centerY,
            int radX, int radY, Card.Shadings fill, Card.Colors cardColor)
   {
      Ellipse ellipse = new Ellipse();
      ellipse.setCenterX(centerX);
      ellipse.setCenterY(centerY);
      ellipse.setRadiusX(radX);
      ellipse.setRadiusY(radY);
      if (fill == Card.Shadings.HATCHED){
         ellipse.setFill(new ImagePattern(hatch));
         ellipse.setStrokeWidth(2.0);
         if (cardColor == Card.Colors.GREEN){
            ellipse.setStroke(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            ellipse.setStroke(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            ellipse.setStroke(Color.PURPLE);
         }
      }
      
      else if (fill == Card.Shadings.SOLID){
         if (cardColor == Card.Colors.GREEN){
            ellipse.setFill(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            ellipse.setFill(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            ellipse.setFill(Color.PURPLE);
         }
      }
      else if (fill == Card.Shadings.OUTLINE){
         ellipse.setFill(Color.WHITE);
         if (cardColor == Card.Colors.GREEN){
            ellipse.setStroke(Color.GREEN);
         }
         else if (cardColor == Card.Colors.RED){
            ellipse.setStroke(Color.RED);
         }
         else if (cardColor == Card.Colors.PURPLE){
            ellipse.setStroke(Color.PURPLE);
         }
      }          
      return ellipse;
   }

   @Override
   public void start(Stage primaryStage) {
      createCardpane(c,0,0);
      Scene scene = new Scene(pane,400,400);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   public static void main(String [] args) {
      launch(args);
   }
}
