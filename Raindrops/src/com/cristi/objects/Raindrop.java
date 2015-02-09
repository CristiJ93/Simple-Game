package com.cristi.objects;

import com.badlogic.gdx.math.Rectangle;

public class Raindrop {

	private Rectangle drop;

	public Raindrop() {
		drop.x = 0;
		drop.y = 0;
		drop.width = 0;
		drop.height = 0;
	}

	public Raindrop(float x, float y, int width, int height) {
		drop = new Rectangle();
		drop.x = x;
		drop.y = y;
		drop.width = width;
		drop.height = height;
	}

	public boolean overlaps(Rectangle rect) {
		return drop.overlaps(rect);
	}

	public boolean isInRectangle(int width, int height) {
		if (drop.x > 0 && drop.x < width - drop.width
				&& drop.y + drop.height > 0 && drop.y < height)
			return true;
		else
			return false;
	}

	public void setX(float x) {
		drop.x = x;
	}

	public void setY(float y) {
		drop.y = y;
	}

	public void setWidth(float x) {
		drop.x = x;
	}

	public void setHeight(float y) {
		drop.y = y;
	}

	public float getX() {
		return drop.x;
	}

	public float getY() {
		return drop.y;
	}

	public float getWidth() {
		return drop.x;
	}

	public float getHeight() {
		return drop.y;
	}

	public Rectangle getRectangle() {
		return drop;
	}
}
