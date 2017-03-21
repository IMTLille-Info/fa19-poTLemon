package potlemon.tools;


public enum MapKeys {
    BLOCKED ("blocked");
    
    private String value = "";
    
    MapKeys(String value){
        this.value=value;
    }

    public String getString(){
        return this.value;
    }
}
