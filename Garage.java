import java.util.*;

public class Garage<T extends Car> {
    private String name;
    private int maxAmount;
    public List<T> cars;

    public Garage(String name, int maxAmount) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.cars = new ArrayList<T>();
    }
    protected void admitCar(T car) {
        if ((cars.size() < maxAmount)) {
            cars.add(car);
        }
    }
    protected void pickUpCar(T car) {
        cars.remove(car);
    }
}
