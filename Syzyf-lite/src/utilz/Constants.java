package utilz;

import Main.Game;

import java.security.PublicKey;

import static utilz.Constants.EnemyConstants.FIRE;

public class Constants {

    public static class EnemyConstants {
        public static final int FIRE = 0;

        public static final int BURNING = 0;
        public static final int IDLE = 1;

        public static final int FIRE_WIDTH_DEFAULT = 64;
        public static final int FIRE_HEIGHT_DEFAULT = 40;
        public static final int FIRE_WIDTH = (int)(FIRE_WIDTH_DEFAULT * Game.SCALE);
        public static final int FIRE_HEIGHT= (int)(FIRE_HEIGHT_DEFAULT * Game.SCALE);
        public static final int FIRE_DRAWOFFSET_X = (int)(22*Game.SCALE);
        public static final int FIRE_DRAWOFFSET_Y = (int)(1*Game.SCALE);
        public static int GetSpriteAmount(int enemy_type, int enemy_state) {
            switch (enemy_type) {
                case FIRE:
                    switch (enemy_state) {
                        case IDLE:
                            return 4;
                        case BURNING:
                            return  4;

                    }
            }

            return 0;
        }

    }

    public static int GetEnemyDmg(int enemy_type) {
        switch (enemy_type) {
            case FIRE:
                return 15;
            default:
                return 0;
        }
    }
    public static class  UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT =  140;
            public static final int B_HEIGHT_DEFAULT =  56;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.SCALE);
        }
    }

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }
    public static class PlayerConstants{
        public static final int BURN = 0;
        public static final int RUNNING = 1;
        public static final int IDLE = 2;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case RUNNING:
                    return 4;
                default:
                    return 2;
            }
        }
    }


    public static class FireConstants{
        public static final int BURNING = 0;
        public static final int NOT_BURNING = 1;
    }
}
