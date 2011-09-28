//REQUIRED CLASS
package project2;

import project1.*;
import java.util.TreeSet;
import java.util.Collection;
import java.util.Iterator;

public class Domain {
  TreeSet<String> domain;

  Domain () {
    domain = new TreeSet<String>();
  }

  public boolean add (String string) {
    return domain.add(string);
  }

  public String toString () {
    String output = "";

    for (String string : domain) {
      output += "\n  " + string; 
    }   
   
    return "Domain(" + domain.size() + "):" + output + "\n"; 
  }
}
