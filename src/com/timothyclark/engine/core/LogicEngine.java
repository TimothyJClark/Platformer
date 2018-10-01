package com.timothyclark.engine.core;

import java.util.Date;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.level.entities.Entity;

public class LogicEngine
{
	private final Thread updateThread;
	private int ticks, tps, workingTickTime, averageTickTime;
	private final Game gameInstance;

	private final Object lock = new Object();

	public LogicEngine(Game gInstance)
	{
		this.gameInstance = gInstance;

		this.updateThread = new Thread(new Runnable()
		{
			public void run()
			{
				updateLoop();
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
	
	private void updateLoop()
	{
		Thread.currentThread().setName("Logic Thread");
		
		long lastSecond = System.currentTimeMillis();
		long lastTick = System.nanoTime();
		long delta = 0;

		while (Engine.getEngineInstance().getEngineRunning())
		{
			if ((System.nanoTime() - lastTick) + delta >= 1000000000 / gameInstance.settings.getTargetTPS())
			{
				delta = ((System.nanoTime() - lastTick) + delta) - (1000000000 / gameInstance.settings.getTargetTPS());
				this.workingTickTime += System.nanoTime() - lastTick;
				lastTick = System.nanoTime();
				this.update();
				this.ticks++;
			} else 
			{
				try
				{
					Thread.sleep(0, 100);
				} catch (InterruptedException e)
				{
				}
			}
			
			if (System.currentTimeMillis() - lastSecond >= 1000)
			{
				lastSecond = System.currentTimeMillis();
				
				synchronized (lock)
				{
					this.tps = this.ticks;
					this.averageTickTime = this.workingTickTime / this.tps;
					System.out.println("FPS: " + Engine.getEngineInstance().getRenderingEngine().getFramesPerSecond() + "\tTPS: " + this.tps + "\tAverage Tick Time: " + this.averageTickTime + "ns" + "\tAverage Frame Time: " + Engine.getEngineInstance().getRenderingEngine().getAverageFrameTime() + "ns");
				}

				this.ticks = 0;
				this.workingTickTime = 0;
			}
		}
		
		Engine.getEngineInstance().cleanup();
	}
	
	private void update()
	{
		for(Entity e : this.gameInstance.getCurrentLevel().getEntities().values())
		{
			e.entityUpdate();
		}
		
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
	
	public int getAverageTickTime()
	{
		int result;
		
		synchronized (lock)
		{
			result = this.averageTickTime;
		}
		
		return result;
	}
}