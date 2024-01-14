package entities;

import static utilz.Constants.PlayerConstants.*;

import static utilz.HelpMethods.CanMoveHere;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import Main.Game;
import gamestates.Playing;
import utilz.LoadSave;


public class Player extends Entity{

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean left,up,down,right;
    private float playerSpeed = 2.0f;
    private boolean moving = false;
    private int[][] lvlData;
    private float xDrawOffSet = 8*Game.SCALE;
    private float yDrawOffSet = 4*Game.SCALE;
    private int maxHealth = 10;
    private int currentHealth = maxHealth;
    private boolean attacking = false;
    private boolean attackChecked;
    private Playing playing;
    private Rectangle2D.Float attackBox;
    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        loadAnimations();
        initHitbox(x,y,(int)(40*Game.SCALE), (int)(32*Game.SCALE));
        initAttackBox();
    }
    public void update () {
        if (currentHealth <= 0){
            playing.setGameOver(true);
        return;
    }
        updateAttackBox();
        updatePos();
        checkAttack();
        updateAnimationTick();
        setAnimation();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
    }

    private void checkAttack() {
        if(attackChecked)
            return;
        attackChecked = true;
        playing.checkEnemyHit(attackBox);
    }

    private void updateAttackBox() {
        if (right)
            attackBox.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 10);
        else if (left)
            attackBox.x = hitbox.x - hitbox.width - (int) (Game.SCALE * 10);

        attackBox.y = hitbox.y + (Game.SCALE * 10);
    }

    private void drawAttackBox(Graphics g, int lvlOffsetX) {
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x - lvlOffsetX, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);

    }

    public void render(Graphics g, int lvlOffset) {
        g.drawImage(animations[playerAction][aniIndex],(int)(hitbox.x - xDrawOffSet) - lvlOffset,(int)(hitbox.y - yDrawOffSet),(int)(64*Game.SCALE),(int)(40*Game.SCALE),null);
        //drawHitbox(g, lvlOffset);
        //drawAttackBox(g, lvlOffset);
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }

        }
    }
    private void setAnimation() {

        int startAni = playerAction;
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
        if(startAni != playerAction){
            resetAniTick();
        }
    }
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    private void updatePos() {
        moving = false;
        if (!left && !right && !up && !down)
            return;

        float xSpeed = 0, ySpeed = 0;

        if (left && !right)
            xSpeed = -playerSpeed;
        else if (right && !left)
            xSpeed = playerSpeed;

        if (up && !down)
            ySpeed = -playerSpeed;
        else if (down && !up)
            ySpeed = playerSpeed;

        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
        }
    }
    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAltas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[3][4];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void resetDirBoolians(){
        left = false;
        down = false;
        up = false;
        right = false;
    }

    public void changeHealth(int value) {
        currentHealth += value;

        if (currentHealth <= 0)
            currentHealth = 0;
        else if (currentHealth >= maxHealth)
            currentHealth = maxHealth;
    }

    public void resetAll(){
        resetDirBoolians();
        attacking = false;
        moving = false;
        playerAction = IDLE;
        currentHealth = maxHealth;

        hitbox.x = x;
        hitbox.y = y;
    }
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
