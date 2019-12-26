import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.*; 
import javafx.stage.Stage;  
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Node;
import java.util.*;

public class GUI extends Application {

   public static GridPane grid = new GridPane();
   public static BorderPane border = new BorderPane(); 
   public static int cardRemain;
   public static Text t;
   public VBox textBox = new VBox();
   public ArrayList<BoardSquare> selectedCard = new ArrayList<>();
   public static Game g;
   public BoardSquare bs;
   public Card c;
   public HBox hbox = new HBox();
   public VBox vbox = new VBox();
   public VBox vboxSet = new VBox();
   public VBox vboxNotSet = new VBox();
   public Text result;
   
      /**addGrid method add elements into
	   each cell of the grid
      */
   
   public GridPane addGrid(GridPane grid,BoardSquare bs,int col,int row) //Stragety: add stackpanes into gridpane
   {
         this.bs = bs;
         StackPane rootPane = new StackPane();
         VBox vbox = new VBox();
         Drawing d = new Drawing();
         rootPane.setPrefSize(100,150);
         Rectangle rectangle = new Rectangle(100.0,150.0);
         rectangle.setArcHeight(15);
         rectangle.setArcWidth(15);
         
         cardRemain = g.d.countCard();
         Text countCard = new Text("Cards Remaining: "+cardRemain);
         border.setBottom(countCard);
         border.setAlignment(countCard,Pos.CENTER);
         
         Card card = bs.getter(row,col);
         vbox = d.toDraw(card);
         
         if (bs.getSelected())
         {               
            rectangle.setFill(Color.GREY);
         }   
         else
         {
            rectangle.setFill(Color.WHITE);
         }
         
         rectangle.setStroke(Color.BLACK);
         rootPane.getChildren().addAll(rectangle,vbox);
         rootPane.setAlignment(vbox,Pos.CENTER);
         grid.add(rootPane,col,row);
        
         //set mouse event to each cell 
         vbox.setOnMouseClicked(new EventHandler<MouseEvent>()
         {
            @Override
            public void handle(MouseEvent me)
            {
               //different actions based on the selected status of cards
               if(bs.getSelected())
               {
                  bs.setUnselected();
                  g.removeSelected(bs,bs.getRow(),bs.getCol());
                  rectangle.setFill(Color.WHITE);             
               }
               else
               {
                  bs.setSelected();
                  rectangle.setFill(Color.GRAY);
                  g.addToSelected(row,col);
                  System.out.println(g.cardlist);
                  
                  //test section
                  if (g.numSelected() == 3){
                      boolean isSet = g.testSelected();
                      selectedCard = g.getSelected();
                      for (int i = 0; i < selectedCard.size(); i++)
                      {
                         if(cardRemain < 3)
                         {
                           if(isSet)
                           {  //When running out of card,add empty cells to grid
                              addEmptyGrid(grid,selectedCard.get(i).getCol(),selectedCard.get(i).getRow());
                           }
                           else
                           {
                              selectedCard.get(i).setUnselected();
                              addGrid(grid,selectedCard.get(i),selectedCard.get(i).getCol(),selectedCard.get(i).getRow()); 
                           }
                         }
                         else
                         { //override old cells
                           BoardSquare newbs = new BoardSquare(selectedCard.get(i).getter(),selectedCard.get(i).getRow(),selectedCard.get(i).getCol());
                           addGrid(grid,newbs,newbs.getCol(),newbs.getRow()); 
                         }
                      }
                      g.clearSelected();
                   } //end of test secton
                } //end of handling unselected cards
              } //end of handle method
         }); //end of mouse event
      
     return grid;
   } 
   
      /**addButton method add buttons as needed
      */

   public VBox addButton(Stage stage, Game g) {
      vbox.setSpacing(10);
      vbox.setPadding(new Insets(15, 12, 15, 12));
      Button buttonAdd = new Button("Add Cards");
      Button buttonCheat = new Button("Cheat");
      Button buttonExit = new Button("Exit");
      buttonAdd.setPrefSize(100,20);
      buttonCheat.setPrefSize(100,20);
      buttonExit.setPrefSize(100,20);
      vbox.setAlignment(Pos.CENTER);
      cardRemain = g.d.countCard();
      vbox.getChildren().addAll(buttonAdd,buttonCheat,buttonExit);
      vbox.setAlignment(Pos.CENTER);
      
      buttonAdd.setOnMouseClicked(new EventHandler<MouseEvent>()
      {
         @Override
         public void handle(MouseEvent me)
         {
            addCard();
         }
      });
            
      buttonExit.setOnMouseClicked(new EventHandler<MouseEvent>()
      {
         @Override
         public void handle(MouseEvent me)
         {
            stage.close();
         }
      });
                        
      return vbox;
   }
   
      /**addCard method add three cards into
	   the GUI. It is called by Add button
      */
   
   public void addCard()
   {
      g.add3(); //add to the board
      int ShownCard = g.b.numCols()*g.b.numRows();
      int col = g.b.numCols()-1;
      if (g.d.countCard() >= 3 && ShownCard <= 18)
      {
         for (int row = 0; row < 3; row++)
         {
            BoardSquare bs = g.b.getBoardSquare(row,col);
            grid = addGrid(grid,bs,col,row);
         }
      }
      else if(ShownCard > 18)
      {
         Text t = new Text("You can't add more than 18 cards");
         t.setFont(Font.font("Courier",16));
         t.setFill(Color.RED);
         border.setBottom(t);
         border.setAlignment(t,Pos.CENTER);
      }
      else
      {
         Text t = new Text("Not enough cards in deck");
         t.setFont(Font.font("Courier",16));
         t.setFill(Color.RED);
         border.setBottom(t);
         border.setAlignment(t,Pos.CENTER);
      }
   }
   
      /**addEmptyGrid method add empty cells into
	   gridpane as needed
      */

   public void addEmptyGrid(GridPane grid,int col,int row)
   {
      cardRemain = g.d.countCard();
      Text outOfCard = new Text("Run out of card");
      border.setBottom(outOfCard);
      border.setAlignment(outOfCard,Pos.CENTER);
      StackPane finalPane = new StackPane();
      Rectangle rectangleFinal = new Rectangle(100.0,150.0);
      rectangleFinal.setStroke(Color.WHITE);
      rectangleFinal.setFill(Color.WHITE);
      finalPane.getChildren().addAll(rectangleFinal);
      finalPane.setAlignment(rectangleFinal,Pos.CENTER);
      grid.add(finalPane,col,row);
   }
   
   @Override 
   public void start(Stage stage) { 
      
      grid.setHgap(5);
      grid.setVgap(5);
      g = new Game();
      
      //Initially start
       
      for (int row = 0; row < 3; row++){
         for (int col = 0; col < 4; col++){
           bs = g.b.getBoardSquare(row,col);
           grid = addGrid(grid,bs,col,row);
         }
      }   
      
      Text title = new Text("Welcome to the Game of Set!");
      title.setFont(Font.font("Courier",26));
      title.setFill(Color.RED);

      VBox bottom = new VBox();
      bottom = addButton(stage,g);  
                
     //display section
      Group root = new Group(grid); 
      border.setTop(title);
      border.setAlignment(title,Pos.CENTER);
      border.setCenter(root);
      border.setLeft(bottom);
      border.setAlignment(bottom,Pos.CENTER);
      Scene scene = new Scene(border,600,600);     
      stage.setTitle("Game Launcher"); 
      stage.setScene(scene); 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}