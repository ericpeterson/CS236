//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Relation {
  // A relation has a name, schema and
  // set of tuples
  private String name;
  private Schema schema;
  private ArrayList<Tuple> tupleSet;

  // copy constructor
  Relation(Relation toBeCopiedRelation) {
    name = toBeCopiedRelation.getName();
    schema = toBeCopiedRelation.getSchema();
    tupleSet = new ArrayList<Tuple>();
    ArrayList<Tuple> toBeCopiedTupleSet = toBeCopiedRelation.getTupleSet(); 
    ArrayList<Parameter> toBeCopiedAVList; 
    ArrayList<Parameter> newAVList = new ArrayList<Parameter>(); 
    Tuple newTuple;

    for (Tuple tuple: toBeCopiedTupleSet) { 
      toBeCopiedAVList = tuple.getAVList();
      for (Parameter parameter: toBeCopiedAVList) {
        newAVList.add(parameter);
      }
      newTuple = new Tuple(newAVList);
      tupleSet.add(newTuple); 
    }
  }

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
        System.out.println("Warning: could not add duplicate element " + currentTuple + " to the set.");
      } else {
        tupleSet.add(currentTuple);
      } 
    }
  }

  public ArrayList<Tuple> getTupleSet() {
    return tupleSet;
  }

  public TreeSet<Tuple> sort() {
    return new TreeSet<Tuple>(this.getTupleSet()); 
  }

  public ArrayList<Tuple> getTuples() {
    return tupleSet;
  } 
  
  public Schema getSchema() {
    return schema;
  }

  public void setSchema(Schema newSchema) {
    this.schema = newSchema;
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

  public void rename(List<Parameter> parameterList) {
    ArrayList<Attribute> currentSchema = 
      this.getSchema().getAttributeSet();
    ArrayList<Attribute> newAttributeSet =
      new ArrayList<Attribute>();
    Schema newSchema = null; 
    Attribute currentAttribute = null;
    String wrapInAttribute = "";
    int index = 0;
    for(Parameter parameter: parameterList) {
      wrapInAttribute = currentSchema.get(index++).getName();
      if (null != parameter.getName()) {
        wrapInAttribute = parameter.getName();
      }

      currentAttribute = 
        new Attribute(wrapInAttribute);
      newAttributeSet.add(currentAttribute); 
    }

    newSchema = new Schema(newAttributeSet);
    this.setSchema(newSchema);
  }

  public ArrayList<Tuple> select(List<Parameter> parameterList) {
    ArrayList<Tuple> selectedTuples = new ArrayList<Tuple>();
    int index = 0;
    String currentValue = "";
    String selectName = "";
    String selectValue = "";
    for (Parameter parameter: parameterList) {
      selectName = parameter.getName();
      selectValue = parameter.getValue();

      //System.out.println("Selected name: " + selectName);
      if (null == selectName) {
        for(Tuple tuple: tupleSet) {
          currentValue = tuple.getAVList().get(index).getValue();
          //System.out.println("Current value: " + currentValue);
          //System.out.println("Selected value: " + selectValue);
          if (currentValue.equals(selectValue)) {
            //System.out.println("Add " + tuple);
            selectedTuples.add(tuple); 
          } 
        //System.out.println();        
        }
      }

      index++;
      //System.out.println();
      //System.out.println();
    } 

    return selectedTuples;
  }
 
  public ArrayList<Tuple> project(ArrayList<Tuple> selectedTuples,
    List<Parameter> parameterList) {
    int index = 0;
    String currentParamName = "";
    ArrayList<Tuple> projectedTuples = new ArrayList<Tuple>();
 
    // copy selectedTuples into projectedTuples
    for(Tuple tuple: selectedTuples) {
      projectedTuples.add(tuple);
    }
System.out.println("inside: " + selectedTuples);
    for(Parameter parameter: parameterList) {
      currentParamName = parameter.getName();
      //System.out.println("current p name: " + currentParamName);
      //System.out.println(index);
      if (currentParamName == null) {
        //System.out.println("Removing " + parameter.getValue() + " from list");
        for (Tuple tuple: projectedTuples) {
          //System.out.println("Tuple before " + tuple);
          tuple.getAVList().remove(index);
          //System.out.println("Tuple after " + tuple);
        } 
      }
      index++;
    } 

    return projectedTuples;
  }

  /*public Relation union(Relation relation) {
    System.out.println("TODO: implement union");
    return null;
  }
  public Relation join(Relation relation) {
    System.out.println("TODO: implement join");
    return null;
  }*/
  
  public String toString() {
    return "(" + this.getName() + ", " + this.getSchema() + ", " + this.getTupleSet() + ")"; 
  }
}
