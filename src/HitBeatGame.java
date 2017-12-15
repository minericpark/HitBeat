import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/*
 * HitBeatGame
 * void HitBeatGame(int)
 * void inputProcess (Rectangle, int)
 * void render(float)
 * void comboCounter()
 * float getHealth()
 * boolean getScoreCompletion()
 * double getScore()
 * void dispose()
 * void pause()
 * void resize(int, int)
 * void resume()
 * void hide()
 * void show()
 * HitBeatGame initializes gameplaying screen
 * Displays and creates rectangles for beats
 * Allows inputs of UP, DOWN, RIGHT, LEFT
 * Manages health and rectangle and shows health bar
 * Shows score
 * Shows comboCounter
 * Knows when game is over, allows user to exit with escape
 */

public class HitBeatGame implements Screen{
	
	private SpriteBatch batch;
	
    private Sprite beat;
    private Sprite hitBoardUp;
    private Sprite hitBoardDown;
    private Sprite hitBoardLeft;
    private Sprite hitBoardRight;
    private Sprite mascot;
    private Sprite background;
    private Sprite greyScale;
    private Sprite gameOver1;
    private Sprite escapeInstruct;
    
    private Rectangle heatBoardUp;
    private Rectangle heatBoardDown;
    private Rectangle heatBoardLeft;
    private Rectangle heatBoardRight;
    
    private ArrayList <Sprite> notes = new ArrayList <Sprite> ();
    
    boolean inputProcess = false;
    boolean scoreNoIncrease = false;
    boolean songComplete = false;
    boolean gameOver;
    
    Beat nextNote = new Beat (0, 0, 0);
    
    SongList list = new SongList();
    Song whichSong;
    
    int score;
    int comboCount;
    int songID;
    int songLength;
    int lastBeatY;
    
    double rank;
    
    Music whichMusic = null;
    
    String scoreDisplay;
    String comboDisplay;
    
    BitmapFont font;
    
    Health health = new Health (100); 
    
    float healthBarMod = 100;
   
    
    HitBeatGame(int ID) 
    {        
        batch = new SpriteBatch();
        
        gameOver = false;
        
        whichSong = new Song(0, null);
        
        songID = ID;
        songLength = 0;
        
        if (songID == 1) //Electrodynamix has been selected
        {
            list.createElectroMix();
            songLength = list.electroMix.length();
            whichSong = list.findSong(0);
            whichSong.submitYCoords();
            whichMusic = whichSong.getMusic();
        }
        else if (songID == 2)
        {
        	list.createSubtleMix();
        	songLength = list.subtleMix.length();
        	whichSong = list.findSong(1);
        	whichSong.submitYCoords();
        	whichMusic = whichSong.getMusic();
        }
        
        score = 0;
        scoreDisplay = "Score: 0";
        comboCount = 0;
        comboDisplay = "Combo : 0";
        font = new BitmapFont();

        for (int i = 0; i < songLength; i++)
        {
        	nextNote = whichSong.whichBeat(i);
        	if (nextNote.direction == 1)
        	{
            	beat = new Sprite (new Texture(Gdx.files.internal("assets/downBeat.png")));
            	beat.setSize (80, 80);
            	beat.setPosition (200, nextNote.beatY);
        	}
        	if (nextNote.direction == 2)
        	{
            	beat = new Sprite (new Texture(Gdx.files.internal("assets/upBeat.png")));
            	beat.setSize (80, 80);
            	beat.setPosition (100, nextNote.beatY);
        	}
        	if (nextNote.direction == 3)
        	{
            	beat = new Sprite (new Texture(Gdx.files.internal("assets/rightBeat.png")));
            	beat.setSize (100, 80);
            	beat.setPosition (290, nextNote.beatY);
        	}
        	if (nextNote.direction == 4)
        	{
            	beat = new Sprite (new Texture(Gdx.files.internal("assets/leftBeat.png")));
            	beat.setSize (100, 80);
            	beat.setPosition (0, nextNote.beatY);
        	}
        	notes.add(beat);
        }
        
        background = new Sprite (new Texture(Gdx.files.internal("assets/background.jpg")));	
        background.setPosition (0, 0);
        
        hitBoardUp = new Sprite( new Texture(Gdx.files.internal("assets/upBeat.png")) );
        hitBoardUp.setSize (80, 80);
        hitBoardUp.setPosition( 100, 400 );
        
        hitBoardDown = new Sprite( new Texture(Gdx.files.internal("assets/downBeat.png")) );
        hitBoardDown.setSize (80, 80);
        hitBoardDown.setPosition( 200, 400 );

        hitBoardLeft = new Sprite( new Texture(Gdx.files.internal("assets/leftBeat.png")) );
        hitBoardLeft.setSize (100, 80);
        hitBoardLeft.setPosition( 0, 400 );
        
        hitBoardRight = new Sprite( new Texture(Gdx.files.internal("assets/rightBeat.png")) );
        hitBoardRight.setSize (100, 80);
        hitBoardRight.setPosition( 290, 400 );
        
        mascot = new Sprite (new Texture(Gdx.files.internal("assets/hitBeatMascot.gif")));
        mascot.setPosition (400, 150);
        
        gameOver1 = new Sprite (new Texture(Gdx.files.internal("assets/gameOver.png")));
        gameOver1.setPosition (20, 250);
        
        greyScale = new Sprite (new Texture (Gdx.files.internal("assets/greyScale.png")));
        greyScale.setSize(700, 700);
        greyScale.setPosition(0,0);
        
        escapeInstruct = new Sprite (new Texture(Gdx.files.internal("assets/escapeInstruct.png")));
        escapeInstruct.setPosition(25, 200);
    }

