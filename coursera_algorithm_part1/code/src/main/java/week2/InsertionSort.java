package week2;

import java.util.Arrays;

public class InsertionSort implements Sort {

    private final int data_size;
    private final int[] data;
    private final boolean step_log;

    public InsertionSort(final int N, int[] data, boolean step_log) {
        this.data_size = N;
        this.data = data;
        this.step_log = step_log;
    }

    @Override
    public void sort() {
        for (int i = 0; i < data_size; i++) {
            if (step_log) {
                CodeUtils.printStepLog(i, data);
            }

            for (int j = i; j > 0; j--) {
                if (less(data[j], data[j-1])) {
                    exch(j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public boolean less(int v, int w) {
        return v < w;
    }

    @Override
    public void exch(int i, int j) {
        int swap = data[i];
        data[i] = data[j];
        data[j] = swap;
    }
}
