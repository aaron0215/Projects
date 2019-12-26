//Aaron Zhang
//CS110
//This is the program to create a card

class Card
{  
   /**
      Enumrator types represent characters of cards
   */
   public enum Symbols
   {
      OVAL, SQUARE, DIAMOND
   }
   
   public enum Shadings
   {
      SOLID, HATCHED, OUTLINE
   }
   
   public enum Colors
   {
      RED, PURPLE, GREEN
   }
   
   public int num;
   public Symbols sym;
   public Shadings sha;
   public Colors col;
   
   /**Constructor creates a card using four parameters
	  @param num describes the numbers of cards
	  @param sym describes the symbols of cards
     @param sha describes the shadings of cards
     @param col describes the colors of cards
    */
   
   public Card(int cNum, int index1, int index2, int index3)
   {
      this.num = cNum;
      this.sym = Symbols.values()[index1];  //It is convinent to generate 81 cards in Deck by using this method
      this.sha = Shadings.values()[index2];
      this.col = Colors.values()[index3];
   }
   
   /**symToString method returns the information about symbol
	@return all information about the symbol
   */

   public String symToString()
   { 
        switch (sym) {
        case OVAL:
            return "OVAL";
        case SQUARE:
            return "SQUARE";
        case DIAMOND:
            return "DIAMOND";
        default:
            return null;
        }    
    }
   
   /**shaToString method returns the information about shading
	@return all information about the shading
   */

   public String shaToString()
   { 
        switch (sha) {
        case SOLID:
            return "SOLID";
        case HATCHED:
            return "HATCHED";
        case OUTLINE:
            return "OUTLINE";
        default:
            return null;
        }    
   }
   
   /**colToString method returns the information about column
	@return all information about the column
   */

   public String colToString()
   { 
        switch (col) {
        case RED:
            return "RED";
        case GREEN:
            return "GREEN";
        case PURPLE:
            return "PUEPLE";
        default:
            return null;
        }    
   }
   
   public int getNum()
   {
      return num;
   }
   
   /**toString method returns the information about Card
	@return all information about the Card
   */

   public String toString()
   {
      return Integer.toString(num)+"_"+colToString()+"_"+symToString()+"_"+shaToString();
   }
   
   /**isSet method returns the boolean result between three cards
	@return all information about the boolean result
   */

   public static boolean isSet(Card c1, Card c2, Card c3) 
   {
      boolean set = false;
      if (((c1.num)==(c2.num)&&(c2.num)==(c3.num)) || 
                  ((c1.num)!=(c2.num)&&(c2.num)!=(c3.num)&&(c1.num)!=(c3.num)))
      {
         if (((c1.col).equals(c2.col)&&(c2.col).equals(c3.col)) || 
                        !((c1.col).equals(c2.col)||(c2.col).equals(c3.col)||(c1.col).equals(c3.col)))
         {              //Use || can make sure it only generates when all of them are false
            if (((c1.sym).equals(c2.sym)&&(c2.sym).equals(c3.sym)) || 
                  !((c1.sym).equals(c2.sym)||(c2.sym).equals(c3.sym)||(c1.sym).equals(c3.sym)))
            {
               if (((c1.sha).equals(c2.sha)&&(c2.sha).equals(c3.sha)) || 
                      !((c1.sha).equals(c2.sha)||(c2.sha).equals(c3.sha)||(c1.sha).equals(c3.sha)))
               {
                   set = true;
               }
            }
         }
      }
      return set;
   }
}