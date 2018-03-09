import java.awt.Point;

public class Pigeon {
	
	private Boolean isAwake;
	private Boolean isAffraid;
	
	private Point position;
	private int vitesseDeplacement = 5;
	
	private Food closestFood = null;
	
	public Pigeon() {
		this.setPosition(new Point((int)(50 + Math.random() * (1230 - 50)), (int)(50 + Math.random() * (670 - 50))));
	}
	
	public void Affraid() {
		setPosition(new Point((int)(50 + Math.random() * (1230 - 50)), (int)(50 + Math.random() * (670 - 50))));
	}

	
	public Boolean getAwake() {
		return isAwake;
	}
	public void setAwake(Boolean isAwake) {
		this.isAwake = isAwake;
	}


	public Boolean getAffraid() {
		return isAffraid;
	}
	public void setAffraid(Boolean isAffraid) {
		this.isAffraid = isAffraid;
	}


	public Point getPosition() {
		return position;
	}


	public void setPosition(Point position) {
		this.position = position;
	}

	public Food getClosestFood() {
		return closestFood;
	}

	public void setClosestFood(Food closestFood) {
		this.closestFood = closestFood;
	}

}
