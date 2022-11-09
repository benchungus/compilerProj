public class OptionalExpr extends Token{
    Expr expr;
    String op;

    public OptionalExpr(Expr e){
        expr = e;
        op = "expr";
    }

    public OptionalExpr(){
        op = "null";
    }

    public String toString(int t){
        if(op.equals("expr")){
            return "= " + expr.toString(0);
        }
        else if(op.equals("null")){
            return "";
        }
        else{
            return "ERROR IN OPTIONALEXPR";
        }
    }
}
