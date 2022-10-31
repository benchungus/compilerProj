import java.util.List;
import java.util.LinkedList;

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
}
