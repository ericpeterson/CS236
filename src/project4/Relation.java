//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.HashSet;

public class Relation {
  // A relation has a name, schema and
  // set of tuples
  private String name;
  private Schema schema;
  private HashSet<Tuple> tupleSet;

  Relation (Scheme scheme) {
    name = scheme.getName();
    schema = new Schema();
    tupleSet = new HashSet<Tuple>();
  }

/*  public HashSet<Tuple> getTuples() {
    return tupleSet;
  } 
  
  public Schema getSchema() {
    return schema;
  }

  public String getName () {
    return name;
  }

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
