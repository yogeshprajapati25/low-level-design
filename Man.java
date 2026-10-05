
public class Man {
    public static void main(String[] args) {
        System.out.println("Hello world");

        controlStrategy fan = new fanStrategy();

        Appliance Fan = new Fan(fan);

        Regulator regulator = new Regulator(Fan);

        
        wallSwitch wallSwitch = new wallSwitch(Fan);

        wallSwitch.pressOn();
        
        regulator.setKnobLimit(0, 5);

        regulator.rotateTo(3);






    }
}

interface Appliance {
    // What can ANY appliance do? 
    void turnOn();
    void turnOff();
    void setLevel(int level);

}

interface controlStrategy { 
    // How do we map a raw input (like knob position 1-5) to a device-specific value
    int mapKnobPosition(int knobPosition);
}


class fanStrategy implements controlStrategy{

    @Override 
    public int mapKnobPosition(int knobPosition){
        int speed = knobPosition*120;
        return speed;
    }
}

class Regulator{
    int minimumknob;
    int maximumknob;
    private Appliance device;


    void setKnobLimit(int minimum,int maximum){
        this.minimumknob = minimum;
        this.maximumknob = maximum;
    }
    Regulator(Appliance device ){
        this.device = device;
    }

    void rotateTo(int position){
        if(check(position)){
            System.out.println("Set the knob to "+ position);
            device.setLevel(position);
        }
    }

    boolean check(int position){
        if(position >= minimumknob && position <=maximumknob) return true;
        return false;
    }

}


class wallSwitch{
    private Appliance device;

    wallSwitch(Appliance device){
        this.device = device;
    }
    void pressOn(){
        device.turnOn();
    }

    void pressOff(){
        device.turnOff();
    }
}

class Fan implements Appliance {
    private boolean isOn;
    private int currentSpeed;
    private controlStrategy controlStrategy;

    // Constructor Injection: pass the strategy from outside
    public Fan(controlStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
        this.isOn = false;
        this.currentSpeed = 0;
    }

    @Override
    public void turnOn() {
        System.out.println("Fan is turned ON.");
        isOn = true;
        // Optional: when turned back on, resume previous speed if desired
    }

    @Override
    public void turnOff() {
        System.out.println("Fan is turned OFF.");
        isOn = false;
    }

    @Override
    public void setLevel(int level) {
        // Always map and save the target speed so it's remembered
        int speed = controlStrategy.mapKnobPosition(level);
        currentSpeed = speed;

        if (isOn) {
            System.out.println("Fan speed set to: " + currentSpeed);
        } else {
            System.out.println("Speed remembered as " + currentSpeed + ", but fan is currently OFF.");
        }
    }
}