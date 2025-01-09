import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;

// Class for the helper window
class HelperWindow implements Callable<Void> {
    private GameModel model;

    public HelperWindow(GameModel model) {
        this.model = model;
    }

    @Override
    public Void call() {
        JFrame helperFrame = new JFrame("Score Viewer");
        helperFrame.setSize(400, 400);
        helperFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        helperFrame.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Current Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        helperFrame.add(scoreLabel, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel();
        helperFrame.add(imageLabel, BorderLayout.CENTER);

        Timer scoreUpdater = new Timer(100, e -> {
            int score = model.getScore();
            scoreLabel.setText("Current Score: " + score);
            if (score < -10) {
                imageLabel.setIcon(new ImageIcon("bad.png")); // Replace with your image file
            } else if (score > 10) {
                imageLabel.setIcon(new ImageIcon("good.jpg")); // Replace with your image file
            } else {
                imageLabel.setIcon(new ImageIcon("ok.jpg")); // Replace with your image file
            }
        });
        scoreUpdater.start();

        helperFrame.setVisible(true);
        return null;
    }
}