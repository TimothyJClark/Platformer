package com.timothyclark.engine.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class LevelIO
{
	private static final String PATH = "res/levels/";
	
	private LevelIO()
	{
	}
	
	private static Level loadLevelFromPath(String path)
	{
		File lvlFile = new File(path);
		
		if (lvlFile.canRead())
		{
			try
			{
				
				FileInputStream fileStream = new FileInputStream(lvlFile);
				ObjectInputStream obIn = new ObjectInputStream(fileStream);
				
				Object o = obIn.readObject();
				
				obIn.close();
				fileStream.close();
				
				if (o instanceof Level)
				{
					Level lvl = (Level) o;
					
					return lvl;
				}
			} catch (Exception e)
			{
			}
		}
		
		System.err.println("Failed to load level: " + path);
		
		return null;
	}
	
	public static Level loadLevel(String name)
	{
		return loadLevelFromPath(PATH + name);
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