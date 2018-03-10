import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameManager {

	private List<Food> foodList = new ArrayList<Food>();
	private List<Pigeon> pigeonList = new ArrayList<Pigeon>();

	public void CreatePigeonList (int ramier, int colombin, int biset)
	{
		for(int i = 0; i<ramier; i++) {

			Ramier p = new Ramier();
			p.gameManager = this;
			pigeonList.add(p);
		}
		for(int i = 0; i<colombin; i++) {

			Colombin p = new Colombin();
			p.gameManager = this;
			pigeonList.add(p);
		}
		for(int i = 0; i<biset; i++) {

			Biset p = new Biset();
			p.gameManager = this;
			pigeonList.add(p);
		}
	}

	public void AddFood (int x, int y)
	{
		int xx = x-9;
		int yy = y-38;
		if (xx < 0) xx += 9;
		if (yy < 0) yy += 38;
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
		if (foodEaten.checkFreshness())
		{
			boolean iseaten = foodList.remove(foodEaten);
			System.out.println("Pigeon a mangé la nourriture : " + iseaten);
		} else
		{
			System.out.println("Food not fresh anymore");
		}
		Food closestFood;
		for (int i=0; i<pigeonList.size(); i++)
		{
			closestFood = FindClosestFood(pigeonList.get(i));
			pigeonList.get(i).setClosestFood(closestFood);
		}
		
		
	}

	// DO ALL THE JOB thanks to the next two methods
	public void DetectFoodAndMovePigeon() {
		for(int i = 0; i<pigeonList.size(); i++) {
			
			pigeonList.get(i).Rush();
			
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
