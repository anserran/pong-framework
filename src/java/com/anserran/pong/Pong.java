package com.anserran.pong;

public class Pong extends Game {
	// Aquí deben ir las variables del juego
	int goals1 = 0;
	int goals2 = 0;

	int x1 = 20;
	int y1 = WINDOW_HEIGHT / 2;

	int x2 = WINDOW_WIDTH - 20;
	int y2 = WINDOW_HEIGHT / 2;

	int bx = WINDOW_WIDTH / 2;
	int by = WINDOW_HEIGHT / 2;

	int dx = 1;
	int dy = 1;

	@Override
	public void draw(float delta) {
		// Aquí debe ir tu código de dibujado
		drawBackground();
		drawRackets();
		drawBall();

		text("Score: " + goals1 + " - " + goals2, 10, 10);
	}

	void drawBackground() {
		fill(LIGHT_GREEN);
		int rectangleWidth = WINDOW_WIDTH / 8;
		for (int i = 0; i < 4; i = i + 1) {
			rectangle(rectangleWidth * 2 * i, 0, rectangleWidth, WINDOW_HEIGHT,
					DARK_GREEN);
		}

		int thickness = 8;
		for (int i = 0; i < 3; i = i + 1) {
			int x = WINDOW_WIDTH / 2 * i - thickness / 2;
			rectangle(x, 0, thickness, WINDOW_HEIGHT, WHITE);
		}

		for (int i = 0; i < 2; i = i + 1) {
			int y = WINDOW_HEIGHT * i - thickness / 2;
			rectangle(0, y, WINDOW_WIDTH, thickness, WHITE);
		}
	}

	void drawRackets() {
		int speed = 10;

		if (isKeyPressed(KEY_W)) {
			y1 = y1 + speed;
		}

		if (isKeyPressed(KEY_S)) {
			y1 = y1 - speed;
		}

		if (isKeyPressed(KEY_UP)) {
			y2 = y2 + speed;
		}

		if (isKeyPressed(KEY_DOWN)) {
			y2 = y2 - speed;
		}

		image(RACKET1, x1, y1);
		image(RACKET2, x2, y2);
	}

	void drawBall() {
		int speed = 5;
		bx = bx + speed * dx;
		by = by + speed * dy;

		if (bx >= WINDOW_WIDTH) {
			bx = WINDOW_WIDTH / 2;
			by = WINDOW_HEIGHT / 2;
			dx = -1;
			dy = 1;
			goals1 = goals1 + 1;
		}

		if (bx <= 0) {
			bx = WINDOW_WIDTH / 2;
			by = WINDOW_HEIGHT / 2;
			dx = 1;
			dy = -1;
			goals2 = goals2 + 1;
		}

		if (by <= 0) {
			dy = 1;
		}

		if (by >= WINDOW_HEIGHT) {
			dy = -1;
		}

		if (racketTouchingBall(x1, y1, bx, by)) {
			dx = 1;
		}

		if (racketTouchingBall(x2, y2, bx, by)) {
			dx = -1;
		}

		image(BALL, bx, by);
	}
}
