package com.mycompany.a1;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	Random random = new Random();
	private int gameTime=0;
	private int liveOfPlayer=3;
	private boolean endGame=false;
	private int lastBase;
	private int count=3;
	private int numberOfDrone;
	private int numberOfEnergyStation;
	private Vector<GameObject> gameObjects = new Vector<GameObject>();
	private boolean exit1=false;
	

	public int getLastBase() {
		return this.lastBase;
	}
	public void endGame()
	{
		if(endGame)
		
			if(this.findCyborg().getLastBaseReached()==this.getLastBase())
			{
				System.out.println("\nYou won!!!!!!");
				System.out.println("Your time is "+this.getGameTime()+" and you have "+this.getLiveOfPlayer()+"lives left :D");
			}
			else {
				System.out.println("GameOver!!!!!!");
				try
				{
				    Thread.sleep(5000);
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
				this.exitTrue();
				this.exit('y');
				}
				
			
		
	}
	public boolean getEndGame()
	{
		return this.endGame;
	}
	public void wonGame() {
		if(this.findCyborg().getLastBaseReached()==this.getLastBase())
		{
			System.out.println("\nYou Won The Game!");
			System.out.println("Your time is "+this.getGameTime()+" and you have "+this.getLiveOfPlayer()+"lives left :D");
			try
			{
			    Thread.sleep(5000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			System.exit(0);
		}
	}
	public void setEndGame(boolean result) {
		this.endGame=result;
	}
	public void setLiveOfPlayer(int numberOfLive)
	{
		liveOfPlayer = numberOfLive;
	}
	public int getLiveOfPlayer()
	{
		return this.liveOfPlayer;
	}
	public int getGameTime() {
		return this.gameTime;
	}
	public void init()
	{
		endGame=false;
		char d='d',b='b',c='c',e='e';
		createGameObject(c);
		numberOfDrone=2+random.nextInt(4);
		numberOfEnergyStation=2+random.nextInt(4);
		lastBase=4+random.nextInt(6);
		for(int i=1; i<= lastBase; i++)
		{
			createGameObject(b);
			
		}
		System.out.println("New Map was just created \n There are "+ this.lastBase+" bases, "+numberOfDrone+" drones, "+numberOfEnergyStation+" energy stations was created!");
		for (int i=0; i<gameObjects.size(); i++) {
			if (gameObjects.elementAt(i) instanceof Base) {
				Base base = (Base)gameObjects.elementAt(i);
				base.setSequenceNumber(i);
				if(i==1)
				{
					findCyborg().setLocation(findBase().getLocation());
				}
			}
		}
		for(int i=1; i<= numberOfDrone; i++)
		{
			createGameObject(d);
		}
		for(int i=1; i<= numberOfEnergyStation; i++)
		{
			createGameObject(e);
		}
	}
	public Cyborg findCyborg() {
		for(int i=0; i< gameObjects.size();i++)
		{
			if(gameObjects.get(i) instanceof Cyborg)
			{
				return (Cyborg) gameObjects.get(i);
			}
		}
		return null;
	}
	public Base findBase() {
		for(int i=0; i< gameObjects.size();i++)
		{
			if(gameObjects.get(i) instanceof Base)
			{
				return (Base) gameObjects.get(i);
			}
		}
		return null;
	}
	public EnergyStation findEnergyStation() {
		for(int i=0; i< gameObjects.size();i++)
		{
			if(gameObjects.get(i) instanceof EnergyStation)
			{
				return (EnergyStation) gameObjects.get(i);
			}
		}
		return null;
	}
	public Drone findDrone() {
		for(int i=0; i< gameObjects.size();i++)
		{
			if(gameObjects.get(i) instanceof Drone)
			{
				return (Drone) gameObjects.get(i);
			}
		}
		return null;
	}
	public void createGameObject(char nameObject) {
		switch(nameObject) {
		case 'c':
			Cyborg c = new Cyborg();
			gameObjects.add(c);
			break;
		case 'd':
			Drone d = new Drone();
			gameObjects.add(d);
			break;
		case 'b':
			Base b = new Base();
			gameObjects.add(b);
			break;
		case 'e':
			EnergyStation e = new EnergyStation();
			gameObjects.add(e);
			break;
		}
	}
	public void clickTick()
	{
		for(int i=0; i< gameObjects.size();i++)
		{
			if(gameObjects.get(i) instanceof Movable)
			{
				Movable mov = (Movable) gameObjects.get(i);
				if(mov instanceof Cyborg)
				{
					Cyborg c = (Cyborg) mov;
					if(this.findCyborg().isOutOfBattery()||this.findCyborg().isBroken())
					{
						this.cyborgReset();
					}else
					{
					c.move();
					c.energyLostAfterTick();
					c.setHeading(c.getHeading()+c.getSteeringDirection());
					c.checkHeadingBoudaries();
					System.out.println("My new location is at ("+c.getX()+","+c.getY()+")");
					System.out.println("My new heading is "+c.getHeading());
					}
				}
				if(mov instanceof Drone)
				{
					Drone d = (Drone) mov;
					d.move();
					d.changeRandomHeading();
				}
			}
		}
		gameTime++;
	}
	public void displayCyborg()
	{
				System.out.println("\nYou have "+this.getLiveOfPlayer()+" lives left\n"
						+"Your clocktime is at "+ this.getGameTime() + " ticks");
				System.out.println("\nYour Cybrog: ");
				System.out.println("++last base reached is "+this.findCyborg().getLastBaseReached());
				System.out.println("++energy level is "+ this.findCyborg().getEnergyLevel());
				System.out.println("++damage level is " +this.findCyborg().getDamageLevel());		
	}
	
	public void displayMap()
	{
		for (int i=0; i<gameObjects.size(); i++) {
			if (gameObjects.elementAt(i) instanceof Cyborg)
			{
				Cyborg c = (Cyborg) gameObjects.elementAt(i);
				System.out.println(c.toString());
			}
			if (gameObjects.elementAt(i) instanceof Drone)
			{
				Drone d = (Drone) gameObjects.elementAt(i);
				System.out.println(d);
			}
			if (gameObjects.elementAt(i) instanceof Base )
			{
				Base b = (Base) gameObjects.elementAt(i);
				System.out.println(b);
			}
			if (gameObjects.elementAt(i) instanceof EnergyStation)
			{
				EnergyStation e = (EnergyStation) gameObjects.elementAt(i);
				System.out.println(e);
			}
		}
	}
	public void cyborgAcceleration()
	{
		int lastSpeed=this.findCyborg().getSpeedWhileDamage();
		this.findCyborg().speedUp();
		if(this.findCyborg().getSpeedWhileDamage()!=lastSpeed) {
			System.out.println("Your Cyborg's speed is "+ this.findCyborg().getSpeedWhileDamage() + " units per tick");
		}
	}
	public void cyborgBreak() {
		int lastSpeedBreak=this.findCyborg().getSpeedWhileDamage();
		this.findCyborg().slowDown();
		if(this.findCyborg().getSpeedWhileDamage()!=lastSpeedBreak)
		{
			System.out.println("Your Cyborg's speed is "+ this.findCyborg().getSpeedWhileDamage() + " units per tick");
		}
	}
	public void collideWithCyborg() {
		System.out.println("\nYou just collided with another cyborg");
		this.findCyborg().collideWithCyborg();
		if(!(this.findCyborg().isBroken()))
		{
			System.out.println(this.cyborgCollide());
			
		}else {
			this.cyborgReset();
		}	
	}
	public void collideWithDrone() {
		System.out.println("\nYou just collided with a drone");
		this.findCyborg().collideWithDrone();
		if(!this.findCyborg().isBroken())
		{

			System.out.println(this.cyborgCollide());
		}else {
			this.cyborgReset();
		}
	}
	public void collideWithEnergyStation() {
		int random1= random.nextInt(numberOfEnergyStation);
		System.out.println("Energy of station number "+(random1+1)+" is "+((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getenergyCapacity()+", and energy of Cyborg is "+this.findCyborg().getEnergyLevel());
		int color=((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getenergyCapacity();
		int engeryRemained=this.findCyborg().reachEnergyStation(((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getenergyCapacity());
		System.out.println(engeryRemained);
		if(engeryRemained<0)
		{
			engeryRemained=0;
		}
		((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).setEnergyCapacity(engeryRemained); 
		System.out.println("Energy of Cyborg is recharged to " + this.findCyborg().getEnergyLevel());
		System.out.println("Energy that remained in the station is: "+((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getenergyCapacity());
		((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).setColor(ColorUtil.rgb(144*(100-100*((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getenergyCapacity())/((color+1)*100),255, 144*(100-100*((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getenergyCapacity())/((color+1)*100)));//light green
		System.out.println("Color of this station is: " + "[" + ColorUtil.red(((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getColor()) + "," +
														ColorUtil.green(((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getColor()) + "," + 							
														+ ColorUtil.blue(((EnergyStation) gameObjects.elementAt(random1+lastBase+numberOfDrone+1)).getColor()) + "]");
		this.createGameObject('e');
		System.out.println("\nA new energy station was just created!!!!");
		numberOfEnergyStation++;
	}
	public String cyborgCollide()
	{
		return "Cyborg's damage level is "+ this.findCyborg().getDamageLevel()+"\nCyborg's color is: " + "[" + ColorUtil.red(this.findCyborg().getColor()) + "," + 
				+ ColorUtil.green(this.findCyborg().getColor()) + "," + 
				+ ColorUtil.blue(this.findCyborg().getColor()) + "]";
	}
	public void cyborgReset()
	{
		System.out.println("Your Cyborg is destroyed. You lost one live!");
		this.count--;
		this.setLiveOfPlayer(count);
		System.out.println("Your number of lives is: "+this.getLiveOfPlayer());
		if(this.getLiveOfPlayer()==0)
		{
			this.setEndGame(true);
			this.endGame();
		}
		gameObjects.clear();
		this.init();
	}
	
	public void exitTrue()
	{
		this.exit1=true;
	}
	public void exitFasle()
	{
		this.exit1=false;
	}

	public void exit(char x) {

		if( this.exit1==true) {
		if(x=='y')
		{
			System.exit(0);
		}else if (x== 'n')
		{
			
				System.out.println("\nThe Game continues");
				this.exit1=false;
		}
		}
	}
	
}
