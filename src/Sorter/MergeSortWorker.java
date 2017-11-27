
package Sorter;

/**
 *
 * @author Vilius
 */
public class MergeSortWorker extends Thread {
    private String[] arr;
    
    MergeSortWorker(String[] newArr) {
        arr = newArr;
    }
    
    public String[] getArray() {
        return arr;
    }

    public void mergeSort(String[] array) {
        if (array.length > 1) {
            String[] left = leftHalf(array);
            String[] right = rightHalf(array);

            mergeSort(left);
            mergeSort(right);

            merge(array, left, right);
        }
    }

    public String[] leftHalf(String[] array) {
        int size1 = array.length / 2;
        String[] left = new String[size1];
        for (int i = 0; i < size1; i++) {
            left[i] = array[i];
        }
        return left;
    }

    public String[] rightHalf(String[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        String[] right = new String[size2];
        for (int i = 0; i < size2; i++) {
            right[i] = array[i + size1];
        }
        return right;
    }

    public void merge(String[] result, String[] left, String[] right) {
        int i1 = 0;   
        int i2 = 0;   

        for (int i = 0; i < result.length; i++) {
            if (i2 >= right.length || (i1 < left.length && (left[i1].compareTo(right[i2]) < 0))) {
                result[i] = left[i1];   
                i1++;
            } else {
                result[i] = right[i2];   
                i2++;
            }
        }
    }

    public void run() {
        mergeSort(arr);
    }
}
