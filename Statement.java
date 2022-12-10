import java.util.ArrayList;

public class Statement extends Token {
    Expr expr;
    String operation;
    Name name;
    ReadList readList;
    PrintLineList printList;
    Args args;
    String value;
    StatementList stmts;
    IfEnd ifend;
    OptionalSemi optionalSemi;
    FieldDecls fielddecllist;


    public Statement(Expr e, String op) {
        expr = e;
        operation = op;
    }

    public Statement(Expr e, StatementList ss, String op) {
        expr = e;
        stmts = ss;
        operation = op;
    }

    public Statement(Expr e, FieldDecls fdl, StatementList ss){
        expr = e;
        fielddecllist = fdl;
        stmts = ss;
        operation = "while";
    }

    public Statement(Expr e, FieldDecls fdl, StatementList ss, IfEnd ie) {
        expr = e;
        fielddecllist = fdl;
        stmts = ss;
        ifend = ie;
        operation = "if";
    }

    public Statement(ReadList rl) {
        readList = rl;
        operation = "read";
    }

    public Statement(String id, String op) {
        value = id;
        operation = op;
    }

    public Statement(String id, Args a) {
        value = id;
        args = a;
        operation = "call full";
    }

    public Statement(PrintLineList pll, String op) {
        printList = pll;
        operation = op;
    }

    public Statement(Name n, String op){
        name = n;
        operation = op;
    }

    public Statement(Name n, Expr e){
        expr = e;
        name = n;
        operation = "assign";
    }

    public Statement(FieldDecls fdl, StatementList sl, OptionalSemi os){
        fielddecllist = fdl;
        stmts = sl;
        optionalSemi = os;
        operation = "optional semi";
    }

    public String toString(int t) {
      switch (operation){
        case "assign":
            return getTabs(t) + name.toString(0) + " = " + expr.toString(0) + ";\n";
        case "read":
            return getTabs(t) + "READ (" + readList.toString(0) + ");\n";
        case "print":
            return getTabs(t) + "PRINT (" + printList.toString(0) + ");\n";
        case "printline":
            return getTabs(t) + "PRINTLINE (" + printList.toString(0) + ");\n";
        case "call empty":
            return getTabs(t) + value + "();\n";
        case "call full":
            return getTabs(t) + value + "(" + args.toString(0) +");\n";
        case "void return":
            return getTabs(t) + "RETURN;\n";
        case "full return":
            return getTabs(t) + "RETURN " + expr.toString(0) +";\n";
        case "increment":
          return getTabs(t) + name.toString(0) + "++;\n";
        case "decrement":
            return getTabs(t) + name.toString(0) + "--;\n";
        case "optional semi":
            return getTabs(t) + "{\n" + fielddecllist.toString(t+1) + stmts.toString(t+1) + getTabs(t) + "}" + optionalSemi.toString(0) + "\n";
        case "while":
          return getTabs(t) + "WHILE: ( " + expr.toString(0) + " ) {\n" + fielddecllist.toString(t+1) + stmts.toString(t+1) + getTabs(t) + "}\n";
        case "if":
          return getTabs(t) + "IF: ( " + expr.toString(0) + " ) {\n" + fielddecllist.toString(t+1) + stmts.toString(t+1) + getTabs(t) + "}\n" + ifend.toString(t);
      }
      return "";
    }

    public TypeInfo typeCheck() throws TypeException{
        switch (operation){
            case "assign":{
                TypeInfo ti = name.typeCheck();
                TypeInfo exprTi = expr.typeCheck();
                if(!exprTi.isNull()){
                    canConvert(ti, exprTi);
                }
                if(ti.isArray != exprTi.isArray){
                    throw new TypeException("Error: Array type and non-array type cannot be used together in assignment");
                }
                if(ti.isFinal){
                    throw new TypeException("Error: Cannot assign new value to a final variable");
                }
                return new TypeInfo();
            }
            case "read":
                readList.typeCheck();
                return new TypeInfo();
            case "print":
                printList.typeCheck();
                return new TypeInfo();
            case "printline":
                printList.typeCheck();
                return new TypeInfo();
            case "call empty":{
                TypeInfo methodTi = symbolTable.getMethod(value);
                ArrayList<TypeInfo> params = methodTi.getParams();
                if(params.size() != 0){
                    throw new TypeException("Error: Function expected " + params.size() + " parameters, instead got 0");
                }
                return new TypeInfo();
            }
            case "call full":
                TypeInfo methodTi = symbolTable.getMethod(value);
                ArrayList<TypeInfo> params = methodTi.getParams();
                ArrayList<TypeInfo> givenParams = args.typeCheck();
                if(params.size() != givenParams.size()){
                    throw new TypeException("Error: Function expected " + params.size() + " parameters, instead got " + givenParams.size());
                }
                for(int i = 0; i < params.size(); i++){
                    canConvert(params.get(i), givenParams.get(i));
                    if(params.get(i).isArray != givenParams.get(i).isArray){
                        throw new TypeException("Error: Tried to use array in place of non-array");
                    }
                }
                return new TypeInfo();
            case "void return":
                return new TypeInfo();
            case "full return":
                return expr.typeCheck();
            case "increment":{
                TypeInfo ti = name.typeCheck();
                if(!ti.type.equals("int") && !ti.type.equals("float")){
                    throw new TypeException("Error: increment can only be performed on ints and floats");
                }
                return new TypeInfo();
            }
            case "decrement":{
                TypeInfo ti = name.typeCheck();
                if(!ti.type.equals("int") && !ti.type.equals("float")){
                    throw new TypeException("Error: decrement can only be performed on ints and floats");
                }
                return new TypeInfo();
            }
            case "optional semi":
                symbolTable.startScope();
                fielddecllist.typeCheck();
                stmts.typeCheck();
                symbolTable.endScope();
                return new TypeInfo();
            case "while":{
                if(!expr.typeCheck().getType().equals("bool") && !expr.typeCheck().getType().equals("int")){
                    throw new TypeException("Condition must be of type bool");
                }
                symbolTable.startScope();
                fielddecllist.typeCheck();
                TypeInfo ti = stmts.typeCheck();
                symbolTable.endScope();
                return ti;
            }
            case "if":
                if(!expr.typeCheck().getType().equals("bool") && !expr.typeCheck().getType().equals("int")){
                    throw new TypeException("Condition must be of type bool");
                }
                symbolTable.startScope();
                fielddecllist.typeCheck();
                TypeInfo ti = stmts.typeCheck();
                symbolTable.endScope();
                ifend.typeCheck();
                return ti;
          }
        return new TypeInfo();
    }
}

