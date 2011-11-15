//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.HashSet;

public class Database {
  // A database is a set of relations
  HashSet<Relation> relationSet;

  // Constructor
  // @param file String the name of the test input file
  Database () {
    relationSet = new HashSet<Relation>();
  }

  // Function called from the body method
  // @param file String name of test input file 
  public String evaluate(String file) {
    String output = "";
 
    System.out.println("Eric");
    System.out.println(file);
    DatalogProgram prog = new DatalogProgram(file);
    
    for(Scheme scheme: prog.getSchemes()) {
      output += scheme.getName() + "\n" + 
                scheme.getParameters() + "\n";
    }

    return output;
  } 
}
