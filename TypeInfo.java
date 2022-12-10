import java.util.ArrayList;

public class TypeInfo{
    boolean isFinal = false;
    String type;
    boolean isArray = false;
    boolean isMethod = false;
    ArrayList<TypeInfo> params;
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

    public void removeArray(){
        isArray = false;
    }

    public void setMethod(){
        isMethod = true;
        params = new ArrayList<>();
    }

    public void addParam(TypeInfo ti){
        params.add(ti);
    }

    public ArrayList<TypeInfo> getParams(){
        return params;
    }

    public void setType(String t){
        type = t;
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

    public boolean isNull(){
        if(type == null){
            return true;
        }
        return false;
    }

}