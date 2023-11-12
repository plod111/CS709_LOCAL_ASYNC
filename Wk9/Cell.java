public class Cell {
    
    private int state;

    public Cell() {
        this.state = 0;  
    }

    //(int)(Math.floor(Math.random() * 2));

    public boolean isOff() {
        return state == 0;
    }

    public boolean isOn() {
        return state == 1;
    }

    public void turnOff() {
        this.state = 0;
    }

    public void turnOn() {
        this.state = 1;
    }

    public int getState() {
        return state;
    }
    


}
