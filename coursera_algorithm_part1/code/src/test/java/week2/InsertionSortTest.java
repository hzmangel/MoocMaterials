package week2;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by huzim on 16/02/20.
 */
public class InsertionSortTest {
    protected int[] data = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    protected InsertionSort sort_obj;

    @Before
    public void setUp() throws Exception {
        sort_obj = new InsertionSort(data.length, data);
    }

    @Test
    public void testSort() throws Exception {
        System.out.println("abcdawsedawsfsadf");
    }

    @Test
    public void testLess() throws Exception {

    }

    @Test
    public void testExch() throws Exception {

    }

    @Test
    public void testMin_idx() throws Exception {

    }
}