package gr.codelearn;

import java.util.Arrays;

public class ArrayDemo {

    public static void main(String[] args) {
//        arrayDefaultValues();
//        arrayActions();
//        fill();
        sortAndSearch();
    }

    private static void arrayDefaultValues() {
        int[] integerArray = new int[3];

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < integerArray.length; i++) {
            stringBuilder.append(integerArray[i]).append(" ");
        }
        System.out.println(stringBuilder);

        stringBuilder.setLength(0);
        for (int val : integerArray) {
            stringBuilder.append(val).append(" ");
        }
        System.out.println(stringBuilder);

        System.out.println("Double array default values:");
        stringBuilder.setLength(0);
        double[] doubleArray = new double[3];
        for (double val : doubleArray) {
            stringBuilder.append(val).append(" ");
        }
        System.out.println(stringBuilder);

        System.out.println("Reference array default values:");
        String[] stringArray = new String[3];
        stringArray[0] = "Nikos";
        stringArray[1] = "Maria";
        stringBuilder.setLength(0);
        for (String val : stringArray) {
            stringBuilder.append(val).append(" ");
        }
        System.out.println(stringBuilder);
    }

    private static void arrayActions() {
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

//        for (int i = 0; i < intArray.length; i++) {
//            intArray[i] += 10;
//        }
//
        for (int val : intArray) {
            System.out.println(val);
        }

        System.out.println("\n2D Array");
        int[][] intArray2D = {{1, 3, 5, 7, 9, 11}, {2, 4, 6, 8, 10, 12}, {1, 1, 2, 3, 5, 8, 13, 21}};
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] array : intArray2D) {
            for (int val : array) {
                stringBuilder.append(val).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);

        int[] cloneArray = intArray.clone();

        System.out.println(cloneArray == intArray ? "Equal" : "Not equal");

        System.out.println(Arrays.equals(cloneArray, intArray) ? "Equal" : "Not equal");
    }

    private static void fill() {
        int[] intArray = new int[100];
        Arrays.fill(intArray, 25);
        System.out.println(Arrays.toString(intArray));
    }

    private static void sortAndSearch() {
        int[] intArray = {2, 7, 1, 11, 5, 13};

        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));

        System.out.println(Arrays.binarySearch(intArray, 5));
        System.out.println(Arrays.binarySearch(intArray, 0));
    }
}
