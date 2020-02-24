package com.mycompany.a1;

abstract public class Movable extends GameObject{
	private int heading ;
	private int speed;
	private float movX;
	private float movY;
	
	public void move() {
		this.setMovX();
		this.setMovY();
		if((super.getLocation().getX()+movX)>1000||super.getLocation().getX()+movX<0||super.getLocation().getY()+movY>1000||super.getLocation().getY()+movY<0)
		{
			this.bound();
		}else
		{
			super.setLocation(this.getMovX()+super.getX(),this.getMovY()+super.getY());
		}
	}
	private void setMovX()
	{
		this.movX=Math.round((Math.cos(Math.toRadians(-(this.heading-90)))*this.speed));
	}
	private float getMovX()
	{
		return this.movX;
	}
	private void setMovY()
	{
		this.movY=Math.round((Math.sin(Math.toRadians(-(this.heading-90)))*this.speed));
	}
	private float getMovY()
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
	@Override
	public String toString()
	{
		String thisCLassData=" heading = "+this.getHeading()+" speed = " +this.getSpeed();
		thisCLassData =super.toString()+ thisCLassData;
		return thisCLassData;
	}
}
