public class Conway {
    
    public static void playGame(Grid grid) {
        grid.printGrid();        
        for (int i = 0; i < 1; i++) {
            System.out.println("Generation: "+i);
            grid = grid.calculateNext();
            grid.printGrid();
        }
    }


    public static void main(String[] args) {
        //create grid 
        Grid grid = new Grid(10, 10);
        
        //play game grid.game() ?
        playGame(grid);

        
    }



}
