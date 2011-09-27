//REQUIRED CLASS
package project2;

import project1.*;
import java.util.ArrayList;

public class RuleList extends ArrayList<Rule>{
  RuleList() {
    super();  
  }

  public boolean add(Rule rule) {
    return super.add(rule);
  }

  public int size() {
    return super.size();
  }

  public String toString () {
    String rules = "";
  
    for (Rule rule: this) {
      rules += "\n" + rule;
    }

    return "Rules(" + this.size() + "):" + rules; 
  }
}
