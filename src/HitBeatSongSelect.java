import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/*
 * HitBeatSongSelect
 * HitBeatSongSelect()
 * void render(float)
 * int returnSongID()
 * void dispose()
 * void hide()
 * void pause()
 * void resize(int, int)
 * void resume()
 * void show()
 * HitBeatSongSelect allows user to select different songs (currently only 2)
 */

public class HitBeatSongSelect implements Screen{
	
	SpriteBatch sprites;
	
	ArrayList <Sprite> songBoxes = new ArrayList <Sprite>();
	
	Sprite songMenu;
	Sprite songBox;
	Sprite spaceInstruct;
	
	SongList songlist= new SongList();
	Song current;
	int songID;
	
	Music titleMusic;
	
    String title1;
    String title2;
    String author1;
    String author2;
    
    BitmapFont font;
	
	
	HitBeatSongSelect()
	{
		sprites = new SpriteBatch();
		songMenu = new Sprite (new Texture(Gdx.files.internal("assets/songSelectBackground.jpg")));
        songMenu.setSize (730, 480);
		/*
		mainMenu.setSize ();
		*/
        songMenu.setPosition(0, 0);
        
        spaceInstruct = new Sprite (new Texture(Gdx.files.internal("assets/spaceInstruct.png")));
        spaceInstruct.setPosition(10,10);
        
        font = new BitmapFont();
        
        title1 = "Song Title: ElectroDynamix";
        title2 = "Song Title: Theory of Everything";
        author1 = "Artist: DJ Nate";
        author2 = "Artist: DJ Nate";
        
        for (int i = 0; i < songlist.length(); i++)
        {
            songBox = new Sprite (new Texture(Gdx.files.internal("assets/songBox2.png")));
            songBox.setSize (300, 60);
            songBox.setPosition (10, 300 + i*100);
            songBoxes.add(songBox);
        }
        
        titleMusic = (Music) Gdx.audio.newMusic(Gdx.files.internal("assets/TitleScreen.mp3"));
        current = songlist.findSong(0);
        songID = 1;
	}
	
    public void render(float arg0) 
    {   
        ShapeRenderer selectBar = new ShapeRenderer();
        
    	sprites.begin();
    	
       	titleMusic.play();
    	titleMusic.setVolume(1);
    	titleMusic.setLooping(true);
    	
    	songMenu.draw(sprites);
    	songBox.draw(sprites);
    	spaceInstruct.draw(sprites);
    	for (int i = 0; i < songBoxes.size(); i++)
        {
    		Sprite song = songBoxes.get(i);
            song.draw(sprites);
        }
    	
        font.setColor (1.0f, 1.0f, 1.0f, 1.0f);
        font.setScale(1, 1);
        font.draw(sprites, title1, 70, 450);
        font.draw(sprites, title2, 70, 350);
        font.draw(sprites, author1, 70, 430);
        font.draw(sprites, author2, 70, 330);
    	
		
        selectBar.begin(ShapeType.Filled);
        selectBar.setColor(0, 1, 0, 1);
        if (songID == 1)
        {
            selectBar.rect(35, 410, 15, 41);
        }
        if (songID == 2)
        {
            selectBar.rect(35, 310, 15, 41);
        }
       
        // process input
    	if (Gdx.input.isKeyPressed(Keys.DOWN))
    	{
    		if (songID < 2) //Change to 2 if everything is not completed
    		{
        		songID++;
    		}
    	}
    	else if (Gdx.input.isKeyPressed(Keys.UP))
    	{
    		if (songID > 1)
    		{
        		songID--;
    		}
    	}
    	
        // draw graphics
        sprites.end();
    	selectBar.end();
    }
    
    public int returnSongID()
    {
		return songID;
    	//return songID
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
