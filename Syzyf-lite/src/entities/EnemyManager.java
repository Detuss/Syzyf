package entities;

import gamestates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[][] fireArr;
    private ArrayList<Fire> fires = new ArrayList<>();
    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    private void addEnemies() {
        fires = LoadSave.GetFires();
        System.out.println("how many fires: " + fires.size());
    }

    public void update(int[][] lvlData, Player player) {
    for (Fire c : fires)
            c.update(lvlData, player);
    }
    public void draw(Graphics g, int xLvlOffset) {
        drawFire(g, xLvlOffset);
    }

    private void drawFire(Graphics g, int xLvlOffset) {
        for (Fire c: fires) {
            g.drawImage(fireArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset, (int) c.getHitbox().y, FIRE_WIDTH, FIRE_HEIGHT, null);
            c.drawAttackBox(g, xLvlOffset);
        }
    }

    private void loadEnemyImgs() {
        fireArr = new BufferedImage[2][4];
        BufferedImage temp = LoadSave.GetSpriteAltas(LoadSave.FIRE_SPRITE);
        for (int j = 0; j < fireArr.length; j ++)
            for (int i = 0; i < fireArr[j].length;i++){
                fireArr[j][i] = temp.getSubimage(i* FIRE_WIDTH_DEFAULT, j*FIRE_HEIGHT_DEFAULT, FIRE_WIDTH_DEFAULT, FIRE_HEIGHT_DEFAULT);
        }
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Fire c : fires)
            if (attackBox.intersects(c.getHitbox())) {
                //c.hurt(10);
                return;
            }
    }

    public void resetAllEnemies(){
        for(Fire c: fires)
            c.resetEnemy();
    }

}
