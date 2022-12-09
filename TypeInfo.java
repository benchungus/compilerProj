public class TypeInfo{
    boolean isFinal = false;
    String type;
    boolean isArray = false;
    boolean isMethod = false;
    public TypeInfo(){

    }

    public TypeInfo(String t){
        type = t;
    }

    public void setFinal(){
        isFinal = true;
    }

    public void setArray(){
        isArray = true;
    }

    public void setMethod(){
        isMethod = true;
    }

    public boolean getFinal(){
        return isFinal;
    }

    public String getType(){
        return type;
    }

    public boolean getArray(){
        return isArray;
    }

    public boolean getMethod(){
        return isMethod;
    }

}