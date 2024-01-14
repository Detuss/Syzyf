package gamestates;

import Main.Game;
import entities.EnemyManager;
import entities.Player;
import iu.GameOverOverlay;
import levels.LevelManager;
import utilz.LoadSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Playing extends State implements Statemethods{
    private GameOverOverlay gameOverOverlay;
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private int xLvlOffset;
    private int leftBoarder = (int)(0.4*Game.GAME_WIDTH);
    private int rightBoarder = (int)(0.6*Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;
    private boolean gameOver;

    private BufferedImage backgroundImg;
    public Playing(Game game) {
        super(game);
        initClasses();

        backgroundImg = LoadSave.GetSpriteAltas(LoadSave.PLAYING_BG_IMG);
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        player = new Player(800, 400, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE), this);
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
        gameOverOverlay = new GameOverOverlay(this);
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
        enemyManager.update(levelManager.getCurrentLevel().getLvlData(), player);
        checkCloseToBoarder();
    }

    private void checkCloseToBoarder() {
        int playerX = (int) player.getHitbox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBoarder)
            xLvlOffset += diff - rightBoarder;
        else if(diff < leftBoarder)
            xLvlOffset += diff - leftBoarder;

        if (xLvlOffset > maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = maxLvlOffsetX;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0,Game.GAME_WIDTH, Game.GAME_HEIGHT,null);
        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        enemyManager.draw(g, xLvlOffset);

        if (gameOver)
            gameOverOverlay.draw((g));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver)
            gameOverOverlay.keyPressed((e));
        else
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOver)
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
        }
    }

    public void windowFocusLost() {
        player.resetDirBoolians();
    }

    public Player getPlayer() {
        return player;
    }

    public void resetAll(){
        gameOver = false;
        player.resetAll();
        enemyManager.resetAllEnemies();
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
