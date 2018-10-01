package com.timothyclark.engine.level;

import java.io.Serializable;
import java.util.HashMap;

import com.timothyclark.engine.level.entities.Entity;

public final class Level implements Serializable
{
	private static final long serialVersionUID = -1941783533764022579L;
	
	private final HashMap<Long, Entity> entities;
	
	public Level()
	{
		entities = new HashMap<Long, Entity>();
	}
	
	public void addEntity(long id, Entity e)
	{
		this.entities.put(id, e);
	}
	
	public HashMap<Long, Entity> getEntities()
	{
		return this.entities;
	}
}