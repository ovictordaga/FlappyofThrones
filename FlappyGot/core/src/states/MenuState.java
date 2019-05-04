package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.FlappyGoT;

public class MenuState extends State {

	
	private Texture background;
	private Texture playBtn;
	private Texture Titleimg;
	private int screenSizex;
	private int screenSizey;
	
	

	public MenuState(GameStateManager gsm) {
		super(gsm);
		background = new Texture("Battleground2.png");
		playBtn = new Texture("playButton.png");
		Titleimg= new Texture("title.png");
		
		
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()) {
			gsm.set(new PlayState(gsm));
		}
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(background, 0, 0, FlappyGoT.width,FlappyGoT.height);
		sb.draw(playBtn,(FlappyGoT.width)/2 - (playBtn.getWidth()/2),FlappyGoT.height/2 -130 );
		sb.draw(Titleimg,(FlappyGoT.width)/2 - (Titleimg.getWidth()/2),FlappyGoT.height/2 -75 );
		sb.end();
		
	}

	@Override
	public void dispose() {
		background.dispose();
		playBtn.dispose();
		System.out.println("Menu State Disposed");
	}

	public int getSizeScreenx() {
		return screenSizex;
	}
	
	public int getSizeScreeny() {
		return screenSizey;
	}
}
