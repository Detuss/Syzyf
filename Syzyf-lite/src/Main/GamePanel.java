package Main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel
{
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;

    private float xDir=1f, yDir = 1f;
    private int frames = 0;
    private long lastCheck = 0;
    private Random random;

    private Color color = new Color(104, 119, 143);



    public GamePanel()
    {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }
    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void seRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta,(int)yDelta,200,50);

    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > 800 || xDelta < 0)
        {
            xDir*=-1;
            color = getRndColor();
        }

        yDelta += yDir;
        if(yDelta > 400 || yDelta < 0){
            color = getRndColor();
            yDir*=-1;
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r,g,b);
    }
}
