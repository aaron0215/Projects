import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class test {

  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
//    Object input = new JSONParser().parse(new FileReader("valid.json"));
//    JSONObject jo = (JSONObject) input; // Typecast
//
//
//    // Get packages
//    JSONArray ja = (JSONArray) jo.get("packages");
//    // Iterate packages
//    Iterator itr = ja.iterator();
//    HashMap<String, Set<String>> list = new HashMap<>();
//    while (itr.hasNext()) {
//      JSONObject obj = (JSONObject) itr.next();
//      String key = (String) obj.get("name");
//      list.put(key, new HashSet<String>());
//      Object temp = obj.get("dependencies");
//      JSONArray edges = (JSONArray) temp;
//      for (int i = 0; i < edges.size(); i++) {
//        list.get(key).add((String) edges.get(i));
//      }
//    }
//    Set<String> test = new HashSet<String>();
//    test.add("test1");
//    test.add("test2");
//    test.add("test3");
//    test.add("test4");
//    ArrayList<Object> test = new ArrayList<Object>();
//    test.add(false);
//    test.add("test2");
//    test.add("test3");
//    System.out.println(test);
////    test.remove("test3");
////    test.add("test4");
//    System.out.println(test.remove("test2"));
//    System.out.println(test);
    List<String> test = new ArrayList<>();
    testRecursion(test,5);
    System.out.println(test);
  }
  
  private static void testRecursion(List<String> test, int count) {
    if(count == 0) {
      return;
    }
    test.add("test");
    count--;
    testRecursion(test,count);
  }

}
