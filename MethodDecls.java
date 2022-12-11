import java.util.ArrayList;

public class MethodDecls extends Token {
    ReturnType returntype;
    String id;
    ArgDecls argdecls;
    FieldDecls fielddecllist;
    StatementList stmts;
    OptionalSemi optionalsemi;
    MethodDecls methoddecllist;
    String op;

    public MethodDecls(ReturnType rt, String i, ArgDecls ads, FieldDecls fdl, StatementList ss, OptionalSemi os, MethodDecls mdl){
        returntype = rt;
        id = i;
        argdecls = ads;
        fielddecllist = fdl;
        stmts = ss;
        optionalsemi = os;
        methoddecllist = mdl;
        op = "mdl";
    }

    public MethodDecls(ArgDecls ads, FieldDecls fdl, StatementList ss, OptionalSemi os, MethodDecls mdl){
        argdecls = ads;
        fielddecllist = fdl;
        stmts = ss;
        optionalsemi = os;
        methoddecllist = mdl;
        op = "mde";
    }

    public MethodDecls(){
        op = "null";
    }

    public String toString(int t){
        if(op.equals("mdl")){
            return getTabs(t) + returntype.toString(t) + " " + id + " (" + argdecls.toString(t)+
        ") {\n" + fielddecllist.toString(t+1) + " " + stmts.toString(t+1) + getTabs(t) + "} " + optionalsemi.toString(0) + "\n" + methoddecllist.toString(t);
        }
        else if(op.equals("mde")){
            return "(" + argdecls.toString(t)+ ") {\n" + fielddecllist.toString(t+1) +
             " " + stmts.toString(t+1) + getTabs(t) + "} " + optionalsemi.toString(0) + "\n" + methoddecllist.toString(t);
        }
        else if(op.equals("null")){
            return "";
        }
        else{
            return "ERROR IN METHODDECLS";
        }
    }

    public TypeInfo typeCheck() throws TypeException{
        if(op.equals("mdl")){
            TypeInfo ti = new TypeInfo(returntype.toString(0));
            ti.setMethod();
            ArrayList<TypeInfo> al = argdecls.typeCheck(ti);
            ti.importArray(al);
            symbolTable.addMethod(id, ti);
            System.out.println("added method " + id + " with " + ti.getParams().size() + " parameters");
            symbolTable.startScope();
            fielddecllist.typeCheck();
            stmts.typeCheck();
            symbolTable.endScope();
            methoddecllist.typeCheck();
            return new TypeInfo();
        }
        else{
            return new TypeInfo();
        }
    }

    public TypeInfo typeCheck(TypeInfo ti, String id) throws TypeException{
        if(op.equals("mde")){
            ArrayList<TypeInfo> al = argdecls.typeCheck(ti);
            ti.importArray(al);
            symbolTable.addMethod(id, ti);
            System.out.println("added method " + id + " with " + ti.getParams().size() + " parameters");
            symbolTable.startScope();
            fielddecllist.typeCheck();
            stmts.typeCheck();
            symbolTable.endScope();
            methoddecllist.typeCheck();
            return new TypeInfo();
        }
        else{
            return new TypeInfo();
        }
    }

    

}
