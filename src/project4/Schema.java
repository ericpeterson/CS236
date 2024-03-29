//REQUIRED CLASS
package project4;

import project1.*;
import datalogProgram.*;
import java.util.ArrayList;
import java.util.Iterator;
//import project2.*;

import java.util.TreeSet;
public class Schema {
  // A Schema has an ordered set of attributes
  private ArrayList<Attribute> attributeSet;
/*  TreeSet<Attribute> attributeSet;
  Scheme originalScheme;*/
  // Constructor
  Schema (Scheme scheme) {
    attributeSet = new ArrayList<Attribute>();
    Iterator<Parameter> iterator = scheme.getParameters().iterator();
    Attribute currentAttribute = null;
    while (iterator.hasNext()) {
      currentAttribute = new Attribute(iterator.next().getName());

      if (attributeSet.contains(currentAttribute)) {
        System.out.println("Warning: could not add duplicate element " + currentAttribute + " to set.");
      } else {
        attributeSet.add(currentAttribute);
      } 
    }
    /*originalScheme = scheme;*/
  }

  // Alternate constructor
  Schema(ArrayList<Attribute> newAttributeSet) {
    attributeSet = newAttributeSet;
  }

  public ArrayList<Attribute> getAttributeSet () {
    return attributeSet;
  }

  public String toString() {
    return attributeSet.toString(); 
  }

  /*public void changeAttributeName(Attribute oldName, Attribute newName) {
    
  }

  public void restrict(Schema restricted) {
    
  }

  public Attribute getAttribute(int location) {
    // Get attribute at location in original scheme    
    return null; 
  }*/
}
