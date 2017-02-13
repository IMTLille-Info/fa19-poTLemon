package core;

public class Pokemon {
	
	private int pv;
	

	public Pokemon() {
		
	}
	
	public Pokemon(int pv) {
		this.pv = pv;
	}

	/**
	 * Remove the amount of pv in parameter
	 * @param lost
	 */
	public void lostPV(int lost) {
		pv -= lost;
	}
	
	/**
	 * Remove the amount of pv in parameter
	 * @param lost
	 */
	public void addPV(int gain) {
		pv += gain;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}
	
	

}
