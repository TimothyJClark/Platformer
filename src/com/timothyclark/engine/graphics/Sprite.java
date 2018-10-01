package com.timothyclark.engine.graphics;

import java.io.Serializable;

public final class Sprite implements Serializable
{
	private static final long serialVersionUID = -5339717990565059876L;
	
	private final int[] imgBuffer;
	private final int width, height;
	
	Sprite(int width, int height, int[] buffer)
	{
		this.width = width;
		this.height = height;
		this.imgBuffer = buffer;
	}
	
	public final int getWidth()
	{
		return this.width;
	}
	
	public final int getHeight()
	{
		return this.height;
	}
	
	public final int[] getImageBuffer()
	{
		return this.imgBuffer;
	}
}