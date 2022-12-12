import java.util.ArrayList;

public class Expr extends Token {
    String value;
    Expr lhs;
    String operator;
    Expr rhs;
    Expr third;
    Args args;
    Type type;
    String tp;
    Name tpName;
    int tpint;
    char tpchar;
    String tpString;
    float tpfloat;
    boolean tpbool;

    public Expr(Name i) {
        value = i.toString(0);
        operator = "";
        tp = "name";
        tpName = i;
    }

    public Expr(int n){
      value = Integer.toString(n);
      operator = "";
      tp = "int";
      tpint = n;
    }

    public Expr(char c){
      value = String.valueOf(c);
      operator = "";
      tp = "char";
      tpchar = c;
    }

    public Expr(String s){
      System.out.println(s);
      if(s.charAt(0) == '\''){
        operator = "";
        value = Character.toString(s.charAt(1));
        tp = "char";
        tpString = value;
      }
      else{
        value = s;
        operator = "";
        tp = "string";
        tpString = s;
      }
    }
    
    public Expr(float f){
      value = Float.toString(f);
      operator = "";
      tp = "float";
      tpfloat = f;
    }

    public Expr(boolean n){
      value = Boolean.toString(n);
      operator = "";
      tp = "bool";
      tpbool = n;
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
      if(operator.equals("")){
        switch(tp){
          case "name":
            return tpName.typeCheck();
          case "int":{
            TypeInfo ti = new TypeInfo("int");
            return ti;
          }
          case "char":{
            TypeInfo ti = new TypeInfo("char");
            return ti;
          }
          case "string":{
            System.out.println("here");
            TypeInfo ti = new TypeInfo("string");
            return ti;
          }
          case "float":{
            TypeInfo ti = new TypeInfo("float");
            return ti;
          }
          case "bool":{
            TypeInfo ti = new TypeInfo("bool");
            return ti;
          }
          default:{
            throw new TypeException("UNEXPECTED ERROR");
          }
        }
      }
      else{
        switch (operator){
          case "paren":
            return lhs.typeCheck();
          case "empty":{
            TypeInfo ti = symbolTable.get(value);
            TypeInfo ti1 = new TypeInfo(ti.getType());
            if(ti.isArray){
              ti1.setArray();
            }
            return ti1;
          }
          case "args":{
            symbolTable.printAll();
            TypeInfo methodTi = symbolTable.getMethod(value);
            ArrayList<TypeInfo> params = methodTi.getParams();
            ArrayList<TypeInfo> givenParams = args.typeCheck();
            TypeInfo newType = new TypeInfo(methodTi.type);
            if(methodTi.getArray()){
              newType.setArray();
            }
            if(params.size() != givenParams.size()){
                throw new TypeException("Error: Function expected " + params.size() + " parameters, instead got " + givenParams.size());
            }
            for(int i = 0; i < params.size(); i++){
                canConvert(params.get(i), givenParams.get(i));
                if(params.get(i).isArray != givenParams.get(i).isArray){
                    throw new TypeException("Error: Tried to use array in place of non-array");
                }
            }
            return newType;
          }
          case "not":{
            TypeInfo ti = lhs.typeCheck();
            if(!ti.getType().equals("bool") && !ti.getType().equals("int")){
              throw new TypeException("Error: ~ operation cannot be used on non-boolean values");
            }
            return new TypeInfo("bool");
          }
          case "preplus":{
            TypeInfo ti = lhs.typeCheck();
            if(!ti.getType().equals("int") && !ti.getType().equals("float")){
              throw new TypeException("Error: + prefix operation cannot be used on non-numeric values");
            }
            return ti;
          }
          case "preminus":{
            TypeInfo ti = lhs.typeCheck();
            if(!ti.getType().equals("int") && !ti.getType().equals("float")){
              throw new TypeException("Error: - prefix operation cannot be used on non-numeric values");
            }
            return ti;
          }
          case "type":{
            TypeInfo ti = lhs.typeCheck();
            TypeInfo cast = new TypeInfo(type.toString(0));
            canConvert(cast, ti);
            return cast;
          }
          case "three":{
            TypeInfo ti1 = lhs.typeCheck();
            TypeInfo ti2 = rhs.typeCheck();
            TypeInfo ti3 = third.typeCheck();
            if(!ti1.getType().equals("int") && !ti1.getType().equals("bool")){
              throw new TypeException("Error: bool type expected for ternary condition");
            }
            if(!ti2.getType().equals(ti3.getType())){
              throw new TypeException("Error: the 2 terms at end of ternary must have same types");
            }
            return ti2;
          }
          case "+":{
            TypeInfo ti1 = lhs.typeCheck();
            TypeInfo ti2 = rhs.typeCheck();
            if(ti1.getType().equals("int")){
              if(ti2.getType().equals("float")){
                return ti2;
              }
              else if(ti2.getType().equals("int")){
                return ti2;
              }
            }
            if(ti1.getType().equals("float")){
              if(ti2.getType().equals("float")){
                return ti2;
              }
              else if(ti2.getType().equals("int")){
                return ti1;
              }
            }
            return new TypeInfo("string");
          }
          case "-":{
            TypeInfo ti1 = lhs.typeCheck();
            TypeInfo ti2 = rhs.typeCheck();
            if(ti1.getType().equals("int")){
              if(ti2.getType().equals("float")){
                return ti2;
              }
              else if(ti2.getType().equals("int")){
                return ti2;
              }
            }
            if(ti1.getType().equals("float")){
              if(ti2.getType().equals("float")){
                return ti2;
              }
              else if(ti2.getType().equals("int")){
                return ti1;
              }
            }
            throw new TypeException("Cannot perform " + operator + " operation on types " + ti1.getType() + " and " + ti2.getType());
          }
          case "*":{
            TypeInfo ti1 = lhs.typeCheck();
            TypeInfo ti2 = rhs.typeCheck();
            if(ti1.getType().equals("int")){
              if(ti2.getType().equals("float")){
                return ti2;
              }
              else if(ti2.getType().equals("int")){
                return ti2;
              }
            }
            if(ti1.getType().equals("float")){
              if(ti2.getType().equals("float")){
                return ti2;
              }
              else if(ti2.getType().equals("int")){
                return ti1;
              }
            }
            throw new TypeException("Cannot perform " + operator + " operation on types " + ti1.getType() + " and " + ti2.getType());
          }
          case "/":{
            TypeInfo ti1 = lhs.typeCheck();
            TypeInfo ti2 = rhs.typeCheck();
            if(ti1.getType().equals("int")){
              if(ti2.getType().equals("float")){
                return ti2;
              }
              else if(ti2.getType().equals("int")){
                return ti2;
              }
            }
            if(ti1.getType().equals("float")){
              if(ti2.getType().equals("float")){
                return ti2;
              }
              else if(ti2.getType().equals("int")){
                return ti1;
              }
            }
            throw new TypeException("Cannot perform " + operator + " operation on types " + ti1.getType() + " and " + ti2.getType());
          }
          case "||":{
            TypeInfo ti1 = lhs.typeCheck();
            TypeInfo ti2 = rhs.typeCheck();
            if(!ti1.getType().equals("int") && !ti1.getType().equals("bool")){
              throw new TypeException("Error: bool type expected for || operation");
            }
            if(!ti2.getType().equals("int") && !ti2.getType().equals("bool")){
              throw new TypeException("Error: bool type expected for || operation");
            }
            return new TypeInfo("bool");
          }
          case "&&":{
            TypeInfo ti1 = lhs.typeCheck();
            TypeInfo ti2 = rhs.typeCheck();
            if(!ti1.getType().equals("int") && !ti1.getType().equals("bool")){
              throw new TypeException("Error: bool type expected for && operation");
            }
            if(!ti2.getType().equals("int") && !ti2.getType().equals("bool")){
              throw new TypeException("Error: bool type expected for && operation");
            }
            return new TypeInfo("bool");
          }
          default:{
            TypeInfo ti1 = lhs.typeCheck();
            TypeInfo ti2 = rhs.typeCheck();
            if(!ti1.getType().equals("int") && !ti1.getType().equals("float")){
              throw new TypeException("Error: numeric type expected for " + operator + " operation");
            }
            if(!ti2.getType().equals("int") && !ti2.getType().equals("float")){
              throw new TypeException("Error: numeric type expected for " + operator + " operation");
            }
            return new TypeInfo("bool");
          }
        }
      }
    }
}

