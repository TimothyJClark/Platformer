package com.timothyclark.engine.level;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class LevelIO
{
	private static final String PATH = "res/levels";
	
	private LevelIO()
	{
	}
	
	private static Level loadLevelFromPath(String path)
	{
		return null;
	}
	
	public static Level loadLevel(String name)
	{
		return null;
	}
	
	public static void writeLevelToDisk(String path, Level lvl)
	{
		File lvlFile = new File(path);
		
		if (lvlFile.canWrite())
		{
			try
			{
				FileOutputStream fileStream = new FileOutputStream(lvlFile);
				ObjectOutputStream obOut = new ObjectOutputStream(fileStream);
				
				obOut.writeObject(lvl);
				fileStream.close();
				obOut.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}