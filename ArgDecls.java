import java.util.ArrayList;

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

    public ArrayList<TypeInfo> typeCheck(TypeInfo ti) throws TypeException{
        if(op.equals("adl")){
            return argdecllist.typeCheck();
        }
        else {
            return new ArrayList<TypeInfo>();
        }
    }
}
