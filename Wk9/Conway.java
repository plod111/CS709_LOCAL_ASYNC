/**
 * CS 709 - Week 9
 * 
 * Conways Game of Life
 * 
 * @author P.Chu
 * @editor B.Cornish
 * @date Nov 10, 2023
 */

public class Conway {
    
    /**
     * Plays the game of life
     * @param grid
     */
    public static void playGame(Grid grid) {
        grid.printGrid();
        //System.out.println("Beginning\n");    
        for (int i = 0; i < 50; i++) {
            //System.out.println("Generation: "+i);
            grid.calculateNext();
            grid.printGrid();
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        //create grid 
        Grid grid = new Grid(10, 10);
        
        //play game grid.game() ?
        playGame(grid);

        
    }
}
