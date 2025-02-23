import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;

public class Card extends JFrame {
    private final int GRID_SIZE = 4; // ขนาด 4x4
    private JButton[][] buttons = new JButton[GRID_SIZE][GRID_SIZE];
    private String[][] values = new String[GRID_SIZE][GRID_SIZE];
    private JButton firstCard = null, secondCard = null;
    private int pairsFound = 0;

    public Card() {
        setTitle("เกมจับคู่");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        initializeGame();
        setSize(400, 400);
        setVisible(true);
    }

    private void initializeGame() {
        ArrayList<String> symbols = new ArrayList<>();
        for (int i = 1; i <= (GRID_SIZE * GRID_SIZE) / 2; i++) {
            symbols.add(String.valueOf(i));
            symbols.add(String.valueOf(i));
        }
        Collections.shuffle(symbols);

        for (int i = 0, k = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++, k++) {
                values[i][j] = symbols.get(k);
                buttons[i][j] = new JButton("?");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("?")) {
                buttons[row][col].setText(values[row][col]);
                if (firstCard == null) {
                    firstCard = buttons[row][col];
                } else {
                    secondCard = buttons[row][col];
                    checkMatch();
                }
            }
        }
    }

    private void checkMatch() {
        if (firstCard.getText().equals(secondCard.getText())) {
            firstCard.setEnabled(false);
            secondCard.setEnabled(false);
            pairsFound++;
            if (pairsFound == (GRID_SIZE * GRID_SIZE) / 2) {
                JOptionPane.showMessageDialog(this, "คุณชนะแล้ว!");
                System.exit(0);
            }
        } else {
            Timer timer = new Timer(500, e -> {
                firstCard.setText("?");
                secondCard.setText("?");
                firstCard = null;
                secondCard = null;
            });
            timer.setRepeats(false);
            timer.start();
        }
        firstCard = null;
        secondCard = null;
    }

    public static void main(String[] args) {
        new Card();
    }
}
