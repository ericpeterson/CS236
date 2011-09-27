//REQUIRED CLASS
package project2;

import project1.*;
import java.util.ArrayList;

public class SchemeList extends ArrayList<Scheme> {
  SchemeList() {
    super();  
  }

  public boolean add(Scheme scheme) {
    return super.add(scheme);
  }

  public int size() {
    return super.size();
  }

  public String toString () {
    String schemes = "";
  
    for (Scheme scheme: this) {
      schemes += "\n" + scheme;
    }

    return "Schemes(" + this.size() + "):" + schemes; 
  }
}
