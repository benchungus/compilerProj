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


    public Statement(Expr e, String op) {
        expr = e;
        operation = op;
    }

    public Statement(Expr e, StatementList ss, String op) {
        expr = e;
        stmts = ss;
        operation = op;
    }

    public Statement(Expr e, StatementList ss, IfEnd ie) {
        expr = e;
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

    public Statement(StatementList sl, OptionalSemi os){
        stmts = sl;
        optionalSemi = os;
        operation = "optional semi";
    }

    public String toString(int t) {
      switch (operation){
        case "assign":
            return name.toString(t) + " = " + expr.toString(0) + ";\n";
        case "read":
            return getTabs(t) + "READ (" + readList.toString(0) + ");\n";
        case "print":
            return getTabs(t) + "PRINT (" + printList.toString(0) + ");\n";
        case "printline":
            return getTabs(t) + "PRINTLINE (" + printList.toString(0) + ");\n";
        case "call empty":
            return getTabs(t) + value + "();";
        case "call full":
            return getTabs(t) + value + "(" + args.toString(0) +");\n";
        case "void return":
            return getTabs(t) + "RETURN;";
        case "full return":
            return getTabs(t) + "RETURN " + expr.toString(0) +";\n";
        case "increment":
          return getTabs(t) + name.toString(0) + "++;\n";
        case "decrement":
            return getTabs(t) + name.toString(0) + "--;\n";
        case "optional semi":
            return getTabs(t) + "{\n" + stmts.toString(t+1) + getTabs(t) + "}" + optionalSemi.toString(0) + "\n";
        case "while":
          return getTabs(t) + "WHILE: ( " + expr.toString(0) + " ) {\n" + stmts.toString(t+1) + getTabs(t) + "}\n";
        case "if":
          return getTabs(t) + "IF: ( " + expr.toString(0) + " ) {\n" + stmts.toString(t+1) + getTabs(t) + "}\n" + ifend.toString(t);
      }
      return "";
    }
}

