package com.timothyclark.engine;

import com.timothyclark.engine.demo.MyGame;

// This class is used only as an entry point to the program. It should never be instantiated;
public class Main
{
	public static void main(String[] args)
	{
		Engine.getEngineInstance().start(new MyGame());
	}
}