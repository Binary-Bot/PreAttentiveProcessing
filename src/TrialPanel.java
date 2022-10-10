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

    public TrialPanel() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(width, height));
        shapes = new ArrayList<Shape>();
        random = new Random();
        border = 20;
        target = false;
        distractors = 10;
    }

    private void generateShapes() {
        int x1 = random.nextInt(width - border*2) + border;
        int y1 = random.nextInt(height - border*2) + border;

        Shape shape = new Circle(new Point(x1, y1), Color.RED);
        shapes.add(shape);
    }

    public void fillPanel() {
        for (int i = 0; i < distractors; i++){
            generateShapes();
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
