import java.awt.Point;

public class Food {

	private float freshness;
	private Boolean isFresh = true;
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
			isFresh = false;
		}else {
			//isFrech = true;
		}
	}


	public void setFresh(Boolean isFrech){ this.isFresh = isFrech; }

	public Boolean getFresh(){ return this.isFresh; }

}
