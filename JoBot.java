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
	public void onScannedRobot(ScannedRobotEvent e)
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
			fire(2);
		}
		
		prevEnergy = e.getEnergy(); //resets prevEnergy to use when this event is called again
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
		public void onHitByBullet(HitByBulletEvent e) {
		setTurnLeft(90); 
		back(10);
		setTurnGunLeft(9999); 
		fire(1.5); 
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		setTurnRight(e.getBearing() + 90); 
		setBack(60);
	}
	
/*
	public void onHitByBulletEvent(HitByBulletEvent e){
		//use e.getHeading() to determine where the bullet came from
		//use e.getName() to determine bot
		//find a way to target this bot and shoot
	}	
	*/
	/*
	 * So I ran the code that I just uploaded, 
	 * and saw that it will just shoot the first
	 * bot it sees and won't pay attention to anyone
	 * else even if they're being shot at. So I think 
	 * we should create a method for what happens when 
	 * our bot gets shot: change its target to whoever is 
	 * shooting it. So it'll set its priorities properly.
	 * The header for the method is above this comment.
	 */	
}
