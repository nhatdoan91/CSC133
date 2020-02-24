package com.mycompany.a1;
import com.codename1.charts.util.ColorUtil;

public class Cyborg extends Movable implements ISteerable {
	
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyComsumptionRate;
	private int damageLevel=0;
	private int lastBaseReached;
	
	public Cyborg()
	{
		this.steeringDirection=0;
		super.setHeading(0);
		super.setSpeed(0);
		this.maximumSpeed=40;
		this.energyComsumptionRate=5;
		this.damageLevel=0;
		this.energyLevel=100;
		this.lastBaseReached=1;
		super.setColor(ColorUtil.BLUE);
		super.setSize(15);
		super.setRandomLocation();

	}

	public void energyLostAfterTick()
	{
		this.energyLevel=this.energyLevel-this.energyComsumptionRate;
	}
	public void changeHeading(char input) {
		switch(input) {
		case 'l':
			if(this.steeringDirection>-40)
			{
				this.steeringDirection=this.steeringDirection-5;
				System.out.println("You steering direction is "+ this.getSteeringDirection());
			}
			else
			{
				System.out.println("You cant steer left anymore!!!!");
			}
			break;
		case 'r':
			if(this.steeringDirection<40)
			{
				this.steeringDirection=this.steeringDirection+5;
				System.out.println("You steering direction is "+ this.getSteeringDirection());
			}
			else
			{
				System.out.println("You cant steer right anymore!!!!");
			}
			break;
		}
		
	}
	public int getSteeringDirection()
	{
		return this.steeringDirection;
	}
	public void setSteeringDirection(int newSteeringDirection)
	{
		this.steeringDirection=newSteeringDirection;
	}
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}
	public int getEnergyLevel()
	{
		return this.energyLevel;
	}
	public void setEnergyLevel(int energy)
	{
		this.energyLevel=energy;
	}
	public int getEnergyConsumptionRate() {
		return this.energyComsumptionRate;
	}
	public void setDamageLevel(int Damage) {
		this.damageLevel=Damage;
	}
	public int getDamageLevel() {
		return this.damageLevel;
	}
	public boolean isMaxSpeed() {
		return super.getSpeed()>this.getMaximumSpeed();
	}
	public void speedUp()
	{
		super.setSpeed(super.getSpeed()+5);
		if(this.isMaxSpeed())
		{
			super.setSpeed(maximumSpeed);
			System.out.println("You reached to the max speed!!!");
		}
	}
	public void slowDown()
	{
		super.setSpeed(super.getSpeed()-5);
		if(super.getSpeed()<=0)
		{
			super.setSpeed(0);
			System.out.println("You stopped!!!");
		}
	}
	public boolean isBroken() {
		return (this.getDamageLevel() >=10);
	}
	public boolean isOutOfBattery() {
		return (this.getEnergyLevel()<=0);
	}
	public void reduceSpeedWithDamage() {
		 super.setSpeed((super.getSpeed()*this.damageLevel)/10);
	 }
	public void collideWithCyborg()
	 {
			 this.setDamageLevel(this.getDamageLevel()+5);
			 super.setColor(ColorUtil.rgb(0, 0, (this.damageLevel*250)/10));
	 }
	public void collideWithDrone()
	 {
			 this.setDamageLevel(this.getDamageLevel()+3);
			 super.setColor(ColorUtil.rgb(0, 0, (this.damageLevel*250)/10));	 
	 }
	public void baseReach(int baseNumber) {
		 if(baseNumber==(this.lastBaseReached+1))
		 {
			 this.lastBaseReached=baseNumber;
			 System.out.println("Congrats!! You reach base "+this.getLastBaseReached());
		 }else if(baseNumber <= this.lastBaseReached)
		 {
			 System.out.println("\nYou have reached this base!!!");
			 System.out.println("You need to reach base "+ (this.lastBaseReached+1));
		 }else
		 {
			 System.out.println("\nYou passed base "+ (this.lastBaseReached+1));
			 System.out.println("You need to reach base "+ (this.lastBaseReached+1) + " first");
		 }
	 }
	public int getLastBaseReached() {
		 return this.lastBaseReached;
	 }
	public int reachEnergyStation(int energy) {
		 this.energyLevel=this.energyLevel+energy;
		 if(this.getEnergyLevel()>=100) {
			 int a=this.getEnergyLevel()-100;
			 this.setEnergyLevel(100);
			 return a;//MAX
		 }
		 return 0;
	 }
	public boolean isAtLastBase()
	 {
		 if(super.getLastBase()==this.lastBaseReached)
		 {
			 return true;
		 }
		 return false;
	 }

	public int getSpeedWhileDamage() {
		if(this.getDamageLevel()==0)
		{
			return super.getSpeed();
		}
		else
			{
			int newspeed= ((100-(this.getDamageLevel()*10))*super.getSpeed())/100;
			return newspeed;
			}
		// 10 is maximum damage
	}
	 @Override
	public void setSize(int size) 
	 {}
	public String toString() {
			String thisclassData = "\nCyborg"+super.toString()+(" maxSpeed = " +this.getMaximumSpeed()+" steeringDirection= "+this.getSteeringDirection()+" energyLevel= "+this.getEnergyLevel()+" damageLevel="+this.getDamageLevel());
			return thisclassData;
	 }

}
