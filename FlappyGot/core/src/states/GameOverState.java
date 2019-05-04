package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.FlappyGoT;

public class GameOverState extends State{

	private Texture gameOver;
	private Texture againBtn;
	private Texture CreditsBtn;
	private BitmapFont font;
	public int Pontos;
	public GameOverState(GameStateManager gsm) {
		super(gsm);
		gameOver = new Texture("gameover2.png");
		againBtn = new Texture("againbutton.png");
		CreditsBtn= new Texture("Credits.png");
		font = new BitmapFont();
        font.setColor(Color.RED);
	}

	@Override
	protected void handleInput() {
		if(Gdx.input.justTouched()) {
			gsm.set(new PlayState(gsm));
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(gameOver, 0, 0, FlappyGoT.width,FlappyGoT.height);
		sb.draw(againBtn,80,200);
		sb.draw(CreditsBtn,220,200);
		font.draw(sb,"Pontos",100, 350);
		sb.end();
		
	}

	@Override
	public void dispose() {
		gameOver.dispose();
		againBtn.dispose();
		CreditsBtn.dispose();
		System.out.println("Menu State Disposed");
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

}
