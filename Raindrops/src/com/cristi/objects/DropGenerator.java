package com.cristi.objects;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.badlogic.gdx.math.MathUtils;
import com.cristi.simplegame.Score;

public class DropGenerator {

	private List<Raindrop> raindrops;
	private Score score;
	public DropGenerator(Score score)
	{
		this.score=score;
		raindrops=new LinkedList<Raindrop>();
	}
	
	public void addRandomDrop(int dropWidth,int dropHeight,int leftXLimit,int rightXLimit,int y)
	{
		int x = MathUtils.random(leftXLimit,rightXLimit);
		Raindrop drop = new Raindrop(x, y, dropWidth,dropHeight);
		raindrops.add(drop);
	}
	
	public List<Raindrop> getDrops(){
		return raindrops;
	}
    public void updateDrops(float speed,int screenW,int screenH,Bucket bucket){
	Iterator<Raindrop> iter = raindrops.iterator();
	while (iter.hasNext()) {
		Raindrop drop = iter.next();
		float newY = drop.getY();
		newY -= speed;
		drop.setY(newY);
		if (!drop.isInRectangle(screenW, screenH)) {
			score.decScore();
			iter.remove();
		}
		if (drop.overlaps(bucket.getRectangle())) {
			score.incScore();
			iter.remove();
		}
	}
    }
	public void resetDrops()
	{
		raindrops.clear();
	}
	
}
