package levels;

import Main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;
    public LevelManager(Game game) {
        this.game = game;
        importLevel();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importLevel() {
        BufferedImage img = LoadSave.GetSpriteAltas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[8];
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 4; i ++) {
                int index = j*4 + i;
                levelSprite[index] = img.getSubimage(i*32, j*32 ,32,32);
            }
    }

    public void draw(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < levelOne.getLvlData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i*Game.TILES_SIZE - lvlOffset, j*Game.TILES_SIZE, Game.TILES_SIZE,Game.TILES_SIZE,null);
            }
    }

    public void update() {

    }

    public Level getCurrentLevel(){
        return levelOne;
    }
}
