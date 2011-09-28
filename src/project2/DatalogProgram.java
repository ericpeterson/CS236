//REQUIRED CLASS
package project2;

import project1.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class DatalogProgram extends NT_Node {
  private LexicalAnalyzer lex;
  private SchemeList schemeList;
  private FactList factList;
  private RuleList ruleList;
  private QueryList queryList;
  private Domain domain;
  

  public DatalogProgram (String file) {
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      lex = new LexicalAnalyzer(reader);     
    } catch (FileNotFoundException exc) {
      System.out.println(exc);
    } catch (IOException exc) {
      System.out.println(exc);
    } catch (Exception exc) {
      System.out.println(exc);
    }

    schemeList = new SchemeList();
    factList = new FactList();
    ruleList = new RuleList();
    queryList = new QueryList();
    domain = new Domain();
  }

  DatalogProgram(SchemeList schemeList, FactList factList, RuleList ruleList, QueryList queryList, Domain domain) {
    this.schemeList = schemeList;
    this.factList = factList;
    this.ruleList = ruleList;
    this.queryList = queryList;
    this.domain = domain; 
  }

  // this is the method that gets the ball rolling
  public DatalogProgram parse () throws ParseError {
    Parser parser = new Parser();
    DatalogProgram datalogProgram = parser.parseDatalogProgram(lex); 
    
    return datalogProgram;
  }

  public String toString () {
    String output = "";

    output = schemeList.toString() + factList + ruleList + queryList + domain;

    return output;
  }
}
