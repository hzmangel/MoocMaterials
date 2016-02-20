package week2;

/**
 * Created by huzim on 16/02/18.
 */
public interface Sort {
    void sort();

    boolean less(int v, int w);

    void exch(int i, int j);
}
