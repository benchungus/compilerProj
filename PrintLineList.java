import java.util.List;
import java.util.LinkedList;

class PrintLineList extends Token {
  private Expr expr;
  private PrintLineList pll;

  public PrintLineList(Expr e) {
        expr = e;
  }

  public PrintLineList(PrintLineList p) {
        pll = p;
  }

  public PrintLineList() {
  }

  public PrintLineList(Expr e, PrintLineList p){
        pll = p;
        expr = e;
  }

  public String toString(int t) {
    if(pll == null){
      if(expr != null)
        return expr.toString(t);
      else
        return "";
    } 
    else{
      if(expr != null)
        return expr.toString(t) + ", " + pll.toString(0);
      else
        return pll.toString(0);
    }
  }

  public TypeInfo typeCheck() throws TypeException{
    throw new TypeException("AAAAA");
  }
}
