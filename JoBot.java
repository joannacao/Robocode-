package JC;
import robocode.*;
//import robocode.util.*;  
//import java.util.*; 
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
	double prevEnergy = 100.00;  
	public void run() {
	
		//WHILE GUN IS COOLING DOWN, HAVE BOT MOVE AS MUCH AS POSSIBLE TO AVOID GETTING HIT
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		setTurnGunRight(9999); 
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	//create shooting strategies: head on, circular, pattern
	//MAYBE: guess factor targeting because it's complex
	public void onScannedRobot(ScannedRobotEvent e) {
		setTurnRight(e.getBearing() + 90); 
		
		double changeEnergy = prevEnergy - e.getEnergy(); 
		if (changeEnergy >= 0 && changeEnergy <= 3){
			setBack(60); 
		}
		
		setTurnGunLeft(9999);
		if (e.getDistance() < 100){
			fire(3); 
		} else {
			fire(1);
		}
		
		prevEnergy = e.getEnergy(); 
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
		fire(1); 
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
		setTurnLeft(90); 
	}	
}
