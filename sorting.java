import java.util.Arrays;

public class sorting {
    public static void main(String[] args) {
        double[] array = {7,5,8,1,9,6,7,4,10.5};
        ExtraList list = new ExtraList(array);

        System.out.println(Arrays.toString(selectionSort(array)));
        System.out.println(quickSort(list).toString());
    }

    private static double[] selectionSort(double[] array){
        // Iterate through the array
        for(int i=0; i < array.length; i++) {
            //Find the minimum element in the unsorted portion of the array
            int minIndex = i;
            for (int j=i; j < array.length; j++){
                if (array[j] < array[minIndex]) minIndex = j;
            }
            System.out.println(minIndex);
            // Swap the minimum element with the first element of the unsorted portion
            double temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    private static ExtraList quickSort(ExtraList list){
        if (list == null) return list;
        // if the list has one or less elements, the list is already sorted
        if (list.oneOrLessElements()) return list;

        // The pivot is the first element of the list
        double pivot = list.getFirst();
        ExtraList smaller = new ExtraList();
        ExtraList equal = new ExtraList(pivot);
        ExtraList larger = new ExtraList();

        // Sort all the elements of the list into the lists smaller, equal and larger
        while (!(list.isEmpty())){
            double element = list.getFirst();
            if(element == pivot) equal.add(element);
            if(element > pivot) larger.add(element);
            if(element < pivot) smaller.add(element);
        }

        // Apply QuickSort on the smaller and larger list and append all lists
        ExtraList sortedList = quickSort(smaller);
        sortedList.append(equal);
        sortedList.append(quickSort(larger));
        return sortedList;
    }
}