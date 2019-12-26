//Aaron Zhang
//CS110
//This is the tester program

import java.util.ArrayList;
public class Tester
{
   public static void main(String[] args)
   {
     Deck d = new Deck();
//      System.out.println(d);//DECK tester
     d.shuffle();
     Board b = new Board(d);
     System.out.println(b); 
     System.out.println(b.getCard(0,0)+"\n");
     System.out.println(b);
     if (Card.isSet(b.getCard(0,0), b.getCard(0,1), b.getCard(0,2)))
        System.out.println("set");       
     else
        System.out.println("not a set"); 
   }
}