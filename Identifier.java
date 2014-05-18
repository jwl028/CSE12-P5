/**
 * A class representing the <identifier> nonterminal symbol in an abstract 
 * syntax tree. This class has no public constructors; the public interface
 * for creating an instance of the class is the static parse(String s) factory
 * method.
 * @author Jimmy Li cs12sdm
 */

public class Identifier extends ASTNode
{
  private String stri;

  /**
   * Private constructor - initializes instance variable
   */

  private Identifier(String s)
  {
    stri = s;
  }

  /**
   * A factory method that parses a String accoring to the definition for
   * <identifier>. The definition is: Any String that starts with a 
   * JavaIdentifierStart character, followed by 0 or more JavaIdentifierPart 
   * characters.
   * @param the String to parse
   * @return a Identifier object that is the root of an abstract syntax
   * subtree resulting from the parse, or null if the String cannot be parsed
   * as a <identifier>.
   */
 
  public static Identifier parse(java.lang.String s)
  {
    int count = 0;
    String str = s.trim();
    if(str.length() !=0 && Character.isJavaIdentifierStart(str.charAt(0))) {
      for(int i = 1; i < str.length(); i++) {
        if(java.lang.Character.isJavaIdentifierPart(str.charAt(i))) {
          count++;
        }
      }
      if(count >= 0) {  
        return new Identifier(str);
      }
      else {
        return null;
      }
    }
    return null;
  }

  /**
   * Evaluate the abstract syntax (sub)tree that is rooted at this ASTNode 
   * in the context of the given symbol table, and return the result.
   * @param symboltable - A map from variable identifiers to values, to use
   * as a symbol table in the evaluation.
   * @return the double value that is the result of evaluating the abstract 
   * syntax (sub)tree rooted at this ASTNode.
   */

  public double eval(java.util.Map<java.lang.String,java.lang.Double> symtab)
  {
    if(symtab.get(stri) == null) {
      throw new RuntimeException("UNINITIALIZED VARIABLE: "+stri);
    }
    return symtab.get(stri);
  }
 
  /**
   * Return the String representation of this identifier.
   * @return the String representation of this identifier
   */

  public java.lang.String toString()
  {
    return stri;
  }
}
