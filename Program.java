class Program extends Token {
     String id;
     MemberDecls memberdecls;
     String op;
    //Constructor
    public Program(String i) {
      id = i;
      op = "empty";
    }

    public Program(String i, MemberDecls md) {
      id = i;
      memberdecls = md;
      op = "full";
    }
  
    public String toString(int t) {
      if(op.equals("empty")){
        return "Program:\n" + getTabs(t) + "class " + id + "{\n" + getTabs(t) + "}\n";
      }
      else{
        return "Program:\n" + getTabs(t) + "class " + id + "{\n" + memberdecls.toString(t+1) + getTabs(t) + "}\n";
      }
    }
  }