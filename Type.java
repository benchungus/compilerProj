public class Type extends Token {
    String type;

    public Type(String t){
        type = t;
    }

    public String toString(int t) {
        return "type:" + type;
      }
}