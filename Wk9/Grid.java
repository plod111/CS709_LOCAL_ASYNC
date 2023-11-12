public class Grid {
    private int row;
    private int col;

    static Cell[][] canvas;

    public Grid(int r, int c) {
        this.canvas = new Cell[r][c];

        // loop and fill
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                canvas[i][j] = new Cell();
            }
        }

        canvas[3][3].turnOn();
        canvas[3][4].turnOn();
        canvas[3][5].turnOn();
    }

    public void printGrid() {
        for (int i = 1; i < canvas.length - 1; i++) {
            for (int j = 1; j < canvas[0].length - 1; j++) {
                System.out.print(canvas[i][j].getState());
            }
            System.out.println();
        }
        System.out.println();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
    }

    public Grid calculateNext() { // uses current param and returns next gen

        // temp Grid
        Grid next = new Grid(canvas.length, canvas[0].length);

        int neighborCount = 0;

        for (int i = 1; i < canvas.length - 1; i++) {
            for (int j = 1; j < canvas[0].length - 1; j++) {

                Cell cell = canvas[i][j]; // .getCell(i,j);
                neighborCount = findNeighbors(i, j);
                System.out.println("(" + i + "," + j + ")" + "=" + neighborCount);

                Cell nextCell = next.getCell(i, j);

                if (cell.isOn() && neighborCount < 2) {
                    nextCell.turnOff();
                } else if (cell.isOn() && neighborCount > 3) {
                    nextCell.turnOff();
                } else if (cell.isOff() && neighborCount == 3) {
                    nextCell.turnOn();
                } else {
                    nextCell = cell;
                }
            }
        }
        return next;
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
                
                Cell cell = canvas[i][j]; //.getCell(i,j);
                if( i==x && j==y){
                    if(cell.isOn()){
                        liveNeighborCount --;
                    }
                        
                    liveNeighborCount += cell.getState();
                    System.out.println(canvas[i][j]);
                
                    
                }
            }
        }
        return liveNeighborCount;
    }

    public Cell getCell(int x, int y) {
        return canvas[x][y];
    }



}