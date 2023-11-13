/**
 * CS 709 - Week 9
 * 
 * Conways Game of Life
 * 
 * @author P.Chu
 * @editor B.Cornish
 * @date Nov 10, 2023
 */

public class Cell {
    
    // state = 0 means off
    private int state;

    // constructor
    public Cell() {
        this.state = 0;  
    }

    /**
     * Returns true if the cell is off
     * @return
     */
    public boolean isOff() {
        return state == 0;
    }

    /**
     * Returns true if the cell is on
     * @return
     */
    public boolean isOn() {
        return state == 1;
    }

    /**
     * Turns the cell off
     */
    public void turnOff() {
        this.state = 0;
    }

    /**
     * Turns the cell on
     */
    public void turnOn() {
        this.state = 1;
    }

    /**
     * Returns the state of the cell
     * @return
     */
    public int getState() {
        return state;
    }

}
