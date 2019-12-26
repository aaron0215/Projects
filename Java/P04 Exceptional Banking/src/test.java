import java.util.zip.DataFormatException;
public class test {

  public static void main(String[] args) throws DataFormatException{
    // TODO Auto-generated method stub
    // try {
    // System.out.println(2/0);
    // }catch(RuntimeException e) {
    // throw new NullPointerException("Wrong");
    // }
//    String test = "I love python#";
//     for (char ch : test.toCharArray()) {
//     System.out.println(Character.isDigit(ch) || Character.isLetter(ch) || ch == ' ');
//     }
    String[] list = { "a", "b", "c" };
    String result = "";
    for (int i = list.length; i > 0; i--)
     for (int j = 0; j < list[i-1].length(); j++)
     if ( j < list[j].length() )
     result += i + ")" + list[3 - j - i] + " ";
    System.out.println( result );
  }

}
