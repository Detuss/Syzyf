package Main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;


import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

import static Main.Game.GAME_HEIGHT;
import static Main.Game.GAME_WIDTH;
public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;

    private Game game;


    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game=game;
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }
    public void updateGame () {

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
