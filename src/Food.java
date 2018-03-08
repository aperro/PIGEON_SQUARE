import java.awt.Point;

public class Food {

	private float freshness;
	private Boolean isFrech = true;
	private long startTime;

	private Point position;

	public Food(float freshness, Point position) {
		this.freshness = freshness;
		this.position = position;
	
		this.startTime = System.currentTimeMillis();	
	}

	public void checkFreshness() {
		long currentTime = System.currentTimeMillis();

		if(currentTime - startTime > freshness*1000) {
			isFrech = false;
		}else {
			//isFrech = true;
		}
	}


	public void setFresh(Boolean isFrech){ this.isFrech = isFrech; }

	public Boolean getFresh(){ return this.isFrech; }

}
