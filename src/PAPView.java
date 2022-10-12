import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private JRadioButton sizeRButton;

    private TrialPanel trialPanel;
    private ButtonGroup buttonGroup;
    private JRadioButton selectedButton;
    private int correctAns;
    private Timer timer;
    private int interval;
    private int trial;

    public PAPView() {
        trialPanel = new TrialPanel();
        mainPanel.add(trialPanel, BorderLayout.CENTER);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(colorRButton);
        buttonGroup.add(ShapeRButton);
        buttonGroup.add(comboRButton);
        buttonGroup.add(sizeRButton);
        trial = 1;
        interval = 150;
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
                trialPanel.fillPanel(TrialPanel.Choice.valueOf(selectedButton.getText().toUpperCase()));
                timer = new Timer(interval, new ActionListener() {
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
            } else {
                interval += 25;
            }
        } else {
            if (input == JOptionPane.NO_OPTION) {
                correctAns++;
            } else {
                interval += 25;
            }
        }
        trialLabel.setText("Trial No: " + trial++);
        correctAnsLabel.setText("Correct: " + correctAns);
        recordData();
    }

    private void recordData() {
        if (correctAns % 10 == 0) {
            try {
                FileWriter fw = new FileWriter("results.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter outFile = new PrintWriter(bw);
                outFile.println(subjectName.getText() + "," + slider1.getValue() + "," + selectedButton.getText() + "," + interval);
                outFile.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pre Attentive Processing");
        frame.setContentPane(new PAPView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
