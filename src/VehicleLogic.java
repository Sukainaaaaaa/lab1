public class VehicleLogic {
    static void moveit(int x, int y, Vehicle car){
        if(car.hasTurbo()){
            car.getPoint().x = x;
            car.getPoint().y = y;
            return;
        }
        if(car instanceof Scania){
            car.getPoint().x = x;
            car.getPoint().y= y;
            return;
        }
        if (car instanceof Volvo240){
            car.getPoint().x = x;
            car.getPoint().y = y;
        }
    }

    public void bounds(Vehicle car){
        if(CarView.getx()-110 <= car.currentxPos() && car.d == Vehicle.Direction.E ){
            car.currentSpeed = 0;
            car.d = Vehicle.Direction.W;
            car.startEngine();
        }

        if (0 > car.currentxPos() && car.d == Vehicle.Direction.W ) {
            car.currentSpeed = 0;
            car.d = Vehicle.Direction.E;
            car.startEngine();
        }

        if(CarView.gety()-70 <= car.currentxPos() && car.d == Vehicle.Direction.S ){
            car.currentSpeed = 0;
            car.d = Vehicle.Direction.N;
            car.startEngine();
        }

        if(0 > car.currentxPos() && car.d == Vehicle.Direction.N ){
            car.currentSpeed = 0;
            car.d = Vehicle.Direction.S;
            car.startEngine();
        }
    }
    
    public void park(Vehicle car, Garage garage) {
        if ((car.currentxPos() >= 300) && (car.currentyPos() >= 300) && (car.getClass() == Volvo240.class)) {
            car.stopEngine();
            garage.admitVehicle((Volvo240) car);
        }
    }
}
