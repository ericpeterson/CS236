//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.HashSet;
import java.util.ArrayList;

public class Database {
  // A database is a set of relations
  HashSet<Relation> relationSet;

  // Constructor
  // @param file String the name of the test input file
  Database (ArrayList<Scheme> schemes) {
    Iterator itr = arrayList.iterator();

    while(itr.hasNext()) {
      System.out.println(itr.next());
    }
    relationSet = new HashSet<Relation>();
  }

  public Relation getRelation(String name) {
    Iterator itr = relationSet.iterator();
    Relation ret_relation = null;
    Relation current_relation = null;

    while (itr.hasNext()) {
      current_relation = itr.next();
  
      if (current_relation.getName().equals(name)) {
        ret_relation = current_relation;
        break;
      }
    }
    return ret_relation;
  }

  void storeRelation(Relation relation) {

    if (relationSet.contains(relation) {
      System.out.println("Replacing " + relation.name);
      relationSet.remove(relation);
    }

    if(relationSet.add(relation)) {
      System.out.println("Relation " + relation.name +
                         " added to set.");
    } else {
      System.out.println("Warning: Relation " + relation.name +
                         "was not added to set.");
    } 
  }

  // Function called from the body method
  // @param file String name of test input file 
  public String evaluate(String file) {
    String output = "";
 
    DatalogProgram prog = new DatalogProgram(file);
    Relation relation;
 
    for(Query query: prog.getQueries()) {
      System.out.println(query);
      relation = new Relation(query);
    }

    return output;
  } 
}
