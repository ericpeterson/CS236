//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.Iterator;
import java.util.ArrayList;

public class Database {
  // A database is a set of relations
  ArrayList<Relation> relationSet;

  // Constructor
  // @param datalogProgram DatalogProgram The datalog program to be converted
  //        into a database.
  Database (DatalogProgram datalogProgram) {
    relationSet = new ArrayList<Relation>(); 
     
    SchemeList schemeList = datalogProgram.getSchemes();
    FactList factList = datalogProgram.getFacts();
    Iterator itr = schemeList.iterator();
    Relation currentRelation = null;
    Scheme currentScheme = null;

    while(itr.hasNext()) {
      currentScheme = (Scheme)itr.next();
      currentRelation = new Relation(currentScheme, factList);
      if(relationSet.contains(currentRelation)) {
        System.out.println("Warning: could not add duplicate element " + currentRelation + " to set."); 
      } else {
        relationSet.add(currentRelation);
      } 
    }

    itr = relationSet.iterator();
    while (itr.hasNext()) {
      currentRelation = (Relation)itr.next();

      System.out.println("Relation");
      System.out.println("Name: " + currentRelation.getName());
      System.out.println("Attributes"); 
      System.out.println(currentRelation.getSchema().getAttributeSet()); 
      Iterator itr3 = currentRelation.getTuples().iterator();
      while (itr3.hasNext()) {
        Tuple currentTuple = (Tuple)itr3.next();
        System.out.println(currentTuple.getAVList());
      }
    }
  }

  // evaluates the query list from the given datalog program. This function is
  // called from the body(String[]) function.
  // @param queryList QueryList The query list to evaluate.
  String evaluateQueryList(QueryList queryList) {
    String output = "";

    return output;
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
  /*public String evaluate(String file) {
    String output = "";
 
    DatalogProgram prog = new DatalogProgram(file);
    Relation relation;*/
 
    /*for(Query query: prog.getQueries()) {
      System.out.println(query);
      relation = new Relation(query);
    }*/

    /*return output;
  } */
}
