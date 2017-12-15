import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/*
 * HitBeat
 * void create()
 * void render()
 * HitBeat extends Game and uses ScreenManager and Screens to maintain gameplay and transitions
 */

public class HitBeat extends Game{

	Screen current;
	HBScreenManager screenManager;
	
	@Override
	public void create()
	{
		screenManager = new HBScreenManager();
	}
	
	public void render()
	{
		screenManager.updateScreen();
		current = screenManager.getScreen();
		setScreen(current);
		current.render(0);
	}
	

}
