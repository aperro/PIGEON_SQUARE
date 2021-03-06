import java.awt.Point;

public class Pigeon {

	protected Boolean isAwake;
	public GameManager gameManager;

	protected RushToFood rush;

	protected Point position;
	protected Point afraidNewPosition = null;
	protected int speed = 60;

	protected Food closestFood = null;

	public Pigeon() {
		super();
		this.setPosition(new Point((int)(50 + Math.random() * (1230 - 50)), (int)(50 + Math.random() * (670 - 50))));
		rush = new RushToFood(this, closestFood, gameManager, speed);
	}

	public void Afraid() {
		afraidNewPosition = new Point((int)(50 + Math.random() * (1230 - 50)), (int)(50 + Math.random() * (670 - 50)));
		rush.closestFood = new Food(0.1f, afraidNewPosition);
		
	}

	public Boolean getAwake() {
		return isAwake;
	}
	public void setAwake(Boolean isAwake) {
		this.isAwake = isAwake;
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
		rush.closestFood = closestFood;
	}

	public void Rush()
	{
		/* Chaque Pigeon lancera un thread pour chaque d�placement
		 * qui s'arretera lorsque le pigeon aura atteint et mang� toutes les Food
		 * ou que toutes les Food restantes seront avari�es (closestFood = null)
		 */
		
		if (!rush.isAlive())
		{
			rush = new RushToFood(this, closestFood, gameManager, speed);
			rush.start();
		}
	}
}
