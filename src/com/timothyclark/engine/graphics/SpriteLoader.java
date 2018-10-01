package com.timothyclark.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public final class SpriteLoader
{
	public static final String DEFAULT_PATH = "res/sprites/";
	private static final HashMap<String, Sprite> loadedSprites = new HashMap<String, Sprite>();
	
	private SpriteLoader()
	{
	}
	
	public static void loadSpritesFromPath(String path)
	{
		File spritePath = new File(path);
		
		try
		{
			File[] files = spritePath.listFiles();
			
			for (File f : files)
			{
				if (f.isFile() && f.canRead())
				{
					BufferedImage img = ImageIO.read(f);
					
					int width = img.getWidth();
					int height = img.getHeight();
					int[] buffer = new int[width * height];
					
					img.getRGB(0, 0, width, height, buffer, 0, width);
					
					Sprite toAdd = new Sprite(width, height, buffer);
					
					String name = f.getName().substring(0, f.getName().lastIndexOf("."));
					
					loadedSprites.put(name, toAdd);
					System.out.println("SpriteLoader: loaded sprite '" + name + "'");
				}
			}
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Sprite getSprite(String name)
	{
		if(!loadedSprites.containsKey(name))
		{
			return null;
		}
		
		return loadedSprites.get(name);
	}
}