package com.timothyclark.engine.core;

import com.timothyclark.engine.GameSettings;

public abstract class Game
{
	public final GameSettings settings;
	
	public Game(GameSettings sets)
	{
		this.settings = sets;
	}
	
	public abstract void updateGame();
	
	public abstract void renderGame();
	
	public abstract void initGame();
	
	public abstract void cleanupGame();
}