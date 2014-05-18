/**
 * A class representing the <expr> nonterminal symbol in an abstract 
 * syntax tree. This class has no public constructors; the public
 * interface for creating an instance of the class is the static 
 * parse(String s) factory method. 
 * 
 * @author Jimmy Li cs12sdm
 */

public class Expr extends ASTNode
{
  
  /**
   * Private constructor that creates a child Operation node
   */

  private Expr(Operation node)
  {
    super(node);
  }

  /**
   * Private constructor that creates a child Assignment node
   */

  private Expr(Assignment node)
  {
    super(node);
  }

  /**
   * A factory method that parses a String accoring to the BNF definition
   * for <expr>. The BNF definition is:  <expr> := <assignment> | <operation>
   *
   * @param s the String to parse
   * @return an Expr object that is the root of an abstract syntax (sub)tree
   * resulting from the parse, or null if the String cannot be parsed as an
   * <expr>.
   */

  public static Expr parse(java.lang.String s)
  {
    String str = s.trim();
    //Parse Assignment
    Assignment result2 = Assignment.parse(str);
    if(result2 != null) {
      return new Expr(result2);
    } 
    //Parse Operation 
    Operation result1 = Operation.parse(str);
    if(result1 != null) {
      return new Expr(result1);
    }
    return null;
  }
 
  /**
   * Evaluate the abstract syntax (sub)tree that is rooted at this ASTNode 
   * in the context of the given symbol table, and return the result.
   * 
   * @param symtab A map from variable identifiers to values, to use as a
   * symbol table in the evaluation.
   * @return the double value that is the result of evaluating the abstract
   * syntax (sub)tree rooted at this ASTNode.
   */

  public double eval(java.util.Map<java.lang.String,java.lang.Double> symtab)
  {
    if(arity() == 1) {
      return getChild(0).eval(symtab);
    }
    else {
      throw new IllegalStateException();
    }
  }

}
