/**
 * The {@code sort} class provides static methods for sorting arrays using different algorithms.
 * It includes methods for the merge sort algorithm, as well as an insertion sort method for sorting in descending order.
 * <p>
 * The {@code mergeSort} method is used to sort an array using the merge sort algorithm, which recursively divides
 * the array into two halves, sorts each half, and merges them back together.
 * The {@code descending} method implements the insertion sort algorithm to sort an array in descending order.
 */
public class sort {

    /**
     * Sorts an array using the merge sort algorithm.
     * <p>
     * Merge sort works by recursively dividing the array into two halves until each half has one or zero elements.
     * Then, it merges the sorted halves back together.
     *
     * @param myArray The array to be sorted.
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
     * Merges two sorted arrays into a single sorted array.
     * This function is used by the merge sort algorithm to combine the left and right arrays after they have been sorted.
     *
     * @param arr   The original array where the merged elements will be stored.
     * @param left  The left sorted array.
     * @param right The right sorted array.
     */
    public static void merge(int[] arr, int[] left, int[] right) {
        // initialize variables to traverse the arrays
        int i = 0;
        int j = 0;
        int k = 0;

        // merge elements from the left and right arrays into arr
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

    /**
     * Sorts an array in descending order using the insertion sort algorithm.
     * <p>
     * Insertion sort works by iterating through the array and inserting each element into its correct
     * position relative to the already sorted portion of the array.
     * The array is sorted in descending order by comparing each element to the previous elements.
     *
     * @param myArray The array to be sorted.
     */
    public static void descending(int[] myArray) {
        // initialize a variable for the length of the array
        int n = myArray.length;

        for (int i = 1; i < n; i++) {
            int key = myArray[i];

            int j = i - 1;
            // shift elements backwards to make room for the key
            while (j >= 0 && myArray[j] < key) {
                myArray[j + 1] = myArray[j];
                j--;
            }

            // insert the key into its correct position
            myArray[j + 1] = key;
        }
    }
}
