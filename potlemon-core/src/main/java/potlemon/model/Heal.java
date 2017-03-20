package potlemon.model;

public class Heal extends Item {
	
	int pv;

	public Heal(String name, String type, int pv) {
		super(name, type);
		this.pv = pv;
		}
	
	
	public void activate(Pokemon p){
		p.addPV(pv);
	}

}
