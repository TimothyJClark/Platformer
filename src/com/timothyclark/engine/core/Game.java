package com.timothyclark.engine.core;

import com.timothyclark.engine.level.Level;

public abstract class Game
{
	public final GameSettings settings;
	private Level currentLevel;
	
	public Game(GameSettings sets)
	{
		this.settings = sets;
	}
	
	public abstract void updateGame();
	
	public abstract void renderGame();
	
	public abstract void initGame();
	
	public abstract void cleanupGame();

	public synchronized Level getCurrentLevel()
	{
		return currentLevel;
	}

	public synchronized void setCurrentLevel(Level currentLevel)
	{
		this.currentLevel = currentLevel;
	}
}