//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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
    // Debugging
    /*itr = relationSet.iterator();
    while (itr.hasNext()) {
      currentRelation = (Relation)itr.next();
      TreeSet<Tuple> sortedTuples = currentRelation.sort(); 
      System.out.println("Relation");
      System.out.println("Name: " + currentRelation.getName());
      System.out.println("Attributes"); 
      System.out.println(currentRelation.getSchema().getAttributeSet()); 
      Iterator itr3 = currentRelation.getTuples().iterator();
      //Iterator itr3 = sortedTuples.iterator();
      while (itr3.hasNext()) {
        Tuple currentTuple = (Tuple)itr3.next();
        System.out.println(currentTuple.getAVList());
      }
    }*/
  }

  // evaluates the query list from the given datalog program. This function is
  // called from the body(String[]) function.
  // @param queryList QueryList The query list to evaluate.
  String evaluateQueryList(QueryList queryList) {
    String output = "";
    Relation copyOfRelation = null;
    ArrayList<Tuple> finalTuples = null;
    List<Parameter> queryParams = null;
    String currentParamName = "";
    String finalValue = "";
    int paramIndex = 0;

    for(Query query: queryList) {
    // System.out.println("new query: "  + query);
      queryParams = query.getParameters();
      for(Relation relation: relationSet) {
        if (relation.getName().equals(query.getName())) {
        //  System.out.println("We have a match " + query.getName());
      //System.out.println(relationSet); 
          copyOfRelation = new Relation(relation);
      //System.out.println(copyOfRelation); 
          //System.out.println("Relation: " + copyOfRelation);
          //System.out.println("Query: " + query);
          copyOfRelation.rename(queryParams); 
     // System.out.println("rename: " + relationSet); 
     // System.out.println("rename: " + copyOfRelation); 
          //System.out.println("Renamed Relation: " + copyOfRelation);
          ArrayList<Tuple> selected = copyOfRelation.select(queryParams);
     // System.out.println("select: " + relationSet); 
     // System.out.println("select: " + copyOfRelation); 
          //System.out.println("Selected Tuples: " + selected);
          finalTuples = copyOfRelation.project(selected, queryParams);
     // System.out.println("project: " + relationSet); 
     // System.out.println("project: " + copyOfRelation); 
          //System.out.println("Final Tuples: " + finalTuples);
        }
      }
      output += query + " ";
      if (finalTuples.isEmpty()) {
        output += "No\n";
      } else {
        output += "Yes(" + finalTuples.size() + ")\n";
        for (Tuple tuple: finalTuples) {
          output += "  ";
          for (Parameter parameter: queryParams) {
            currentParamName = parameter.getName();
            if (currentParamName != null) {
              finalValue = tuple.getAVList().get(paramIndex).getValue();
              output += currentParamName + "='" + finalValue + "', "; 
            }
          }
          paramIndex = 0;
          output = output.substring(0, output.length()-2);
          output += "\n";
        }
      }
    }

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
