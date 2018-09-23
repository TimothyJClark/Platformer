package com.timothyclark.engine.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import com.timothyclark.engine.Engine;
import com.timothyclark.engine.RenderingMode;

public final class Window
{
	private final JFrame frame;
	private final Canvas canvas;
	private BufferedImage buffImg;
	private int[] pixelBuffer;

	private Graphics g;

	private int width, height;
	private Dimension size;
	private RenderingMode renderMode;
	
	private int scale = 1;

	public Window(int width, int height, RenderingMode renderMode)
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

		buffImg = new BufferedImage((int) (size.getWidth() / scale), (int) (size.getHeight() / scale), BufferedImage.TYPE_INT_RGB);

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
				
				buffImg = new BufferedImage((int) (size.getWidth() / scale), (int) (size.getHeight() / scale), BufferedImage.TYPE_INT_RGB);
				
				pixelBuffer = ((DataBufferInt) buffImg.getRaster().getDataBuffer()).getData();
				
				System.out.println("buffImg size " + buffImg.getWidth() + " " + buffImg.getHeight());
				System.out.println("frame size " + frame.getWidth() + " " + frame.getHeight());
				System.out.println("canvas size " + canvas.getWidth() + " " + canvas.getHeight());
			}

			public void componentShown(ComponentEvent arg0)
			{
			}
		});

		frame.setTitle(Engine.ENGINE_NAME + " ver " + Engine.VERSION);
		frame.setVisible(true);
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

			for (int i : pixelBuffer)
			{
				i = 0x00;
			}

			g.drawImage(buffImg, 0, 0, (int) size.getWidth(), (int) size.getHeight(), null);
		} else if (this.renderMode == RenderingMode.HARDWARE)
		{

		}
	}
	
	public void setPixelColor(int x, int y, int color)
	{
		if (x < 0 || x > (size.getWidth() / scale) || y < 0 || y > (size.getHeight() / scale)) return;
		
		pixelBuffer[x + (int) (y * size.getWidth() / scale)] = color;
	}
}