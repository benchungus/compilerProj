public class Name extends Token {
    String value;
    Expr expr;

    public Name(String i) {
        value = i;
        expr = null;
    }

    public Name(String i, Expr e1){
      expr = e1;
      value = i;
    }

    public String toString(int t) {
      if(expr == null){
        return getTabs(t) + value;
      }
      else{
        return value + "[" + expr.toString(0) + "]";
      }
    }

    public TypeInfo typeCheck() throws TypeException{
      throw new TypeException("AAAAA");
  }
}
