public class FieldDecl extends Token{
    OptionalFinal optionalfinal;
    Type type;
    String id;
    OptionalExpr optionalexpr;
    int num;
    String op;

    public FieldDecl(OptionalFinal of, Type t, String i, OptionalExpr oe){
        optionalfinal = of;
        type = t;
        id = i;
        optionalexpr = oe;
        op = "OPFIN";
    }

    public FieldDecl(Type t, String i, OptionalExpr oe){
        type = t;
        id = i;
        optionalexpr = oe;
        op = "NO FIN";
    }

    public FieldDecl(Type t, String i, int n){
        type = t;
        id = i;
        num = n;
        op = "BRACK";
    }

    public String toString(int t){
        if(op.equals("OPFIN")){
            return getTabs(t) + optionalfinal.toString(0) + " " + type.toString(0) + " " + id + 
            " " + optionalexpr.toString(0) + ";\n";
        }
        else if(op.equals("NO FIN")){
            return getTabs(t) + type.toString(0) + " " + id + 
            " " + optionalexpr.toString(0) + ";\n";
        }
        else if(op.equals("BRACK")){
            return getTabs(t) + type.toString(0) + " " + id + "[" + Integer.toString(num) + "]\n";
        }
        else{
            return "ERROR IN FIELDDECL";
        }
    }

    public TypeInfo typeCheck() throws TypeException{
        throw new TypeException("AAAAA");
    }
}