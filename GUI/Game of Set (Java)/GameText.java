import java.util.Scanner;
import java.util.ArrayList;

public class GameText
{

   public static void main(String [] args) {
      // create game
      Game g = new Game();
      
      // display board
      System.out.println(g); 
      // connect Scanner to keyboard
      Scanner keyboard = new Scanner(System.in);
      // variables for user input
      int row, col;
      String input;
      // flag for ending
      boolean stop = false;
      
      // while the game isn't over and user doesn't want to quit
      while (!g.outOfCards() && !stop) {
         // give user their choices
         System.out.print("(s)elect, (u)nselect, (a)dd3, (l)ist selected, (e)nd: ");
         // get user choice
         input = keyboard.nextLine();
         // act on choice
         // select 
         if (input.equalsIgnoreCase("s"))        
         {
            
            System.out.print("row  col ? ");
            row = keyboard.nextInt();
            col = keyboard.nextInt();
            g.addToSelected(row,col);
            // if it's the 3rd card selected
            if (g.numSelected() == 3) 
            {
               // is it a set?
               // if it is a set, the cards are replaced
               // if it is NOT a set, all selected cards are unselected
               g.testSelected();
               // re-display board
               System.out.println(g);
            }     
          }
          // unselect
          else if (input.equalsIgnoreCase("u"))
          {
            System.out.print("row  col ? ");
            row = keyboard.nextInt();
            col = keyboard.nextInt();
            g.removeSelected(row,col);            
          
          }
          // add 3 cards (1 to each row), redisplay
          else if (input.equalsIgnoreCase("a"))
          {
            g.add3(); 
            System.out.println(g); 
          
          }
          // list all cards currently selected
          else if (input.equalsIgnoreCase("l"))
          {
            ArrayList<BoardSquare> selected = g.getSelected();
            System.out.println(selected);      
          
          }
          // user wants to be done
          else if (input.equalsIgnoreCase("e"))
          {
               stop = true;
          }
          
          if (!stop)
             input = keyboard.nextLine(); // get next choice
                  
      }  
    }

}