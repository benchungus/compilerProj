import java.util.ArrayList;

public class ArgDeclList extends Token{
    ArgDecl argdecl;
    ArgDeclList argdecllist;
    String op;

    public ArgDeclList(ArgDecl ad, ArgDeclList adl){
        argdecl = ad;
        argdecllist = adl;
        op = "list";
    }

    public ArgDeclList(ArgDecl ad){
        argdecl = ad;
        op = "ad";
    }

    public String toString(int t){
        if(op.equals("list")){
            return argdecl.toString(0) + ", " + argdecllist.toString(0);
        }
        else if(op.equals("ad")){
            return argdecl.toString(0);
        }
        else{
            return "ERROR IN ARGDECLLIST";
        }
    }

    public ArrayList<TypeInfo> typeCheck() throws TypeException{
        if(op.equals("list")){
            TypeInfo ti = argdecl.typeCheck();
            ArrayList<TypeInfo> al = argdecllist.typeCheck();
            al.add(0, ti);
            return al;
        }
        else{
            ArrayList<TypeInfo> al = new ArrayList<>();
            al.add(0, argdecl.typeCheck());
            return al;
        }
    }
}
