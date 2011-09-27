//REQUIRED CLASS
package project2;

import project1.*;
import java.util.ArrayList;

public class QueryList extends ArrayList<Query> {
  QueryList() {
    super();  
  }

  public boolean add(Query query) {
    return super.add(query);
  }

  public int size() {
    return super.size();
  }

  public String toString () {
    String queries = "";
  
    for (Query query: this) {
      queries += "\n" + query;
    }

    return "Queries(" + this.size() + "):" + queries; 
  }
}
