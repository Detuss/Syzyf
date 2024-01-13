package inputs;

import Main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInputs implements KeyListener {

    private GamePanel gamePanel;
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                System.out.println("w");
                gamePanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
                System.out.println("a");
                gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                System.out.println("s");
                gamePanel.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
                System.out.println("d");
                gamePanel.changeXDelta(5);
                break;
        }
    }
}
