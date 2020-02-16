package com.mycompany.a1;

abstract public class Movable extends GameObject{
	private int heading ;
	private int speed;
	private double movX;
	private double movY;
	
	public void move() {
		this.setMovX();
		this.setMovY();
		super.setLocation(this.getMovX()+super.getX(),this.getMovY()+super.getY());
	}
	private void setMovX()
	{
		this.movX=Math.round((Math.cos(-(this.heading-90))*this.speed));
	}
	private double getMovX()
	{
		return this.movX;
	}
	private void setMovY()
	{
		this.movY=Math.round((Math.sin(-(this.heading-90))*this.speed));
	}
	private double getMovY()
	{
		return this.movY;
	}
	public void setHeading(int heading) {
		this.heading=heading;
	}
	public int getHeading(){
		return this.heading;
	}
	public void setSpeed(int speed) {
		this.speed=speed;
	}
	public int getSpeed() {
		return this.speed;
	}
	public boolean isGoingOutOfBoudaries() {
		if((super.getLocation().getX()+movX)>1000||super.getLocation().getX()+movX<0||super.getLocation().getY()+movY>1000||super.getLocation().getY()+movY<0)
		{
			return true;
		}else
		{
			return false;
		}
	}

	public void bound() {
		this.heading=this.heading+180;
		this.checkHeadingBoudaries();
	}
	
	public void checkHeadingBoudaries()
	{
		if(this.heading>360)
		{
			this.heading=this.heading-360;
		}else if(this.heading<0)
		{
			this.heading=this.heading+360;
		}
	}
	public int getLastBase() {
		int i=super.getLastBase();
		return i;
	}
}
