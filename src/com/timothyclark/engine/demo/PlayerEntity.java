package com.timothyclark.engine.demo;

import java.awt.event.KeyEvent;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.graphics.Sprite;
import com.timothyclark.engine.graphics.SpriteLoader;
import com.timothyclark.engine.level.Level;
import com.timothyclark.engine.level.entities.Entity;
import com.timothyclark.engine.level.entities.MovementDirection;

public class PlayerEntity extends Entity
{
	private static final long serialVersionUID = -2244295326578245672L;

	public PlayerEntity(Level lvl)
	{
		super(lvl, SpriteLoader.getSprite("guy"));
	}

	@Override
	public void entityUpdate()
	{
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_A))
		{
			Engine.getEngineInstance().getRenderingEngine().getCamera().moveCameraRight(this.move(MovementDirection.LEFT, 1));
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_D))
		{
			Engine.getEngineInstance().getRenderingEngine().getCamera().moveCameraLeft(this.move(MovementDirection.RIGHT, 1));
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_W))
		{
			Engine.getEngineInstance().getRenderingEngine().getCamera().moveCameraDown(this.move(MovementDirection.UP, 1));
		}
		
		if (Engine.getEngineInstance().getKeyboard().isKeyDown(KeyEvent.VK_S))
		{
			Engine.getEngineInstance().getRenderingEngine().getCamera().moveCameraUp(this.move(MovementDirection.DOWN, 1));
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