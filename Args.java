import java.util.ArrayList;

public class Args extends Token{
    Expr expr;
    Args args;

    public Args(Expr n1){
        expr = n1;
    }

    public Args(Expr e, Args a){
        expr = e;
        args = a;
    }

    public String toString(int t){
        if(args == null){
            return expr.toString(0);
        }
        else{
            return expr.toString(0) + " , " + args.toString(0);
        }
    }

    public ArrayList<TypeInfo> typeCheck() throws TypeException{
        if(args == null){
            ArrayList<TypeInfo> al = new ArrayList<>();
            al.add(expr.typeCheck());
            return al;
        }
        else{
            TypeInfo ti = expr.typeCheck();
            ArrayList<TypeInfo> al = args.typeCheck();
            al.add(0, ti);
            return al;
        }
    }
}
