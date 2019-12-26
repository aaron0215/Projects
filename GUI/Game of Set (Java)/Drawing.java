import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.shape.*;
   
public class Drawing{ // extends Application {
   Image hatch = new Image("hatch.png");
   Card card;
   
   /**toDraw method imports a card and read characters
	   Use a vbox to hold all shapes because vbox
      can center nodes automatically
   */

   public VBox toDraw(Card card) //Drawing a vbox
   {
      VBox vbox = new VBox();
      this.card = card;
      String symbol = card.symToString();
         switch (symbol){
             case "DIAMOND":
               for (int num = 1; num <= card.getNum(); num++){ //use for loop to handle number
               Polygon p = drawDiamond(card);
               vbox.getChildren().add(p);
               }
                vbox.setAlignment(Pos.CENTER);
               break;
             case "SQUARE":
                for (int num = 1; num <= card.getNum(); num++){
                Circle c = drawSquare(card);
                vbox.getChildren().add(c);
                }
                 vbox.setAlignment(Pos.CENTER);
                break;
            case "OVAL":
               for (int num = 1; num <= card.getNum(); num++){
               Ellipse e = drawEllipse(card);
               vbox.getChildren().add(e);
               }
                vbox.setAlignment(Pos.CENTER);
               break;
         }
      return vbox;
   }
   
   /**drawDiamond method imports a card object to read
      shadings and colors and return a diamond shape
   */

   public Polygon drawDiamond(Card card)
   {
       Polygon polygon = new Polygon();
       polygon.getPoints().addAll(new Double[]{ 
         60.0, 10.0, 
         90.0, 30.0, 
         60.0, 50.0, 
         30.0, 30.0, });
       if (card.sha == Card.Shadings.HATCHED){
            polygon.setFill(new ImagePattern(hatch));
            if (card.col == Card.Colors.GREEN){
               polygon.setFill(new ImagePattern(hatch));
               polygon.setStroke(Color.GREEN);
               polygon.setStrokeWidth(2.0);
            }
            else if (card.col == Card.Colors.RED){
               polygon.setFill(new ImagePattern(hatch));
               polygon.setStroke(Color.RED);
               polygon.setStrokeWidth(2.0);            
            }
            else if (card.col == Card.Colors.PURPLE){
               polygon.setFill(new ImagePattern(hatch));
               polygon.setStroke(Color.PURPLE);
               polygon.setStrokeWidth(2.0);
            }
       }
      
      else if (card.sha == Card.Shadings.SOLID){
         if (card.col == Card.Colors.GREEN){
            polygon.setFill(Color.GREEN);
         }
         else if (card.col == Card.Colors.RED){
            polygon.setFill(Color.RED);
         }
         else if (card.col == Card.Colors.PURPLE){
            polygon.setFill(Color.PURPLE);
         }
      }
      else if (card.sha == Card.Shadings.OUTLINE){
         if (card.col == Card.Colors.GREEN){
            polygon.setStroke(Color.GREEN);
            polygon.setFill(Color.WHITE);
         }
         else if (card.col == Card.Colors.RED){
            polygon.setStroke(Color.RED);
            polygon.setFill(Color.WHITE);
         }
         else if (card.col == Card.Colors.PURPLE){
            polygon.setStroke(Color.PURPLE);
            polygon.setFill(Color.WHITE);
         }
       }          
         return polygon;
      }
  
     /**drawSquare method imports a card object to read
      shadings and colors and return a circle
    */
    
   public Circle drawSquare(Card card)
   {
       Circle circle = new Circle(20.0);
       if (card.sha == Card.Shadings.HATCHED){
            circle.setFill(new ImagePattern(hatch));
            if (card.col == Card.Colors.GREEN){
               circle.setFill(new ImagePattern(hatch));
               circle.setStroke(Color.GREEN);
               circle.setStrokeWidth(2.0);
            }
            else if (card.col == Card.Colors.RED){
               circle.setFill(new ImagePattern(hatch));
               circle.setStroke(Color.RED);
               circle.setStrokeWidth(2.0);            
            }
            else if (card.col == Card.Colors.PURPLE){
               circle.setFill(new ImagePattern(hatch));
               circle.setStroke(Color.PURPLE);
               circle.setStrokeWidth(2.0);
            }
       }
      
      else if (card.sha == Card.Shadings.SOLID){
         if (card.col == Card.Colors.GREEN){
            circle.setFill(Color.GREEN);
         }
         else if (card.col == Card.Colors.RED){
            circle.setFill(Color.RED);
         }
         else if (card.col == Card.Colors.PURPLE){
            circle.setFill(Color.PURPLE);
         }
      }
      else if (card.sha == Card.Shadings.OUTLINE){
         if (card.col == Card.Colors.GREEN){
            circle.setStroke(Color.GREEN);
            circle.setFill(Color.WHITE);
         }
         else if (card.col == Card.Colors.RED){
            circle.setStroke(Color.RED);
            circle.setFill(Color.WHITE);
         }
         else if (card.col == Card.Colors.PURPLE){
            circle.setStroke(Color.PURPLE);
            circle.setFill(Color.WHITE);
         }
       }          
         return circle;
   }

      /**drawEllipse method imports a card object to read
      shadings and colors and return an ellipse
      */
   
   public Ellipse drawEllipse(Card card)
   {
      Ellipse ellipse = new Ellipse();
      ellipse.setRadiusX(30);
      ellipse.setRadiusY(20);
      if (card.sha == Card.Shadings.HATCHED){
         ellipse.setFill(new ImagePattern(hatch));
         if (card.col == Card.Colors.GREEN){
            ellipse.setFill(new ImagePattern(hatch));
            ellipse.setStroke(Color.GREEN);
            ellipse.setStrokeWidth(2.0);
         }
         else if (card.col == Card.Colors.RED){
            ellipse.setFill(new ImagePattern(hatch));
            ellipse.setStroke(Color.RED);
            ellipse.setStrokeWidth(2.0);            
         }
         else if (card.col == Card.Colors.PURPLE){
            ellipse.setFill(new ImagePattern(hatch));
            ellipse.setStroke(Color.PURPLE);
            ellipse.setStrokeWidth(2.0);
         }
      }
      
      else if (card.sha == Card.Shadings.SOLID){
         if (card.col == Card.Colors.GREEN){
            ellipse.setFill(Color.GREEN);
         }
         else if (card.col == Card.Colors.RED){
            ellipse.setFill(Color.RED);
         }
         else if (card.col == Card.Colors.PURPLE){
            ellipse.setFill(Color.PURPLE);
         }
      }
      else if (card.sha == Card.Shadings.OUTLINE){
         if (card.col == Card.Colors.GREEN){
            ellipse.setStroke(Color.GREEN);
            ellipse.setFill(Color.WHITE);
         }
         else if (card.col == Card.Colors.RED){
            ellipse.setStroke(Color.RED);
            ellipse.setFill(Color.WHITE);
         }
         else if (card.col == Card.Colors.PURPLE){
            ellipse.setStroke(Color.PURPLE);
            ellipse.setFill(Color.WHITE);
         }
      }          
      return ellipse;
   }
}
