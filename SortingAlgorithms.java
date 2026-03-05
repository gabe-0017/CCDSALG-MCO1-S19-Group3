public class SortingAlgorithms {

    private long stepCount; // for tracking the number of operations performed
                            // (used for all sorting algorithms)
    
    // 'stepCount' getter (used in Main.java)
    public long getStepCount() {
        return stepCount;
    }

    // INSERTION SORT
    public void insertionSort(Record[] arr, int n) {
        stepCount = 0;

        for (int i = 1; i < n; i++) { // start from second element (index=1)
            Record key = arr[i]; // set second element as 'key'
            stepCount++;

            int j = i - 1;
            // if current element (arr[j]) is greater than 'key', move it one position to the right of key
            while (j >= 0 && arr[j].getIdNumber() > key.getIdNumber()) {
                stepCount++;
                arr[j + 1] = arr[j];
                stepCount++;
                j--;
            }

            arr[j + 1] = key; // insert 'key' in its correct sorted position
            stepCount++;      // (once all other elements are sorted)
        }
    }

    // SELECTION SORT
    public void selectionSort(Record[] arr, int n) {
        stepCount = 0;

        for (int i = 0; i < n - 1; i++) { // start from first element (index=0)
            int min = i; // assume current index contains the smallest value
            stepCount++;

            for (int j = i + 1; j < n; j++) { // search for smaller element
                stepCount++;
                if (arr[j].getIdNumber() < arr[min].getIdNumber()) {
                    min = j; // update index of the smallest element found
                    stepCount++;
                }
            }

            // swap the new found smallest element with the first unsorted element
            Record temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            stepCount += 3;
        }
    }

    // Helper for MERGE SORT
    private void merge(Record[] arr, int p, int q, int r) {
        int n1 = q - p + 1; // size of left subarray
        int n2 = r - q; // size of right subarray

        Record[] L = new Record[n1]; // temporary array for left half
        Record[] R = new Record[n2]; // temporary array for right half

        // copy data into left temp array
        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
            stepCount++;
        }

        // copy data into right temp array
        for (int j = 0; j < n2; j++) {
            R[j] = arr[q + 1 + j];
            stepCount++;
        }

        int i = 0, j = 0, k = p;

        // merge the two temporary arrays back into the main array (arr[]) while sorting 
        while (i < n1 && j < n2) {
            stepCount++;

            // compare elements and copy the smaller one into arr[]
            if (L[i].getIdNumber() <= R[j].getIdNumber()) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
            stepCount++;
        }

        // copy remaining elements of L[] (if any)
        while (i < n1) {
            arr[k++] = L[i++];
            stepCount++;
        }

        // copy remaining elements of R[] (if any)
        while (j < n2) {
            arr[k++] = R[j++];
            stepCount++;
        }
    }

    // MERGE SORT
    public void mergeSort(Record[] arr, int p, int r) {
        // p = starting index of the current subarray 
        // r = ending index of the current subarray
        if (p < r) { // keep dividing while the subarray has more than one element
            stepCount++;

            int q = (p + r) / 2; // find midpoint (q) of the current subarray

            mergeSort(arr, p, q); // sort left half (recursively)
            mergeSort(arr, q + 1, r); // sort right half (recursively)

            merge(arr, p, q, r); // merge the two sorted halves
        }
    }

    // Helper partition()
    private void swap(Record[] arr, int i, int j) {
        // swap operation between two array elements
        Record temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        stepCount += 3;
    }

    // Helper for QUICK SORT
    private int partition(Record[] arr, int low, int high) {
        // select a random pivot index (to improve average performance)
        int randomIndex = low + (int)(Math.random() * (high - low + 1));
        swap(arr, randomIndex, high); // move pivot to the end
        stepCount++;

        Record pivot = arr[high]; // pivot element
        stepCount++;

        int i = low - 1; // index of smaller element

        for (int j = low; j < high; j++) {
            stepCount++;

            // if current element is less than or equal to pivot...
            if (arr[j].getIdNumber() <= pivot.getIdNumber()) {
                i++;
                swap(arr, i, j); // ...place element in correct partition
            }
        }

        swap(arr, i + 1, high); // place pivot in its correct sorted position
        return i + 1;           // (once all other elements are sorted)
    }
    
    // QUICK SORT (EXTRA)
    public void quickSort(Record[] arr, int low, int high) {
        // set step counter to 0 (only for the initial call)
        if (low == 0 && high == arr.length - 1) {
            stepCount = 0;
        }

        if (low < high) { // while subarray size > 1...
            stepCount++;

            int pi = partition(arr, low, high); // ...keep partitioning array and get pivot index

            quickSort(arr, low, pi - 1); // sort elements before pivot (recursively)
            quickSort(arr, pi + 1, high); // sort elements after pivot (recursively)
        }
    }
}
