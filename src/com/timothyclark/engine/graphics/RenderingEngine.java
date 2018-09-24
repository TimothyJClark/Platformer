package com.timothyclark.engine.graphics;

import java.util.Date;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.core.Game;

public class RenderingEngine
{
	private final Window theWindow;
	private final Game gameInstance;
	private final Thread renderingThread;
	
	private int frames, fps;
	
	private final Object lock = new Object();

	public RenderingEngine(Game gInstance)
	{
		this.gameInstance = gInstance;
		this.theWindow = new Window(this.gameInstance.settings.getWidth(), this.gameInstance.settings.getHeight(), this.gameInstance.settings.getRenderMode());

		this.renderingThread = new Thread(new Runnable()
		{
			public void run()
			{
				Thread.currentThread().setName("Rendering Thread");
				
				long lastSecond = System.currentTimeMillis();
				
				while (Engine.getEngineInstance().getEngineRunning())
				{
					if (System.currentTimeMillis() - lastSecond >= 1000)
					{
						lastSecond = System.currentTimeMillis();
						
						synchronized (lock)
						{
							fps = frames;
						}
						
						frames = 0;
					}
					
					render();
					frames++;
				}
			}
		});
	}

	public void start()
	{
		this.renderingThread.start();
		System.out.println("Rendering engine started at..." + new Date().toString());
	}

	public void stop()
	{
		this.theWindow.close();
		System.out.println("Rendering engine stopped at..." + new Date().toString());
	}

	private void render()
	{
		this.theWindow.clearScreen();
		
		gameInstance.renderGame();
		
		for (int i = 0; i < 5000; i++)
		{
			drawRect(i, i, 50, 150, 0xcc00ff);
		}
		
		this.theWindow.drawFrame();
	}
	
	public int getFramesPerSecond()
	{
		int result;
		
		synchronized (lock)
		{
			result = this.fps;
		}
		
		return result;
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