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
}
