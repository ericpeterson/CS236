//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.HashSet;
import java.util.Iterator;

public class Database {
  // A database is a set of relations
  HashSet<Relation> relationSet;

  // Constructor
  // @param file String the name of the test input file
  Database (SchemeList schemes) {
    Iterator itr = schemes.iterator();
    Scheme currentScheme = null;
    Relation currentRelation = null;
    relationSet = new HashSet<Relation>();

    while(itr.hasNext()) {
      currentScheme = (Scheme)itr.next();
      currentRelation = new Relation(currentScheme);
      
      if (!relationSet.add(currentRelation)) {  
        System.out.println("Warning: could not add " + currentRelation + " to set.");
      }
    }
  }

  /*public Relation getRelation(String name) {
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
  }*/

  /*void storeRelation(Relation relation) {

    if (relationSet.contains(relation)) {
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
  }*/

  // Function called from the body method
  // @param file String name of test input file 
  public String evaluate(String file) {
    String output = "";
 
    DatalogProgram prog = new DatalogProgram(file);
    Relation relation;
 
    /*for(Query query: prog.getQueries()) {
      System.out.println(query);
      relation = new Relation(query);
    }*/

    return output;
  } 
}
