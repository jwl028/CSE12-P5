/**
 * A class representing the <term> nonterminal symbol in an abstract 
 * syntax tree. This class has no public constructors; the public 
 * interface for creating an instance of the class is the static 
 * parse(String s) factory method.
 * @author Jimmy Li cs12sdm
 */

public class Term extends ASTNode
{
  private char symbol;
 
  /**
   * Private constructor - creates a child Factor node
   */

  private Term(Factor factor)
  {
    super(factor);
  }
 
  /**
   * Private constructor - creates children Term and Factor nodes and 
   * initializes instance variable
   */

  private Term(Term term, Factor factor, char c)
  {
    super(term, factor);
    symbol = c;
  }
  
  /**
   * A factory method that parses a String accoring to the BNF definition
   * for <term>. The BNF definition is: <term> := <factor> | <term> "*"
   * <factor> | <term> "/" <factor>
   * @param s the String to parse
   * @return a Term object that is the root of an abstract syntax (sub)tree
   * resulting from the parse, or null if the String cannot be parsed as 
   * a <term>.
   */

  public static Term parse(java.lang.String s)
  {
    String str = s.trim();
    //Factor
    Factor result = Factor.parse(str);
    if(result != null) {
      return new Term(result);
    }
    //Term * Factor
    for(int i = 0; i < str.length()-1; i++) {
      //Checks various substrings of *
      if(str.charAt(i) == '*') {
        String s1 = str.substring(0, i);
        String s2 = str.substring(i+1);
        Term result1 = Term.parse(s1);
        Factor result2 = Factor.parse(s2);
        if(result1 != null && result2 != null) {
          return new Term(result1,result2,'*');
        }
      }
    }
    //Term / Factor
    for(int j = 0; j < str.length()-1; j++) {
      //Checks various substrings of /
      if(str.charAt(j) == '/') {
        String s1 = str.substring(0,j);
        String s2 = str.substring(j+1);
        Term result3 = Term.parse(s1);
        Factor result4 = Factor.parse(s2);
        if(result3 != null && result4 != null) {
          return new Term(result3, result4, '/');
        }
      }
    }
    return null;
  }

  /**
   * Evaluate the abstract syntax (sub)tree that is rooted at this ASTNode 
   * in the context of the given symbol table, and return the result.
   * @param symtab - A map from variable identifiers to values, to use as 
   * a symbol table in the evaluation.
   * @return the double value that is the result of evaluating the abstract 
   * syntax (sub)tree rooted at this ASTNode.
   */

  public double eval(java.util.Map<java.lang.String,java.lang.Double> symtab)
  {
    if(arity() == 2 && symbol == '*') {
      return getChild(0).eval(symtab) * getChild(1).eval(symtab);
    } 
    else if(arity() == 2 && symbol == '/') {
      return getChild(0).eval(symtab) / getChild(1).eval(symtab);
    }
    else if(arity() == 1) {
      return getChild(0).eval(symtab);
    }
    else {
      throw new IllegalStateException();
    }
  }
}
