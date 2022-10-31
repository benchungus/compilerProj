class Program extends Token {
    private Expr expr;
    private Args args;
    private StatementList stmts;
    //Constructor
    // public Program(ParExp e) {
    //     expr = e;
    // }

    public Program(Args e) {
      args = e;
    }

    public Program(Expr e) {
      expr = e;
    }

    public Program(StatementList s) {
      stmts = s;
    }
  
    public String toString(int t) {
      if(expr != null){
        return "Program:\n" + expr.toString(t+1) + "\n";
      }
      return "Program:\n" + stmts.toString(t+1) + "\n";
    }
  }