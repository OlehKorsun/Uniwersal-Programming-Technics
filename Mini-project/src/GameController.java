import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// Controller class to manage game logic
class GameController {
    private GameModel model;
    private GameView view;
    private Timer objectGenerator;
    private Timer objectMover;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        objectGenerator = new Timer(1000, e -> spawnObject());
        objectGenerator.start();

        objectMover = new Timer(50, e -> moveObjects());
        objectMover.start();
    }

    private void spawnObject() {
        ImageIcon icon = new ImageIcon("images.jpg"); // Replace with your image file
        JLabel object = new JLabel(icon);
        int x = (int) (Math.random() * (view.mainFrame.getWidth() - 150));
        object.setBounds(x, 0, 150, 150);
        object.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.removeObject(object);
                view.repaint();
                model.incrementScore();
                view.updateScoreLabel(model.getScore());
            }
        });
        view.addObject(object);
        view.repaint();
    }

    private void moveObjects() {
        ArrayList<JLabel> toRemove = new ArrayList<>();
        for (JLabel object : view.objects) {
            object.setLocation(object.getX(), object.getY() + 5);
            if (object.getY() > view.mainFrame.getHeight()) {
                toRemove.add(object);
                model.decrementScore();
                view.updateScoreLabel(model.getScore());
            }
        }
        for (JLabel object : toRemove) {
            view.removeObject(object);
        }
        view.repaint();
    }
}