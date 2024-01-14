package utilz;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "res/postac.png";
    public static final String LEVEL_ATLAS = "res/level.png";
    public static final String LEVEL_ONE_DATA = "res/level_one_data.png";
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

    public static int[][] GetLevelData(){
        int[][] lvlData = new int[Game.GAME_WIDTH][Game.GAME_HEIGHT];
        BufferedImage img = GetSpriteAltas(LEVEL_ONE_DATA);
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
