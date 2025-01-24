/*
    mergeSort:
        if the array has 1 or 0 elements:
            we stop the function
        find the middle of the original array
        left array = elements of the original array up to the middle
        right array = remaining elements
        copy elements from the original array up to the middle into the left array
        copy elements from the middle to the end into the right array
        sort the left array by recursively calling mergeSort
        sort the right array by recursively calling mergeSort
        call the merge function to merge the left and right arrays

    merge:
        initialize variables to traverse the arrays
        as long as i and j do not exceed the size of their respective arrays:
            if left[i] is smaller than right[j]:
                insert left[i] into arr and increment k and i
            otherwise:
                insert right[j] into arr and increment k and j
        while i is less than the size of the left array:
            add the remaining elements of the left array to arr
        while j is less than the size of the right array:
            add the remaining elements of the right array to arr
 */

public class sort {

    /**
     * sort the table in ascending order.
     *
     * @param myArray the score array.
     */
    public static void mergeSort(int[] myArray) {
        // if the array has less than 2 elements, stop the function
        if (myArray.length <= 1) {
            return;
        }
        // find the middle of the array
        int mid = myArray.length / 2;
        // initialize the left array
        int[] left = new int[mid];
        // initialize the right array
        int[] right = new int[myArray.length - mid];

        // copy the first elements of myArray up to the middle into the left array
        System.arraycopy(myArray, 0, left, 0, mid);
        // copy the remaining elements of myArray from the middle into the right array
        System.arraycopy(myArray, mid, right, 0, myArray.length - mid);

        // recursively call the function with the left array
        mergeSort(left);
        // recursively call the function with the right array
        mergeSort(right);

        // call the merge function to combine the two arrays
        merge(myArray, left, right);
    }

    /**
     * merge left array and right array to arr.
     *
     * @param arr, left, right the score array.
     */
    public static void merge(int[] arr, int[] left, int[] right) {
        // initialize variables to traverse the arrays
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                // insert left[i] into arr and increment i and k
                arr[k++] = left[i++];
            } else {
                // insert right[j] into arr and increment j and k
                arr[k++] = right[j++];
            }
        }

        // insert remaining elements from the left array if the right array is empty
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // insert remaining elements from the right array if the left array is empty
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    /*
        Initialize the length of the array
        Traverse the array:
            initialize the variable key with the value at index i
            initialize j with the value i - 1
            while j is greater than or equal to 0 and myArray[j] is less than key:
                shift elements backwards
            insert the value of key at index j + 1
     */

    /**
     * sort the array in descending order .
     *
     * @param myArray the score array.
     */
    public static void descending(int[] myArray) {
        // initialize a variable for the length of the array
        int n = myArray.length;
        for (int i = 1; i < n; i++) {
            int key = myArray[i];

            int j = i - 1;
            while (j >= 0 && myArray[j] < key) {
                myArray[j + 1] = myArray[j];
                j--;
            }

            myArray[j + 1] = key;
        }
    }

    // show scores
    /**
     * show the array .
     *
     * @param scoresArray the score array.
     */
    public static void showTab(int [] scoresArray){
        for (int i = 0; i < 10 && i < scoresArray.length; i++) {
            System.out.println(scoresArray[i]);
        }
    }
}