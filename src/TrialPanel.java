import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class TrialPanel extends JPanel {
    private ArrayList<Shape> shapes;
    private Random random;
    private final int border;
    private int distractors;
    private boolean target;
    private static final int width = 500;
    private static final int height = 500;

    enum Choice {
        COLOR, SHAPE, COMBO, SIZE
    }
    public TrialPanel() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(width, height));
        shapes = new ArrayList<Shape>();
        random = new Random();
        border = 20;
        target = false;
        distractors = 10;
    }

    private void generateCircle(int r, Color color) {
        int x1 = random.nextInt(width - border*2) + border;
        int y1 = random.nextInt(height - border*2) + border;
        Shape shape = new Circle(new Point(x1, y1), r, color);
        shapes.add(shape);
    }

    private void generateSquare(Color color) {
        int x1 = random.nextInt(width - border*2) + border;
        int y1 = random.nextInt(height - border*2) + border;
        Shape shape = new Square(new Point(x1, y1), color);
        shapes.add(shape);
    }

    public void fillPanel(Choice choice) {
        target = false;
        switch (choice) {
            case COLOR -> {
                for (int i = 0; i < distractors; i++) {
                    generateCircle(8, Color.BLUE);
                }
                if (random.nextDouble() < 0.5) {
                    target = true;
                    shapes.remove(0);
                    generateCircle(8, Color.RED);
                }
            }
            case SHAPE -> {
                for (int i = 0; i < distractors; i++) {
                    generateCircle(8, Color.RED);
                }
                if (random.nextDouble() < 0.5) {
                    target = true;
                    shapes.remove(0);
                    generateSquare(Color.RED);
                }
            }
            case COMBO -> {
                for (int i = 0; i < distractors / 2; i++) {
                    generateCircle(8, Color.BLUE);
                    generateSquare(Color.RED);
                }
                if (random.nextDouble() < 0.5) {
                    target = true;
                    shapes.remove(random.nextInt(shapes.size()));
                    generateCircle(8, Color.RED);
                }
            }
            case SIZE -> {
                for (int i = 0; i < distractors; i++) {
                    generateCircle(8, Color.RED);
                }
                if (random.nextDouble() < 0.5) {
                    target = true;
                    shapes.remove(0);
                    generateCircle(12, Color.RED);
                }
            }
        }
    }
    
    public void clear() {
        shapes.clear();
    }
    
    public void setDistractors(int num){
        distractors = num;
    }

    public boolean getTarget() {
        return target;
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
