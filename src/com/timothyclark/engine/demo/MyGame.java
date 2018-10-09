package com.timothyclark.engine.demo;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.core.Game;
import com.timothyclark.engine.core.GameSettings;
import com.timothyclark.engine.graphics.RenderingMode;
import com.timothyclark.engine.graphics.SpriteLoader;
import com.timothyclark.engine.level.Level;
import com.timothyclark.engine.level.LevelIO;

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
		Engine.getEngineInstance().getRenderingEngine().getCamera().setMaxVarianceX(100);
		Engine.getEngineInstance().getRenderingEngine().getCamera().setMaxVarianceY(50);
		Level cur = new Level();

		this.setCurrentLevel(cur);
		
		cur.addEntity(7777777, new PlayerEntity(cur));
		
		cur.addEntity(4, new TileEntity(cur));
	}

	@Override
	public void cleanupGame()
	{
		LevelIO.writeLevelToDisk(LevelIO.DEFAULT_PATH + "test.lvl", this.getCurrentLevel());
	}
	
	public static void main(String[] args)
	{
		Engine.getEngineInstance().start(new MyGame());
	}
}