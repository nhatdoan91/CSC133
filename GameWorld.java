package com.mycompany.a1;
import java.util.Random;
import java.util.Vector;

public class GameWorld {
	Random random = new Random();
	private int gameTime;
	private int liveOfPlayer;
	private boolean endGame=false;
	private final int lastBase= 4+random.nextInt(6);
	private Vector<GameObject> gameObjects = new Vector<GameObject>();
	
	public int getLastBase() {
		return this.lastBase;
	}
	public void endGame()
	{
		if(endGame)
		{	for (int i=0; i<gameObjects.size(); i++) {
				if (gameObjects.elementAt(i) instanceof Cyborg) {
					Cyborg c = (Cyborg)gameObjects.elementAt(i);
					if(c.isAtLastBase())
					{
						System.out.println("You won!!!!!!");
					}
					else {
						System.out.println("GameOver!!!!!!");
					}
				}
			}
		}
	}
	public boolean getEndGame()
	{
		return this.endGame;
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
		return liveOfPlayer;
	}
	public int getGameTime() {
		return this.gameTime;
	}
	public void init()
	{
		gameTime=0;
		liveOfPlayer=3;
		endGame=false;
		char d='d',b='b',c='c',e='e';
		createGameObject(c);
		int numberOfDrone=2+random.nextInt(4);
		int numberOfEnergyStation=2+random.nextInt(4);
		for(int i=1; i<= lastBase; i++)
		{
			createGameObject(b);
			
		}
		System.out.println("There are "+ this.lastBase+" bases, "+numberOfDrone+" drones, "+numberOfEnergyStation+" energy stations was created!");
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
					if(c.isGoingOutOfBoudaries())
					{
						c.bound();
					}
					c.move();
					c.energyLostAfterTick();
					if(c.isOutOfBattery()||c.isBroken())
					{
						this.liveOfPlayer--;//Cyborg died
						if(this.liveOfPlayer==0)
						{
							this.endGame();
						}else
						{
							System.out.println("\nYour Cyborg ran out of energy or too damaged. You lost one live!!");
							c= new Cyborg();
						}
						
					}
					
					c.setHeading(c.getHeading()+c.getSteeringDirection());
					c.checkHeadingBoudaries();
					System.out.println("\nMy new location is at ("+c.getX()+","+c.getY()+")");
					System.out.println("\nMy new heading is "+c.getHeading());
				}
				if(mov instanceof Drone)
				{
					Drone d = (Drone) mov;
					if(d.isGoingOutOfBoudaries())
					{
						d.bound();
					}
					d.move();
					d.changeRandomHeading();
					System.out.println("\nDrone's new location is at ("+d.getX()+","+d.getY()+")");
					System.out.println("\nDrone's new heading is "+d.getHeading());
				}
			}
		}
		gameTime++;
	}
	public void displayCyborg()
	{
		System.out.println("\nYou have "+this.liveOfPlayer+" lives left\n"
				+"\nYour clocktime is "+ this.getGameTime() + " ticks");
	}
	
	public void displayMap()
	{
		for (int i=0; i<gameObjects.size(); i++) {
			if (gameObjects.elementAt(i) instanceof Cyborg)
			{
					System.out.print("\nCyborg: ");
			}
			if (gameObjects.elementAt(i) instanceof Drone)
			{
					System.out.print("\nDrone: ");
			}
			if (gameObjects.elementAt(i) instanceof Base )
			{
					System.out.print("\nBase: ");
			}
			if (gameObjects.elementAt(i) instanceof EnergyStation)
			{
					System.out.print("\nEnergyStation: ");
			}
			if (gameObjects.elementAt(i) instanceof GameObject)
			{
				GameObject g = (GameObject) gameObjects.elementAt(i);
				g.displayObjects();
			}
			if (gameObjects.elementAt(i) instanceof Cyborg)
			{
					Cyborg c = (Cyborg) gameObjects.elementAt(i);
					System.out.print(" heading = "+c.getHeading()+" speed = " +c.getSpeed()+" maxSpeed = " +c.getMaximumSpeed()+" steeringDirection= "+c.getSteeringDirection()+" energyLevel= "+c.getEnergyLevel()+" damageLevel="+c.getDamageLevel());
			}
			if (gameObjects.elementAt(i) instanceof Drone)
			{
					Drone d = (Drone) gameObjects.elementAt(i);
					System.out.print(" heading = "+d.getHeading()+" speed = " +d.getSpeed());
			}
			if (gameObjects.elementAt(i) instanceof Base)
			{
					Base b = (Base) gameObjects.elementAt(i);
					System.out.print(" SequenceNunmber = "+b.getSequenceNumber());
			}
			if (gameObjects.elementAt(i) instanceof EnergyStation)
			{
					EnergyStation e = (EnergyStation) gameObjects.elementAt(i);
					System.out.print(" Capacity = "+e.getenergyCapacity());
			}
			
		}
	}

	public void exit() {
	
		System.exit(0);
	}
	
}
