public class IfEnd extends Token{
    StatementList stmts;
    FieldDecls fielddeclslist;

    public IfEnd(FieldDecls fdl, StatementList ss){
        fielddeclslist = fdl;
        stmts = ss;
    }

    public IfEnd(){
    }

    public String toString(int t){
        if(stmts == null){
            return "";
        }
        return getTabs(t) + "ELSE: {\n" + fielddeclslist.toString(t+1) + stmts.toString(t+1) + getTabs(t) + "}\n" ;
    }

    public TypeInfo typeCheck() throws TypeException{
        throw new TypeException("ifend");
    }
}
