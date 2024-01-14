package entities;
import gamestates.Playing;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.GetEnemyDmg;

public abstract class Enemy extends Entity{
    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 120;
    protected boolean attackChecked;
    protected boolean firstUpdate = true;
    protected int currentHealth;
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x,y,width,height);
    }

    protected void updateAnimationTick(){
        aniTick ++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
            }
        }

    }

    public void update(int[][] lvlData, Player player) {
        updateAnimationTick();
    }

    public int getAniIndex(){
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

    protected void checkEnemyHit(Rectangle2D.Float attackBox,Player player) {
        if (attackBox.intersects(player.hitbox))
            player.changeHealth(-GetEnemyDmg(enemyType));
    }

    protected void firstUpdateCheck(int[][] lvlData) {
        firstUpdate = false;
    }

    protected void newState(int enemyState) {
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }
    public void hurt(int amount) {
        currentHealth -= amount;
        System.out.println("hp: " + currentHealth);
    }

    public void resetEnemy(){
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        newState(BURNING);
    }
}
