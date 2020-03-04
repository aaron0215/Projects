
public class MergeSort {
   
  
  public void sort(int[] A, int a, int b) {
    if(b>a) {
      int m = (a+b)/2;
      sort(A, a, m);
      sort(A, m+1, b);
      merge(A,a,m,b);
    }
  }
  
  public void merge(int[] A, int a, int m, int b) {
    int i = a;
    int j = m+1;
    int[] B = new int[A.length];
    for (int k = a; k <= b; k++) {
      if(i == m+1) {
        B[k] = A[j];
        j++;
      } else if(j == b+1) {
        B[k] = A[i];
        i++;
      } else {
        if(A[i] < A[j]) {
          B[k] = A[i];
          i++;
        } else {
          B[k] = A[j];
          j++;
        }
      }
    }
    for (i = a; i <= b; i++) {
      A[i] = B[i];
    }
  }

  public static String print(int[] arr) {
    String ret = "";
    for(int i = 0; i < arr.length; i++) {
      ret += arr[i] + ", ";
    }
    return ret;
  }
  
  public static void main(String args[]) 
  { 
      
      int arr[] = {12, 11, 13, 5, 6, 7};
      System.out.println("Given Array"); 
      System.out.println(print(arr));

      MergeSort ob = new MergeSort();
      ob.sort(arr, 0, arr.length-1);

      System.out.println("\nSorted array"); 
      System.out.println(print(arr));
  } 
}
