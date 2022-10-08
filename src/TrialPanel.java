import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class TrialPanel extends JPanel {
    private ArrayList<Shape> shapes;
    private Random random;
    private static final int width = 500;
    private static final int height = 500;

    public TrialPanel() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(width, height));
        shapes = new ArrayList<Shape>();
        random = new Random();
    }

    private void generateShapes() {
        int x1 = random.nextInt(getWidth() - 20);
        int y1 = random.nextInt(getHeight() - 20);

        Shape shape = new Circle(new Point(x1, y1), Color.RED);
        shapes.add(shape);

    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) {
            s.paint(g);
        }
        repaint();
    }
}
