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
    throw new TypeException("AAAAA");
  }
}
