import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
	
	private List<Food> foodList = new ArrayList<Food>();
	private List<Pigeon> pigeonList = new ArrayList<Pigeon>();
	
	public Food GetClosestFood(Pigeon pigeon) {
		double MaxDistance = 0;
		int indexMaxDistance = 0;
		for(int i = 0; i<foodList.size(); i++) {
			double currentDistance = GetDistanceBetweenPoint((pigeon.getPosition()), foodList.get(i).getPosition());
			if(currentDistance > MaxDistance && foodList.get(i).checkFreshness()) {
				MaxDistance = currentDistance;
				indexMaxDistance = i;
			}
		}
		return foodList.get(indexMaxDistance);
	}
	
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
	}

	public double GetDistanceBetweenPoint(Point firstPoint, Point secondPoint) {
		return Math.sqrt((firstPoint.x-firstPoint.y)*(firstPoint.x-firstPoint.y) + (secondPoint.x-secondPoint.y)*(secondPoint.x-secondPoint.y));
	}
	
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
		for(int i =0; i < foodList.size(); i++) {
			if(foodList.get(i).equals(foodEaten)) {
				foodList.remove(i);
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
