package com.timothyclark.engine;

import com.timothyclark.engine.core.Game;
import com.timothyclark.engine.graphics.Window;

public final class Engine
{
	private static Window theWindow;
	private static Game theGameInstance;
	
	
	private Engine()
	{
	}
	
	
	public static void start(Game gameInstance)
	{
		theGameInstance = gameInstance;
		init();
	}
	
	private static void init()
	{
		theWindow = new Window(theGameInstance.settings.getWidth(), theGameInstance.settings.getHeight(), theGameInstance.settings.getRenderMode());
		theGameInstance.initGame();
	}
}