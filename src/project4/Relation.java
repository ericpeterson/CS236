//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.Iterator;
import java.util.ArrayList;

public class Relation {
  // A relation has a name, schema and
  // set of tuples
  private String name;
  private Schema schema;
  private ArrayList<Tuple> tupleSet;

  Relation (Scheme scheme, FactList factList) {
    name = scheme.getName();
    schema = new Schema(scheme);
    tupleSet = new ArrayList<Tuple>();

    Iterator<Fact> iterator = factList.iterator();
    Tuple currentTuple = null;
    Fact currentFact = null;

    // iterate over the facts. Only work with facts
    // that match the name of this relation
    while (iterator.hasNext()) {
      currentFact = iterator.next();
      if (!currentFact.getName().equals(name)) {
        continue;
      }

      currentTuple = new Tuple(currentFact);

      // attempt to add the current tuple to the set
      if(tupleSet.contains(currentTuple)) {
        System.out.println("Warning: coult not add duplicate element " + currentTuple + " to the set.");
      } else {
        tupleSet.add(currentTuple);
      } 
    } 
  }

  public ArrayList<Tuple> getTuples() {
    return tupleSet;
  } 
  
  public Schema getSchema() {
    return schema;
  }

  public String getName () {
    return name;
  }
/*
  void add(Tuple tuple) {
    if(!tupleSet.add(tuple)) {
      System.out.println("Warning: could not add " + tuple + 
                         " to set.");
    }
  }*/

  public Relation rename(AAPairs aaPairs) {
    System.out.println("TODO: implement rename");
    return null;
  }
  public Relation select(Predicate predicate) {
    System.out.println("TODO: implement select");
    return null;
  } 
  public Relation project(Schema schema) {
    System.out.println("TODO: implement project");
    return null;
  }
  public Relation union(Relation relation) {
    System.out.println("TODO: implement union");
    return null;
  }
  public Relation join(Relation relation) {
    System.out.println("TODO: implement join");
    return null;
  }
}
