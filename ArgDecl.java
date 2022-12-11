public class ArgDecl extends Token{
    Type type;
    String id;
    String op;

    public ArgDecl(Type t, String i, String o){
        type = t;
        id = i;
        op = o;
    }

    public String toString(int t){
        if(op.equals("brack")){
            return type.toString(0) + " " + id + " []";
        }
        else if(op.equals("no brack")){
            return type.toString(0) + " " + id;
        }
        else{
            return "ERROR";
        }
    }

    public TypeInfo typeCheck() throws TypeException{
        if(op.equals("brack")){
            TypeInfo argti = new TypeInfo(type.toString(0));
            argti.setArray();
            symbolTable.addVar(id, argti);
            return argti;
        }
        else {
            TypeInfo argti = new TypeInfo(type.toString(0));
            symbolTable.addVar(id, argti);
            return argti;
        }
    }
}
