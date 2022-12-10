public class ReturnType extends Token{
    Type type;
    String op;

    public ReturnType(Type t){
        type = t;
        op = "type";
    }

    public ReturnType(){
        op = "void";
    }

    public String toString(int t){
        if(op.equals("type")){
            return type.toString(0);
        }
        else if(op.equals("void")){
            return "void";
        }
        else{
            return "ERROR IN RETURNTYPE";
        }
    }

    public TypeInfo typeCheck() throws TypeException{
        throw new TypeException("returntype");
    }
}
