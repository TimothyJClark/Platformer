package com.timothyclark.engine;

import com.timothyclark.engine.core.Game;
import com.timothyclark.engine.graphics.Window;

public final class Engine
{
	private Window theWindow;
	private Game theGameInstance;
	private boolean engineRunning;

	private final Object runningLock = new Object();

	private static final Engine engineInstance = new Engine();

	private Engine()
	{
	}

	public static final Engine getEngineInstance()
	{
		return engineInstance;
	}

	public void start(Game gameInstance)
	{
		this.theGameInstance = gameInstance;
		this.init();
		this.engineLoop();
	}

	public void stop()
	{
		this.setEngineRunning(false);
	}

	private void init()
	{
		setEngineRunning(true);

		this.theWindow = new Window(this.theGameInstance.settings.getWidth(), this.theGameInstance.settings.getHeight(), this.theGameInstance.settings.getRenderMode());
		this.theGameInstance.initGame();
	}

	private void cleanup()
	{
		this.theWindow.close();
	}

	private void engineLoop()
	{
		while (this.getEngineRunning())
		{
			this.update();
			this.render();
		}

		this.cleanup();
	}

	private void render()
	{
		this.theWindow.drawFrame();
	}

	private void update()
	{

	}

	private boolean getEngineRunning()
	{
		boolean result = false;

		synchronized (runningLock)
		{
			result = this.engineRunning;
		}

		return result;
	}

	private void setEngineRunning(boolean run)
	{
		synchronized (runningLock)
		{
			this.engineRunning = run;
		}
	}
}