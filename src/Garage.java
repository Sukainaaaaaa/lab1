import java.util.*;

public class Garage<T extends Vehicle> {
    private String name;
    private int maxAmount;
    public List<T> vehicles;

    public Garage(String name, int maxAmount) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.vehicles = new ArrayList<T>();
    }
    protected void admitVehicle(T car) {
        if ((vehicles.size() < maxAmount)) {
            vehicles.add(car);
        }
    }
    protected void pickUpVehicle(T car) {
        vehicles.remove(car);
    }
}
