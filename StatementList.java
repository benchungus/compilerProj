import java.util.ArrayList;

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

    public ArrayList<TypeInfo> typeCheck() throws TypeException{
        if(stmts == null){
            if(stmt != null){
                TypeInfo ti = stmt.typeCheck();
                if(!ti.isNull()){
                    ArrayList<TypeInfo> al = new ArrayList<>();
                    al.add(ti);
                    return al;
                }
                else{
                    ArrayList<TypeInfo> al = new ArrayList<>();
                    if(ti.getParams().size() != 0){
                        for(int i = 0; i < ti.getParams().size(); i++){
                            al.add(ti.getParams().get(i));
                        }
                    }
                    return al;
                }
            }
            return new ArrayList<>();
        }
        else{
            TypeInfo ti = stmt.typeCheck();
            ArrayList<TypeInfo> stmtsTi = stmts.typeCheck();
            if(!ti.isNull()){
                stmtsTi.add(ti);
                return stmtsTi;
            }
            else{
                if(ti.getParams().size() != 0){
                    for(int i = 0; i < ti.getParams().size(); i++){
                        stmtsTi.add(ti.getParams().get(i));
                    }
                }
                return stmtsTi;
            }
        }
    }
}
