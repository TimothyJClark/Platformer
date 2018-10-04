package com.timothyclark.engine.graphics;

import java.util.Date;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.core.Game;
import com.timothyclark.engine.level.entities.Entity;

public class RenderingEngine
{
	private final Window theWindow;
	private final Game gameInstance;
	private final Thread renderingThread;
	
	private Camera cam;

	private int frames, fps, workingFrameTime, averageFrameTime;

	private final Object lock = new Object();

	public RenderingEngine(Game gInstance)
	{
		this.gameInstance = gInstance;
		this.theWindow = new Window(this.gameInstance.settings.getWidth(), this.gameInstance.settings.getHeight(), this.gameInstance.settings.getRenderMode());
		
		this.cam = new Camera(this.theWindow, 0, 0);

		this.renderingThread = new Thread(new Runnable()
		{
			public void run()
			{
				renderingLoop();
			}
		});
		
		SpriteLoader.loadSpritesFromPath(SpriteLoader.DEFAULT_PATH);
	}
	
	public Window getWindowInstance()
	{
		return this.theWindow;
	}
	
	public Camera getCamera()
	{
		return this.cam;
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

	private void renderingLoop()
	{
		Thread.currentThread().setName("Rendering Thread");

		long lastSecond = System.currentTimeMillis();
		long lastFrame = System.nanoTime();
		long delta = 0;

		while (Engine.getEngineInstance().getEngineRunning())
		{
			if ((System.nanoTime() - lastFrame) + delta >= (1000000000 / this.gameInstance.settings.getTargetFPS()))
			{
				delta = ((System.nanoTime() - lastFrame) + delta) - (1000000000 / this.gameInstance.settings.getTargetFPS());
				this.workingFrameTime += System.nanoTime() - lastFrame;
				lastFrame = System.nanoTime();
				this.render();
				this.frames++;
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
					this.fps = this.frames;
					this.averageFrameTime = this.workingFrameTime / this.fps;
				}

				this.frames = 0;
				this.workingFrameTime = 0;
			}
		}
	}

	private void render()
	{
		this.theWindow.clearScreen();
		
		for(Entity e : this.gameInstance.getCurrentLevel().getEntities().values())
		{
			Object o = (Sprite) e.getProperty("sprite");
			
			if (o instanceof Sprite)
			{
				Sprite sprite = (Sprite)o;
				
				this.drawSprite((int) Math.floor(e.getX()), (int) Math.floor(e.getY()), sprite);
			}
		}

		gameInstance.renderGame();

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

	public int getAverageFrameTime()
	{
		int result;

		synchronized (lock)
		{
			result = this.averageFrameTime;
		}

		return result;
	}

	public void setPixelColor(int x, int y, int color)
	{
		this.theWindow.setPixelColor(x + (int) this.cam.getOffsetX(), y + (int) this.cam.getOffsetY(), color);
	}

	public void drawRect(int x, int y, int width, int height, int color)
	{
		for (int xx = x; xx < x + width; xx++)
		{
			for (int yy = y; yy < y + height; yy++)
			{
				this.setPixelColor(xx, yy, color);
			}
		}
	}

	public void drawSprite(int x, int y, Sprite sprite)
	{
		for (int xx = 0; xx < sprite.getWidth(); xx++)
		{
			for (int yy = 0; yy < sprite.getHeight(); yy++)
			{
				int color = sprite.getImageBuffer()[xx + (yy * sprite.getWidth())];

				if (color == 0xFFFF00DC) continue;

				this.setPixelColor(xx + x, yy + y, color);
			}
		}
	}
}