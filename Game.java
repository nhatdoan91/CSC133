package com.mycompany.a1;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.codename1.charts.util.ColorUtil;

public class Game extends Form{
	private GameWorld gw;
	public Game() {
	gw = new GameWorld();
	gw.init();
	play();
	}
	private void play()
	{
		Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				//System.out.println("\nYou just entered '"+ sCommand+"'....");
					if(sCommand.length() != 0)
						switch (sCommand.charAt(0)) {
						case 'x':
							
							gw.exitTrue();
							System.out.println("Are you sure that you want to exit the game???\n Type 'y' for yes and 'n' for no.");
							break;
						case 'a':
							gw.exitFasle();
							gw.cyborgAcceleration();
							break;
						case 'b':
							gw.exitFasle();
							gw.cyborgBreak();
							break;
						case 'l':
							gw.exitFasle();
							gw.findCyborg().changeHeading('l');
							break;
						case 'r':
							gw.exitFasle();
							gw.findCyborg().changeHeading('r');
							break;
						case 'c':
							gw.exitFasle();
							gw.collideWithCyborg();
							
							break;	
						case '1' :
						case '2' :
						case '3' :
						case '4' :
						case '5' :
						case '6' :
						case '7' :
						case '8' :
						case '9' :
							gw.exitFasle();
							int numberHold= sCommand.charAt(0);
							numberHold=numberHold-48;
							gw.findCyborg().baseReach(numberHold);
							gw.wonGame();
							break;
							
						case 'e':
							gw.exitFasle();
							gw.collideWithEnergyStation();
							break;
						case 'g':
							gw.exitFasle();
							gw.collideWithDrone();
							break;
						case 't':
							gw.exitFasle();
							gw.clickTick();
							break;
						case 'd':
							gw.exitFasle();
							gw.displayCyborg();
							break;
						case 'm':
							gw.exitFasle();
							gw.displayMap();
							break;
						case 'n':
							gw.exit('n');	
							break;
						case 'y':
							gw.exit('y');
							
							break;	
						default:
							System.out.println("\nYour Input is invalid.Please enter valid command!!!\n");
							break;
						
	//add code to handle rest of the commands
						} //switch
				} //actionPerformed
		} //new ActionListener()
		); //addActionListener
	} //play

}
