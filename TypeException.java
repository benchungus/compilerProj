public class TypeException extends Exception{
    String error;
    public TypeException(String s){
        error = s;
    }
    
    public String toString(){
      return error;
    }
}
