package project2;

import project1.*;
import java.util.ArrayList;

public class Parser {
  private SchemeList schemeList;
  private FactList factList;
  private RuleList ruleList;
  private QueryList queryList;
  private Domain domain;

  private ArrayList<Token> tokenList;

  private Rule rule;

  Parser () {
    schemeList = new SchemeList();
    factList = new FactList();
    ruleList = new RuleList();
    queryList = new QueryList();
    domain = new Domain();

    tokenList = new ArrayList<Token>();

    this.rule = new Rule();
  }

  private void ignoreComments() {
    Token currentToken = tokenList.get(0);
    // Ignore comments
    while (currentToken.getType() == TokenType.COMMENT) {
      tokenList.remove(0);
      currentToken = tokenList.get(0);
    }
  }

  public DatalogProgram parseDatalogProgram (LexicalAnalyzer lex) throws ParseError {
    lex.analyze();
    Token currentToken;

    tokenList = lex.getTokenList();
    currentToken = tokenList.get(0);
  
    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
 
    // make sure the Schemes are first
    if(currentToken.getType() != TokenType.SCHEMES) {
      throw new ParseError(currentToken); 
    }
    tokenList.remove(0);
    currentToken = tokenList.get(0);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 

    if(currentToken.getType() != TokenType.COLON) {
      throw new ParseError(currentToken); 
    }
    tokenList.remove(0);

    // parse SchemeList
    schemeList = this.parseSchemeList();
    currentToken = tokenList.get(0);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    // make sure Facts comes next
    if(currentToken.getType() != TokenType.FACTS) {
      throw new ParseError(currentToken);
    }
    tokenList.remove(0);
    currentToken = tokenList.get(0);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    if(currentToken.getType() != TokenType.COLON) {
      throw new ParseError(currentToken);
    }
    tokenList.remove(0);

    // parse FactList
    factList = this.parseFactList(); 
    currentToken = tokenList.get(0);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    // make sure Rules comes next
    if(currentToken.getType() != TokenType.RULES) {
      throw new ParseError(currentToken);
    }
    tokenList.remove(0);
    currentToken = tokenList.get(0);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    if(currentToken.getType() != TokenType.COLON) {
      throw new ParseError(currentToken);
    }
    tokenList.remove(0); 
 
    // parse RuleList
    ruleList = this.parseRuleList(); 
    currentToken = tokenList.get(0);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    // make sure Queries comes last
    if(currentToken.getType() != TokenType.QUERIES) {
      throw new ParseError(currentToken);
    }
    tokenList.remove(0);
    currentToken = tokenList.get(0);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    if(currentToken.getType() != TokenType.COLON) {
      throw new ParseError(currentToken);
    }
    tokenList.remove(0); 
 
    // parse QueryList
    queryList = this.parseQueryList();

    return new DatalogProgram(schemeList, factList, ruleList, queryList, domain);
  }

