package com.anserran.pong;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

public interface C {
    int WINDOW_WIDTH = 800;
    int WINDOW_HEIGHT = 450;

    Color LIGHT_GREEN = Color.valueOf("43ad2a");
    Color DARK_GREEN = Color.valueOf("389023");
    Color WHITE = Color.valueOf("ffffff");

    String RACKET1 = "racket1.png";
    String RACKET2 = "racket2.png";
    String BALL = "ball.png";

    String[] RESOURCES = new String[]{RACKET1, RACKET2, BALL};

    int KEY_W = Keys.W;
    int KEY_S = Keys.S;
    int KEY_UP = Keys.UP;
    int KEY_DOWN = Keys.DOWN;
}
