package com.cristi.simplegame;

public final class Properties {
	static int WIDTH = 800;
	static int HEIGHT = 480;
	static int dropWidth = 32, dropHeight = 32, bucketWidth = 64,
			bucketHeight = 64;
	static int bucketX = WIDTH / 2 - bucketWidth / 2, bucketY = 20;
	static int leftLimit = 0, rightLimit = WIDTH - dropWidth;
    static int initalDropSpeed=200;
    static long initialDropInterval=1000000000;
}
