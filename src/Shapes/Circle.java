package Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import poo.Shape;

/**
 * Cicle is the class representing a shape in the form of circle.
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */
public class Circle implements Shape {

    // The default diameter of this circle.
	public static final int DEFAULT_DIAMETER = 100;
	
	
    // The position of this circle.
	private Point position;
	
    // The color of this circle.
	private Color color;

    // The rotation angle of this circle.
	private double rotationAngle;
	
	
    /**
     * Constructor initiating an instance of circle with a rotation angle of 0.
     */
	public Circle() {
		setRotationAngle(0);
	}
	
	/**
	 * Getter: 
     * Returns the position of this circle.
     * @return A Point representing the position of this circle.
     */
	@Override
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Getter: 
     * Returns the rotation angle of this circle.
     * @return A double representing the rotation angle of this circle.
     */
	@Override
	public double getRotationAngle() {
		return rotationAngle;
	}
	
	/**
	 * Getter: 
     * Returns the color of this circle.
     * @return A Color representing the color of this circle.
     */
	@Override
	public Color getColor() {
		return color;
	}
	
	/**
	 * Setter: 
     * Updates the position of this circle.
     * @param position
     *           The new position of this circle.
     */
	@Override
	public void setPosition(Point position) {
		this.position = position;
		
	}
	
	/**
	 * Setter: 
     * Updates the rotation angle of this circle.
     * @param rotationAngle
     *           The new rotation angle of this circle.
     */
	@Override
	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	/**
	 * Setter: 
     * Updates the color of this circle.
     * @param color
     *           The new color of this circle.
     */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

    /**
     *  Returns if the position is included in this circle.
     *  @param position
     *           A position in our panel. (used with the position of the mouse when clicking).
     *  @return A boolean representing the inclusion of this position in this circle.
     */
	@Override
	public boolean containsMouse(Point position) {
		Point centerPosition = new Point();
		centerPosition.x = this.position.x + DEFAULT_DIAMETER/2;
		centerPosition.y = this.position.y + DEFAULT_DIAMETER/2;
		return centerPosition.distance(position) <= DEFAULT_DIAMETER/2;
	}

	/**
     * Draws this circle in our panel.
     * @param g
     *           The 2D graphic in which this circle will be drawn.
     */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(getColor());
		g.drawOval((int) position.getX(), (int) position.getY(), DEFAULT_DIAMETER, DEFAULT_DIAMETER);
	}
	
	/**
     * Returns a clone of this circle.
     * @return A Shape representing a new circle instance of this circle.
     */
	@Override
	public Shape clone() {
		Circle circle = new Circle();
		circle.setPosition(getPosition());
		circle.setColor(getColor());
		circle.setRotationAngle(getRotationAngle());
		return circle;
	}

    /**
     *  Returns If the two circles are geometrically identical (without taking into account the colour and angle).
     *  @param circle
     *           Another circle that we want to compare to this circle.
     *  @return A boolean representing the equivalence between these two circles.
     */
	@Override
	public boolean equals(Shape circle) {
		return getPosition().equals(circle.getPosition());
	}

}
