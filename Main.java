public class Main {

    private static final int TRIALS = 5;

    public static void main(String[] args) {

        System.out.println("Program started");

        String[] files = {
                "data/almostsorted.txt",
                "data/random100.txt",
                "data/random25000.txt",
                "data/random50000.txt",
                "data/random75000.txt",
                "data/random100000.txt",
                "data/totallyreversed.txt"
        };

        FileReader reader = new FileReader();
        SortingAlgorithms sorter = new SortingAlgorithms();

        for (String file : files) {

            System.out.println("======================================");
            System.out.println("Dataset: " + file);

            Record[] original = reader.readFile(file);

            if (original == null) {
                System.out.println("File not found: " + file);
                continue;
            }

            int n = original.length;

            testAlgorithm("Insertion Sort", original, n, sorter, 1);
            testAlgorithm("Selection Sort", original, n, sorter, 2);
            testAlgorithm("Merge Sort", original, n, sorter, 3);
            testAlgorithm("Quick Sort", original, n, sorter, 4);
        }

        System.out.println("Program finished");
    }

    private static void testAlgorithm(String name, Record[] original, int n,
                                      SortingAlgorithms sorter, int type) {

        long totalTime = 0;
        long stepCount = 0;

        for (int i = 0; i < TRIALS; i++) {

            Record[] copy = original.clone();

            long start = System.currentTimeMillis();

            switch (type) {
                case 1 -> sorter.insertionSort(copy, n);
                case 2 -> sorter.selectionSort(copy, n);
                case 3 -> sorter.mergeSort(copy, 0, n - 1);
                case 4 -> sorter.quickSort(copy, 0, n - 1);
            }

            long end = System.currentTimeMillis();
            totalTime += (end - start);

            if (i == 0) {
                stepCount = sorter.getStepCount();
                verifySorted(copy);
            }
        }

        System.out.println(name);
        System.out.println("Average Time: " + (totalTime / TRIALS) + " ms");
        System.out.println("Step Count: " + stepCount);
        System.out.println();
    }

    private static void verifySorted(Record[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].getIdNumber() > arr[i].getIdNumber()) {
                System.out.println("Sorting failed!");
                return;
            }
        }
    }
}