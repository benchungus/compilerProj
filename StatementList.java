public class StatementList extends Token {
    Statement stmt;
    StatementList stmts;

    public StatementList(Statement s){
        stmt = s;
    }

    public StatementList(Statement s, StatementList ss){
        stmt = s;
        stmts = ss;
    }

    public StatementList(){
    }

    public String toString(int t){
        if(stmts == null){
            if(stmt != null){
                return stmt.toString(t);
            }
            return "";
        }
        else{
            return stmt.toString(t) + stmts.toString(t);
        }
    }

    public TypeInfo typeCheck() throws TypeException{
        if(stmts == null){
            if(stmt != null){
                TypeInfo ti = stmt.typeCheck();
                if(!ti.isNull()){
                    return ti;
                }
                return new TypeInfo();
            }
            return new TypeInfo();
        }
        else{
            TypeInfo ti = stmt.typeCheck();
            TypeInfo stmtsTi = stmts.typeCheck();
            if(!ti.isNull()){
                return ti;
            }
            else{
                return stmtsTi;
            }
        }
    }
}
