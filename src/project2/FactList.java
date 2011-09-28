//REQUIRED CLASS
package project2;

import project1.*;
import java.util.ArrayList;

public class FactList extends ArrayList<Fact> {
  FactList() {
    super();  
  }

  public boolean add(Fact fact) {
    return super.add(fact);
  }

  public int size() {
    return super.size();
  }

  public String toString () {
    String facts = "";
  
    for (Fact fact: this) {
      facts += "\n  " + fact;
    }

    return "Facts(" + this.size() + "):" + facts + "\n"; 
  }
}
