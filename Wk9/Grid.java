/**
 * CS 709 - Week 9
 * 
 * Conways Game of Life
 * 
 * @author P.Chu
 * @editor B.Cornish
 * @date Nov 10, 2023
 */

public class Grid {

    // Text-based canvas of Cells
    public Cell[][] canvas;

    /**
     * Constructor
     * 
     * @param r
     * @param c
     */
    public Grid(int r, int c) {
        canvas = new Cell[r][c];

        // loop and fill
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                canvas[i][j] = new Cell();
            }
        }

        // turn on some random cells
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
        canvas[(int)Math.floor(Math.random()*(r))][(int)Math.floor(Math.random()*(c))].turnOn();
    }


    /**
     * Prints the grid
     */
    public void printGrid() {

        // loop and print
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                System.out.print(canvas[i][j].getState() == 0 ? "-" : "X");
            }
            System.out.println();
        }
        System.out.println();

        // pause
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
    }


    /**
     * Calculates the next generation
     */
    public void calculateNext() { // uses current param and returns next gen

        // temp Grid
        Cell[][] next = new Cell[canvas.length][canvas[0].length];

        // loop and fill
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next[0].length; j++) {
                next[i][j]= new Cell(); // assign next gen into current gen
            }
        }

        // loop thru entire grids
        int neighborCount = 0;

        for (int i = 1; i < canvas.length - 1; i++) {
            for (int j = 1; j < canvas[0].length - 1; j++) {

                Cell cell = canvas[i][j]; // pull current gen cell

                neighborCount = findNeighbors(i, j); // find neighbors
                // System.out.println(i + "," + j);

                Cell nextCell = next[i][j]; // just 0

                // apply rules of GoL
                if (cell.isOn() && neighborCount < 2) {
                    nextCell.turnOff();
                } else if (cell.isOn() && neighborCount > 3) {
                    nextCell.turnOff();
                } else if (cell.isOff() && neighborCount == 3) {
                    nextCell.turnOn();
                } else {
                     nextCell = cell;
                }

                next[i][j] = nextCell; // store in next gen grid
                //System.out.println("("+i+","+j+")"+"= "+neighborCount);
            }
        }

        // loop thru entire grids
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                canvas[i][j] = next[i][j]; // assign next gen into current gen
            }
        }
    }


    /**
     * Finds the number of neighbors
     * 
     * @param x
     * @param y
     * @return
     */
    public int findNeighbors(int x, int y) {
        int liveNeighborCount = 0;
     
        for (int i = x - 1; i <= x+1; i++){
            // if (i < 0 || i >= canvas.length) {
            //     continue;        
            // }            
            for (int j = y - 1; j <= y + 1; j++) {
                // if ( j < 0 || j>=  canvas[0].length) {
                //     System.out.println("what??");
                //     continue;   
                // }
                
                Cell cell = canvas[i][j];
                
                // ignore the cell itself
                if (i == x && j == y) {
                    if (cell.isOn()) {
                        liveNeighborCount--;
                    }
                }
                
                // count the live neighbors
                liveNeighborCount += cell.getState();
            }
        }
        return liveNeighborCount;
    }


    /**
     * Returns the cell at the given coordinates
     * 
     * @param x
     * @param y
     * @return
     */
    public Cell getCell(int x, int y) {
        return canvas[x][y];
    }



}