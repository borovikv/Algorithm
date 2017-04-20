package sort;

import java.util.Arrays;

/**
 * Created by vborovic on 4/20/17.
 */
public class MergeSort {

    void merge(int[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(A, p, L, 0, n1);
        System.arraycopy(A, q + 1, R, 0, n2);

        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (R.length <= j || L.length > i && L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }

    }

    void mergeSort(int[] A, int p, int r) {
        if (p < r) {
            int q = p + (r - p) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    void sort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }


    public static void main(String[] args) {
        int[] A = {1, 2, 4, 3};
        new MergeSort().sort(A);
        System.out.println(Arrays.toString(A));
    }
}
