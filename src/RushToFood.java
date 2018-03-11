import java.awt.Point;

public class RushToFood extends Thread {

	public Pigeon pigeon;
	public Food closestFood;
	public GameManager gameManager;
	public float speed;

	public RushToFood(Pigeon pigeon, Food closestFood, GameManager gm, float speed)
	{
		this.pigeon = pigeon;
		this.closestFood = closestFood;
		this.gameManager = gm;
		this.speed = speed;
	}

	@Override
	public void run() {
		int currentXPigeon;
		int currentYPigeon;
		
		while(closestFood != null) {
			int foodXposition = closestFood.getPosition().x;
			int foodYposition = closestFood.getPosition().y;

			currentXPigeon = pigeon.getPosition().x;
			currentYPigeon = pigeon.getPosition().y;

			if(Math.abs(currentXPigeon - foodXposition) >= 2 || Math.abs(currentYPigeon - foodYposition) >= 2) {

				if(Math.abs(currentYPigeon - foodYposition) >= 2) {
					if(foodYposition > currentYPigeon) currentYPigeon++;
					if(foodYposition < currentYPigeon) currentYPigeon--;
				}
				if( Math.abs(currentXPigeon - foodXposition) >= 2) {
					if(foodXposition > currentXPigeon) currentXPigeon++;
					if(foodXposition < currentXPigeon) currentXPigeon--;
				}
				pigeon.setPosition(new Point(currentXPigeon, currentYPigeon));
			}else {
				// eat the food
				gameManager.FoodEaten(closestFood);
				closestFood = null;
				//System.out.println("Pigeon Position is : x =" + currentXPigeon + " y = " + currentYPigeon);
			}
			try {
				sleep((long)(1000/speed));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
