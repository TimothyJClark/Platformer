package com.timothyclark.engine.demo;

import com.timothyclark.engine.graphics.SpriteLoader;
import com.timothyclark.engine.level.Level;
import com.timothyclark.engine.level.entities.Entity;

public class TileEntity extends Entity
{
	private static final long serialVersionUID = 4170444267610465003L;

	public TileEntity(Level lvl)
	{
		super(lvl, SpriteLoader.getSprite("test"));
		this.setProperty("solid", true);
	}

	@Override
	public void entityUpdate()
	{
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