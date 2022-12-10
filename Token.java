abstract class Token {

    protected static SymbolTable symbolTable;

    protected String getTabs(int t)
    {
        String tabs = "";
        for (int i = 0; i < t; i++)
            tabs = tabs + "\t";
        return tabs;
    }
  
    public String toString(int t)
    {
        return "";
    }

    public void canConvert(TypeInfo lhs, TypeInfo rhs) throws TypeException{
        if(!lhs.getType().equals(rhs.getType())){
            if(lhs.getType().equals("bool")){
                if(!rhs.getType().equals("int")){
                    throw new TypeException("Error: cannot assign " + rhs.getType() + " to variable defined as type " + lhs.getType());
                }
            }
            else if(lhs.getType().equals("float")){
                if(!rhs.getType().equals("int")){
                    throw new TypeException("Error: cannot assign " + rhs.getType() + " to variable defined as type " + lhs.getType());
                }
            }
            else if(!lhs.getType().equals("string")){
                throw new TypeException("Error: cannot assign " + rhs.getType() + " to variable defined as type " + lhs.getType());
            }
        }
    }
    

    //abstract TypeInfo typeCheck() throws TypeException;
  }
  