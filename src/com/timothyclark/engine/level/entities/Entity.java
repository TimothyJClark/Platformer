package com.timothyclark.engine.level.entities;

import java.io.Serializable;
import java.util.HashMap;

import com.timothyclark.engine.graphics.Sprite;
import com.timothyclark.engine.level.Level;

public abstract class Entity implements Serializable
{
	private static final long serialVersionUID = -139153158918741250L;

	private int x, y, width, height;

	private final Level lvl;

	private final HashMap<String, Object> properties = new HashMap<String, Object>();

	public Entity(Level lvl, Sprite s)
	{
		this.lvl = lvl;
		this.setProperty("sprite", s);
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
		this.properties.put(key, value);
	}
	
	public abstract void entityUpdate();

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
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