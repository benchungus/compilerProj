public class ReadList extends Token{
    private Name name;
    private ReadList read;

    public ReadList(Name n) {
        name = n;
    }

     public ReadList(Name n, ReadList rl){
        name = n;
        read = rl;
    }

  public String toString(int t) {
    if(read == null){
        return name.toString(t);
    } 
    else{
        return name.toString(t) + ", " + read.toString(t);
    }
  }

  public TypeInfo typeCheck() throws TypeException{
    if(read == null){
      TypeInfo ti = name.typeCheck();
      if(ti.isFinal){
        throw new TypeException("Error: Cannot read from final");
      }
      if(ti.isArray){
        throw new TypeException("Error: Cannot read from array");
      }
      if(ti.isMethod){
        throw new TypeException("Error: Cannot read from method");
      }
      return ti;
    } 
    else{
      TypeInfo ti = name.typeCheck();
      if(ti.isFinal){
        throw new TypeException("Error: Cannot read from final");
      }
      if(ti.isArray){
        throw new TypeException("Error: Cannot read from array");
      }
      if(ti.isMethod){
        throw new TypeException("Error: Cannot read from method");
      }
      read.typeCheck();
      return ti;
    }
  }
}
