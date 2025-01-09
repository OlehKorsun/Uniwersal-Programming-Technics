import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.FutureTask;

public class ClickGame {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        FutureTask<Void> musicTask = new FutureTask<>(new MusicPlayer());
        new Thread(musicTask).start();

        FutureTask<Void> helperWindowTask = new FutureTask<>(new HelperWindow(model));
        new Thread(helperWindowTask).start();

        SwingUtilities.invokeLater(() -> view.setVisible(true));
    }
}