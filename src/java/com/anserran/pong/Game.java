package com.anserran.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter implements C {

	private SpriteBatch batch;

	private Texture blank;

	private OrthographicCamera camera;

	private BitmapFont font;

	private GlyphLayout glyphLayout = new GlyphLayout();

	private AssetManager assetManager;

	private Vector2 v = new Vector2();

	private Rectangle r = new Rectangle();

    private int collision = 0;

	@Override
	public void create() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		batch = new SpriteBatch();
		Pixmap pixmap = new Pixmap(1, 1, Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		blank = new Texture(pixmap);
		pixmap.dispose();
		camera = new OrthographicCamera(WINDOW_WIDTH, WINDOW_HEIGHT);
		camera.translate(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
		camera.update();
		font = new BitmapFont();
		assetManager = new AssetManager();
		for (String resource : RESOURCES) {
			assetManager.load(resource, Texture.class);
		}
		assetManager.finishLoading();
		r.set(0, 0, 23, 129);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		draw(Gdx.graphics.getDeltaTime());
		batch.end();
	}

	protected void draw(float delta) {
	}

	protected void fill(Color color) {
		batch.setColor(color);
		batch.draw(blank, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	protected void rectangle(float x, float y, float width, float height,
			Color color) {
		batch.setColor(color);
		batch.draw(blank, x, y, width, height);
	}

	protected void text(CharSequence text, float x, float y) {
		glyphLayout.setText(font, text);
		font.draw(batch, text, x, y + glyphLayout.height);
	}

	protected void image(String name, float cx, float cy) {
		batch.setColor(Color.WHITE);
		Texture texture = assetManager.get(name);
		float x = cx - texture.getWidth() / 2;
		float y = cy - texture.getHeight() / 2;
		batch.draw(texture, x, y);
	}

	protected boolean isKeyPressed(int key) {
		return Gdx.input.isKeyPressed(key);
	}

	protected int horizontalBounceX(int x, int y) {
		v.set(x, y);
		v.scl(1, -1);
		return (int) v.x;
	}

	protected int horizontalBounceY(int x, int y) {
		v.set(x, y);
		v.scl(1, -1);
		return (int) v.y;
	}

	protected int verticalBounceX(int x, int y) {
		v.set(x, y);
		v.scl(-1, 1);
		return (int) v.x;
	}

	protected int verticalBounceY(int x, int y) {
		v.set(x, y);
		v.scl(-1, 1);
		return (int) v.y;
	}

	protected boolean racketTouchingBall(int rx, int ry, int bx, int by) {
		r.x = rx - r.width / 2;
		r.y = ry - r.height / 2;

		boolean newCollision = r.contains(bx, by);
        boolean result = newCollision && collision == 0;
        if (newCollision){
            collision = 2;
        } else if (collision > 0 ){
            collision--;
        }
        return result;
	}

	@Override
	public void dispose() {
		blank.dispose();
		batch.dispose();
	}

	public static void main(String[] args) {
		new LwjglApplication(new Pong(), "Pong", WINDOW_WIDTH, WINDOW_HEIGHT);
	}
}
