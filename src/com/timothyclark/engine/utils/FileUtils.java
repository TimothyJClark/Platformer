package com.timothyclark.engine.utils;

import java.io.File;
import java.util.ArrayList;

public final class FileUtils
{
	private FileUtils()
	{
	}
	
	public static ArrayList<File> getFilesFromSubs(File folder)
	{
		ArrayList<File> result = new ArrayList<File>();
		
		File[] files = folder.listFiles();
		
		for (File f : files)
		{
			if (f.isDirectory())
			{
				result.addAll(getFilesFromSubs(f));
			} else 
			{
				result.add(f);
			}
		}
		
		return result;
	}
}