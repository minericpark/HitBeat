import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/*
 * GameStates
 * GameStates(float)
 * void gameStateCheck()
 * boolean checkGame()
 * boolean checkWasInGame()
 * boolean checkMenu()
 * boolean checkWasInMenu()
 * boolean checkSelectScreen()
 * boolean checkWasInSelectScreen()
 * boolean checkResults()
 * boolean checkWasInResults()
 * void updateHealth(float)
 * void updateSongCompletion(boolean)
 * GameState manages gamestates in the program and transmits booleans to Screen Manager
 */

public class GameStates {

	boolean inGame;
	boolean inMenu;
	boolean inSongSelect;
	boolean inResults;
	boolean wasInGame;
	boolean wasInMenu;
	boolean wasInSongSelect;
	boolean wasInResults;
	
	boolean songComplete;
	int health;
	
	GameStates(float f)
	{
		inGame = false;
		inMenu = true;
		inSongSelect = false;
		inResults = false;
		wasInGame = false;
		wasInMenu = false;
		wasInSongSelect = false;
		wasInResults = false;
		
		health = (int) f;
		songComplete = false;
	}
	
	public void gameStateCheck()
	{
		if (inGame)
		{
			if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			{
				if (health == 0)
				{
					inGame = false;
					inSongSelect = true;
					wasInGame = true;
					wasInMenu = false;
					wasInSongSelect = false;
					wasInResults = false;
				}
			}
			if (songComplete)
			{
				inGame = false;
				inResults = true;
				wasInGame = true;
				wasInMenu = false;
				wasInSongSelect = false;
				wasInResults = false;
			}
		}
		else if (inMenu)
		{
			if (Gdx.input.isKeyPressed(Keys.ENTER))
			{
				inMenu = false;
				inSongSelect = true;
				wasInMenu = true;
				wasInGame = false;
				wasInSongSelect = false;
				wasInResults = false;
			}
		}
		else if (inSongSelect)
		{
			if (Gdx.input.isKeyJustPressed(Keys.SPACE))
			{
				inSongSelect = false;
				inGame = true;
				wasInSongSelect = true;
				wasInGame = false;
				wasInMenu = false;
				wasInResults = false;
			}
			if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			{
				inSongSelect = false;
				inMenu = true;
				wasInSongSelect = true;
				wasInGame = false;
				wasInMenu = false;
				wasInResults = false;
			}
		}
		else if (inResults)
		{
			if (Gdx.input.isKeyPressed(Keys.ENTER))
			{
				inResults = false;
				inSongSelect = true;
				wasInResults = true;
				wasInGame = false;
				wasInMenu = false;
				wasInSongSelect = false;
			}
		}
	}
	
	public boolean checkGame()
	{
		return inGame;
	}
	
	public boolean checkWasInGame()
	{
		return wasInGame;
	}
	
	public boolean checkMenu()
	{
		return inMenu;
	}
	
	public boolean checkWasInMenu()
	{
		return wasInMenu;
	}
	
	public boolean checkSongSelect()
	{
		return inSongSelect;
	}
	
	public boolean checkWasInSongSelect()
	{
		return wasInSongSelect;
	}
	
	public boolean checkResult()
	{
		return inResults;
	}
	
	public boolean checkWasInResults()
	{
		return wasInResults;
	}
	
	public void updateHealth(float f)
	{
		health = (int) f;
	}
	
	public void updateSongCompletion(boolean songDone)
	{
		songComplete = songDone;
	}
}
