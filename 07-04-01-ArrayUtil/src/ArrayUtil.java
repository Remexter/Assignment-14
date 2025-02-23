import java.util.Scanner;

public class ArrayUtil {
    public static void main(String[] args) {
        double[] a = { 1, 2, 3, 5, 2, 4, 1, 3.2, 9, 2, 1};
        System.out.println(max(a));
    }
    public static double max(double[] d) {
        double max = d[0];
        for (int i = 1; i < d.length; i++) {
            if (d[i] > max) max = d[i];
        }
        return max;
    }
}