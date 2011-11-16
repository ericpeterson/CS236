//REQUIRED CLASS
package project2;

import project1.*;

public class SimplePredicate {
  // Constructor
  SimplePredicate () {
    
  }

  public boolean eval(Query) {
    return database.getRelation(query.getName()).
          select(new Predicate(query)).
            rename(new AAPairs(query)).
              project(new Schema(query));
  }
}
