package com.timothyclark.engine.demo;

import java.awt.event.KeyEvent;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.graphics.Sprite;
import com.timothyclark.engine.level.Level;
import com.timothyclark.engine.level.entities.Entity;

public class PlayerEntity extends Entity
{
	public PlayerEntity(Level lvl, Sprite s)
	{
		super(lvl, s);
	}

	@Override
	public void entityUpdate()
	{
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_A))
		{
			this.move(0, 8);
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_D))
		{
			this.move(1, 8);
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_W))
		{
			this.move(2, 4);
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_S))
		{
			this.move(3, 8);
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