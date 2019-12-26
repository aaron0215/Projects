import java.util.ArrayList;

class Game
{
   public Board b;
   public Deck d;
   public BoardSquare bs;
   public ArrayList<BoardSquare> selectedCard = new ArrayList<>();
   public ArrayList<Card> cardlist = new ArrayList<>();
   public boolean set;
   
   /**Constructor accepts no arguments
      Create a new Deck object
      Shuffle the deck
      Create a new board object and accepts
      deck as needed
    */
   
   public Game()
   {
     d = new Deck();
     d.shuffle();
     b = new Board(d);
   }
   
   /**add3 method add selected BoardSquare into a list
	   named as selectedCard
      Clean this list while it has more than three objects
   */
   
   public void addToSelected(int row, int col)
   {
         if (selectedCard.size() >= 3)
         {
            selectedCard.clear();
            cardlist.clear();
         }
         selectedCard.add(b.getBoardSquare(row,col));
         cardlist.add(b.getCard(row,col));
   }
   
   /**numSelected method returns the information about Game object
	@return the number of selected cards
   */
   
   public int numSelected()
   {
      return selectedCard.size();
   }
   
   /**testSelecter method test the selected cards
	   if they are a set
   */
   
   public boolean testSelected()
   {
      if(Card.isSet(cardlist.get(0),cardlist.get(1),cardlist.get(2)))
      {
         System.out.println("A set!");
         if (d.countCard() >= 3)
         {
            for (int i = 0; i < selectedCard.size(); i++)
            {
               bs = selectedCard.get(i);
               b.replaceCard(d.getTopCard(),bs.getRow(),bs.getCol()); //replace cards in board
            }
         }
         set = true;
      }
      else
      {
         System.out.println("Not a set!");
         for (int i = 0; i < selectedCard.size(); i++)
         {
            selectedCard.get(i).setUnselected();
         }
         set = false;
      }
      return set;
   }
   
   /**removeSelected method remove selected status
	   of specific card and remove it from the 
      selectedCard list
   */

   public void removeSelected(BoardSquare bs,int row, int col, ArrayList<BoardSquare> selectedCard, ArrayList<Card> cardlist)
   {
      b.getBoardSquare(row,col).setUnselected();
      for (int i = 0; i < selectedCard.size(); i++)
      {
         bs = selectedCard.get(i);
         if(bs.getRow() == row && bs.getCol() == col)
         {
            selectedCard.remove(i);
            cardlist.remove(i);
         }
      }
   }
   
   /**add3 method add three cards into
	   the end of each row
   */
   
   public void add3()
   {
      if (d.isEmpty()){
         System.out.println("Run out of card");
      }
      else{
         b.add3(d);
      }
   }
   
   /**getSelected method returns the information about selected cards
	@return the list of selected Card
   */
   
   public ArrayList<BoardSquare> getSelected()
   {
      return selectedCard;
   }
      
   /**outOfCard method returns the information about Card
	@return the infomation about if the Deck is empty
   */
   
   public boolean outOfCards()
   {
      return d.isEmpty();
   }
   
   /**toString method convert the information of Board
	@return all information about the Board
   */
   
   public String toString()
   {
      return b.toString();
   }
   
   public void clearSelected()
   {
      selectedCard.clear();
      cardlist.clear();
   }
}
