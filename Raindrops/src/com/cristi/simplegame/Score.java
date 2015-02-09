package com.cristi.simplegame;

public class Score {

	private int score;
	private int prevScore;
	private int highScore;
	
	public Score()
	{
		score=0;
		prevScore=0;
		highScore=0;
	}
	
	public void resetScore()
	{
		score=0;
		prevScore=0;
		highScore=0;
	}
	public void updateScore()
	{
		if (highScore < score)
			highScore = score;
		prevScore=score;
	}
	
	public boolean isGameOver()
	{
		if (score == 0 && prevScore > 0 || score < 0) return true;
		else return false;
	}
	
	public int getPrevScore()
	{
		return prevScore;
	}
	public int getCurrentScore()
	{
		return score;
	}
	public int getHighScore()
	{
		return highScore;
	}
	public void incScore()
	{
		score++;
	}
	public void decScore()
	{
		score--;
	}
	
	
}
