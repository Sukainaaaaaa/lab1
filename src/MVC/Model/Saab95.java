package MVC.Model;

import java.awt.*;

public class Saab95 extends Car{
    private boolean turboOn;

    public Saab95(){
        super(Color.red, "Saab95", 125, 2, 5000, 1900);
	    turboOn = false;
        stopEngine();
        image = readImage(modelName);
    }

    public void setTurboOn(){
	    turboOn = true;
    }
    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
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
    public boolean hasTurbo() { return true;}
    @Override
    public boolean hasPlatform() { return false;}
}
