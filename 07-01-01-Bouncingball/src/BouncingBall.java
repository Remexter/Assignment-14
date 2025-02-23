import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BouncingBall extends JPanel implements ActionListener {
    private static final int WIDTH = 500, HEIGHT = 500;
    private static final int RADIUS = 20;
    private int x = 100, y = 100;
    private int dx, dy;
    private Timer timer;

    public BouncingBall() {
        Random rand = new Random();
        dx = rand.nextInt(11) - 5; // Random number between -5 and 5
        dy = rand.nextInt(11) - 5;

        timer = new Timer(30, this); // Redraw every 30ms
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;

        // Bounce off walls
        if (x <= 0 || x + 2 * RADIUS >= getWidth()) dx = -dx;
        if (y <= 0 || y + 2 * RADIUS >= getHeight()) dy = -dy;

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball");
        BouncingBall panel = new BouncingBall();
        
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
}
