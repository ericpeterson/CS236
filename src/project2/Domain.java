//REQUIRED CLASS
package project2;

import project1.*;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

public class Domain implements Set {
  // implemented methods of Set
  public void clear() {}
  public boolean removeAll(Collection collection) {
    return true;
  }
  public boolean retainAll(Collection collection) {
    return true;
  }
  public boolean addAll(Collection collection) {
    return true;
  }
  public boolean containsAll(Collection collection) {
    return true;
  }
  public boolean containsAll(Object object) {
    return true;
  }
  public boolean remove(Object object) {
    return true;
  }
  public boolean add(Object object) {
    return true;
  }
  public String[] toArray(Object[] object_array) {
    return null;
  }
  public Object[] toArray() {
    return null;
  }
  public Iterator iterator() {
    return null;
  }
  public boolean contains(Object object) {
    return true;
  }
  public boolean isEmpty() {
    return true;
  }
  public int size() {
    return 0;
  }
}
