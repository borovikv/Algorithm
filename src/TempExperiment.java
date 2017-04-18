import java.util.Arrays;
import java.util.Vector;
import java.util.stream.IntStream;

/**
 * Created by vborovic on 4/3/17.
 */
public class TempExperiment {

    private static int bottomUpCutRod(int[] p, int n) {
        int[] r = new int[n+1];
        int[] s = new int[n+1];
        r[0] = 0;
        for(int j=1; j <= n; j++) {
            int q = -1;
            for(int i = 1; i <= j; i++) {
                System.out.println(j + " - " + i);
                if (q < p[i-1] + r[j-i]) {
                    q = p[i-1] + r[j-i];
                    s[j] = i;
                }
            }
            r[j] = q;
        }
        System.out.println(Arrays.toString(r));
        System.out.println(Arrays.toString(s));
        for (int k = n; k > 0; k--) {
            System.out.println(s[n]);
            k = k - s[n];
        }
        return r[n];
    }

    private static double cutRod(int[] p, int n) {
        if (n == 0) return 0;
        double q = Double.NEGATIVE_INFINITY;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, p[i-1] + cutRod(p, n-i));
        }
        return q;
    }

    public static void main(String[] args) {
        int[] p = {1, 5, 8, 9};
        System.out.println(bottomUpCutRod(p, 4));
        System.out.println(cutRod(p, 4));
    }
}
