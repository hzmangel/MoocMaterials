package week2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by huzim on 16/02/20.
 */
public class SelectionSortTest {
    protected int[] data;
    protected int[] sorted_data;
    protected final static int size = 42;
    protected SelectionSort sort_obj;

    @Before
    public void setUp() throws Exception {
        data = new int[size];
        Utils.prepareData(size, data);
        sorted_data = data.clone();
        Arrays.sort(sorted_data);

        sort_obj = new SelectionSort(size, data, true);
    }

    @Test
    public void testSort() throws Exception {
        sort_obj.sort();
        Assert.assertArrayEquals(sorted_data, data);
    }

}