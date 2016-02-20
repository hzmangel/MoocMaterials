package week2;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by huzim on 16/02/20.
 */
public class Utils {

    protected static void prepareData(int size, int[] data) {
        Random generator = new Random();

        for (int i = 0; i < size; i++) {
            data[i] = generator.nextInt(1024);
        }

    }
}
