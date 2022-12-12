import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    ArrayList<HashMap<String, TypeInfo>> table;
    public SymbolTable(){
        table = new ArrayList<>();
        table.add(new HashMap<>());
    }
    
    public TypeInfo get(String s) throws TypeException{
        for (int i = table.size()-1; i > -1; i--){
            HashMap<String, TypeInfo> map = table.get(i);
            if(map.containsKey(s)){
                if(map.get(s).getMethod() == false){
                    return map.get(s);
                }
            }
        }
        throw new TypeException("Error: variable not declared " + s);
    }

    public TypeInfo getMethod(String s) throws TypeException{
        for (int i = table.size()-1; i > -1; i--){
            HashMap<String, TypeInfo> map = table.get(i);
            if(map.containsKey(s)){
                if(map.get(s).getMethod() != false){
                    return map.get(s);
                }
            }
        }
        throw new TypeException("Error: method not declared " + s);
    }

    public void addVar(String id, TypeInfo t) throws TypeException{
        HashMap<String, TypeInfo> map = table.get(table.size()-1);
        if(map.containsKey(id)){
            throw new TypeException("Error: tried to redeclare " + id);
        }
        map.put(id, t);
    }

    public void addMethod(String id, TypeInfo t) throws TypeException{
        HashMap<String, TypeInfo> map = table.get(table.size()-1);
        if(map.containsKey(id)){
            throw new TypeException("Error: tried to redeclare " + id);
        }
        map.put(id, t);
    }

    public void startScope(){
        table.add(new HashMap<>());
    }

    public void endScope(){
        table.remove(table.size()-1);
    }

}
