/**
 * A class representing the <factor> nonterminal symbol in an abstract syntax 
 * tree. This class has no public constructors; the public interface for 
 * creating an instance of the class is the static parse(String s) factory
 * method.
 * @author Jimmy Li cs12sdm
 *
 */

public class Factor extends ASTNode
{
  private char symbol;

  /**
   * Private constructor - creates child Constant node
   */

  private Factor(Constant node)
  {
    super(node);
  }

  /**
   * Private constructor - creates child Identifier node
   */

  private Factor(Identifier node)
  {
    super(node);
  } 

  /**
   * Private constructor - creates child Expr node
   */

  private Factor(Expr node, char c)
  {
    super(node);
    symbol = c;
  }

  /**
   * A factory method that parses a String accoring to the BNF definition for
   * <factor>. The BNF definition is: <factor> := <constant> | <identifier> |
   * "(" <expr> ")"
   * @param s the String to parse
   * @return a Factor object that is the root of an abstract syntax (sub)tree
   * resulting from the parse, or null if the String cannot be parsed as a
   * <factor>.
   */

  public static Factor parse(java.lang.String s)
  {
    String str = s.trim();
    //Constant
    Constant result = Constant.parse(str);
    if(result != null) {
      return new Factor(result);
    }
    //Identifier
    Identifier result1= Identifier.parse(str);
    if(result1 != null) {
      return new Factor(result1);
    }
    //(expr)
    if(str.length() > 2 && str.charAt(0) == '(' && str.charAt(str.length()-1) 
       == ')') {
      String s1 = str.substring(1,str.length()-1);
      Expr result2 = Expr.parse(s1);
      if(result2 != null) {
        return new Factor(result2,'(');
      }
    }
    return null;
  }

  /**
   * Evaluate the abstract syntax (sub)tree that is rooted at this ASTNode 
   * in the context of the given symbol table, and return the result.
   * @param symtab - A map from variable identifiers to values, to use 
   * as a symbol table in the evaluation.
   * @return the double value that is the result of evaluating the abstract
   * syntax (sub)tree rooted at this ASTNode.
   */

  public double eval(java.util.Map<java.lang.String,java.lang.Double> symtab)
  {
    if(arity() == 1 && symbol == '(') {
      return (getChild(0).eval(symtab));
    }
    else if(arity() == 1) {
      return getChild(0).eval(symtab);
    }
    else {
      throw new IllegalStateException();
    }
  }
}
