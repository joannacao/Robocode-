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

		// Robot main loop
		setTurnGunRight(99999); //scans for robots
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	//create shooting strategies: head on, circular, pattern
	public void onScannedRobot(ScannedRobotEvent e) { 
		setTurnRight(e.getBearing() + 90); //turns to the right so it's easier to dodge
		
		double changeEnergy = prevEnergy - e.getEnergy(); //checks for change in energy
		if (changeEnergy >= 0 && changeEnergy <= 3){
			setAhead(100); //dodges if it senses loss in energy
		}
		
		setTurnGunLeft(9999); //scans before firing
		if (e.getDistance() < 100){
			fire(2.5); 
		} else {
			fire(1.5);
		}
		
		prevEnergy = e.getEnergy(); //updates value of prevEnergy
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		setTurnLeft(90); //moves aside for caution
		setAhead(80); 
		setTurnGunLeft(e.getBearing()); //Turns gun left to the direction of where the bullet came from
		fire(1.5); 
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		double wallMeasure = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		setTurnRight(e.getBearing() + 90); 
		setAhead(wallMeasure);
		setTurnRight(90); 
		setAhead(150); 
	}	
	
	public void onHitRobot(HitRobotEvent e){
		setTurnLeft(e.getBearing() + 90); //moves aside
		setBack(100); 
		setTurnGunRight(e.getBearing()); //turns gun and fires, rechecks and fires again
		fire(1);
		setTurnGunLeft(e.getBearing()); 
		fire(1);
	}

}
