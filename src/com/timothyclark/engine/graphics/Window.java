package com.timothyclark.engine.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.timothyclark.engine.GameSettings;

public final class Window
{
	private final JFrame frame;
	private final Canvas canvas;
	private BufferedImage buffImg;
	private int[] pixelBuffer;
	
	private int width, height;
	private Dimension size;
	private GameSettings.RenderingMode renderMode;
	
	public Window(int width, int height, GameSettings.RenderingMode renderMode)
	{
		this.width = width;
		this.height = height;
		this.renderMode = renderMode;
		
		this.size = new Dimension(this.width, this.height);
		
		frame = new JFrame();
		canvas = new Canvas();
		
		canvas.setMinimumSize(size);
		canvas.setMaximumSize(size);
		canvas.setPreferredSize(size);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}