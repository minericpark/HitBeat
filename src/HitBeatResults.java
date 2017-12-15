import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * HitBeatResults
 * void HitBeatResults(double)
 * void render(float)
 * void getScore(double)
 * void dispose()
 * void pause()
 * void resize(int, int)
 * void resume()
 * void hide()
 * void show()
 * HitBeatResults displays result screen and assesses how well player has accomplished song
 */

public class HitBeatResults implements Screen{

	private SpriteBatch batch;
	private Sprite resultScreen;
	private Sprite S;
	private Sprite A;
	private Sprite B;
	private Sprite C;
	private Sprite F;
	private Sprite rank;
	
	double rankDecider;
	
	HitBeatResults(double percent)
	{
		batch = new SpriteBatch();
		
		resultScreen = new Sprite (new Texture(Gdx.files.internal("assets/scoreBackground.jpg")));
		resultScreen.setSize(700, 550);
		resultScreen.setPosition(0, -70);
		
		S = new Sprite (new Texture(Gdx.files.internal("assets/ranks/S.png")));
		S.setPosition (370, 230);
		A = new Sprite (new Texture(Gdx.files.internal("assets/ranks/A.png")));
		A.setPosition (370, 230);
		B = new Sprite (new Texture(Gdx.files.internal("assets/ranks/B.png")));
		B.setPosition (370, 230);
		C = new Sprite (new Texture(Gdx.files.internal("assets/ranks/C.png")));
		C.setPosition (370, 230);
		F = new Sprite (new Texture (Gdx.files.internal("assets/ranks/F.png")));
		F.setPosition(370,  230);
		rank = new Sprite (new Texture(Gdx.files.internal("assets/ranks/rank.png")));
		rank.setPosition (150, 230);
		
	}
	

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		
		batch.begin();
		resultScreen.draw(batch);
		if (rankDecider >= 90)
			S.draw(batch);
		else if (rankDecider < 90 && rankDecider >= 80)
			A.draw(batch);
		else if (rankDecider < 80 && rankDecider >= 70)
			B.draw(batch);
		else if (rankDecider < 70 && rankDecider >= 50)
			C.draw(batch);
		else
			F.draw(batch);
		rank.draw(batch);
		batch.end();
		batch.dispose();
	}
	
	public void getScore(double rankScore)
	{
		rankDecider = rankScore*100;
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
