import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class PAPView {
    private JLabel subjectLabel;
    private JTextField subjectName;
    private JPanel menuPanel;
    private JLabel trialTypeLabel;
    private JRadioButton colorRButton;
    private JRadioButton ShapeRButton;
    private JRadioButton comboRButton;
    private JLabel distLabel;
    private JSlider slider1;
    private JButton startButton;
    private JPanel mainPanel;

    private TrialPanel trialPanel;
    private ButtonGroup buttonGroup;
    private JRadioButton selectedButton;

    public PAPView() {
        trialPanel = new TrialPanel();
        mainPanel.add(trialPanel, BorderLayout.CENTER);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(colorRButton);
        buttonGroup.add(ShapeRButton);
        buttonGroup.add(comboRButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        selectedButton = (JRadioButton) button;
                    }
                }
            }


        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Signal Detection Theory");
        frame.setContentPane(new PAPView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
