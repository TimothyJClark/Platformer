package com.timothyclark.engine.graphics;

public class RenderingEngine
{
	private final Window theWindow;

	public RenderingEngine(Window win)
	{
		this.theWindow = win;
	}

	public void setPixelColor(int x, int y, int color)
	{
		this.theWindow.setPixelColor(x, y, color);
	}

	public void drawRect(int x, int y, int width, int height, int color)
	{
		for (int xx = x; xx < x + width; xx++)
		{
			for (int yy = y; yy < y + height; yy++)
			{
				this.theWindow.setPixelColor(xx, yy, color);
			}
		}
	}
}