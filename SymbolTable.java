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
        System.out.println("looking for method " + s + " size: " + table.size());
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
        if(map.containsKey(id) && map.get(id).getMethod() == false){
            throw new TypeException("Error: tried to redeclare variable " + id);
        }
        map.put(id, t);
    }

    public void addMethod(String id, TypeInfo t) throws TypeException{
        HashMap<String, TypeInfo> map = table.get(table.size()-1);
        if(map.containsKey(id) && map.get(id).getMethod() != false){
            throw new TypeException("Error: tried to redeclare method " + id);
        }
        map.put(id, t);
    }

    public void startScope(){
        table.add(new HashMap<>());
        System.out.println("started, new size: " + table.size());
    }

    public void endScope(){
        table.remove(table.size()-1);
        System.out.println("ended, new size: " + table.size());
    }

    public void printAll(){
        System.out.println("Printing all");
        for(int i = 0; i < table.size(); i++){
            System.out.println("Level " + i);
            HashMap<String, TypeInfo> map = table.get(i);
            for(String x : map.keySet()){
                if(map.get(x).isMethod){
                    System.out.println(x);
                }
            }
        }
    }

}
