public class MemberDecls extends Token{
    ReturnType returntype;
    String id;
    FieldDecls fielddecllist;
    MethodDecls methoddecllist;
    Type type;
    String op;


    public MemberDecls(FieldDecls fdl, MethodDecls mdl){
        fielddecllist = fdl;
        methoddecllist = mdl;
    }

    public MemberDecls(Type r, String i, MethodDecls mdl){
        type = r;
        id = i;
        methoddecllist = mdl;
        op = "method";
    }

    public MemberDecls(String i, MethodDecls mdl, String o){
        id = i;
        methoddecllist = mdl;
        op = o;
    }

    public MemberDecls(Type t, String i, FieldDecls fde, String o){
        type = t;
        id = i;
        fielddecllist = fde;
        op = o;
    }

    public MemberDecls(){
        op = "null";
    }


    public String toString(int t){
        if(op.equals("method")){
            return getTabs(t) + type.toString(0) + " " + id + " " + methoddecllist.toString(t);
        }
        else if(op.equals("nonfinal")){
            return getTabs(t) + type.toString(0) + " " + id + " " + fielddecllist.toString(t);
        }
        else if(op.equals("final")){
            return getTabs(t) + "FINAL " + type.toString(0) + " " + id + " " + fielddecllist.toString(t);
        }
        else if(op.equals("VOID")){
            return getTabs(t) + "VOID " + id + " " + methoddecllist.toString(t);
        }
        else if(op.equals("null")){
            return "";
        }
        else{
            return "error in memberdecls";
        }
    }

    public TypeInfo typeCheck() throws TypeException{
        if(op.equals("method")){
            TypeInfo ti = new TypeInfo(type.toString(0));
            ti.setMethod();
            return methoddecllist.typeCheck(ti, id);
        }
        else if(op.equals("nonfinal")){
            TypeInfo ti = new TypeInfo(type.toString(0));
            symbolTable.addVar(id, ti);
            return fielddecllist.typeCheck(ti);
        }
        else if(op.equals("final")){
            TypeInfo ti = new TypeInfo(type.toString(0));
            ti.setFinal();
            symbolTable.addVar(id, ti);
            return fielddecllist.typeCheck(ti);
        }
        else if(op.equals("VOID")){
            TypeInfo ti = new TypeInfo("void");
            ti.setMethod();
            return methoddecllist.typeCheck(ti, id);
        }
        else{
            return new TypeInfo();
        }
    }
}