package MVC.Model;

import java.awt.*;
public class Scania extends Truck {
    private double angle;
    private PlatformScania platform;

    public Scania() {
        super(Color.white,"Scania", 400,2,12000,2600);
        this.angle = 0;
        platform = new PlatformScania();
        stopEngine();
        image = readImage(modelName);
    }

    protected double getAngle() {
        return this.angle;
    }
    protected void setAngle(double angle) {
        this.angle = angle;
    }

    public void raiseScania(double amount){
        if (this.getAngle() >= 70 || this.currentSpeed != 0) {
            throw new IllegalArgumentException("Cannot raise platform");
        }
        if ((this.getAngle() + amount) <= 70) {
            setAngle(platform.raise(amount, this.getAngle()));
        }

        System.out.println(this.angle);
    }

    public void lowerScania(double amount){
        if (this.getAngle() <= 0 || this.currentSpeed != 0) {
            throw new IllegalArgumentException("Cannot lower platform");
        }
        if ((this.getAngle() - amount) >= 0 ) {
            setAngle(platform.lower(amount, this.getAngle()));
        }

        System.out.println(this.angle);
    }

    @Override
    public double speedFactor() {

        System.out.println(this.angle);

        return this.angle == 0 ? enginePower * 0.01 : 0;
    }

    @Override
    public void incrementSpeed(double amount) {

        System.out.println(this.angle);

        if (this.angle == 0) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        }
    }

    @Override
    public void decrementSpeed(double amount) {

        System.out.println(this.angle);

        if (this.angle == 0) {
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        }
    }

    @Override
    public boolean isLoadable(){ return false;}
    @Override
    public boolean hasTurbo() { return false;}
    @Override
    public boolean hasPlatform() { return true;}
}
