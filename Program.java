class Program extends Token {
     String id;
     MemberDecls memberdecls;
    ArgDecls argdecls;
     MethodDecl methoddecls;
     FieldDecls fieldDecls;
    //Constructor
     public Program(FieldDecls i, MethodDecl e) {
        //fieldDecls = f;
        //fieldDecls = i;
        fieldDecls = i;
        methoddecls = e;
     }

    public Program(String i, MemberDecls md) {
      //id = i;
      memberdecls = md;
    }
  
    public String toString(int t) {
      //return "Program:\n" + getTabs(t+1) + "class " + id + "{\n" + memberdecls.toString(t+1) + getTabs(t+1) + "}\n";
      return "Program:\n" /*+ fieldDecls.toString(0)*/ + methoddecls.toString(0);
    }
  }