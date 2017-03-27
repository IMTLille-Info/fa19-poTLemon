package potlemon.core.tools;


public enum MapKeys {
    BLOCKED ("blocked"), MAP("map"), WATER("water"), HERB("he");
    
    private String value = "";
    
    MapKeys(String value){
        this.value=value;
    }

    public String getString(){
        return this.value;
    }

    @Override
    public String toString() {
        return getString();
    }
}
