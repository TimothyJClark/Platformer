package com.timothyclark.engine.core;

import java.util.Date;

import com.timothyclark.engine.Engine;

public class LogicEngine
{
	private final Thread updateThread;
	private int ticks, tps;
	private final Game gameInstance;

	private final Object lock = new Object();

	public LogicEngine(Game gInstance)
	{
		this.gameInstance = gInstance;

		this.updateThread = new Thread(new Runnable()
		{
			public void run()
			{
				Thread.currentThread().setName("Logic Thread");
				
				long lastSecond = System.currentTimeMillis();

				while (Engine.getEngineInstance().getEngineRunning())
				{
					if (System.currentTimeMillis() - lastSecond >= 1000)
					{
						lastSecond = System.currentTimeMillis();
						
						synchronized (lock)
						{
							tps = ticks;
							System.out.println("FPS: " + Engine.getEngineInstance().getRenderingEngine().getFramesPerSecond() + "\tTPS: " + tps);
						}

						ticks = 0;
					}

					update();
					ticks++;
				}
				
				Engine.getEngineInstance().cleanup();
			}
		});
	}

	public void start()
	{
		this.updateThread.start();
		System.out.println("Logic engine started at..." + new Date().toString());
	}
	
	public void stop()
	{
		System.out.println("Logic engine stopped at..." + new Date().toString());
	}
	
	private void update()
	{
		gameInstance.updateGame();
	}

	public int getTicksPerSecond()
	{
		int result;

		synchronized (lock)
		{
			result = this.tps;
		}

		return result;
	}
}