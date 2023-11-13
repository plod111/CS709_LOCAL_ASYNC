public class Conway {
    
    public static void playGame(Grid grid) {
        grid.printGrid();
        //System.out.println("Beginning\n");    
        for (int i = 0; i < 50; i++) {
            //System.out.println("Generation: "+i);
            grid.calculateNext();
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
