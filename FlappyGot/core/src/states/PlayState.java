package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyGoT;

import sprites.Crow;
import sprites.Tube;



public class PlayState extends State {
	private static final int TUBE_SPACING= 125;
	private static final int TUBE_COUNT=4;
	private static final int GROUND_Y_OFFSET=-60;
	private Crow bird;
	private Texture bg;
	private Texture ground;
	private Vector2 groundPos1, groundPos2;
	public int Pontos;
	private BitmapFont font;
	private Array<Tube>tubes;
	private int lifes=3;
	

	public PlayState(GameStateManager gsm) {
		super(gsm);
		bird= new Crow(50,300);
		cam.setToOrtho(false, FlappyGoT.width/2,FlappyGoT.height/2);
		bg = new Texture("battleground3.png");
		ground = new Texture("ground.png");
		groundPos1= new Vector2(cam.position.x-cam.viewportWidth/2, GROUND_Y_OFFSET);
		groundPos2= new Vector2((cam.position.x-cam.viewportWidth/2)+ ground.getWidth(),GROUND_Y_OFFSET );
		tubes= new Array<Tube>();
		for(int i=1; i<= TUBE_COUNT; i++) {
			tubes.add(new Tube(i*(TUBE_SPACING+ Tube.TUBE_WIDTH)));
		}
		font = new BitmapFont();
        font.setColor(Color.WHITE);
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched())
			bird.jump();
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
		updateGround();
		bird.update(dt);
		cam.position.x = bird.getPosition().x+80;
		cam.position.y = FlappyGoT.height/2-200 + bird.getPosition().y/8;
		
		for(int i = 0; i<tubes.size; i++) {
			Tube tube = tubes.get(i);
			if(cam.position.x-(cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
				tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH+ TUBE_SPACING)*TUBE_COUNT));
			}
			
			if(tube.collides(bird.getBounds())) {
				
					gsm.set(new GameOverState(gsm));
				
			}
	
		}
		
		
		Pontos= (int) Math.round(((double)bird.getPosition().x/100 / 2)+0.5d)-1;
		
		if(bird.getPosition().y<= ground.getHeight()+GROUND_Y_OFFSET) {
				
			gsm.set(new GameOverState(gsm));
			
		}
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		
		// TODO Auto-generated method stub
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
		sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
		for(Tube tube: tubes) {
		sb.draw(tube.getTopTube(), tube.getPosTopTube().x,tube.getPosTopTube().y);
		sb.draw(tube.getBottomTube(), tube.getPosBotTube().x,tube.getPosBotTube().y);
		}
		System.out.println(lifes);
		if((int)Pontos>=3) {
			font.draw(sb, "PRIMEIRA FASE CONCLUIDA", bird.getPosition().x-50, 350);
		}
		else {
		font.draw(sb, Integer.toString(Pontos), bird.getPosition().x+100, 350);
		}
		sb.draw(ground, groundPos1.x,groundPos1.y);
		sb.draw(ground, groundPos2.x,groundPos2.y);
		
		sb.end();
	}

	@Override
	public void dispose() {
		bg.dispose();
		bird.dispose();
		ground.dispose();
		for(Tube tube : tubes)
			tube.dispose();
        font.dispose();
		System.out.println("Play State Disposed");
		
	}

	private void updateGround() {
		if(cam.position.x-(cam.viewportWidth/2)>groundPos1.x+ground.getWidth())
				groundPos1.add(ground.getWidth()*2,0);
		if(cam.position.x-(cam.viewportWidth/2)>groundPos2.x+ground.getWidth())
			groundPos2.add(ground.getWidth()*2,0);
	}
	public int getPoints() {
		return Pontos;
	}
}
