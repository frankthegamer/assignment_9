import java.util.Arrays;
import java.util.Scanner;

public class compareSorting {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter array length: ");  // input length of array
        int arrayLength = scanner.nextInt();
        int[] randomArray = createRandomArray(arrayLength);

        // bubble sort
        int[] bubbleArray = Arrays.copyOf(randomArray, randomArray.length);
        long startTime = System.nanoTime();
        bubbleSort(bubbleArray);
        long endTime = System.nanoTime();
        System.out.println("Bubble sort time: " + (endTime - startTime) + " ns");

        // merge sort
        int[] mergeArray = Arrays.copyOf(randomArray, randomArray.length);
        startTime = System.nanoTime();
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        endTime = System.nanoTime();
        System.out.println("Merge sort time: " + (endTime - startTime) + " ns");
        
        System.out.println("Array sorted, would you like to print it? (y/n)");    // ask to print sorted array
        String printArr = scanner.next();
        if (printArr.equals("y")) {
            System.out.println("Sorted Array: " + Arrays.toString(mergeArray));
        }
        scanner.close();
    }

    public static int[] createRandomArray(int arrayLength){  // creates array of random ints 1-100
        int[] randomArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = (int) (Math.random() * 100) + 1; 
        }
        return randomArray;
    }

    public static void bubbleSort(int[] array) {   // bubble sort
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // swap array[j] and array[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] array, int left, int right) {   // merge sort
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
