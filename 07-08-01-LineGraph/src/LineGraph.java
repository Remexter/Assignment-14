import javax.swing.*;
import java.awt.*;

public class LineGraph {
    public static void main(String[] args) {
        int n = 200;
        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < x.length; i++) {
            x[i] = -10 + 0.1 * i;
            y[i] = function(x[i]);
        }

        plot(600, 400, x, y);
    }

    private static double function(double x) {
        double y = 0;
        for (int i = 1; i <= 4; i++) {
            y += Math.sin(x * i);
        }
        return y;
    }

    public static void plot(int width, int height, double[] x, double[] y) {
        double xmin = min(x);
        double xmax = max(x);
        double ymin = min(y);
        double ymax = max(y);

        JFrame frame = new JFrame("Line Graph");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GraphPanel(width, height, x, y, xmin, xmax, ymin, ymax));
        frame.setVisible(true);
    }

    static class GraphPanel extends JPanel {
        int width, height;
        double[] x, y;
        double xmin, xmax, ymin, ymax;

        public GraphPanel(int width, int height, double[] x, double[] y, double xmin, double xmax, double ymin, double ymax) {
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.xmin = xmin;
            this.xmax = xmax;
            this.ymin = ymin;
            this.ymax = ymax;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);

            for (int i = 0; i < x.length - 1; i++) {
                int sx0 = (int) ((x[i] - xmin) * (width - 1) / (xmax - xmin));
                int sy0 = (int) ((y[i] - ymax) * (height - 1) / (ymin - ymax));
                int sx1 = (int) ((x[i + 1] - xmin) * (width - 1) / (xmax - xmin));
                int sy1 = (int) ((y[i + 1] - ymax) * (height - 1) / (ymin - ymax));

                g.drawLine(sx0, sy0, sx1, sy1);
            }
        }
    }

    public static double max(double[] d) {
        double max = d[0];
        for (int i = 1; i < d.length; i++) {
            if (d[i] > max) max = d[i];
        }
        return max;
    }

    public static double min(double[] d) {
        double min = d[0];
        for (int i = 1; i < d.length; i++) {
            if (d[i] < min) min = d[i];  // Fixed condition
        }
        return min;
    }
}
