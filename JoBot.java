package JC;
import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html
// Where I found dodgebot code: https://www.ibm.com/developerworks/library/j-dodge/index.html

/**
 * JoBot - a robot by (your name here)
 */
public class JoBot extends AdvancedRobot
{
	/**
	 * run: JoBot's default behavior
	 */
	double prevEnergy = 100.00;  
	public void run() {
		setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		setTurnGunRight(9999); //constantly rotates gun to scan for robots
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	//create shooting strategies: head on, circular, pattern
	//MAYBE: guess factor targeting because it's complex
	public void onScannedRobot(ScannedRobotEvent e) {
		//turns right so it's easier to move (can just use setAhead or setBack)
		setTurnRight(e.getBearing() + 90); 
		
		//if there is a slight change in the other bot's energy (meaning a bullet was just fired) 
		double changeEnergy = prevEnergy - e.getEnergy(); 
		if (changeEnergy >= 0 && changeEnergy <= 3){
			setBack(60); //the bot moves to dodge
		}
		
		setTurnGunLeft(9999); //spins radar once again and then fires
		if (e.getDistance() < 100){
			fire(3); //fires more if the bot is closer
		} else {
			fire(1);
		}
		
		prevEnergy = e.getEnergy(); //resets prevEnergy to use when this event is called again
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
		fire(1); //back up and fire again
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		back(20); 
		setTurnLeft(90); //if moving back doesn't move it away from the wall, turning left/right should
	}	
}
