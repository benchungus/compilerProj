public class OptionalFinal extends Token{
    String op;

    public OptionalFinal(String o){
        op = "FINAL";
    }

    public OptionalFinal(){
        op = "null";
    }

    public String toString(int t){
        if(op.equals("FINAL")){
            return "FINAL";
        }
        else if(op.equals("null")){
            return "";
        }
        else{
            return "ERROR IN OPTIONALFINAL";
        }
    }
}
