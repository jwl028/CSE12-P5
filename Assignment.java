/**
 * A class representing the <assignment> nonterminal symbol in an abstract 
 * syntax tree. This class has no public constructors; the public interface
 * for creating an instance of the class is the static parse(String s) factory
 * method.
 * @author Jimmy Li cs12sdm
 */

public class Assignment extends ASTNode
{
  private char symbol;

  /**
   * Private constructor - creates children Identifier and Expr nodes. Also
   * initializes instance variable.
   */

  private Assignment(Identifier result1, Expr result2, char c)
  {
    super(result1, result2);
    symbol = c;
  }

  /**
   * A factory method that parses a String accoring to the BNF definition for
   * <assignment>. The BNF definition is: <assignment> := <identifier> "="
   * <expr>
   * @param s the String to parse
   * @return an Assignment object that is the root of an abstract syntax
   * (sub)tree resulting from the parse, or null if the String cannot be 
   * parsed as a <assignment>.
   */

  public static Assignment parse(java.lang.String s)
  {
    String str = s.trim();
    //Checks all possible ways to split between = sign
    for(int i = 1; i < str.length()-1; i++) {
      if(str.charAt(i) == '=') {
        String s1 = str.substring(0,i);
        String s2 = str.substring(i+1);
        Identifier result1 = Identifier.parse(s1);
        Expr result2 = Expr.parse(s2);
        if(result1 != null && result2 != null) {
          return new Assignment(result1, result2, '=');
        }
      }
    }
    return null;
  }

  /**
   * Evaluate the abstract syntax (sub)tree that is rooted at this ASTNode 
   * in the context of the given symbol table, and return the result.
   * @param symtab - A map from variable identifiers to values, to use as a 
   * symbol table in the evaluation.
   * @return the double value that is the result of evaluating the abstract
   * syntax (sub)tree rooted at this ASTNode.
   */

  public double eval(java.util.Map<java.lang.String,java.lang.Double> symtab)
  {
    if(arity() == 2) {
      symtab.put(getChild(0).toString(),getChild(1).eval(symtab));
      return getChild(0).eval(symtab);
    }
    else {
      throw new IllegalStateException();
    }
  }

}

