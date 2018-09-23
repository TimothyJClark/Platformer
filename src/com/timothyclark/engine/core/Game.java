package com.timothyclark.engine.core;

import com.timothyclark.engine.GameSettings;
import com.timothyclark.engine.graphics.RenderingEngine;

public abstract class Game
{
	public final GameSettings settings;
	private RenderingEngine renderer;
	
	public Game(GameSettings sets)
	{
		this.settings = sets;
	}
	
	public void setRenderingEngine(RenderingEngine renderer)
	{
		this.renderer = renderer;
	}
	
	public RenderingEngine getRenderingEngine()
	{
		return this.renderer;
	}
	
	public abstract void updateGame();
	
	public abstract void renderGame();
	
	public abstract void initGame();
	
	public abstract void cleanupGame();
}