import java.util.Arrays;

public class test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    CustomProcess[] test = new CustomProcess[5];
    System.out.println(test[1]);
    for(int i = 0; i < test.length; i++) {
      test[i] = new CustomProcess(i);
    }
    System.out.println(test[1].getBurstTime());
    test = Arrays.copyOf(test, 10);
    System.out.println(test[4].getBurstTime());
    System.out.println(test[5]);
  }

}
