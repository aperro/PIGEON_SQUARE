import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

	private List<Food> foodList = new ArrayList<Food>();
	private List<Pigeon> pigeonList = new ArrayList<Pigeon>();

	public void CreatePigeonList (int size)
	{
		for(int i =0; i<size; i++) {

			pigeonList.add(new Pigeon());
		}
	}

	public void AddFood (int x, int y)
	{
		int xx = x-9;
		int yy = y-38;
		if (xx < 0) xx += 9;
		if (yy < 0) yy += 38;
		System.out.println("Mouse Clicked at X: " + xx + " - Y: " + yy);
		foodList.add(new Food((float) (10 + Math.random() * (15 - 10)), new Point(xx,yy)));

		for(int i = 0; i<pigeonList.size(); i++) {
			pigeonList.get(i).setClosestFood(FindClosestFood(pigeonList.get(i)));
		}
	}
/*
	public double GetDistanceBetweenPoint(Point firstPoint, Point secondPoint) {
		return Math.sqrt((firstPoint.x-firstPoint.y)*(firstPoint.x-firstPoint.y) + (secondPoint.x-secondPoint.y)*(secondPoint.x-secondPoint.y));
	}
*/
	public void CheckFreshnessFoodList() {
		for(int i =0; i < foodList.size(); i++) {
			foodList.get(i).checkFreshness();
		}
	}

	public void isFoodListEmpty() {
		if(foodList.isEmpty()) {
			for(int i =0; i < pigeonList.size(); i++) {
				pigeonList.get(i).setAwake(false);
			}
		}
	}

	public void FoodEaten(Food foodEaten) {
		
		boolean iseaten = foodList.remove(foodEaten);
		Food closestFood;
		for (int i=0; i<pigeonList.size(); i++)
		{
			closestFood = FindClosestFood(pigeonList.get(i));
			pigeonList.get(i).setClosestFood(closestFood);
		}
		System.out.println("is eaten = " + iseaten);
		
		
	}

	// DO ALL THE JOB thanks to the next two methods
	public void DetectFoodAndMovePigeon() {
		for(int i = 0; i<pigeonList.size(); i++) {
			
			MovePigeonToFood(pigeonList.get(i));
		}
	}

	public Food FindClosestFood(Pigeon pigeon) {
		double MinDistance = 2000;
		int indexMinDistance = 0;
		boolean isFreshFoodNear = false;
		
		if(!foodList.isEmpty())
		{
			for(int i = 0; i<foodList.size(); i++) {
				double currentDistance = pigeon.getPosition().distance(foodList.get(i).getPosition());
				if(currentDistance < MinDistance && foodList.get(i).checkFreshness()) {
					MinDistance = currentDistance;
					indexMinDistance = i;
					isFreshFoodNear = true;
				}
			}
			//System.out.println(foodList.get(indexMinDistance));
			if (isFreshFoodNear) return foodList.get(indexMinDistance);
		}
		return null;
	}

	public void MovePigeonToFood(Pigeon pigeon) {

		if(pigeon.getClosestFood() != null) {
			Food closestFood = pigeon.getClosestFood();
			
			int foodXposition = closestFood.getPosition().x;
			int foodYposition = closestFood.getPosition().y;

			int currentXPigeon = pigeon.getPosition().x;
			int currentYPigeon = pigeon.getPosition().y;

			if(Math.abs(currentXPigeon - foodXposition) >= 2 || Math.abs(currentYPigeon - foodYposition) >= 2) {
				
				if(Math.abs(currentYPigeon - foodYposition) >= 2) {
					if(foodYposition > currentYPigeon) currentYPigeon+=2;
					if(foodYposition < currentYPigeon) currentYPigeon-=2;
				}
				if( Math.abs(currentXPigeon - foodXposition) >= 2) {
					if(foodXposition > currentXPigeon) currentXPigeon+=2;
					if(foodXposition < currentXPigeon) currentXPigeon-=2;
				}
				pigeon.setPosition(new Point(currentXPigeon, currentYPigeon));
			}else {
				// DELETE FOOD
				FoodEaten(closestFood);
				//System.out.println("Pigeon Position is : x =" + currentXPigeon + " y = " + currentYPigeon);
			}
		}
	}

	/*
	 * Getter && Setter
	 */
	public List<Pigeon> getPigeonList() {
		return pigeonList;
	}

	public void setPigeonList(List<Pigeon> pigeonList) {
		this.pigeonList = pigeonList;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> food) {
		this.foodList = food;
	}
}
