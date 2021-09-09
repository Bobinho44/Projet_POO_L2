package Paths;


import java.awt.Point;

import poo.Path;
import poo.View;

/**
 * CirclePath is the class representing a path in the form of cirle.
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */
public class CirclePath implements Path {

	// Resizing the length of the drawing area.
	// WIDTH is 4 units long.
	private static final int RESIZING_WIDTH = 4;
	
	// Resizing of the cirle path.
	// The coordinates of each point are multiplied by this constant.
	private static final double CIRCLE_GOOD_PROPORTION  = (RESIZING_WIDTH*0.6)/4;
	
    // The path progression of this cirle path.
	private double pathProgression;

	
    /**
     * Constructor initiating an instance of cirle path with a path progression of 0.
     */
	public CirclePath() {
		setProgressOnPath(0);
	}
	
	/**
	 * Getter: 
     * Returns the progression of this path.
     * @return A double representing the progression of this cirle path.
     */
	@Override
	public double getPathProgression() {
	 	return pathProgression;
	}
	
	/**
	 * Setter: 
     * Updates the progression of this cirle path.
     * @param pathProgression
     *           The new progression of this cirle path.
     */
	@Override
	public void setProgressOnPath(double pathProgression) {
		this.pathProgression = pathProgression;
	}
	
    /**
     *  Returns the position of the next point of this cirle path.
     *  @return A Point representing the next position to progress on this cirle path.
     */
	@Override
	public Point nexPoint() {
		
		// Parametric equation.
		double x = CIRCLE_GOOD_PROPORTION*Math.cos(pathProgression);
		double y = CIRCLE_GOOD_PROPORTION*Math.sin(pathProgression);
		
		// We put (x;y) coordinates back into the resize marker 
		// Length: RESIZING_WIDTH and width: (RESIZING_WIDTH*View.HEIGHT)/View.WIDTH.
		int resizeX = (int) (View.WIDTH/2 + View.WIDTH*x/RESIZING_WIDTH);
		int resizeY = (int) (View.HEIGHT/2 + View.WIDTH*y/RESIZING_WIDTH);
		
		return new Point(resizeX, resizeY);
	}
	
}

