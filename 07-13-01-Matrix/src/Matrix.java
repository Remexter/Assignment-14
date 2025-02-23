public class Matrix {
    public static void main(String[] args) {
        double[][] p = {{1, 2, 1}, {3, 2, 1}};
        double[][] q = {{2, 1, 2}, {4, 5, 6}};
        double[][] r = add(p, q);
        double[] v = {2, 1};
        double[][] a = {{2, 3}, {1, 4}};
        double[][] b = {{1, 2, 3}, {3, 4, 2}};
        Demo.printArray(multiply(a, b));
    }

    public static double[][] add(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException();
        }
        int row = a.length;
        int col = a[0].length;
        double[][] c = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException();
        }
        int rowA = a.length;
        int colA = a[0].length;
        int colB = b[0].length;
        double[][] c = new double[rowA][colB];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                for (int k = 0; k < colA; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}

class Demo {
    public static void printArray(double[][] arr) {
        for (double[] row : arr) {
            for (double val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}