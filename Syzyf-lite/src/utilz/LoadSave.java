package utilz;

import Main.Game;
import entities.Fire;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.FIRE;

public class LoadSave {
    public static final String PLAYER_ATLAS = "res/postac.png";
    public static final String LEVEL_ATLAS = "res/level.png";
   // public static final String LEVEL_ONE_DATA = "res/level_one_data.png";
    public static final String LEVEL_ONE_DATA = "res/level_one_long_data.png";
    public static final String MENU_BUTTONS = "res/button_atlas.png";
    public static final String MENU_BACKGROUND = "res/background.png";
    public static final String PLAYING_BG_IMG = "res/background_playing.png";
    public static final String FIRE_SPRITE = "res/ognisko.png";
    public static BufferedImage GetSpriteAltas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } // pokazanie errora
        finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return img;
    }
    public static ArrayList<Fire> GetFires(){
        BufferedImage img = GetSpriteAltas(LEVEL_ONE_DATA);
        ArrayList<Fire> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++)
            for (int i=0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i,j));
                int value = color.getGreen();
                if (value == FIRE)
                    list.add(new Fire(i* Game.TILES_SIZE, j * Game.TILES_SIZE));
            }
        return list;

    }
    public static int[][] GetLevelData(){
        BufferedImage img = GetSpriteAltas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++)
            for (int i=0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if (value >= 8)
                    value = 0;
                lvlData[j][i] = value;
            }
        return lvlData;
    }
}
