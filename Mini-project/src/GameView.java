import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// View class for GUI components
class GameView {
    JFrame mainFrame;
    JLabel scoreLabel;
    ArrayList<JLabel> objects = new ArrayList<>();

    public GameView() {
        mainFrame = new JFrame("Click Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1600, 850);
        mainFrame.setLayout(null);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setBounds(10, 10, 200, 30);
        mainFrame.add(scoreLabel);
    }

    public void addObject(JLabel object) {
        objects.add(object);
        mainFrame.add(object);
    }

    public void removeObject(JLabel object) {
        objects.remove(object);
        mainFrame.remove(object);
    }

    public void repaint() {
        mainFrame.repaint();
    }

    public void setVisible(boolean visible) {
        mainFrame.setVisible(visible);
    }

    public void updateScoreLabel(int score) {
        scoreLabel.setText("Score: " + score);
    }
}