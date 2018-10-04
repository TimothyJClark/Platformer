package com.timothyclark.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.input.KeyboardInput;

public final class Window
{
	private final JFrame frame;
	private final Canvas canvas;
	private BufferedImage buffImg;
	private volatile int[] pixelBuffer;

	private Graphics g;

	private Dimension size;
	private RenderingMode renderMode;

	private int scale = 2;

	public Window(int width, int height, RenderingMode renderMode)
	{
		this.renderMode = renderMode;

		this.size = new Dimension(width, height);

		frame = new JFrame();
		canvas = new Canvas();

		canvas.setMinimumSize(size);
		canvas.setMaximumSize(size);
		canvas.setPreferredSize(size);
		
		canvas.setBackground(Color.BLACK);

		buffImg = new BufferedImage((int) (size.getWidth() / scale), (int) (size.getHeight() / scale), BufferedImage.TYPE_INT_ARGB);

		g = canvas.getGraphics();

		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new WindowListener()
		{
			public void windowActivated(WindowEvent arg0)
			{
			}

			public void windowClosed(WindowEvent arg0)
			{
			}

			public void windowClosing(WindowEvent arg0)
			{
				Engine.getEngineInstance().stop();
			}

			public void windowDeactivated(WindowEvent arg0)
			{
			}

			public void windowDeiconified(WindowEvent arg0)
			{
			}

			public void windowIconified(WindowEvent arg0)
			{
			}

			public void windowOpened(WindowEvent arg0)
			{
			}
		});

		pixelBuffer = ((DataBufferInt) buffImg.getRaster().getDataBuffer()).getData();

		frame.addComponentListener(new ComponentListener()
		{
			public void componentHidden(ComponentEvent arg0)
			{
			}

			public void componentMoved(ComponentEvent arg0)
			{
			}

			public void componentResized(ComponentEvent arg0)
			{
				size = frame.getSize();

				canvas.setMinimumSize(size);
				canvas.setMaximumSize(size);
				canvas.setPreferredSize(size);
			}

			public void componentShown(ComponentEvent arg0)
			{
			}
		});

		frame.setTitle(Engine.ENGINE_NAME + " ver " + Engine.VERSION);
		frame.setVisible(true);
	}
	
	public int getWidth()
	{
		return (int) this.size.getWidth() / scale;
	}
	
	public int getHeight()
	{
		return (int) this.size.getHeight() / scale;
	}
	
	public void registerKeyboardInput(KeyboardInput input)
	{
		this.canvas.addKeyListener(input);
	}

	public void close()
	{
		frame.dispose();
	}

	public void drawFrame()
	{
		if (this.renderMode == RenderingMode.SOFTWARE)
		{
			if (g == null)
			{
				g = canvas.getGraphics();
				return;
			}

			g.drawImage(buffImg, 0, 0, (int) size.getWidth(), (int) size.getHeight(), null);
		} else if (this.renderMode == RenderingMode.HARDWARE)
		{

		}
	}

	public void clearScreen()
	{
		for(int i = 0; i < this.pixelBuffer.length; i++)
		{
			this.pixelBuffer[i] = 0xFF000000;
		}
	}

	public void setPixelColor(int x, int y, int color)
	{
		if (x < 0 || x >= (buffImg.getWidth()) || y < 0 || y >= (buffImg.getHeight()))
			return;

		pixelBuffer[x + (int) (y * buffImg.getWidth())] = color;
	}
}