import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/*
 * HitBeatMainMenu
 * HitBeatMainMenu()
 * void render(float)
 * void dispose()
 * void pause()
 * void resize(int, int)
 * void resume()
 * void hide()
 * void show()
 * HitBeatMainMenu displays Main Menu screen and instructs player to press enter to enter song select screen
 */

public class HitBeatMainMenu implements Screen {
	
	private SpriteBatch sprites;
	private Sprite mainMenu;
	private Sprite title;
	private Sprite enter;
	
	Music titleMusic;
	
    String noOfSongDisplay;
    String versionDisplay;
    String mention;
    String author;
    String purpose;
    
    BitmapFont font;
	
	boolean onScreen;
	
	HitBeatMainMenu()
	{
		onScreen = true; 
		
		sprites = new SpriteBatch();
		
        font = new BitmapFont();
        
        versionDisplay = "v3.0";
        noOfSongDisplay = "Songs: 2";
        mention = "Based on the widely popular 'StepMania' rhythm game";
        author = "Program created by Min Park";
        purpose = "Project for ICS4U0 Class with Mr. Camilleri";
        
		
        title = new Sprite (new Texture(Gdx.files.internal("assets/title.png")));
        title.setPosition (70, 370);
        
		mainMenu = new Sprite (new Texture(Gdx.files.internal("assets/backgroundMenu.jpg")));
		mainMenu.setSize(630, 500);
		/*
		mainMenu.setSize ();
		*/
        mainMenu.setPosition(0, 0);
        
        enter = new Sprite (new Texture(Gdx.files.internal("assets/enterInstruct.png")));
        enter.setPosition(200,40);
        
        titleMusic = (Music) Gdx.audio.newMusic(Gdx.files.internal("assets/mainMenu.mp3"));
	}
	
    public void render(float arg0) 
    {   
    	titleMusic.play();
    	titleMusic.setVolume((float) 0.5);
    	titleMusic.setLooping(true);
    	
    	sprites.begin();
    	mainMenu.draw(sprites);
    	title.draw(sprites);
    	enter.draw(sprites);
    	
        font.setColor (1.0f, 1.0f, 1.0f, 1.0f);
        font.setScale(1, 1);
        font.draw(sprites, noOfSongDisplay, 70, 350);
        font.draw(sprites, versionDisplay, 500, 375);
        font.draw(sprites, mention, 70, 480);
        font.draw(sprites, author, 70, 330);
        font.draw (sprites, purpose, 10, 20);
    	
        sprites.end();
    }
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		titleMusic.stop();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	

}
