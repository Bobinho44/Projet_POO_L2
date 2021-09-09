package Paths;

import java.awt.Point;

import poo.Path;
import poo.View;

/**
 * ArchimedeanSpiral is the class representing a path in the form of Archimedean spiral.
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */
public class ArchimedeanSpiral implements Path {

	// Resizing the length of the drawing area.
	// WIDTH is 4 units long.
	private static final int RESIZING_WIDTH = 4;
	
	// Resizing of the Archimedean spiral.
	// The coordinates of each point are multiplied by this constant.
	private static final double SPIRAL_GOOD_PROPORTION  = (RESIZING_WIDTH*0.08)/4;
	
    // The path progression of this Archimedean spiral.
	private double pathProgression;

	
    /**
     * Constructor initiating an instance of Archimedean spiral with a path progression of 0.
     */
	public ArchimedeanSpiral() {
		setProgressOnPath(0);
	}
	
	/**
	 * Getter: 
     * Returns the progression of this path.
     * @return A double representing the progression of this Archimedean spiral.
     */
	@Override
	public double getPathProgression() {
	 	return pathProgression;
	}
	
	/**
	 * Setter: 
     * Updates the progression of this Archimedean spiral.
     * @param pathProgression
     *           The new progression of this Archimedean spiral.
     */
	@Override
	public void setProgressOnPath(double pathProgression) {
		this.pathProgression = pathProgression;
	}
	
    /**
     *  Returns the position of the next point of this Archimedean spiral.
     *  @return A Point representing the next position to progress on this Archimedean spiral.
     */
	@Override
	public Point nexPoint() {
		
		// Parametric equation.
		double x = SPIRAL_GOOD_PROPORTION*pathProgression*Math.cos(pathProgression);
		double y = SPIRAL_GOOD_PROPORTION*pathProgression*Math.sin(pathProgression);
		
		// We put (x;y) coordinates back into the resize marker 
		// Length: RESIZING_WIDTH and width: (RESIZING_WIDTH*View.HEIGHT)/View.WIDTH.
		int resizeX = (int) (View.WIDTH/2 + View.WIDTH*x/RESIZING_WIDTH);
		int resizeY = (int) (View.HEIGHT/2 + View.WIDTH*y/RESIZING_WIDTH);
		
		return new Point(resizeX, resizeY);
	}
	
}
