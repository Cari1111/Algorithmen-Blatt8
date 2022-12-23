package CustomSort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Sorting {
    // Takes two arguments: array size and type of sorting algorithem and prints a running time
    public static void main(String[] args) {
        // If there are not two arguments, the code can't be excuted
        if (args.length != 2) {
            System.out.print(-1);
            return;
        }
        // The first argument is the Arraysize.
        int sampleSize = Integer.parseInt(args[0]);
        long time = 0;

        // Try the sorting algorithem 50. times to get a more accurate result.
        for (int index = 0; index < 50; index++) {
            // Create a random array of the given size.
            double[] array = ThreadLocalRandom.current().doubles(sampleSize).toArray();

            // initialize start and end (retures -1 if values are not overwritten)
            long start = 0, end = -2;
            switch (args[1]) {
                case "selection":
                    start = System.nanoTime(); // start is the current time
                    array = selectionSort(array); // sort the array
                    end = System.nanoTime(); // end is the current time
                    break;
                case "quick":
                    ExtraList list = new ExtraList(array); // make a new list from the array
                    start = System.nanoTime(); // start is the current time
                    list = quickSort(list); // sort the array
                    end = System.nanoTime(); // end is the current time
                    array = list.toArray(); // write the list in the array
                    break;
                default:
                    break;
            }
            // calculate the time
            time += end-start;
            // run test and set time to -1 if they fail
            if (!runTest(array)) time = -1;
        }
        // print the average of the 50 run times
        System.out.print(time/50.0);
        
        /* Some Tests:
         * double[] array = {7,5,8,1,9,6,7,4,10.5};
         * ExtraList list = new ExtraList(array);
         * 
         * System.out.println(Arrays.toString(selectionSort(array)));
         * System.out.println(quickSort(list).toString());
         */
    }

    private static double[] selectionSort(double[] array) {
        // Iterate through the array
        for (int i = 0; i < array.length; i++) {
            // Find the minimum element in the unsorted portion of the array
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            // Swap the minimum element with the first element of the unsorted portion
            double temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    private static ExtraList quickSort(ExtraList list) {
        if (list == null)
            return list;
        // if the list has one or less elements, the list is already sorted
        if (list.oneOrLessElements())
            return list;

        // The pivot is the first element of the list
        double pivot = list.getFirst();
        ExtraList smaller = new ExtraList();
        ExtraList equal = new ExtraList(pivot);
        ExtraList larger = new ExtraList();

        // Sort all the elements of the list into the lists smaller, equal and larger
        while (!(list.isEmpty())) {
            double element = list.getFirst();
            if (element == pivot)
                equal.add(element);
            if (element > pivot)
                larger.add(element);
            if (element < pivot)
                smaller.add(element);
        }

        // Apply QuickSort on the smaller and larger list and append all lists
        ExtraList sortedList = quickSort(smaller);
        sortedList.append(equal);
        sortedList.append(quickSort(larger));
        return sortedList;
    }

    // test if the array is increasing
    public static boolean runTest(double[] array){
        if (array.length < 1) return false;
        boolean result = true;
        for (int index = 0; index < array.length-1; index++) {
            result = result && (array[index] <= array[index+1]);
        }
        return result;
    }
}