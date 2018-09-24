package com.timothyclark.engine;

import com.timothyclark.engine.core.Game;
import com.timothyclark.engine.core.LogicEngine;
import com.timothyclark.engine.graphics.RenderingEngine;
import com.timothyclark.engine.graphics.Window;

public final class Engine
{
	public static final String VERSION = "0.1";
	public static final String ENGINE_NAME = "Unnamed";
	
	private Game theGameInstance;
	private boolean engineRunning;

	private LogicEngine logicEngine;
	private RenderingEngine renderingEngine;

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
		this.logicEngine = new LogicEngine(this.theGameInstance);
		this.renderingEngine = new RenderingEngine(this.theGameInstance);
		this.init();
	}

	public void stop()
	{
		this.setEngineRunning(false);
	}

	private void init()
	{
		setEngineRunning(true);

		this.theGameInstance.initGame();
		this.logicEngine.start();
		this.renderingEngine.start();
	}

	public void cleanup()
	{
		this.logicEngine.stop();
		this.renderingEngine.stop();
	}

	public boolean getEngineRunning()
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
	
	public LogicEngine getLogicEngine()
	{
		return this.logicEngine;
	}
	
	public RenderingEngine getRenderingEngine()
	{
		return this.renderingEngine;
	}
}