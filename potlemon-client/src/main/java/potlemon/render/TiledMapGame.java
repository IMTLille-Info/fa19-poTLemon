package potlemon.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import potlemon.core.network.client.PotlemonClient;
import potlemon.core.network.exceptions.NetworkClientException;
import potlemon.screen.PokeFight;
import potlemon.screen.PokeWorld;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import javax.swing.*;

public class TiledMapGame extends Game {

    // NETWORK CLIENT
    private PotlemonClient networkClient =  PotlemonClient.getInstance();

    private Viewport viewport;
    private Camera camera;

    public void create() {

        camera = new PerspectiveCamera();
        viewport = new FitViewport(1280, 780, camera);

        setScreen(new PokeWorld("sprites/TileMap/WorldStart.tmx"));

        viewport.apply();

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();

        viewport.apply();

        if (Gdx.input.isKeyPressed(Keys.R)) {
            try {
                // removes all listeners on option
                networkClient.removeListeners();

                setScreen(getScreen().getClass().newInstance());

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (Gdx.input.isKeyPressed(Keys.F1) || Gdx.input.isKeyPressed(Keys.F2)) {

            if(Gdx.input.isKeyPressed(Keys.F1)){
                setScreen(new PokeWorld("sprites/TileMap/WorldStart.tmx"));
            } else {
                setScreen(new PokeFight());
            }

        } else if (Gdx.input.isKeyPressed(Keys.N)) {

            if (!networkClient.isStarted()) {
                System.out.println("Starting network mode...");

                try {
                    networkClient.start();
                } catch (NetworkClientException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("STOPPING network mode...");
                networkClient.stop();
            }

        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
