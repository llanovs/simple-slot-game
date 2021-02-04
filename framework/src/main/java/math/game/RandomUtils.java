package math.game;

import java.util.Arrays;
import java.util.Random;

public abstract class RandomUtils {

    public static int getIndex(Random random, int[] weights) {
        int sum = Arrays.stream(weights).reduce(Integer::sum).orElse(-1);
        int randomValue = random.nextInt(sum);
        sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > randomValue) {
                return i;
            }
        }
        return -1;
    }
}
