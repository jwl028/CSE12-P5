/**
 * A class representing the <constant> nonterminal symbol in an abstract 
 * syntax tree. This class has no public constructors; the public interface
 * for creating an instance of the class is the static parse(String s) factory
 * method.
 * @author Jimmy Li cs12sdm
 */

public class Constant extends ASTNode
{
  private double num;

  /**
   * Private constructor - initializes instance variable
   */

  private Constant(double result)
  {
    num = result;
  }
 
  /**
   * A factory method that parses a String accoring to the definition for 
   * <constant>. The definition is: Any String that can be parsed by 
   * java.lang.Double.parseDouble(s) without throwing a NumberFormatException.
   * @param the String to parse
   * @return a Constant object that is the root of an abstract syntax subtree 
   * resulting from the parse, or null if the String cannot be parsed as a
   * <constant>.
   */
  
  public static Constant parse(java.lang.String s)
  {
    String str = s.trim();
    try {
      double result = java.lang.Double.parseDouble(str);
      return new Constant(result);
    }
    catch(NumberFormatException e) {
      return null;
    }
  }

  /**
   * Evaluate the abstract syntax (sub)tree that is rooted at this ASTNode in 
   * the context of the given symbol table, and return the result.
   * @param symtab - A map from variable identifiers to values, to use as 
   * a symbol table in the evaluation.
   * @return the double value that is the result of evaluating the abstract
   * syntax (sub)tree rooted at this ASTNode.
   */

  public double eval(java.util.Map<java.lang.String,java.lang.Double> symtab)
  {
    return num;
  }

}
