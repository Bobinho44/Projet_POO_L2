package poo;

/**
 * The Model class contains the application data and logic for manipulate this data.
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JSlider;

public class Model {
	
	// Trail selected by default (comet trail).
	private static final int DEFAULT_TRAIL = 3;
	
	// Sleep time selected by default (25ms).
	private static final int DEFAULT_SLEEP_TIME = 25;
	
	// Move direction selected by default (classic).
	private static final int DEFAULT_MOVE_DIRECTION = 1;
	
	// Shape's color selected by default (red).
	private static final Color DEFAULT_COLOR = Color.RED;
	
	// Shape's angle increment selected by default (0°).
	private static final int DEFAULT_SHAPE_ANGLE_INCREMENT = 0;
	
	// Integer corresponds to the no trail.
	private static final int NO_TRAIL_INDEX = 2;
	
	// Integer corresponds to the comet trail.
	private static final int COMET_TRAIL_INDEX = 3;
	
	// Minimum sleep time of the thread.
	private static final int MINIMUM_SLEEP_TIME = 10;
	
	// Number of shapes making up the comet trail.
	private static final int COMET_TRAIL_LENGTH = 5;
	
	// Maximum number of shapes in the drawables's list
	private static final int DRAWABLE_LIST_MAX_SIZE = 1000;
	
	// Increment of the progress of the path.
	private static final double PROGRESS_ON_PATH_INCREMENT = Math.PI/50;

	// Selected trail.
	private int trail = DEFAULT_TRAIL;
	
	// Selected shape's color.
	private Color color = DEFAULT_COLOR;
	
	// Selected sleep time.
	private int sleepTime = DEFAULT_SLEEP_TIME;
	
	// Selected move direction.
	private int moveDirection = DEFAULT_MOVE_DIRECTION;
	
	// Selected shape's angle increment.
	private int shapeAngleIncrement = DEFAULT_SHAPE_ANGLE_INCREMENT;
	
	// View is the graphical representation of the Model allowing interactions with users.
	private View view;
	
	// Selected shape.
	private Shape shape;
	
	// Selected path.
	private Path path;
	
	/**
	 * Create a link: Model to View.
     * @param view
     *           The model that is the  graphical representation of the Model allowing interactions with users
	 * @see View
	 */
	public void linkView(View view) {
		this.view = view;
	}
	
	/**
	 * Getter: 
     * Returns the selected shape.
     * @return A Shape representing the selected shape.
     * @see Shape
     */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * Getter: 
     * Returns the selected shape's color.
     * @return A Shape representing the selected shape's color.
     * @see Shape
     */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Setter: 
     * Updates the selected shape.
     * @param shape
     *           The shape selected by the user.
     * @see Shape
     */
	public void setShape(Shape shape) {
		this.shape = shape;
		this.shape.setColor(color);
	}
	
	/**
	 * Setter: 
     * Updates the selected path.
     * @param path
     *           The path selected by the user.
     * @see Path
     */
	public void setPath(Path path) {
		this.path = path;
		view.clearDrawable();
	}
	
	/**
	 * Setter: 
     * Updates the selected trail.
     * @param trail
     *           The trail selected by the user.
     */
	public void setTrail(int trail) {
		this.trail = trail;
	}
	
	/**
	 * Setter: 
     * Updates the selected sleep time.
     * @param sleepTime
     *           The sleep time selected by the user.
     */
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	/**
	 * Setter: 
     * Updates the selected move direction.
     * @param moveDirection
     *           The move direction selected by the user.
     */
	public void setMoveDirection(int moveDirection) {
		this.moveDirection = moveDirection;
		if (path != null) {
			path.setProgressOnPath(path.getPathProgression() + moveDirection * PROGRESS_ON_PATH_INCREMENT);
		}
	}
	
	/**
	 * Setter: 
     * Updates the selected shape's angle increment.
     * @param shapeAngleIncrement
     *           The shape's angle increment selected by the user.
     */
	public void setShapeAngleIncrement(int shapeAngleIncrement) {
		this.shapeAngleIncrement = shapeAngleIncrement;
	}
	
	/**
	 * Setter: 
     * Updates the selected shape's color.
     * @param color
     *           The shape's color selected by the user.
     */
	public void setColor(Color color) {
		this.color = color;
		if (shape != null) { shape.setColor(color); }
	}
	/**
     * Updates the selected movement's speed. Selected by the user, with the key '+' or '-'.
     * @param speedIncrementMultiplierSymbol
     *           The movement's speed key symbol pressed by the user.
     */
	public void keypadChangingSpeed(char speedIncrementMultiplierSymbol) {
		int speedIncrementMultiplier = speedIncrementMultiplierSymbol == '+' ? 1 : -1;
		JSlider speedSlider = view.getSpeedSlider();
		
		// Update speed modifier's slider value.
		speedSlider.setValue(speedSlider.getValue() + 1 * speedIncrementMultiplier);
		
		setSleepTime(view.getSpeedSlider().getValue());
	}
	
	/**
     * Updates the color of the next drawn shape if the user clicks on an already drawn shape.
     * The color is chosen randomly.
     * @param x
     *           The x coordinates of the mouse clicks's user.
     * @param y
     *           The y coordinates of the mouse clicks's user.
     */
	public void mouseClicked(int x, int y) {
		if (getShape() != null && view.containMouse(new Point(x , y))) {

			// Get random color.
			ColorList randomColor = ColorList.values()[new Random().nextInt(ColorList.values().length)];
			
			// If the random color is the actual shape's color, we choose another color.
			if (getColor().equals(randomColor.getColor())) { mouseClicked(x, y); }
			ArrayList<AbstractButton> colorsButtonList = Collections.list(view.getColorsButtonGroup().getElements());
			
			// Select the color in the menu.
			for (AbstractButton colorCheckButton : colorsButtonList) {
				if (colorCheckButton.getText().equalsIgnoreCase(randomColor.getName())) {
					colorCheckButton.setSelected(true);
					setColor(randomColor.getColor());
				}
			}
		}
	}

	/*
	 * Core of the application.
	 * Manages the sending of the shapes to be drawn to the drawingPanel, 
	 * the movement of the shapes on a path.
	 */
	public void evolve() {
		while (true) {
			if (view != null && shape != null && path != null && shape.getColor() != null) {
				Point nextPoint = path.nexPoint();
				
				// Avoid drawing it off the screen.
				if (nextPoint.getX() <= View.WIDTH && nextPoint.getX() >= 0 && nextPoint.getY() <= View.HEIGHT && nextPoint.getY() >= 0) {
					shape.setPosition(path.nexPoint());
					
					// Keep maximum COMET_TRAIL_LENGTH shapes in the drawables's list.
					if (trail == COMET_TRAIL_INDEX) {
						if (view.numberOfDrawable() == COMET_TRAIL_LENGTH) {
							view.removeFirstDrawable();
						}
						else if(view.numberOfDrawable() > COMET_TRAIL_LENGTH) {
							view.clearDrawable();
						}
					}
					
					// Keep maximum 1 shape in the drawables's list.
					else if (trail == NO_TRAIL_INDEX) {
						view.clearDrawable();
					}
					
					// Keep maximum DRAWABLE_LIST_MAX_SIZE shapes in the drawables's list..
					else if (view.numberOfDrawable() == DRAWABLE_LIST_MAX_SIZE) {
						view.removeFirstDrawable();
					}
					view.addDrawable(shape.clone());
					view.update();
					shape.setRotationAngle((shape.getRotationAngle() + shapeAngleIncrement) % 360);
					path.setProgressOnPath(path.getPathProgression() + moveDirection * PROGRESS_ON_PATH_INCREMENT);
				}
				else {
					path.setProgressOnPath(0);
				}
			}
			try {
				Thread.sleep(Math.abs(sleepTime - 50) + MINIMUM_SLEEP_TIME);
			}
			catch(Exception e) {}
		}
	}
}
