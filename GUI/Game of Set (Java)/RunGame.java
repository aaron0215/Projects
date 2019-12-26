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

public class RunGame extends Application {

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
   
   //Problems remaining:
   //need a diaplay when running out of cards and matching all sets. Done
   //how to set up remaining cards counter? Done
   //how to set up second click action? Done
   //need to precent user from selecting same cards. Not necessary,second click will cancel selected status
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
                           {
                              addEmptyGrid(grid,selectedCard.get(i).getCol(),selectedCard.get(i).getRow());
                           }
                           else
                           {
                              selectedCard.get(i).setUnselected();
                              addGrid(grid,selectedCard.get(i),selectedCard.get(i).getCol(),selectedCard.get(i).getRow()); 
                           }
                         }
                         else
                         {
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
      
//       buttonCheat.setOnMouseClicked(new EventHandler<MouseEvent>()
//       {
//          @Override
//          public void handle(MouseEvent me)
//          {
//             Set<Card> testSet = new HashSet<Card>();
//             Card test1;
//             Card test2;
//             Card test3;
//             int numRow = g.b.numRows();
//             int numCol = g.b.numCols();
//             for (int row = 0; row < numRow; row++){
//                for (int col = 0; col < numCol; col++){
//                   test1 = g.b.getCard(row,col);
//                   for (int col2 = 0; col2 < numCol; col2++){
//                      test2 = g.b.getCard(row,col);
//                      if (col2 == numCol - 1 && row < 2){
//                         int row2 = row + 1;
//                         for (col2 = 0; col2 < numCol; col++){
//                            test2 = g.b.getCard(row2,col2);}
//                      }
//                      for (int col3 = col2+1; col3 < numCol; col3++){
//                         test3 = g.b.getCard(row,col);
//                         if (col3 == numCol - 1 && row < 2){
//                            int row3 = row + 1;
//                            for(col3 = 0; col3 < numCol; col++){
//                               test3 = g.b.getCard(row+1,col3);}
//                         }
//                         if (Card.isSet(test1,test2,test3))
//                         {
//                            System.out.println("Find a set");
//                            System.out.println(test1.toString()+test2.toString()+test3.toString());
//                            break;
//                         }
//                      }
//                   }
//                }
//             }
//          }
//       });
                  
      return vbox;
   }
   
   public void addCard()
   {
      g.add3(); //add to the board
      int col = g.b.numCols()-1;
      if (g.d.countCard() >= 3)
      {
         for (int row = 0; row < 3; row++)
         {
            BoardSquare bs = g.b.getBoardSquare(row,col);
            grid = addGrid(grid,bs,col,row);
         }
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