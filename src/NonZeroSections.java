/**
 * Created by Dave on 6/17/17.
 *
 * Code Test

 Find the number of “regions” (non-zero values) in a matrix of (m x n)

 First line is the number of rows (m).
 Second  line is the number of columns (n).
 Then it follow with a matrix.

 Input
 5
 6
 0 1 1 0 0 1
 0 0 0 0 0 0
 1 0 0 1 0 1
 1 0 0 1 0 0
 0 0 1 1 0 0

 Output
 5

 Input
 6
 4
 0 1 1 0
 0 0 0 0
 1 0 0 1
 1 0 0 1
 0 0 1 1
 0 0 1 1

 Output
 3

 */

import java.util.Scanner;

public class NonZeroSections {

    // main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your data...");
        int numberOfRows = Integer.parseInt(scanner.nextLine());
        int numberOfColumns = Integer.parseInt(scanner.nextLine());

        int[][] arrayElements = new int[numberOfRows][numberOfColumns];
        int i = 0;
        int j = 0;
        while (scanner.hasNext()) {
            arrayElements[i][j] = Integer.parseInt(scanner.next());
            if (j == numberOfColumns - 1 && i == numberOfRows - 1) {
                break;
            }
            else if (j == numberOfColumns - 1) {
                i++;
                j = 0;
            }
            else {
                j++;
            }
        }
        scanner.close();

        // Print Array
        printArray(arrayElements);

        // Calculate and display the result
        System.out.println("\nNumber of Sections: " + findNumberOfRegions(arrayElements));
    }

    public static int findNumberOfRegions(int[][] a) {
        /* Logic
             1. Iterate over the array in row major order
             2. If the value is zero we can ignore it
             3. If the value != 0 set a boolean flag "sectionStarted to true
             4. Check to see if the section continues
                    (either the space to the right or the space below must be a 1
             5. If neither the row nor the column continues the section is over

             Corner Case @ (2, 3) row and col don't continue but the section isn't over
             1 0 0 0 1
             0 1 1 0 1
             0 0 1 1 0
             0 1 1 0 0


             We don't have to find the whole section at once because as we iterate
             over the array if the col or row continued we will eventually reach that
             spot and see if it continues further.
        */
        int numberOfSections = 0;
        boolean sectionStarted = false;
        boolean rowContinues = false;
        boolean colContinues = false;
        boolean newSection = true;

        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[0].length; col++) {
                // ignore zero spaces as they are not relevant
                if (a[row][col] != 0) {
                    sectionStarted = true;
                    // check what neighbors are in bounds
                    if (row < a.length - 1) {
                        // we can check below us
                        colContinues = a[row + 1][col] > 0;
                        System.out.println("Checking Space: (" + (row + 1) + ", " + col + ")");
                        System.out.println("ColContinues: " + colContinues +"\n");
                        newSection = !colContinues;


                    }
                    if (col < a[0].length - 1) {
                        // we can check to the right of us
                        rowContinues = a[row][col + 1] > 0;
                        System.out.println("Checking Space: (" + row + ", " + (col + 1) + ")");
                        System.out.println("RowContinues: " + rowContinues + "\n");
                        newSection = !rowContinues;
                    }

                    if (newSection) {
                        System.out.println("New Section is true");
                        // check other two possible moves "UP and LEFT"
                        if (row > 0) {
                            newSection = a[row - 1][col] == 0;
                            System.out.println("After checking above " + newSection);
                        }

                        if (newSection && col > 0) {
                            newSection = a[row][col - 1] == 0;
                            System.out.println("After checking left " + newSection);
                        }
                    }
                }



                // section ended if rowContinues/colContinues are both false
                if (!rowContinues && !colContinues && sectionStarted && newSection) {
                    System.out.println("Sections increased at (" + row + ", " + (col) + ")\n");
                    numberOfSections++;
                    sectionStarted = false;
                }

                // we are at the last spot in the array and the section hasn't ended
                // so we need to increment the count
                if (sectionStarted && row == a.length - 1 && col == a[0].length - 1) {
                    numberOfSections++;
                    sectionStarted = false; // not necessary
                }
            }
        }
        return numberOfSections;
    }

    public static void printArray(int[][] a) {
        // Checking Everything
        System.out.println("Number of rows: " + a.length);
        System.out.println("Number of columns " + a[0].length);
        for (int[] row: a) {
            System.out.print("\n");
            for (int x: row) {
                System.out.print(x + " ");
            }
        }
        System.out.println("\n"); // formatting
    }
}
