//Aaron Zhang
//CS110
//This is the program to create each square of the board

import java.util.ArrayList;
class BoardSquare
{
   private Card c;
   private int row;
   private int col;
   public boolean selected = false;
   
   /**Constructor accepts a Card, row and column
	  @param c describes the Card of this object
	  @param row describes the row number of this object
     @param col describes the colume number of this object
    */
   
   public BoardSquare(Card c, int row, int col)
   {
      this.c = c;
      this.row = row;
      this.col = col;
   }
   
   /**getter method returns the information about Card
	@return all information about the Card
   */

   public Card getter(int row,int col)
   {
      return c;
   }
   
      public Card getter()
   {
      return c;
   }

   
   /**setter method set the Card at specific
	   row and column
   */

   public void setter(Card c, int row, int col)
   {
      this.c = c;
      this.row = row;
      this.col = col;
      selected = false;
   }
   
   /**getRow method returns the information about Card
	@return ro number about the Card
   */
   
   public int getRow()
   {
      return row;
   }
   
   /**getCol method returns the information about Card
	@return column number about the Card
   */
   
   public int getCol()
   {
      return col;
   }
   
   /**setSelected method set the selected status of Card
      to be true
   */
   
   public void setSelected()
   {
      selected = true;
   }
   
   /**setUnselected method set the selected status of Card
      to be false
   */

   public void setUnselected()
   {
      selected = false;
   }
   
   /**getSelected method returns the information about Card
	@return selected status about the Card
   */
   
   public boolean getSelected()
   {
      return selected;
   }
   
   /**toString method convert the information of Card to String
	@return all information about the Card
   */

   public String toString()
   {
      return c.toString();
   }
}