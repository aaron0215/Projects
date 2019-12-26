
public class DS_My2 implements DataStructureADT {

  // TODO may wish to define an inner class
  // for storing key and value as a pair
  // such a class and its members should be "private"

  // Private Fields of the class
  // TODO create field(s) here to store data pairs

  Comparable[] key;
  Object[] value;
  int Capacity, count;

  public DS_My2() {
    Capacity = 500;
    count = 0;
    key = new Comparable[Capacity];
    value = new Object[Capacity];
    for (int i = 0; i < Capacity; i++) { // initialize
      key[i] = null;
      value[i] = null;
    }
  }

  @Override
  public void insert(Comparable k, Object v) {
    if (count == 0) {
      key[0] = k;
      value[0] = v;
      count++;
    } else {
      for (int i = 0; i < count; i++) {
        if (key[i].equals(k)) {
          throw new RuntimeException();
        }
      }
      if (count == Capacity) {
        Capacity *= 2;
        Comparable[] temp1 = new Comparable[Capacity];
        Object[] temp2 = new Object[Capacity];
        for(int j = 0; j < count; j++) {
          temp1[j] = key[j];
          temp2[j] = value[j];
        }
        key = temp1;
        value = temp2;
      }
      key[count] = k;
      value[count] = v;
      count++;
    }
  }

  @Override
  public boolean remove(Comparable k) {
    if (count == 0) {
      return false;
    } else {
      for (int i = 0; i < count; i++) {
        if (key[i].equals(k)) {
          do { // TODO: is it necessary to resort the array?
            key[i] = key[i + 1];
            value[i] = value[i + 1];
          } while (key[i + 1] != null && i < count - 1);
          key[count - 1] = null;
          value[count - 1] = null;
          count--;
          return true;
        }
      }
      return false;
    }
  }

  @Override
  public boolean contains(Comparable k) {
    if (key[0] == null)
      return false;
    for (int i = 0; i < key.length; i++) {
      if (key[i].equals(k)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Object get(Comparable k) {
    if (key[0] == null)
      return null;
    for (int i = 0; i < key.length; i++) {
      if (key[i].equals(k)) {
        return value[i];
      }
    }
    return null;
  }

  @Override
  public int size() {
    return count;
  }

}
