public class Grid {

    public Cell[][] canvas;

    public Grid(int r, int c) {
        canvas = new Cell[r][c];

        // loop and fill
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                canvas[i][j] = new Cell();
            }
        }

        // canvas[1][1].turnOn();
        // canvas[2][2].turnOn();
        // canvas[1][2].turnOn();
        // canvas[4][2].turnOn();
        // canvas[3][2].turnOn();
        // canvas[2][2].turnOn();
        // canvas[6][4].turnOn();
        // canvas[6][3].turnOn();
        // canvas[6][2].turnOn();

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

    public void printGrid() {
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                
                System.out.print(canvas[i][j].getState() == 0 ? "-" : "X");
            }
            System.out.println();
        }
        System.out.println();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
    }


    

  


    public void calculateNext() { // uses current param and returns next gen

        // temp Grid
        Cell[][] next = new Cell[canvas.length][canvas[0].length];

        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next[0].length; j++) {
                next[i][j]= new Cell(); // assign next gen into current gen
            }
        }


        int neighborCount = 0;

        for (int i = 1; i < canvas.length - 1; i++) {
            for (int j = 1; j < canvas[0].length - 1; j++) {

                Cell cell = canvas[i][j]; // pull current gen cell

                neighborCount = findNeighbors(i, j); // find neighbors
                // System.out.println(i + "," + j);

                Cell nextCell = next[i][j]; // just 0

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

                if (i == x && j == y) {
                    if (cell.isOn()) {
                        liveNeighborCount--;
                    }
                }
                
                liveNeighborCount += cell.getState();
            }
        }
        return liveNeighborCount;
    }

    public Cell getCell(int x, int y) {
        return canvas[x][y];
    }



}