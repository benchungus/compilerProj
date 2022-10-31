public class IfEnd extends Token{
    StatementList stmts;

    public IfEnd(StatementList ss){
        stmts = ss;
    }

    public IfEnd(){
    }

    public String toString(int t){
        if(stmts == null){
            return "";
        }
        return getTabs(t) + "ELSE: {\n" + stmts.toString(t+1) + getTabs(t) + "}\n" ;
    }
}
