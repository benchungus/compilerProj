public class Expr extends Token {
    String value;
    Expr lhs;
    String operator;
    Expr rhs;
    Expr third;
    Args args;
    Type type;

    public Expr(Name i) {
        value = i.toString(0);
        operator = "";
    }

    public Expr(int n){
      value = Integer.toString(n);
      operator = "";
    }

    public Expr(char c){
      value = String.valueOf(c);
      operator = "";
    }

    public Expr(String s){
      value = s;
      operator = "";
    }
    
    public Expr(float f){
      value = Float.toString(f);
      operator = "";
    }

    public Expr(boolean n){
      value = Boolean.toString(n);
      operator = "";
    }

    public Expr(String i, Args a){
      value = i;
      args = a;
      operator = "args";
    }

    public Expr(Expr e, Type a){
      lhs = e;
      type = a;
      operator = "type";
    }

    public Expr(String i, String a){
      value = i;
      operator = a;
    }

    public Expr(Expr n1, String op, Expr n2){
      lhs = n1;
      operator = op;
      rhs = n2;
    }

    public Expr(Expr n1, String op){
      lhs = n1;
      operator = op;
    }

    public Expr(Expr b1, Expr b2, Expr b3){
      lhs = b1;
      rhs = b2;
      third = b3;
      operator = "three";
    }

    public String toString(int t) {
      if(operator.equals("")){
        return getTabs(t) + value;
      }
      else if(operator.equals("empty")){
        return value + " ()" ;
      }
      else if(operator.equals("args")){
        return value + "(" + args.toString(0) + ")";
      }
      switch (operator){
        case "paren":
          return "(" + lhs.toString(0) + ")";
        case "empty":
          return value + "()" ;
        case "args":
          return value + "(" + args.toString(0) + ")";
        case "not":
          return "(~ " + lhs.toString(0) + ")";
        case "preplus":
          return "(+ " + lhs.toString(0) + ")";
        case "preminus":
          return "(- " + lhs.toString(0) + ")";
        case "type":
          return "(" + type.toString(0) + ")" + lhs.toString(0);
        case "three":
          return "(" + lhs.toString(0) + " ? " + rhs.toString(0) + " : " + third.toString(0) + ")";
      }
      return "(" + lhs.toString(0) + " " + operator + " " + rhs.toString(0) + ")";
    }

    public TypeInfo typeCheck() throws TypeException{
      throw new TypeException("AAAAA");
    }
}

