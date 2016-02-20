package week2;

public class SelectionSort implements Sort {

    private final int data_size;
    private final int[] data;

    public SelectionSort(final int N, int[] data) {
        this.data_size = N;
        this.data = data;
    }

    @Override
    // Change data order in-place
    public void sort() {
        for (int i = 0; i < data_size-1; i++) {
            exch(i, min_idx(i));
        }
    }

    @Override
    public boolean less(int v, int w) {
        return v < w;
    }

    @Override
    public void exch(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public int min_idx(int start_idx) {
        int result_idx = start_idx;
        int min_val = data[start_idx];

        for (int i = start_idx; i < data_size; i++) {
            if(data[i] < min_val) {
                min_val = data[i];
                result_idx = i;
            }
        }

        return result_idx;
    }

}
