//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
//import project2.*;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Collections;

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
    tupleSet = deepCopyArrayList(toBeCopiedRelation.getTupleSet());
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

  public static ArrayList<Tuple> deepCopyArrayList (ArrayList<Tuple> toBeCopied) {
    ArrayList<Parameter> toBeCopiedAVList;
    ArrayList<Parameter> copiedAVList = new ArrayList<Parameter>();
    Parameter[] p;
    Parameter[] toBeCopiedArray = null;
    Parameter[] copiedArray = null;
    Tuple copiedTuple;
    ArrayList<Tuple> copied = new ArrayList<Tuple>();

    for (Tuple tuple: toBeCopied) {
      copiedAVList = new ArrayList<Parameter>();
      toBeCopiedAVList = tuple.getAVList();
      p = new Parameter[toBeCopiedAVList.size()];
      toBeCopiedArray = toBeCopiedAVList.toArray(p);
      copiedArray = new Parameter[p.length];

      for (int index = 0; index < toBeCopiedArray.length; index++) {
        copiedArray[index] = toBeCopiedArray[index];
        copiedAVList.add(copiedArray[index]);
      }

      copiedTuple = new Tuple(copiedAVList);
      copied.add(copiedTuple);
      copiedAVList = null;
    }
    
    return copied;
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
    boolean allVariables = true;
    boolean addTuple = true;

    for(Tuple tuple: tupleSet) {
      int listSize = parameterList.size();
      addTuple = true;
      for (index = 0; index < listSize; index++) {
        currentValue = tuple.getAVList().get(index).getValue();
        selectName = parameterList.get(index).getName();
        selectValue = parameterList.get(index).getValue();

        if (null == selectName) {
          allVariables = false; 
          if (!currentValue.equals(selectValue)) {
            addTuple = false;
            break; 
          } 
        }
      }
  
      if (addTuple) {
        selectedTuples.add(tuple);
      } 
    }

    /*for (Parameter parameter: parameterList) {
      selectName = parameter.getName();
      selectValue = parameter.getValue();

      //System.out.println("Selected name: " + selectName);
      if (null == selectName) {
        allVariables = false;
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
    }*/ 

    if (allVariables) {
      selectedTuples = tupleSet;  
    }

    return selectedTuples;
  }
 
  public ArrayList<Tuple> project(ArrayList<Tuple> selectedTuples,
    List<Parameter> parameterList) {
    int index = 0;
    String currentParamName = "";
    ArrayList<Tuple> projectedTuples = new ArrayList<Tuple>();
    boolean allVariables = true;
 
    // copy selectedTuples into projectedTuples
    for(Tuple tuple: selectedTuples) {
      projectedTuples.add(tuple);
    }

    for(Parameter parameter: parameterList) {
      currentParamName = parameter.getName();
      if (currentParamName == null) {
        allVariables = false;
        for (Tuple tuple: projectedTuples) {
          if (tuple.getAVList().size() != 0) {
            tuple.getAVList().remove(index);
          } else {
            continue;
          }
        } 
      } else {
        index++;
      }
    }

    if (allVariables) {
      projectedTuples = new ArrayList<Tuple>();
      ArrayList<String> keepTrackName = new ArrayList<String>();
      ArrayList<String> keepTrackValue = new ArrayList<String>();
      String currentValue = "";
      boolean addTuple = true;
      for(Tuple tuple: selectedTuples) {
        keepTrackName = new ArrayList<String>();
        keepTrackValue = new ArrayList<String>();
        addTuple = true;
        int listSize = parameterList.size();
//System.out.println("tuple: " + tuple);
//System.out.println("Query list: " + parameterList);
        for (index = 0; index < listSize; index++) { 
          currentParamName = parameterList.get(index).getName();
          currentValue = tuple.getAVList().get(index).getValue();
          //System.out.println("Name: " + currentParamName);
          //System.out.println("Value: " + currentValue);
          if (keepTrackName.contains(currentParamName)) {
            //System.out.println("We've seen " + currentParamName + " before.");
            if (!keepTrackValue.get(keepTrackName.indexOf(currentParamName)).equals(currentValue)) {
              //System.out.println("Throw out " + tuple); 
              addTuple = false;
              break;
            }
          } else {
            keepTrackName.add(currentParamName);
            keepTrackValue.add(currentValue);
          }
        }
        if (addTuple) {
          //System.out.println("This tuple is good to add: " + tuple);
          projectedTuples.add(tuple);
        }
        //System.out.println();
      }
    } 

    return projectedTuples;
  }

  public void union (Relation relation) {
    System.out.println("TODO: implement union");
  }

  public void join (Relation relation) {
    System.out.println("TODO: implement join");
  }
  
  public String toString() {
    return "(" + this.getName() + ", " + this.getSchema() + ", " + this.getTupleSet() + ")"; 
  }
}