    public void render(float arg0) 
    {   
        ShapeRenderer healthBar = new ShapeRenderer();
        
        if (gameOver != true)
        {
        	whichMusic.play();
        	whichMusic.setVolume(1);	
        }
    	
        // check win condition
        heatBoardUp = new Rectangle(hitBoardUp.getBoundingRectangle());
        heatBoardDown = new Rectangle (hitBoardDown.getBoundingRectangle());
        heatBoardLeft = new Rectangle (hitBoardLeft.getBoundingRectangle());
        heatBoardRight = new Rectangle (hitBoardRight.getBoundingRectangle());
        
    	Rectangle nextBeat;
    	healthBarMod = (float) (health.healthCheck() * 4);

        batch.begin();
        background.draw(batch);
        mascot.draw( batch );
        hitBoardUp.draw( batch );
        hitBoardDown.draw(batch);
        hitBoardRight.draw(batch);
        hitBoardLeft.draw( batch );
        
        for (int i = 0; i < notes.size(); i++)
        {
        	Sprite beat = notes.get(i);
        	beat.draw(batch);
        	beat.translateY(4);
            nextBeat = beat.getBoundingRectangle();
            inputProcess(nextBeat, i);
            if (i == notes.size() - 1)
            	lastBeatY = (int) beat.getY();
        }

		healthBar.begin(ShapeType.Filled);
        healthBar.setColor(0, 1, 0, 1);
        healthBar.rect(630, 40, 20, healthBarMod);
        	
        font.setColor (1.0f, 1.0f, 1.0f, 1.0f);
        font.setScale(2, 2);
        font.draw(batch, scoreDisplay, 450, 100);
        font.draw(batch, comboDisplay, 430, 430);
        
        /*
         * Add health system and modification to rectangle
         */
        
    	if (healthBarMod == 0)
    	{
    		gameOver = true;
    		greyScale.draw(batch);
    		gameOver1.draw(batch);
    		escapeInstruct.draw(batch);
    	}
    	
    	if (gameOver)
    	{
    		whichMusic.stop();
            healthBar.setColor(0, 1, 0, 0.5f);
    	}
    	
    	if (lastBeatY > hitBoardUp.getY() && health.healthCheck() > 0)
    	{
    		songComplete = true;
    		rank = score / songLength;
    	}
    	
        batch.end();
        healthBar.end();
    }
    
