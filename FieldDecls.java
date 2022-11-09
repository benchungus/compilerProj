public class FieldDecls extends Token{
    FieldDecl fielddecl;
    FieldDecls fielddecllist;
    OptionalFinal optionalfinal;
    Type type;
    String id;
    OptionalExpr optionalexpr;
    int num;
    String op;

    public FieldDecls(OptionalFinal of, Type t, String i, OptionalExpr oe, FieldDecls fdl){
        fielddecllist = fdl;
        optionalfinal = of;
        type = t;
        id = i;
        optionalexpr = oe;
        op = "OPFIN";
    }

    public FieldDecls(Type t, String i, OptionalExpr oe, FieldDecls fdl){
        fielddecllist = fdl;
        type = t;
        id = i;
        optionalexpr = oe;
        op = "NO FIN";
    }

    public FieldDecls(Type t, String i, int n, FieldDecls fdl){
        fielddecllist = fdl;
        type = t;
        id = i;
        num = n;
        op = "BRACK";
    }

    public FieldDecls(){
        op = "null";
    }

    public String toString(int t){
        if(op.equals("OPFIN")){
            return getTabs(t) + optionalfinal.toString(0) + " " + type.toString(0) + " " + id + 
            " " + optionalexpr.toString(0) + ";\n" + fielddecllist.toString(0);
        }
        else if(op.equals("NO FIN")){
            return getTabs(t) + type.toString(0) + " " + id + 
            " " + optionalexpr.toString(0) + ";\n"+ fielddecllist.toString(0);
        }
        else if(op.equals("BRACK")){
            return getTabs(t) + type.toString(0) + " " + id + "[" + Integer.toString(num) + "]\n"+ fielddecllist.toString(0);
        }
        else if(op.equals("null")){
            return "";
        }
        else{
            return "ERROR IN FIELDDECLS";
        }
    }
}