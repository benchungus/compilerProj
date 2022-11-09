public class MemberDecls extends Token{
    FieldDecls fielddecllist;
    MethodDecls methoddecllist;

    public MemberDecls(FieldDecls fdl, MethodDecls mdl){
        fielddecllist = fdl;
        methoddecllist = mdl;
    }


    public String toString(int t){
        return fielddecllist.toString(t+1) + methoddecllist.toString(t+1);
    }
}