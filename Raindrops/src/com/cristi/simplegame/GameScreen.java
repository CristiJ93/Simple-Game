package com.cristi.simplegame;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.cristi.objects.Bucket;
import com.cristi.objects.DropGenerator;
import com.cristi.objects.Raindrop;

public class GameScreen implements ApplicationListener {
	private Texture dropImage, bucketImage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Bucket bucket;
	private DropGenerator dropFactory;
	private Score score;
	private TextWriter textWriter;
	private long lastDropTime, dropInterval = 1000000000;
	private int gameState = GameStates.INIT,dropSpeed = 200;
	private float[] backgroundColor = { 0, 0, 0.2f, 1 };
	boolean end = false;

	@Override
	public void create() {
		dropImage = new Texture(Gdx.files.internal("Resources/droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("Resources/bucket.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Properties.WIDTH, Properties.HEIGHT);
		batch = new SpriteBatch();
		textWriter = new TextWriter(batch);
		bucket = new Bucket(Properties.bucketX, Properties.bucketY,
				Properties.bucketWidth, Properties.bucketHeight);
		score = new Score();
		dropFactory = new DropGenerator(score);
		dropFactory.addRandomDrop(Properties.dropWidth, Properties.dropHeight,
				Properties.leftLimit, Properties.rightLimit, Properties.HEIGHT);

	}
	@Override
	public void render() {
		switch (gameState) {
		case GameStates.INIT:
			initScreen();
			break;
		case GameStates.RUN:
			updateGame();
			break;
		case GameStates.GAME_OVER:
			endGame();
			break;
		}
	}

	public void initScreen() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		textWriter.write("Click to start the game", Properties.WIDTH / 2 - 50,
				Properties.HEIGHT / 2);
		textWriter.write("Hold mouse left and move the mouse",
				Properties.WIDTH / 2 - 50, Properties.HEIGHT / 2 - 20);
		if (Gdx.input.isTouched()) {
			gameState = GameStates.RUN;
		}
	}

	public void updateGame() {
		Gdx.gl.glClearColor(backgroundColor[0], backgroundColor[1],
				backgroundColor[2], backgroundColor[3]);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		drawComponents();
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.setX(touchPos.x - bucket.getWidth() / 2);
		}
		bucket.checkBounds(Properties.WIDTH);
		updateRainDrops();
		updateSpeed(5);
		score.updateScore();
		if (score.isGameOver())
			gameState = GameStates.GAME_OVER;
		textWriter.write("Score " + score.getCurrentScore() + " ",
				Properties.WIDTH - 120, Properties.HEIGHT - 20);
	}

	public void drawComponents() {
		batch.begin();
		batch.draw(bucketImage, bucket.getX(), bucket.getY());
		Raindrop drop;
		for (int i = 0; i < dropFactory.getDrops().size(); i++) {
			drop = dropFactory.getDrops().get(i);
			batch.draw(dropImage, drop.getX(), drop.getY());
		}
		batch.end();
	}

	public void updateRainDrops() {

		if (TimeUtils.nanoTime() - lastDropTime > dropInterval) {
			dropFactory.addRandomDrop(Properties.dropWidth,
					Properties.dropHeight, Properties.leftLimit,
					Properties.rightLimit, Properties.HEIGHT);
			lastDropTime = TimeUtils.nanoTime();
		}
		float speed = dropSpeed * Gdx.graphics.getDeltaTime();
		dropFactory.updateDrops(speed, Properties.WIDTH, Properties.HEIGHT,
				bucket);
	}

	public void updateSpeed(int scoreInterval) {
		if (score.getCurrentScore() / scoreInterval > score.getPrevScore()
				/ scoreInterval) {
			dropSpeed += 20 * score.getCurrentScore() / scoreInterval;
			dropInterval /= 1.2;
		}
	}

	public void endGame() {
		dropFactory.resetDrops();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (!end) {
			end = true;
		}
		textWriter.write("HighScore " + score.getHighScore(),
				Properties.WIDTH / 3, Properties.HEIGHT / 2);
		textWriter.write("Click to try again", Properties.WIDTH / 3,
				Properties.HEIGHT / 2 - 50);
		if (Gdx.input.isTouched()) {
			gameState = GameStates.RUN;
			end = false;
			dropSpeed = Properties.initalDropSpeed;
			dropInterval = Properties.initialDropInterval;
			score.resetScore();
		}

	}

	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}