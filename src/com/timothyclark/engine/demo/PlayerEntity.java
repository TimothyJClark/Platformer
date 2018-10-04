package com.timothyclark.engine.demo;

import java.awt.event.KeyEvent;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.graphics.Sprite;
import com.timothyclark.engine.graphics.SpriteLoader;
import com.timothyclark.engine.level.Level;
import com.timothyclark.engine.level.entities.Entity;

public class PlayerEntity extends Entity
{
	private static final long serialVersionUID = -2244295326578245672L;

	public PlayerEntity(Level lvl)
	{
		super(lvl);
		
		this.setProperty("sprite", SpriteLoader.getSprite("guy"));
	}

	@Override
	public void entityUpdate()
	{
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_A))
		{
			this.move(0, 2);
			Engine.getEngineInstance().getRenderingEngine().getCamera().moveCameraRight(2);
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_D))
		{
			this.move(1, 2);
			Engine.getEngineInstance().getRenderingEngine().getCamera().moveCameraLeft(2);
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_W))
		{
			this.move(2, 2);
			Engine.getEngineInstance().getRenderingEngine().getCamera().moveCameraDown(2);
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_S))
		{
			this.move(3, 2);
			Engine.getEngineInstance().getRenderingEngine().getCamera().moveCameraUp(2);
		}
	}

	@Override
	public void onLeftHit(Entity hitter)
	{
	}

	@Override
	public void onRightHit(Entity hitter)
	{
	}

	@Override
	public void onTopHit(Entity hitter)
	{
	}

	@Override
	public void onBottomHit(Entity hitter)
	{
	}
}