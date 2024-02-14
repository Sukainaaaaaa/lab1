import java.awt.*;

public class Scania extends Truck {
    private double angle;

    public Scania() {
        super(Color.white,"Scania", 400,2,12000,2600);
        angle = 0;
        stopEngine();
    }

    protected double getAngle() {
        return this.angle;
    }
    protected double setAngle(double angle) {
        return this.angle = angle;
    }

    @Override
    public void raisePlatform(double amount) {
        if (this.angle >= 70 || this.currentSpeed != 0) {
            throw new IllegalArgumentException("Cannot raise platform");
        }
        if ((this.angle + amount) <= 70 && (this.angle >= 0)) {
            this.angle += amount;
        }
    }

    @Override
    public void lowerPlatform(double amount) {
        if (this.angle <= 0 || this.currentSpeed != 0) {
            throw new IllegalArgumentException("Cannot lower platform");
        }
        if ((this.angle - amount) >= 0 && (this.angle <= 70)) {
            this.angle -= amount;
        }
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public void incrementSpeed(double amount) {
        if (this.angle == 0) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        }
    }

    @Override
    public void decrementSpeed(double amount) {
        if (this.angle == 0) {
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        }
    }

    @Override
    public boolean isLoadable() {
        return false;
    }
}
