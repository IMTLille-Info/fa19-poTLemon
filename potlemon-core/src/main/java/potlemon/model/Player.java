package potlemon.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import potlemon.tools.MapKeys;


public class Player extends Sprite implements InputProcessor{
    
    private Vector2 velocity = new Vector2();
    private float speed = 100;
    
    private TiledMapTileLayer collisionLayer;
    
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer){
        super(sprite);
        this.collisionLayer=collisionLayer;
        setSize(collisionLayer.getWidth() /3,collisionLayer.getHeight() / 2);
    }
    
    public void draw(SpriteBatch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }
    
    public void update(float delta){
        float oldX = getX();
        float oldY = getY();
        
        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
        
        if(isCellBlocked(getX()+getWidth(), getY()+getHeight())){
            velocity.x=0;
            velocity.y=0;
            setX(oldX);
            setY(oldY);
        }
    }
    
    private boolean isCellBlocked(float x,float y){
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        if(cell != null && cell.getTile().getProperties().containsKey(MapKeys.BLOCKED.getString())){
            return true;
        }
        return false;
    }

    public boolean keyDown(int keyCode) {
        switch(keyCode){
            case Keys.LEFT:
                velocity.x = -speed;
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

    public boolean keyTyped(char c) { return false; }

    public boolean touchDown(int i, int i1, int i2, int i3) { return false;}

    public boolean touchUp(int i, int i1, int i2, int i3) { return false; }

    public boolean touchDragged(int i, int i1, int i2) { return false; }

    public boolean mouseMoved(int i, int i1) { return false; }

    public boolean scrolled(int i) { return false; }
    
}
