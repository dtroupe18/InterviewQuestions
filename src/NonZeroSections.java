/*
 * Created by Dave on 6/17/17.
 *
 * Code Test

 Find the number of “regions” (non-zero values) in a matrix of (m x n)

 First line is the number of rows (m).
 Second  line is the number of columns (n).
 Then it follow with a matrix.

 4
 5
 1 0 0 0 1
 0 1 1 0 1
 0 0 1 1 0
 0 1 1 0 0

 Output = 3

 Input
 5
 6
 0 1 1 0 0 1
 0 0 0 0 0 0
 1 0 0 1 0 1
 1 0 0 1 0 0
 0 0 1 1 0 0

 Output = 5

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

 Input
 4
 5
 1 0 0 0 1
 0 0 1 0 1
 0 0 0 1 0
 0 1 1 1 0

 Output
 4

 Input
 3
 3
 1 1 1
 1 1 1
 1 1 1

 Output
 1

 Input
 3
 3
 0 0 0
 0 0 0
 0 0 0

 Output
 0

 */

import java.util.Scanner;
import java.util.Arrays;

public class NonZeroSections {

    // main method
    public static void main(String[] args) {
        // read in user data
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your data...");
        int numberOfRows = Integer.parseInt(scanner.nextLine());
        int numberOfColumns = Integer.parseInt(scanner.nextLine());

        int[][] arrayElements = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                arrayElements[i][j] = Integer.parseInt(scanner.next());
            }
        }
        scanner.close();

        // Print Array
        printArray(arrayElements);

        // Calculate and display the result
        System.out.println("\nNumber of Sections: " + findNumberOfRegions(arrayElements));
    }

    private static int findNumberOfRegions(int[][] a) {
        /*
        Logic
             1. Iterate over the array in row major order
             2. If the value is zero we can ignore it
             3. If the value != 0 set a boolean flags row/col continues to false
             4. Check the space above and left if either is 1 we don't have a new section
             5. If both are zero we check to the right and above until the row ends
                if it is connected to the right and above at any spot it is NOT a
                new section. This scenario is exhibited in the following test case:
                        4
                        5
                        1 0 0 0 1
                        0 0 1 0 1
                        0 0 0 1 0
                        0 1 1 1 0

                (3, 1) is not a new section
        */
        int numberOfSections = 0;

        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[0].length; col++) {
                // ignore zero spaces as they are not relevant
                if (a[row][col] != 0) {
                    // determine if this is a new section
                    boolean rowContinues = false;
                    boolean colContinues = false;

                    if (row > 0) {
                        // check above
                        colContinues = a[row - 1][col] == 1;
                    }
                    if (col > 0) {
                        // check left
                        rowContinues = a[row][col - 1] == 1;
                    }

                    if (!colContinues && !rowContinues) {
                        // either a new section or it is connected to the right
                        // and above somewhere in the current row

                        for (int startingCol = col; startingCol < a[0].length - 1; startingCol++) {
                            rowContinues = a[row][startingCol + 1] == 1;
                            if (!rowContinues) {
                                // new section
                                break;
                            }
                            else if (row == 0) {
                                // can't be connected above so it must be a new section
                                rowContinues = false;
                                break;
                            }
                            else if (row > 0 && a[row -1][startingCol + 1] == 1) {
                                // rowContinues is true
                                // check if above space is 1 if it is there is not a new section
                                colContinues = true;
                                break;
                            }
                        }
                    }

                    if (!colContinues && !rowContinues) {
                        // we have a new section (i.e not connected)
                        numberOfSections++;
                        System.out.println("Sections incremented at " + row + ", " + col);
                    }
                }
            }
        }
        return numberOfSections;
    }


    private static void printArray(int[][] a) {
        // Checking Everything
        System.out.println("Number of rows: " + a.length);
        System.out.println("Number of columns " + a[0].length);

        String array = Arrays.deepToString(a).replace("], ", "\n")
                .replace("[", "").replace(", ", " ").replace("]", "");
        System.out.println(array);
    }
}
