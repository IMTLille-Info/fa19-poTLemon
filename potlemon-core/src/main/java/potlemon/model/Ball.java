package potlemon.model;

public class Ball extends Item {

	public Ball(String name, String type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}
	
	public void activate(Team t, Pokemon p){
		if(t.size()<t.MAX_SIZE){
			t.add(p);
		}
		
		else{
			//Send pokemon to the secondary team
			return;
		}
	}

}
