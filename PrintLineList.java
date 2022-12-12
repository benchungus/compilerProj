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
    if(pll == null){
      if(expr != null){
        TypeInfo exprTi = expr.typeCheck();
        if(exprTi.isArray){
          throw new TypeException("Error: Cannot print an array");
        }
        if(exprTi.getType().equals("void")){
          throw new TypeException("Error: Cannot print void type");
        }
      }
    } 
    else{
      if(expr != null){
        TypeInfo exprTi = expr.typeCheck();
        if(exprTi.isArray){
          throw new TypeException("Error: Cannot print an array");
        }
        if(exprTi.getType().equals("void")){
          throw new TypeException("Error: Cannot print void type");
        }
        pll.typeCheck();
      }
      else{
        pll.typeCheck();
      }
    }
    return new TypeInfo();
  }
}
