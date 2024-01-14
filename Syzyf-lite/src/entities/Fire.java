package entities;
import Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
public class Fire extends Enemy{
    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX;

    public Fire(float x, float y) {
        super(x, y, FIRE_WIDTH, FIRE_HEIGHT, FIRE);
        initHitbox(x,y,(int)(20 * Game.SCALE),(int)(38 * Game.SCALE));
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y,(int)(26*Game.SCALE),(int)(38*Game.SCALE));
        attackBoxOffsetX = (int)(Game.SCALE * -20);
    }

    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    public void drawAttackBox(Graphics g, int xLvlOffset) {
        g.setColor(Color.pink);
       // g.drawRect((int)(attackBox.x - xLvlOffset),(int)attackBox.y,(int)attackBox.width, (int)attackBox.height);
    }

    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate)
            firstUpdateCheck(lvlData);
        else {
            if (aniIndex == 0)
                attackChecked = false;
            if (aniIndex >= 2 && !attackChecked)
                checkEnemyHit(attackBox, player);
            }
        }
}