  public SchemeList parseSchemeList() throws ParseError {
    SchemeList schemel = new SchemeList();
    Token currentToken = tokenList.get(0);
    boolean inSchemeListTail = true;

    do {
      Scheme scheme = new Scheme();
      boolean inAttributeListTail = true;

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 

      // <Identifier>
      if (currentToken.getType() != TokenType.ID) { 
        throw new ParseError(currentToken);
      }
      scheme.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      // ( 
      if (currentToken.getType() != TokenType.LEFT_PAREN) {
        throw new ParseError(currentToken);
      }
      scheme.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      // <Identifier>
      if(currentToken.getType() != TokenType.ID) {
        throw new ParseError(currentToken);
      }
      scheme.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      // <Attribute List Tail>
      while (inAttributeListTail) {
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        // ,
        if (currentToken.getType() != TokenType.COMMA) {
          if (currentToken.getType() != TokenType.RIGHT_PAREN) {
            throw new ParseError(currentToken); 
          }
          scheme.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);

          // break out of loop
          inAttributeListTail = false;
          break;
        }
        scheme.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);

        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        // <Identifier>
        if (currentToken.getType() != TokenType.ID) {
            throw new ParseError(currentToken); 
        }
        scheme.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);
      } 

      schemel.add(scheme);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() == TokenType.FACTS) {
        inSchemeListTail = false;
      }
    } while (inSchemeListTail);

    return schemel;
  }

  public FactList parseFactList() throws ParseError {
    FactList factl = new FactList();
    Token currentToken = tokenList.get(0);
    boolean inFactList = true;
    
    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    // empty fact list
    if (currentToken.getType() == TokenType.RULES) {
      return factl;
    }
  
    // <FactList>
    do {
      Fact facts = new Fact();
      boolean inConstantListTail = true;
 
      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      // <Identifier> 
      if (currentToken.getType() != TokenType.ID) {
        throw new ParseError(currentToken);
      }
      facts.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      // (
      if (currentToken.getType() != TokenType.LEFT_PAREN) {
        throw new ParseError(currentToken);
      }
      facts.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      // String
      if (currentToken.getType() != TokenType.STRING) {        
        throw new ParseError(currentToken);
      }
      facts.add(currentToken);
      domain.add(currentToken.getValue());
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      // <Constant List Tail>
      while (inConstantListTail) {
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        // ,
        if (currentToken.getType() != TokenType.COMMA) {
          if (currentToken.getType() != TokenType.RIGHT_PAREN) {
            throw new ParseError(currentToken);
          }
          facts.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);
          
          this.ignoreComments(); 
          currentToken = tokenList.get(0); 
          if (currentToken.getType() != TokenType.PERIOD) {
            throw new ParseError(currentToken);
          }
          facts.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);
      
          inConstantListTail = false;
          break;
        }
        facts.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);
        
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        // String
        if (currentToken.getType() != TokenType.STRING) {        
          throw new ParseError(currentToken);
        }
        facts.add(currentToken);
        domain.add(currentToken.getValue());
        tokenList.remove(0);
        currentToken = tokenList.get(0);
      }

      factl.add(facts);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() == TokenType.RULES) {
        inFactList = false;
      }
    } while (inFactList);
   
    return factl;
  }

  public RuleList parseRuleList() throws ParseError {
    RuleList rulel = new RuleList();
    Token currentToken = tokenList.get(0);
    boolean inRuleList = true;

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    // empty RuleList
    if (currentToken.getType() == TokenType.QUERIES) {
      return rulel;
    }
  
    do {
      this.rule = new Rule();
      boolean inArgumentListTail = true;
   
      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      // <Simple Predicate> (ID)
      if (currentToken.getType() != TokenType.ID) {
        throw new ParseError(currentToken);
      }     
      this.rule.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      // (
      if (currentToken.getType() != TokenType.LEFT_PAREN) {
        throw new ParseError(currentToken);
      }
      this.rule.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      // String or <Identifier>
      if (currentToken.getType() != TokenType.STRING) {
        if (currentToken.getType() != TokenType.ID) {
          throw new ParseError(currentToken);
        }
      }
      this.rule.add(currentToken);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() == TokenType.STRING){domain.add(currentToken.getValue());}
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      // <Argument List Tail>
      while (inArgumentListTail) { 
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        // ,
        if (currentToken.getType() != TokenType.COMMA) {
          if(currentToken.getType() != TokenType.RIGHT_PAREN) {
            throw new ParseError(currentToken);
          }
          this.rule.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);
          
          inArgumentListTail = false;
          break;
        }
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);

        // String or <Identifier>
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.STRING) {
          if (currentToken.getType() != TokenType.ID) {
            throw new ParseError(currentToken);
          }
        }
        this.rule.add(currentToken);

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() == TokenType.STRING){domain.add(currentToken.getValue());}
        tokenList.remove(0);
        currentToken = tokenList.get(0);
      } 

      // :-
      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() != TokenType.COLON_DASH) {
        throw new ParseError(currentToken);
      }
      this.rule.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);
 

      boolean inPredicateList = true;

      while (inPredicateList) {
        // <Predicate>
        // <Identifier>
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if(currentToken.getType() != TokenType.ID) {
          throw new ParseError(currentToken);
        }
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);

        // (
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.LEFT_PAREN) {
          throw new ParseError(currentToken);
        }
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);

        this.doParameter();
        currentToken = tokenList.get(0);
   
        boolean inParameterList = true;
   
        while (inParameterList) {
          // ,
          this.ignoreComments(); 
          currentToken = tokenList.get(0); 
          if (currentToken.getType() != TokenType.COMMA) {
            if (currentToken.getType() != TokenType.RIGHT_PAREN) {
              throw new ParseError(currentToken);
            } // handle )
            this.rule.add(currentToken);
            tokenList.remove(0);
            currentToken = tokenList.get(0);

            inParameterList = false;
            break;
          }
          this.rule.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);

          this.doParameter();
          currentToken = tokenList.get(0);
        }

        // .
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.PERIOD) {
          // ,
          if (currentToken.getType() != TokenType.COMMA) {
            throw new ParseError(currentToken);
          }
          this.rule.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);
           
          continue;
        }
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);
   
        rulel.add(this.rule);
        inPredicateList = false;
        break;
      }

      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() == TokenType.QUERIES) {
        inRuleList = false;
        break;
      }
    } while (inRuleList); // outer loop

    return rulel;
  }

  // returns false to break out of parameter list loop 
  private void doParameter () throws ParseError {
    Token currentToken = tokenList.get(0);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    // String | <Identifier>
    if (currentToken.getType() != TokenType.STRING) {
      if (currentToken.getType() != TokenType.ID) {
        if (currentToken.getType() != TokenType.LT &&
            currentToken.getType() != TokenType.LE &&
            currentToken.getType() != TokenType.EQ &&
            currentToken.getType() != TokenType.NE &&
            currentToken.getType() != TokenType.GE &&
            currentToken.getType() != TokenType.GT) {
          if (currentToken.getType() != TokenType.LEFT_PAREN) { 
            throw new ParseError(currentToken);
          } // handle Expression
          this.rule.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);
          
          this.doParameter();
          currentToken = tokenList.get(0);
  
          this.ignoreComments(); 
          currentToken = tokenList.get(0); 
          // + | *
          if(currentToken.getType() != TokenType.ADD) {
            if (currentToken.getType() != TokenType.MULTIPLY) {
              throw new ParseError(currentToken);
            }
          } 
          this.rule.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);

          this.doParameter();
          currentToken = tokenList.get(0);

          // )
          this.ignoreComments(); 
          currentToken = tokenList.get(0); 
          if (currentToken.getType() != TokenType.RIGHT_PAREN) {
            throw new ParseError(currentToken);
          }
          this.rule.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);

          return;
        } // handle Comparator 
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);
    
        // (
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.LEFT_PAREN) {
          throw new ParseError(currentToken);
        } 
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);
 
        // String | <Identifier>
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.STRING) {
          if (currentToken.getType() != TokenType.ID) {
            throw new ParseError(currentToken);
          }
        }
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);

        // ,
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.COMMA) {
          throw new ParseError(currentToken);
        }
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);
 
        // String | <Identifier>
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.STRING) {
          if (currentToken.getType() != TokenType.ID) {
            throw new ParseError(currentToken);
          }
        }
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);
      
        // )
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.RIGHT_PAREN) {
          throw new ParseError(currentToken);
        }
        this.rule.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);

        return;
      }
    } // handle String or Identifier
    this.rule.add(currentToken);

    this.ignoreComments(); 
    currentToken = tokenList.get(0); 
    if (currentToken.getType() == TokenType.STRING){domain.add(currentToken.getValue());}
    tokenList.remove(0);
    currentToken = tokenList.get(0);
  
    return;
  }

  public QueryList parseQueryList() throws ParseError {
    QueryList queryl = new QueryList();
    Token currentToken = tokenList.get(0);
    boolean inQueryList = true;

    do {
      Query query = new Query();
      boolean inArgumentListTail = true;

      // <Identifier>
      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() != TokenType.ID) {
        throw new ParseError(currentToken);
      } 
      query.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      // (
      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() != TokenType.LEFT_PAREN) {
        throw new ParseError(currentToken);
      } 
      query.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      // String or <Identifier>
      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if(currentToken.getType() != TokenType.STRING) {
        if (currentToken.getType() != TokenType.ID) {
          throw new ParseError(currentToken);
        }
      }
      query.add(currentToken);
      if (currentToken.getType() == TokenType.STRING){domain.add(currentToken.getValue());}
      tokenList.remove(0);
      currentToken = tokenList.get(0);

      // <Argument Tail List>
      while (inArgumentListTail) {
        //,
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if (currentToken.getType() != TokenType.COMMA) {
          if (currentToken.getType() != TokenType.RIGHT_PAREN) {
            throw new ParseError(currentToken);
          }
          query.add(currentToken);
          tokenList.remove(0);
          currentToken = tokenList.get(0);

          inArgumentListTail = false;
          break;
        } 
        query.add(currentToken);
        tokenList.remove(0);
        currentToken = tokenList.get(0);
        
        // String or <Identifier>
        this.ignoreComments(); 
        currentToken = tokenList.get(0); 
        if(currentToken.getType() != TokenType.STRING) {
          if (currentToken.getType() != TokenType.ID) {
            throw new ParseError(currentToken);
          }
        }
        query.add(currentToken);
        if (currentToken.getType() == TokenType.STRING){domain.add(currentToken.getValue());}
        tokenList.remove(0);
        currentToken = tokenList.get(0);
      }
    
      // ?
      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if(currentToken.getType() != TokenType.Q_MARK) {
        throw new ParseError(currentToken);
      } 
      query.add(currentToken);
      tokenList.remove(0);
      currentToken = tokenList.get(0);
      
      queryl.add(query);

      
      this.ignoreComments(); 
      currentToken = tokenList.get(0); 
      if (currentToken.getType() == TokenType.EOF) {
        inQueryList = false;
      }
    } while (inQueryList);

    return queryl;
  }
}
