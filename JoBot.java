package JC;
import robocode.*; 
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * JoBot - a robot by (your name here)
 */
public class JoBot extends AdvancedRobot
{
	/**
	 * run: JoBot's default behavior
	 */
	double prevEnergy = 100.00; //initializes prevEnergy for onScannedRobot
	public void run() {
		setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		int turn = 1; 
		// Robot main loop
		for (int i = 0; i < 100; i++){
			setTurnGunLeft(99999); 
			setTurnGunRight(99999); 
		} //scans for robots
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	//create shooting strategies: head on, circular, pattern
	public void onScannedRobot(ScannedRobotEvent e) { 
		if ((turn%2) == 0){
			setTurnLeft(e.getBearing() + 90); //turns to the right to make it easier to
		} else {
			setTurnLeft(e.getBearing() - 90); 
		}
		
		double changeEnergy = prevEnergy - e.getEnergy(); //checks for change in energy
		if (changeEnergy >= 0 && changeEnergy <= 3){
			setAhead(100); //dodges if it senses loss in energy
		}
		
		setTurnGunLeft(9999); //scans before firing
		fire(2); 
		turn++; 
		prevEnergy = e.getEnergy(); //updates value of prevEnergy
		setTurnGunLeft(9999); 
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		e.setPriority(2);  
		setTurnGunRight(e.getBearing()); //Turns gun right to the direction of where the bullet came from
		//if (e.getBearing() == 0)
		fire(2);
		setTurnRight(90);
		setAhead(80); 
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		public void onHitWall(HitWallEvent e) {
		e.setPriority(1); 
		setTurnLeft(e.getBearing() + 90); 
		setAhead(500); 
		//setTurnGunLeft(9999); 
		setTurnRight(90); 
		scan(); 	
	}
	}	
	
	public void onHitRobot(HitRobotEvent e){
		e.setPriority(2);
		if (e.isMyFault()){ 
			setBack(200); 
		} else {
			setTurnGunLeft(e.getBearing()); 
			double myEnergy = this.getEnergy(); 
			if (myEnergy > 1){
				fire(2); 
			} else {
				fire(1); 
			}
		}
	}
	
	public void onBulletMissed(BulletMissedEvent e){
		setStop(); 
		setTurnGunLeft(720); 
		scan(); 
	}	
	//create method/algorithn for preventing bot from running into walls (bc running into walls = loss of power and possibility of getting stuck)

}
