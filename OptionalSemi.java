public class OptionalSemi extends Token{
    String accept;
    public OptionalSemi(String s){
        accept = s;
    }

    public String toString(int t){
        if(accept == "accept"){
            return ";";
        }
        else{
            return "";
        }
    }
}