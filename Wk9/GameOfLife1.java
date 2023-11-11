/**
 * Game Of Life
 * Modelled using Coding Train's Game of Life implementation
 * Implemented using 2D int array - can remap to chars at render time using inChars boolean flag
 * Wrap around option also available via wrap boolean flag
 * 
 * @author B.Cornish
 * @date Nov 4, 2023
 */

public class GameOfLife1 {

    public static void main(String[] args) {

        // canvas size
        int cols = 15;
        int rows = 15;

        // render options
        boolean inChars = true;
        boolean wrap = false;

        // setup
        int[][] arr = setup2DArray(cols, rows);
        System.out.println();

        print2DArray(arr, inChars);

        // run
        for (int i = 0; i < 200; i++) {

            arr = nextGeneration(arr, wrap);
            print2DArray(arr, inChars);
            System.out.println("Generation: " + i + "\n");

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Sets up a 2D array of 0s and 1s
     * 
     * @param cols
     * @param rows
     * @return
     */
    public static int[][] setup2DArray(int cols, int rows) {
        int[][] arr = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                arr[i][j] = (int) Math.floor(Math.random() * 2);
            }
        }
        return arr;
    }

    /**
     * Prints a 2D array of 0s and 1s
     * 
     * @param arr
     * @param inChars
     */
    public static void print2DArray(int[][] arr, boolean inChars) {
        for (int[] row : arr) {
            for (int cell : row) {
                if (inChars) {
                    System.out.print(cell == 1 ? "X " : "  ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Calculates the next generation of a 2D array of 0s and 1s
     * 
     * @param arr
     * @param wrap
     * @return
     */
    public static int[][] nextGeneration(int[][] arr, boolean wrap) {
        int[][] next = new int[arr.length][arr[0].length];
        int neighbors;
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr[0].length - 1; j++) {

                if (wrap) {
                    neighbors = countNeighborsWrap(arr, i, j);
                } else {
                    neighbors = countNeighbors(arr, i, j);
                }
                
                if (arr[i][j] == 1 && neighbors < 2) {
                    next[i][j] = 0;
                } else if (arr[i][j] == 1 && neighbors > 3) {
                    next[i][j] = 0;
                } else if (arr[i][j] == 0 && neighbors == 3) {
                    next[i][j] = 1;
                } else {
                    next[i][j] = arr[i][j];
                }
            }
        }
        return next;
    }

    /**
     * Counts the number of neighbors of a cell in a 2D array of 0s and 1s
     * bounded by the edges
     * 
     * @param arr
     * @param x
     * @param y
     * @return
     */
    public static int countNeighbors(int[][] arr, int x, int y) {
        int neighbors = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            if (i < 0 || i >= arr.length) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0 || j >= arr[0].length) {
                    continue;
                }
                if (i == x && j == y) {
                    continue;
                }
                neighbors += arr[i][j];
            }
        }
        return neighbors;
    }

    /**
     * Counts the number of neighbors of a cell in a 2D array of 0s and 1s
     * wrapping around the edges
     * 
     * @param arr
     * @param x
     * @param y
     * @return
     */
    public static int countNeighborsWrap(int[][] arr, int x, int y) {
        int neighbors = 0;
        for (int i = x - 1; i <= x + 1; i++) {

            if (i < 0) {
                i = arr.length - 1;
            } else if (i >= arr.length) {
                i = 0;
            }

            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0) {
                    j = arr[0].length - 1;
                } else if (j >= arr[0].length) {
                    j = 0;
                }
                if (i == x && j == y) {
                    continue;
                }
                neighbors += arr[i][j];
            }
        }
        return neighbors;
    }

}