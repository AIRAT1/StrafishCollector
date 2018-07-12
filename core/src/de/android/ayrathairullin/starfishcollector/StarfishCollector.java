package de.android.ayrathairullin.starfishcollector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class StarfishCollector extends Game {
	private SpriteBatch batch;
	private Texture turtleTexture, starfishTexture, waterTexture, winTexture;
	private Rectangle turtleRectangle, starfishRectangle;
	private float turtleX, turtleY, starfishX, starfishY;
	private boolean win;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		turtleTexture = new Texture(Gdx.files.internal("turtle.png"));
		starfishTexture = new Texture(Gdx.files.internal("starfish.png"));
		waterTexture = new Texture(Gdx.files.internal("water.jpg"));
		winTexture = new Texture(Gdx.files.internal("you_win.png"));
		initGame();
	}

	private void initGame() {
		turtleX = 20;
		turtleY = 20;
		turtleRectangle = new Rectangle(turtleX, turtleY, turtleTexture.getWidth(), turtleTexture.getHeight());
		starfishX = 380;
		starfishY = 380;
		starfishRectangle = new Rectangle(starfishX, starfishY, starfishTexture.getWidth(), starfishTexture.getHeight());
		win = false;
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			turtleX--;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			turtleX ++;
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			turtleY++;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			turtleY--;
		}

		turtleRectangle.setPosition(turtleX, turtleY);

		if (turtleRectangle.overlaps(starfishRectangle)) win = true;

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(waterTexture, 0, 0);

		if (!win) {
			batch.draw(starfishTexture, starfishX, starfishY);
			batch.draw(turtleTexture, turtleX, turtleY);
		}

		if (win) {
			batch.draw(winTexture, 180, 180);
			if (Gdx.input.isKeyPressed(Keys.SPACE)) {
				initGame();
			}
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		turtleTexture.dispose();
		starfishTexture.dispose();
		waterTexture.dispose();
		winTexture.dispose();
	}
}
