package com.timothyclark.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import com.timothyclark.engine.utils.ICallback;

public final class KeyboardInput implements KeyListener
{
	private final ArrayList<Integer> downKeys;
	private final HashMap<Integer, ICallback> pressedCallbacks;
	private final HashMap<Integer, ICallback> releasedCallbacks;
	
	public KeyboardInput()
	{
		downKeys = new ArrayList<Integer>();
		pressedCallbacks = new HashMap<Integer, ICallback>();
		releasedCallbacks = new HashMap<Integer, ICallback>();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if (!this.downKeys.contains(arg0.getKeyCode()))
		{
			if (this.pressedCallbacks.containsKey(arg0.getKeyCode()))
			{
				this.pressedCallbacks.get(arg0.getKeyCode()).callback(null);
			}
			
			this.downKeys.add(arg0.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		if (this.downKeys.contains(arg0.getKeyCode()))
		{
			if (this.releasedCallbacks.containsKey(arg0.getKeyCode()))
			{
				this.releasedCallbacks.get(arg0.getKeyCode()).callback(null);
			}
			
			this.downKeys.remove(this.downKeys.indexOf(arg0.getKeyCode()));
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}
	
	public boolean isKeyDown(int keyCode)
	{
		return this.downKeys.contains(keyCode);
	}
	
	public void registerKeyPressedCallback(int keyCode, ICallback callback)
	{
		this.pressedCallbacks.put(keyCode, callback);
	}
	
	public void registerKeyReleasedCallback(int keyCode, ICallback callback)
	{
		this.releasedCallbacks.put(keyCode, callback);
	}
}