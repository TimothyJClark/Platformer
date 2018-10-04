package com.timothyclark.engine.level.entities;

import java.io.Serializable;
import java.util.HashMap;

import com.timothyclark.engine.graphics.Sprite;
import com.timothyclark.engine.graphics.SpriteLoader;
import com.timothyclark.engine.level.Level;

public abstract class Entity implements Serializable
{
	private static final long serialVersionUID = -139153158918741250L;

	private double x, y;
	private int width, height;

	private final Level lvl;

	private final HashMap<String, Object> properties = new HashMap<String, Object>();

	public Entity(Level lvl)
	{
		this.lvl = lvl;
		this.setProperty("sprite", SpriteLoader.getSprite("null"));
		this.calculateWidthHeight();
	}
	
	public Object getProperty(String key)
	{
		Object result;
		
		result = properties.get(key);
		
		return result;
	}
	
	public void setProperty(String key, Object value)
	{
		if (this.properties.containsKey(key))
		{
			this.properties.remove(key);
		}
		
		this.properties.put(key, value);
	}
	
	public abstract void entityUpdate();
	
	public abstract void onLeftHit(Entity hitter);
	public abstract void onRightHit(Entity hitter);
	public abstract void onTopHit(Entity hitter);
	public abstract void onBottomHit(Entity hitter);
	
	public void move(int direction, double movement)
	{
		if (direction == 0)
		{
//			for(Entity other : this.lvl.getEntities().values())
//			{
//				
//			}
			
			this.setX(this.getX() - movement);
		}
		else if (direction == 1)
		{
//			for(Entity other : this.lvl.getEntities().values())
//			{
//				
//			}
			
			this.setX(this.getX() + movement);
		}
		else if (direction == 2)
		{
//			for(Entity other : this.lvl.getEntities().values())
//			{
//				
//			}
			
			this.setY(this.getY() - movement);
		}
		else if (direction == 3)
		{
//			for(Entity other : this.lvl.getEntities().values())
//			{
//				
//			}
			
			this.setY(this.getY() + movement);
		}
	}
	
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
	
	public void calculateWidthHeight()
	{
		Sprite sprite = (Sprite) this.getProperty("sprite");
		
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}
}