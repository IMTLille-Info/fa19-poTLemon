package potlemon.render;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import potlemon.core.network.client.PotlemonClient;
import potlemon.core.network.exceptions.NetworkClientException;
import potlemon.screen.PokeWorld;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import javax.swing.*;

public class TiledMapGame extends Game {

    // NETWORK CLIENT
    private PotlemonClient networkClient = null;

    public void create() {
        setScreen(new PokeWorld());
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
        if (Gdx.input.isKeyPressed(Keys.R)) {
            try {
                setScreen(getScreen().getClass().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (Gdx.input.isKeyPressed(Keys.N)) {

            if (networkClient == null) {
                System.out.println("Starting network mode...");
                networkClient = PotlemonClient.getInstance();

                try {
                    networkClient.start();
                } catch (NetworkClientException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("STOPPING network mode...");
                networkClient.stop();
                networkClient=null;
            }


        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
