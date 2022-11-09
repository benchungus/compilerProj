public class MethodDecls extends Token {
    ReturnType returntype;
    String id;
    ArgDecls argdecls;
    FieldDecls fielddecllist;
    StatementList stmts;
    OptionalSemi optionalsemi;
    MethodDecls methoddecllist;
    String op;

    public MethodDecls(ReturnType rt, String i, ArgDecls ads, FieldDecls fdl, StatementList ss, OptionalSemi os, MethodDecls mdl){
        returntype = rt;
        id = i;
        argdecls = ads;
        fielddecllist = fdl;
        stmts = ss;
        optionalsemi = os;
        methoddecllist = mdl;
        op = "mdl";
    }

    public MethodDecls(){
        op = "null";
    }

    public String toString(int t){
        if(op.equals("mdl")){
            return getTabs(t) + "METHOD:" + returntype.toString(t) + " " + id + " (" + argdecls.toString(t)+
        ") {\n" + fielddecllist.toString(t+1) + " " + stmts.toString(t+1) + getTabs(t) + "} " + optionalsemi.toString(0) + "\n" + methoddecllist.toString(t);
        }
        else if(op.equals("null")){
            return "";
        }
        else{
            return "ERROR IN METHODDECLS";
        }
    }
}
