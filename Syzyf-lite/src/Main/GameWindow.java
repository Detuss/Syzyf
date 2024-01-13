package Main;

import javax.swing.*;

public class GameWindow
{
    private  JFrame jframe;
    public GameWindow(GamePanel gamePanel)
    {
        jframe = new JFrame();
        jframe.setSize(800,400);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true); //na dole zawsze!
    }
}