    void inputProcess(Rectangle beat1, int beatNum)
    {
    	Beat nextNote = new Beat(beatNum, beatNum, beatNum);
    	if (songID == 1)
    	{
    		nextNote = list.electroMix.songSheet.get(beatNum);
    	}
    	else if (songID == 2)
    	{
    		nextNote = list.subtleMix.songSheet.get(beatNum);
    	}
    	Sprite nextSprite = notes.get(beatNum);
        // process input
    	/*
    	 * Create health system, create input delay so inputs aren't taken for multiple rectangles when they've been merged
    	 * Add percentage overlap for rectangles
    	 * 
    	 */
    	
    	if (health.health > 0)
    	{
        	if (heatBoardLeft.overlaps(beat1) && nextNote.direction == 4)
        	{
        		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) 
        		{
            		nextSprite.setSize(0,0);
            		score++;
            		scoreDisplay = "Score: " + score;
                	if (health.healthCheck() != 100)
                	{
                		health.healthIncrease();
                	}
        		}
        		else if (beat1.y == heatBoardLeft.y)
        		{
        			if (health.healthCheck() == health.healthCap())
        				healthBarMod = 0;
        			else
        				health.healthDrop();
        		}
            	
            	comboCounter();
        	}
        	
        	if (beat1.overlaps(heatBoardRight) && nextNote.direction == 3)
        	{
        		if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
        		{
            		nextSprite.setSize(0,0);
            		score++;
            		scoreDisplay = "Score: " + score;
                	if (health.healthCheck() != 100)
                	{
                		health.healthIncrease();
                	}
        		}
        		else if (beat1.y == heatBoardRight.y)
        		{
        			if (health.healthCheck() == health.healthCap())
        				healthBarMod = 0;
        			else
        				health.healthDrop();
        		}
            	comboCounter();
        	}
        	
        	if (beat1.overlaps(heatBoardUp) && nextNote.direction == 2)
        	{
        		if (Gdx.input.isKeyJustPressed(Keys.UP))
        		{
            		nextSprite.setSize(0,0);
            		score++;
            		scoreDisplay = "Score: " + score;
                	if (health.healthCheck() != 100)
                	{
                		health.healthIncrease();
                	}
        		}
        		else if (beat1.y == heatBoardUp.y)
        		{
        			if (health.healthCheck() == health.healthCap())
        				healthBarMod = 0;
        			else
        				health.healthDrop();
        		}
            	
            	comboCounter();
        	}
        	
        	if (beat1.overlaps(heatBoardDown) && nextNote.direction == 1)
        	{
        		if (Gdx.input.isKeyJustPressed(Keys.DOWN))
        		{
            		nextSprite.setSize(0,0);
            		score++;
            		scoreDisplay = "Score: " + score;
                	if (health.healthCheck() != 100)
                	{
                		health.healthIncrease();
                	}
        		}
        		else if (beat1.y == heatBoardDown.y)
        		{
        			if (health.healthCheck() == health.healthCap())
        				healthBarMod = 0;
        			else
        				health.healthDrop();
        		}
            	
            	comboCounter();
        	}
    	}
    }
    
    public void comboCounter()
    {
    	if (health.healthDropped())
    	{
    		comboCount = 0;
    		comboDisplay = "Combo: " + comboCount;
    	}
    	else
    	{
    		comboCount++;
    		comboDisplay = "Combo: " + comboCount;
    	}
    }
    
    public float getHealth()
    {
    	return healthBarMod;
    }
    
    public boolean getSongCompletion()
    {
    	return songComplete;
    }
    
    public double getScore()
    {
    	return rank;
    }
    
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		whichMusic.stop();
		whichMusic.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		whichMusic.stop();
		
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}
