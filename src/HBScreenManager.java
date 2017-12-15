import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

/*
 * HBScreenManager
 * HBScreenManager()
 * void updateScreen()
 * void updateGameID()
 * Screen getScreen()
 * sendGameID()
 * HBScreenManager manages each different screen there is to the game
 * Allows smooth transitions and stops screen from running when another is activated
 */

public class HBScreenManager {

	Screen game;
	Screen menu;
	Screen songSelect;
	Screen current;
	Screen results;
	int songID;
	int gameID;
	double rankScore;
	
	GameStates gamestate;
	
	HBScreenManager()
	{
		menu = new HitBeatMainMenu();
		songSelect = new HitBeatSongSelect();
		game = new HitBeatGame(gameID);
		results = new HitBeatResults(rankScore);
		gamestate = new GameStates(((HitBeatGame) game).getHealth());
		current = menu;
	}
	
	public void updateScreen()
	{
		gamestate.gameStateCheck();
		
		if (gamestate.checkGame())
		{
			songSelect.pause();
			current = game;
			gamestate.updateHealth(((HitBeatGame) game).getHealth());
			gamestate.updateSongCompletion(((HitBeatGame) game).getSongCompletion());
			rankScore = ((HitBeatGame) game).getScore();
		}
		if (gamestate.checkMenu())
		{
			current = menu;
			if (gamestate.checkWasInSongSelect())
				songSelect.pause();
		}
		if (gamestate.checkSongSelect())
		{
			current = songSelect;
			updateGameID();
			if (gamestate.checkWasInMenu())
				menu.pause();
		}
		if (gamestate.checkResult())
		{
			((HitBeatResults) results).getScore(rankScore);
			current = results;
		}
	}
	
	public void updateGameID()
	{
		gameID = (((HitBeatSongSelect) songSelect).returnSongID());
		game = new HitBeatGame(gameID);
	}
	
	public Screen getScreen ()
	{
		return current;
	}
	
	public int sendGameID()
	{
		return songID;
	}
	
}
