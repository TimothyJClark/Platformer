package com.timothyclark.engine;

import com.timothyclark.engine.core.Game;

public class MyGame extends Game
{
	public MyGame()
	{
		super(new GameSettings());
	}

	@Override
	public void updateGame()
	{
	}

	@Override
	public void renderGame()
	{
		this.getRenderingEngine().drawRect(50, 50, 150, 50, 0xff0000);
	}

	@Override
	public void initGame()
	{
	}

	@Override
	public void cleanupGame()
	{
	}
}