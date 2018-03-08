import java.awt.Point;

public class Food {

	private float freshness;
	private Boolean isFresh = true;
	private long startTime;

	private Point position;

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Food(float freshness, Point position) {
		this.freshness = freshness;
		this.position = position;
	
		this.startTime = System.currentTimeMillis();	
	}
		
	public Boolean checkFreshness() {
		long currentTime = System.currentTimeMillis();

		if(currentTime - startTime > freshness*1000) {
			isFresh = false;
		}else {
			isFresh = true;
		}
		return isFresh;
	}


	public void setFresh(Boolean isFrech){ this.isFresh = isFrech; }

	public Boolean getFresh(){ return this.isFresh; }

}
