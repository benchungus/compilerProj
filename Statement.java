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
        throw new TypeException("statement");
    }
}

