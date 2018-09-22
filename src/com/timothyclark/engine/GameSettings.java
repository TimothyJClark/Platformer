package com.timothyclark.engine;

public final class GameSettings
{
	private final RenderingMode renderMode;
	private final int targetFPS, targetTPS;
	private boolean fullscreen;
	private int width, height;
	
	public GameSettings()
	{
		this(800, 600, 60, 60, RenderingMode.SOFTWARE);
	}

	public GameSettings(int width, int height, int targetFPS, int targetTPS, RenderingMode renderMode)
	{
		this.width = width;
		this.height = height;
		this.targetFPS = targetFPS;
		this.targetTPS = targetTPS;
		this.renderMode = renderMode;
	}

	public RenderingMode getRenderMode()
	{
		return renderMode;
	}

	public int getTargetFPS()
	{
		return targetFPS;
	}

	public int getTargetTPS()
	{
		return targetTPS;
	}

	public boolean isFullscreen()
	{
		return fullscreen;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
}