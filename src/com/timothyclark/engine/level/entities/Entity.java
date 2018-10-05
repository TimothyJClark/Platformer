package com.timothyclark.engine.level.entities;

import java.io.Serializable;
import java.util.HashMap;

import com.timothyclark.engine.graphics.Sprite;
import com.timothyclark.engine.graphics.SpriteLoader;
import com.timothyclark.engine.level.Level;

public abstract class Entity implements Serializable
{
	private static final long serialVersionUID = -139153158918741250L;

	private double x = 4, y = 4;
	private int width, height;

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
	
	public double move(MovementDirection direction, double movement)
	{
		double result = movement;
		
		if (direction == MovementDirection.LEFT)
		{			
			for(Entity other : this.lvl.getEntities().values())
			{
				if (other == this) continue;
				
				if (this.x >= other.x + (other.width / 2) && this.x <= other.x + other.width && this.y + this.height > other.y && this.y < other.y + other.height)
				{
					other.onRightHit(this);
					
					if (other.getProperty("solid") == Boolean.TRUE)
					{
						result = this.x - (other.x + other.width);
					}
				}
			}
			
			this.setX(this.getX() - result);
		}
		else if (direction == MovementDirection.RIGHT)
		{
			for(Entity other : this.lvl.getEntities().values())
			{
				if (other == this) continue;
				
				if (this.x + this.width >= other.x && this.x + this.width <= other.x + (other.width / 2) && this.y + this.height > other.y && this.y < other.y + other.height)
				{
					other.onLeftHit(this);
					
					if (other.getProperty("solid") == Boolean.TRUE)
					{
						result = other.x - (this.x + this.width);
					}
				}
			}
			
			this.setX(this.getX() + result);
		}
		else if (direction == MovementDirection.UP)
		{
			for(Entity other : this.lvl.getEntities().values())
			{
				if (other == this) continue;
				
				if (this.y <= other.y + other.height && this.y >= other.y + (other.height / 2) && this.x + this.width > other.x && this.x < other.x + other.width)
				{
					other.onBottomHit(this);
					
					if (other.getProperty("solid") == Boolean.TRUE)
					{
						result = this.y - (other.y + other.height);
					}
				}
			}
			
			this.setY(this.getY() - result);
		}
		else if (direction == MovementDirection.DOWN)
		{
			for(Entity other : this.lvl.getEntities().values())
			{
				if (other == this) continue;
				
				if (this.y + this.height >= other.y && this.y + this.height <= other.y + (other.height / 2) && this.x + this.width > other.x && this.x < other.x + other.width)
				{
					other.onTopHit(this);
					
					if (other.getProperty("solid") == Boolean.TRUE)
					{
						result = other.y - (this.y + this.height);
					}
				}
			}
			
			this.setY(this.getY() + result);
		}
		
		return result;
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