
public enum MapKeys {
    BLOCKED ("blocked");
    
    private String name = "";
    
    private MapKeys(String name){
        this.name=name;
    }

    public String toString(){
        return this.name;
    }
}
