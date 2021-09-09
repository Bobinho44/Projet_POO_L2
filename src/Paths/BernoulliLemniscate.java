package Paths;

import java.awt.Point;

import poo.Path;
import poo.View;

/**
 * BernoulliLemniscate is the class representing a path in the form of Bernoulli's lemniscate.
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */
public class BernoulliLemniscate implements Path {

	// Resizing the length of the drawing area.
	// WIDTH is 4 units long.
	private static final int RESIZING_WIDTH = 4;
	
	// Resizing of the Bernoulli's lemniscate.
	// The coordinates of each point are multiplied by this constant.
	private static final double LEMNISCATE_GOOD_PROPORTION = (RESIZING_WIDTH*1.4)/4;
	
    // The path progression of this Bernoulli's lemniscate.
	private double pathProgression;

	
    /**
     * Constructor initiating an instance of Bernoulli's lemniscate with a path progression of 0.
     */
	public BernoulliLemniscate() {
		setProgressOnPath(0);
	}
	
	/**
	 * Getter: 
     * Returns the progression of this path.
     * @return A double representing the progression of this Bernoulli's lemniscate.
     */
	@Override
	public double getPathProgression() {
		return pathProgression;
	}
	
	/**
	 * Setter: 
     * Updates the progression of this Bernoulli's lemniscate.
     * @param pathProgression
     *           The new progression of this Bernoulli's lemniscate.
     */
	@Override
	public void setProgressOnPath(double pathProgression) {
		this.pathProgression = pathProgression;
	}
	
    /**
     *  Returns the position of the next point of this Bernoulli's lemniscate.
     *  @return A Point representing the next position to progress on this Bernoulli's lemniscate.
     */
	@Override
	public Point nexPoint() {
		double sin = Math.sin(pathProgression);
		double cos = Math.cos(pathProgression);
		
		// Parametric equation.
		double x = LEMNISCATE_GOOD_PROPORTION*sin/(1+ Math.pow(cos,2));
		double y = LEMNISCATE_GOOD_PROPORTION*sin*cos/(1+ Math.pow(cos, 2));
		
		// We put (x;y) coordinates back into the resize marker. 
		// Length: RESIZING_WIDTH and width: (RESIZING_WIDTH*View.HEIGHT)/View.WIDTH.
		int resizeX = (int) (View.WIDTH/2 + View.WIDTH*x/RESIZING_WIDTH);
		int resizeY = (int) (View.HEIGHT/2 + View.WIDTH*y/RESIZING_WIDTH);
		
		return new Point(resizeX, resizeY);
	}
	
}
