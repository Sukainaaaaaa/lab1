import java.awt.*;

public class Volvo240 extends Vehicle{
    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(Color.black, "Volvo240", 100, 4, 4800, 1700);
        stopEngine();
        image = readImage(modelName);
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }

    @Override
    public void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    @Override
    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    @Override
    public boolean isLoadable(){
        return true;
    }

    @Override
    public boolean hasTurbo() { return false;}

    @Override
    public boolean hasPlatform() { return false;}
}
