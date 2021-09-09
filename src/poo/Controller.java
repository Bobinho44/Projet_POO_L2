package poo;

import java.awt.Color;

/**
 * The controller class processes user requests and synchronizes the model and the view.
 * 
 * @see Model
 * @see View
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */
public class Controller {

	// Controller contains the application data and logic for manipulate this data.
	private Model model;
	
	/**
	 * Constructor that instantiates the controller, and creates a link: Controller to Model.
     * @param model
     *           The model that contains the application data and the logic to manipulate this data.
     * @see Model
	 */
	public Controller(Model model) {
		this.model = model;
	}
	
	/**
	 * Transmit the information of the shape selected by the user to the model.
     * @param shape
     *           The shape selected by the user.
     * @see Shape
     * @see Model
     */
	public void setShape(Object shape) {
		if (shape instanceof Shape) {
			Color color = null;
			if (model.getShape() != null && model.getColor() != null) {
				color = model.getColor();
			}
			model.setShape((Shape) shape);
			if (color != null) { setColor(color); }
		}
	}
	
	/**
	 * Transmit the information of the path selected by the user to the model.
     * @param path
     *           The path selected by the user.
     * @see Path
     * @see Model
     */
	public void setPath(Object path) {
		if (path instanceof Path) {
			model.setPath((Path) path);
		}
	}
	
	/**
	 * Transmit the information of the trail selected by the user to the model.
	 * Complete trail: 1
	 * No trail: 2
	 * Comet trail: 3
     * @param trail
     *           The trail selected by the user.
     * @see Model
     */
	public void setTrail(int trail) {
		model.setTrail(trail);
	}

	/**
	 * Transmit the information of the shape angle increment selected by the user to the model.
     * @param angle
     *           The shape angle increment selected by the user.
     * @see Model
     */
	public void setShapeAngleIncrement(int angle) {
		model.setShapeAngleIncrement(angle);
	}
	
	/**
	 * Transmit the information of the sleep time selected by the user to the model.
     * @param sleepTime
     *           The sleep time selected by the user.
     * @see Model
     */
	public void setSleepTime(int sleepTime) {
		model.setSleepTime(sleepTime);
	}
	
	/**
	 * Transmit the information of the move direction selected by the user to the model.
     * @param moveDirection
     *           The move direction selected by the user.
     * @see Model
     */
	public void setMoveDirection(int moveDirection) {
		model.setMoveDirection(moveDirection);
	}
	
	/**
	 * Transmit the information of the shape's color selected by the user to the model.
     * @param color
     *           The shape's color selected by the user.
     * @see Model
     */
	public void setColor(Color color) {
		model.setColor(color);
	}
	
	/**
	 * Transmit the information of the movement's speed selected by the user, with the key '+' or '-', to the model.
	 * '+': add 1 from speed
	 * '-': remove 1 from speed
     * @param speedIncrementMultiplierSymbol
     *           The movement's speed key symbol.
     * @see Model
     */
	public void keypadChangingSpeed(char speedIncrementMultiplierSymbol) {
		model.keypadChangingSpeed(speedIncrementMultiplierSymbol);
	}
	
	/**
	 * Transmit the information of the coordinates of the mouse clicks to the model.
     * @param x
     *           The x coordinates of the mouse clicks.
     * @param y
     *           The y coordinates of the mouse clicks.
     * @see Model
     */
	public void mouseClicked(int x, int y) {
		model.mouseClicked(x, y);
	}
}
