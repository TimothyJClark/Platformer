package com.timothyclark.engine.graphics;

public class Camera
{
	private double offsetX, offsetY, currentVarianceX, maxVarianceX, currentVarianceY, maxVarianceY;
	
	private Window window;
	
	public Camera(Window window, double varianceX, double varianceY)
	{
		this.window = window;
		this.offsetX = window.getWidth() / 2;
		this.offsetY = window.getHeight() / 2;
		this.maxVarianceX = varianceX;
		this.maxVarianceY = varianceY;
	}
	
	
	public synchronized double getOffsetX()
	{
		return offsetX;
	}


	public synchronized void setOffsetX(double offsetX)
	{
		this.offsetX = offsetX;
	}


	public synchronized double getOffsetY()
	{
		return offsetY;
	}


	public synchronized void setOffsetY(double offsetY)
	{
		this.offsetY = offsetY;
	}


	public synchronized double getMaxVarianceX()
	{
		return maxVarianceX;
	}


	public synchronized void setMaxVarianceX(double maxVarianceX)
	{
		this.maxVarianceX = maxVarianceX;
	}


	public synchronized double getMaxVarianceY()
	{
		return maxVarianceY;
	}


	public synchronized void setMaxVarianceY(double maxVarianceY)
	{
		this.maxVarianceY = maxVarianceY;
	}


	public synchronized void moveCameraRight(double movement)
	{
		if (this.currentVarianceX >= this.maxVarianceX)
		{
			this.offsetX += movement;
		} else if (this.currentVarianceX + movement > this.maxVarianceX)
		{
			double extra = this.maxVarianceX - this.currentVarianceX;
			
			this.currentVarianceX = this.maxVarianceX;
			
			this.offsetX += extra;
		} else 
		{
			this.currentVarianceX += movement;
		}
	}
	
	public synchronized void moveCameraLeft(double movement)
	{
		if (this.currentVarianceX <= -this.maxVarianceX)
		{
			this.offsetX -= movement;
		} else if (this.currentVarianceX - movement < -this.maxVarianceX)
		{
			double extra = this.currentVarianceX + this.maxVarianceX;
			
			this.currentVarianceX = -this.maxVarianceX;
			
			this.offsetX -= extra;
		} else 
		{
			this.currentVarianceX -= movement;
		}
	}
	
	public synchronized void moveCameraDown(double movement)
	{
		if (this.currentVarianceY >= this.maxVarianceY)
		{
			this.offsetY += movement;
		} else if (this.currentVarianceY + movement > this.maxVarianceY)
		{
			double extra = this.maxVarianceY - this.currentVarianceY;
			
			this.currentVarianceY = this.maxVarianceY;
			
			this.offsetY += extra;
		} else 
		{
			this.currentVarianceY += movement;
		}	
	}
	
	public synchronized void moveCameraUp(double movement)
	{
		if (this.currentVarianceY <= -this.maxVarianceY)
		{
			this.offsetY -= movement;
		} else if (this.currentVarianceY - movement < -this.maxVarianceY)
		{
			double extra = this.currentVarianceY + this.maxVarianceY;
			
			this.currentVarianceY = -this.maxVarianceY;
			
			this.offsetY -= extra;
		} else 
		{
			this.currentVarianceY -= movement;
		}	
	}
}