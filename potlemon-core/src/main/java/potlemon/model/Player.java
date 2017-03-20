package potlemon.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Player extends Sprite implements InputProcessor{
    
    private Vector2 velocity = new Vector2();
    private float speed = 60*2, gravity = 60*1.8f;
    
    public Player(Sprite sprite){
        super(sprite);
    }
    
    public void draw(SpriteBatch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }
    
    public void update(float delta){
        
        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
        
                setX(10);

    }

    public boolean keyDown(int keyCode) {
        switch(keyCode){
            case Keys.LEFT:
                velocity.x = -speed;
                System.out.println("Lefting\n");
                break;
            case Keys.RIGHT:
                velocity.x = speed;
                break;
            case Keys.UP:
                velocity.y = speed;
                break;
            case Keys.DOWN:
                velocity.y = -speed;
                break; 
        }
        return true;
    }

    public boolean keyUp(int keyCode) {
        switch(keyCode){
            case Keys.LEFT:
                velocity.x =0;
                break;
            case Keys.RIGHT:
                velocity.x =0;
                break;
            case Keys.UP:
                velocity.y =0;
                break;
            case Keys.DOWN:
                velocity.y =0;
                break;
        }
        return true;
    }

    public boolean keyTyped(char c) {
        return false;
    }

    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    public boolean scrolled(int i) {
        return false;
    }
    
}
