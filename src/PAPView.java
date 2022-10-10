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
    private JLabel correctAnsLabel;
    private JLabel trialLabel;

    private TrialPanel trialPanel;
    private ButtonGroup buttonGroup;
    private JRadioButton selectedButton;
    private int correctAns;
    private Timer timer;
    private int delay;
    private int trial;

    public PAPView() {
        trialPanel = new TrialPanel();
        mainPanel.add(trialPanel, BorderLayout.CENTER);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(colorRButton);
        buttonGroup.add(ShapeRButton);
        buttonGroup.add(comboRButton);
        trial = 0;
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        selectedButton = (JRadioButton) button;
                    }
                }
                trialPanel.setDistractors(slider1.getValue());
                trialPanel.fillPanel();
                timer = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        trialPanel.clear();
                        timer.stop();
                        feedback();
                    }
                });
                timer.start();
            }
        });
    }

    private void feedback() {
        int input = JOptionPane.showConfirmDialog(null,
                "Was the target present?", "Select an Option...", JOptionPane.YES_NO_OPTION);
        if (trialPanel.getTarget()){
            if (input == JOptionPane.YES_OPTION){
                correctAns++;
            } else {delay += 50;}
        } else {
            if (input == JOptionPane.NO_OPTION) {
                correctAns++;
            } else {delay += 50;}
        }
        trialLabel.setText("Trial No: " + trial++);
        correctAnsLabel.setText("Correct: " + correctAns);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Signal Detection Theory");
        frame.setContentPane(new PAPView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
