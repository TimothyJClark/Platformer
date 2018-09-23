package com.timothyclark.engine;

import com.timothyclark.engine.core.Game;
import com.timothyclark.engine.graphics.RenderingEngine;
import com.timothyclark.engine.graphics.Window;

public final class Engine
{
	public static final String VERSION = "0.1";
	public static final String ENGINE_NAME = "Unnamed";
	
	private Window theWindow;
	private Game theGameInstance;
	private boolean engineRunning;

	private volatile int frames;
	private volatile int ticks;
	private volatile int framesPerSecond;
	private volatile int ticksPerSecond;

	private volatile Thread renderingThread, updatingThread;

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
		this.updatingThread.start();
		this.renderingThread.start();
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
		this.theGameInstance.setRenderingEngine(new RenderingEngine(this.theWindow));

		this.updatingThread = new Thread(new Runnable()
		{
			public void run()
			{
				updateLoop();
			}
		});
		
		this.renderingThread = new Thread(new Runnable()
		{
			public void run()
			{
				renderLoop();
			}
		});
	}

	private void cleanup()
	{
		this.theWindow.close();
	}

	private void renderLoop()
	{
		while (this.getEngineRunning())
		{
			this.render();
			frames++;
		}
	}

	private void updateLoop()
	{
		long lastSecond = System.currentTimeMillis();
		
		while (this.getEngineRunning())
		{
			this.update();
			ticks++;
			
			if (System.currentTimeMillis() - lastSecond >= 1000)
			{
				lastSecond = System.currentTimeMillis();
				
				ticksPerSecond = ticks;
				framesPerSecond = frames;
				
				ticks = 0;
				frames = 0;
				
				//System.out.println("TPS " + ticksPerSecond + "    FPS: " + framesPerSecond);
			}
		}

		this.cleanup();
	}

	private void render()
	{
		this.theGameInstance.renderGame();
		
		this.theWindow.drawFrame();
	}

	private void update()
	{
		this.theGameInstance.updateGame();
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