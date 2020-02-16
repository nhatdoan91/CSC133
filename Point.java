package com.mycompany.a1;

public class Point {
	private double x, y ;
	public Point(){
		x = 0.0 ;
		y = 0.0 ;
	}
	public double getX() 
	{
		return x ;
	}
	public double getY() 
	{
		return y ;
	}
	public void setLocationXY(double x, double y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void setX(double newX ) 
	{
		this.x = newX;
	}
	public void setY(double newY )
	{
		this.y = newY;
	}
}
