package com.fuzzdota.codechallenge;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt(); scanner.nextLine();
        String[][] matrix = new String[size][];
        for(int i = 0; i < size; i++) {
            matrix[i] = scanner.nextLine().split(" ");
            if(matrix[i].length != size) {
                System.out.println("Not a squared matrix");
                return;
            }
        }
        rotateMatrixVer1(matrix);
        System.out.println("-----------");
        rotateMatrixVer2(matrix);
    }

    public static void rotateMatrixVer1(String[][] matrix) {
        int size = matrix.length;
        for(int i = 0; i < size/2; i++) {
            int first = i;
            int last =  size-1-i;
            // save 4 corners
            String topLeft = matrix[first][first];
            String bottomLeft = matrix[last][first];
            String topRight = matrix[first][last];
            String bottomRight = matrix[last][last];
            for(int j = first; j < last-1; j++) {
                matrix[j][first] = matrix[j+1][first];
                matrix[last][j] = matrix[last][j+1];
                matrix[last-j][last] = matrix[last-j-1][last];
                matrix[first][last-j] = matrix[first][last-j-1];
            }
            // restore 4 corners
            matrix[first][first+1] = topLeft;
            matrix[last-1][first] = bottomLeft;
            matrix[first+1][last] = topRight;
            matrix[last][last-1] = bottomRight;
        }
        printMatrix(matrix);
    }

    public static void rotateMatrixVer2(String[][] matrix) {
        int size = matrix.length;
        String[][] rotatedMatrix = new String[size][size];
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < matrix[row].length; col++) {
                if(       row <  size/2 && row <  col && (row+col <= size-1)) {
                    rotatedMatrix[row][col] = matrix[row][col-1];
                } else if(row >= size/2 && row >  col && (row+col >= size-1)) {
                    rotatedMatrix[row][col] = matrix[row][col+1];
                } else if(col <  size/2 && row >= col && (row+col <  size-1)) {
                    rotatedMatrix[row][col] = matrix[row+1][col];
                } else if(col >= size/2 && row <= col && (row+col >  size-1)) {
                    rotatedMatrix[row][col] = matrix[row-1][col];
                } else {
                    rotatedMatrix[row][col] = matrix[row][col];
                }
            }
        }
        printMatrix(rotatedMatrix);
    }

    public static void printMatrix(String[][] matrix) {
        for(String[] i : matrix) {
            System.out.println(Arrays.toString(i));
        }
    }
}
