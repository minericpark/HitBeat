import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/*
 * HBLauncher
 * void Main (String [] args)
 * Launcher class launches game from HitBeat
 */

public class HBLauncher {

	public static void main (String [] args)
	{
		HitBeat game = new HitBeat();
		LwjglApplication launcher = new LwjglApplication(game);
	}
}
 