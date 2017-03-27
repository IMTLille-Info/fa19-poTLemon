
import potlemon.render.TiledMapGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title="poTLemon";
        config.useGL30 = true;
        config.width=1280;
        config.height=780;

        new LwjglApplication(new TiledMapGame(), config);
    }
}


