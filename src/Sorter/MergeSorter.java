
package Sorter;

/**
 *
 * @author Vilius
 */
public class MergeSorter {
    
    private String[] arr;
    
    public String[] sort(String[] newArr) {
        arr = newArr;
        String[] subArr1 = new String[arr.length/2];
        String[] subArr2 = new String[arr.length - arr.length/2];
        System.arraycopy(arr, 0, subArr1, 0, arr.length/2);
        System.arraycopy(arr, arr.length/2, subArr2, 0, arr.length - arr.length/2);

        MergeSortWorker runner1 = new MergeSortWorker(subArr1);
        MergeSortWorker runner2 = new MergeSortWorker(subArr2);
        runner1.start();
        runner2.start();
        
        try {
            runner1.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            runner2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return finalMerge(runner1.getArray(), runner2.getArray());
    }
    
    public String[] finalMerge(String[] a, String[] b) {
        String[] result = new String[a.length + b.length];
        int i=0; 
        int j=0; 
        int r=0;
        while (i < a.length || j < b.length) {
            if (i==a.length) {
                while (j<b.length) {
                    result[r]=b[j];
                    r++;
                    j++;
                }
            }
            else if (j==b.length) {
                while (i<a.length) {
                    result[r]=a[i];
                    r++;
                    i++;
                }
            }
            else {
                if (a[i].compareTo(b[j]) < 0) {
                result[r]=a[i];
                i++;
                r++;
            } else {
                result[r]=b[j];
                j++;
                r++;
            }
            }
        }
        
        return result;
    }
}