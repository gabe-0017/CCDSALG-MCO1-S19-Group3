public class SortingAlgorithms {

    private long stepCount;

    public long getStepCount() {
        return stepCount;
    }

    // INSERTION SORT
    public void insertionSort(Record[] arr, int n) {
        stepCount = 0;

        for (int i = 1; i < n; i++) {
            Record key = arr[i];
            stepCount++;

            int j = i - 1;
            while (j >= 0 && arr[j].getIdNumber() > key.getIdNumber()) {
                stepCount++;
                arr[j + 1] = arr[j];
                stepCount++;
                j--;
            }
            arr[j + 1] = key;
            stepCount++;
        }
    }

    // SELECTION SORT
    public void selectionSort(Record[] arr, int n) {
        stepCount = 0;

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            stepCount++;

            for (int j = i + 1; j < n; j++) {
                stepCount++;
                if (arr[j].getIdNumber() < arr[min].getIdNumber()) {
                    min = j;
                    stepCount++;
                }
            }

            Record temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            stepCount += 3;
        }
    }

    // MERGE SORT
    public void mergeSort(Record[] arr, int p, int r) {
        if (p < r) {
            stepCount++;
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private void merge(Record[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        Record[] L = new Record[n1];
        Record[] R = new Record[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
            stepCount++;
        }

        for (int j = 0; j < n2; j++) {
            R[j] = arr[q + 1 + j];
            stepCount++;
        }

        int i = 0, j = 0, k = p;

        while (i < n1 && j < n2) {
            stepCount++;
            if (L[i].getIdNumber() <= R[j].getIdNumber()) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
            stepCount++;
        }

        while (i < n1) {
            arr[k++] = L[i++];
            stepCount++;
        }

        while (j < n2) {
            arr[k++] = R[j++];
            stepCount++;
        }
    }

    // QUICK SORT (EXTRA)
    public void quickSort(Record[] arr, int low, int high) {

        if (low == 0 && high == arr.length - 1) {
            stepCount = 0;
        }

        if (low < high) {
            stepCount++;

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Record[] arr, int low, int high) {

        int randomIndex = low + (int)(Math.random() * (high - low + 1));
        swap(arr, randomIndex, high);
        stepCount++;

        Record pivot = arr[high];
        stepCount++;

        int i = low - 1;

        for (int j = low; j < high; j++) {
            stepCount++;

            if (arr[j].getIdNumber() <= pivot.getIdNumber()) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(Record[] arr, int i, int j) {
        Record temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        stepCount += 3;
    }
}