package com.timothyclark.engine;

import com.timothyclark.engine.core.Game;
import com.timothyclark.engine.core.GameSettings;
import com.timothyclark.engine.graphics.RenderingMode;
import com.timothyclark.engine.graphics.SpriteLoader;
import com.timothyclark.engine.level.Level;
import com.timothyclark.engine.level.entities.Entity;

public class MyGame extends Game
{
	public MyGame()
	{
		super(new GameSettings(900, 450, 999999999, 60, RenderingMode.SOFTWARE));
	}

	@Override
	public void updateGame()
	{
	}

	@Override
	public void renderGame()
	{
	}

	@Override
	public void initGame()
	{
		Level cur = new Level();

		this.setCurrentLevel(cur);

		Entity test = new Entity(cur, SpriteLoader.getSprite("guy"))
		{
			@Override
			public void entityUpdate()
			{
			}
		};
		
		cur.addEntity(50505050, test);
	}

	@Override
	public void cleanupGame()
	{
	}
}