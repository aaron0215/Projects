//Aaron Zhang
//CS110
//This is the program to create a board of cards

import java.util.ArrayList;
class Board
{
   private int row;
   private int col;
   private ArrayList<ArrayList<BoardSquare>> board;
   private BoardSquare bs;
   private ArrayList<BoardSquare> bd;//sub list

   /**Constructor accepts a Deck object and
      grabs 12 cards from deck
      and puts them into the 2D arraylist
	  @param r describes the rows
	  @param c describes the columns
    */

   public Board(Deck d)
   {
      board = new ArrayList<>();
      for (int r = 0; r < 3; r++)
      {
         bd = new ArrayList<>();
         for (int c = 0; c < 4; c++)
         {
            bd.add(new BoardSquare(d.getTopCard(),r,c));
         }
         board.add(bd);
      }
   }

   /**add3 method add three cards into
	   the end of each row
   */

   public void add3(Deck d)
   {
      for (int r = 0; r < 3; r++)
      {
         int c = board.get(r).size(); //Get the index number that new object should take
         board.get(r).add(new BoardSquare(d.getTopCard(),r,c));
      }
   }

   /**replaceCard method replace the card in specific
      row and column by using setter method in BoardSquare
   */

   public void replaceCard(Card c, int row, int col)
   {
      board.get(row).get(col).setter(c,row,col);
   }

   /**getBoardSquare method returns the
      boardsquare in specific column and rou
	   @return all information about the BoardSquare
   */

   public BoardSquare getBoardSquare(int row, int col)
   {
      bs = board.get(row).get(col);
      return bs;
   }

   /**getCard method returns the information about Card
	@return all information about the Card
   */

   public Card getCard(int row, int col)
   {
      bs = getBoardSquare(row,col);
      Card c = bs.getter(row,col);
      return c;
   }

   /**numRows method returns the numbers of rows
	@return all information about the rows
   */

   public int numRows()
   {
      return board.size();
   }

   /**numCols method returns the numbers of columns
	@return all information about the column
   */

   public int numCols()  //Assume each row has same column numbers so far
   {
      return board.get(0).size();
   }

   /**toString method returns the information in the BoardSquare
	@return all information in the BoardSquare
   */

   public String toString()
   {
      String boardString = "";
      for (int r = 0; r < numRows(); r++)
      {
         for (int c = 0; c < numCols(); c++)
         {
            bs = getBoardSquare(r,c);
            boardString += bs.toString()+"\t";
         }
         boardString += "\n";
      }
      return boardString;
   }
}
