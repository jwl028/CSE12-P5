/**
 * A class representing the <operation> nonterminal symbol in an abstract 
 * syntax tree. This class has no public constructors; the public interface
 * for creating an instance of the class is the static parse(String s) 
 * factory method.
 * @author Jimmy Li cs12sdm
 */

public class Operation extends ASTNode
{
  private char symbol;

  /**
   * Private constructor - creates child Term node
   */

  private Operation(Term term)
  {
    super(term);
  }

  /**
   * Private constructor - creates children Operation and Term nodes and
   * initializes instance variable
   */

  private Operation(Operation operation, Term term, char c)
  {
    super(operation, term);
    symbol = c;
  }

  /**
   * A factory method that parses a String accoring to the BNF definition 
   * for <operation>. The BNF definition is: <operation> := <term> | 
   * <operation> "+" <term> | <operation> "-" <term>
   * @param s the String to parse
   * @return an Operation object that is the root of an abstract syntax 
   * (sub)tree resulting from the parse, or null if the String cannot be
   * parsed as an <operation>.
   */

  public static Operation parse(java.lang.String s)
  {
    String str = s.trim();
    //Term
    Term result = Term.parse(str);
    if(result != null) {
      return new Operation(result);
    }
    //Operation + Term
    for(int i = 0; i < str.length()-1; i++) {
      //Check each combination of strings split between plus 
      if(str.charAt(i) == '+') {
        String s1 = str.substring(0, i);
        String s2 = str.substring(i+1);
        Operation result1 = Operation.parse(s1);
        Term result2 = Term.parse(s2);
        if(result1 != null && result2 != null) {
          return new Operation(result1,result2,'+');
        }
      }
    }
    //Operation - Term
    for(int j = 0; j < str.length()-1; j++) {
      //Check each combination of strings split between minus
      if(str.charAt(j) == '-') {
        String s1 = str.substring(0,j);
        String s2 = str.substring(j+1);
        Operation result3 = Operation.parse(s1);
        Term result4 = Term.parse(s2);
        if(result3 != null && result4 != null) {
          return new Operation(result3, result4, '-');
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
    if(arity() == 2 && symbol == '+') {
      return getChild(0).eval(symtab) + getChild(1).eval(symtab);
    }
    else if(arity() == 2 && symbol == '-') {
      return getChild(0).eval(symtab) - getChild(1).eval(symtab);
    }
    else if(arity() == 1) {
      return getChild(0).eval(symtab);
    }
    else {
      throw new IllegalStateException();
    }
  }
}
