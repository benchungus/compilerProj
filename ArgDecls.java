public class ArgDecls extends Token{
    ArgDeclList argdecllist;
    String op;

    public ArgDecls(ArgDeclList adl){
        argdecllist = adl;
        op = "adl";
    }

    public ArgDecls(){
        op = "null";
    }

    public String toString(int t){
        if(op.equals("adl")){
            return argdecllist.toString(0);
        }
        else if(op.equals("null")){
            return "";
        }
        else{
            return "ERROR IN ARGDECLS";
        }
    }
}
