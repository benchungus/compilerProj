public class MethodDecl extends Token {
    ReturnType returntype;
    String id;
    ArgDecls argdecls;
    FieldDecls fielddecllist;
    StatementList stmts;
    OptionalSemi optionalsemi;

    public MethodDecl(ReturnType rt, String i, ArgDecls ads, FieldDecls fdl, StatementList ss, OptionalSemi os){
        returntype = rt;
        id = i;
        argdecls = ads;
        fielddecllist = fdl;
        stmts = ss;
        optionalsemi = os;
    }


    public String toString(int t){
        return getTabs(t) + "METHOD:" + returntype.toString(t) + " " + id + " (" + argdecls.toString(t)+
        ") {\n" + fielddecllist.toString(t+1) + " " + stmts.toString(t+1) + getTabs(t) + "} " + optionalsemi.toString(0) + "\n";  
    }
}
