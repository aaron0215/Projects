//Aaron Zhang
//CS110
//This is the program to generate 81 cards

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
class Deck
{
   public final int CARDS_IN_DECK = 81;
   private ArrayList<Card> DECK = new ArrayList<>(); 
   private String card;
   private Card removedCard; 
   
   /**Constructor creates a arraylist contains 81 cards
    */
   
   public Deck()
   {
     for (int i1 = 1; i1 < 4; i1++)
     {
        for (int i2 = 0; i2 < 3; i2++)
        {
          for (int i3 = 0; i3 < 3; i3++)
          {
            for (int i4 = 0; i4 < 3; i4++)
            {
             Card c = new Card(i1,i2,i3,i4);
             DECK.add(c);
            }
          }
        }        
     }
   }
   
   /**shuffle method shuffles the order of cards in DECK
   */

   public void shuffle()
   {
      Collections.shuffle(DECK);
   }
   
   /**getTopCard method returns the information about removedCard
	@return all information about the removedCard
   */

   public Card getTopCard()
   {
       removedCard = DECK.get(0); //also represents the choosen card
       DECK.remove(0);
       return removedCard;
   }
   
   public int countCard()
   {
      return DECK.size();
   }

   
   /**isEmpty method returns the boolean result about if the DECK is empty
	@return all information about the DECK determination
   */

   public boolean isEmpty()
   {
         boolean empty = false;
         if (DECK.isEmpty())
            empty = true;
         return empty;
   }

//    public String toString() //toString that used to test DECK
//    {
//     String output = "";
//     for (int i = 0; i < DECK.size(); i++)
//     {
//       output += DECK.get(i).toString()+"\n";
//     }
//     return output;
//    }
   
   /**toString method returns the information about card in DECK
	@return all information about the card
   */

   public String toString()
   {
      card = removedCard.toString();
      return card;
   }
 
}