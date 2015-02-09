package com.neet.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cristi.simplegame.GameScreen;

public class Main {
	
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg =
			new LwjglApplicationConfiguration();
		cfg.title = "Drops";
		cfg.width = 800;
		cfg.height = 500;
		cfg.useGL20 = false;
		cfg.resizable = true;
		
		new LwjglApplication(new GameScreen(), cfg);
		
	}
	
}
