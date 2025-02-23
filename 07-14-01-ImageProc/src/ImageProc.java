import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageProc extends JFrame {

    private BufferedImage image;
    private JLabel imageLabel;

    public ImageProc() {
        setTitle("Image Processing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        JButton importButton = new JButton("Import Image");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importImage();
            }
        });
        add(importButton, BorderLayout.SOUTH);

        pack(); // Adjust window size initially
    }

    private void importImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadImage(selectedFile.getAbsolutePath());
        }
    }

    public void loadImage(String filePath) {
        try {
            image = ImageIO.read(new File(filePath));
            imageLabel.setIcon(new ImageIcon(image));
            pack(); // Adjust window size to fit the image
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setPixmap(BufferedImage newImage) {
        image = newImage;
        imageLabel.setIcon(new ImageIcon(image));
        revalidate(); // Update the layout
        repaint();    // Redraw the component
    }

    public BufferedImage negative(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage negativeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                Color color = new Color(rgb);
                int red = 255 - color.getRed();
                int green = 255 - color.getGreen();
                int blue = 255 - color.getBlue();
                Color negativeColor = new Color(red, green, blue);
                negativeImage.setRGB(x, y, negativeColor.getRGB());
            }
        }
        return negativeImage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageProc imageProc = new ImageProc();
            imageProc.setVisible(true);
        });
    }
}