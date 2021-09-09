package Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import poo.Shape;

/**
 * Square is the class representing a shape in the form of square.
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */
public class Square implements Shape {

    // The default side of this square.
	public static final int DEFAULT_SIDE = 100;
	
    // The position of this square.
	private Point position;
	
    // The color of this square.
	private Color color;
	
    // The rotation angle of this square.
	private double rotationAngle;
	
    /**
     * Constructor initiating an instance of square with a rotation angle of 0.
     */	
	public Square() {
		setRotationAngle(0);
	}
	
	/**
	 * Getter: 
     * Returns the position of this square.
     * @return A Point representing the position of this square.
     */
	@Override
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Getter: 
     * Returns the rotation angle of this square.
     * @return A double representing the rotation angle of this square.
     */
	@Override
	public double getRotationAngle() {
		return rotationAngle;
	}
	
	/**
	 * Getter: 
     * Returns the color of this square.
     * @return A Color representing the color of this square.
     */
	@Override
	public Color getColor() {
		return color;
	}
	
	/**
	 * Setter: 
     * Updates the position of this square.
     * @param position
     *           The new position of this square.
     */
	@Override
	public void setPosition(Point position) {
		this.position = position;
		
	}
	
	/**
	 * Setter: 
     * Updates the rotation angle of this square.
     * @param rotationAngle
     *           The new rotation angle of this square.
     */
	@Override
	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	/**
	 * Setter: 
     * Updates the color of this square.
     * @param color
     *           The new color of this square.
     */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

    /**
     *  Returns if the position is included in this square.
     *  @param position
     *           A position in our panel. (used with the position of the mouse when clicking).
     *  @return A boolean representing the inclusion of this position in this square.
     */
	@Override
	public boolean containsMouse(Point position) {
		// Creation of the position coordinates relative to the centre point of this square. 
		// Taking into account that the Y axis is reversed there.
		double relativeX = (position.getX() - (this.position.getX() + DEFAULT_SIDE/2));
		double relativeY = ((this.position.getY() + DEFAULT_SIDE/2) - position.getY()); 
		
		// Angle rotation of -rotationAngle of this relative position around the centre of the circle.
		// We check if this relative position with having a rotation opposite to that of this square, 
		// around its centre, belongs to the square without rotation. 
		double unRotatedX = relativeX * Math.cos(Math.toRadians(rotationAngle)) - (relativeY * Math.sin(Math.toRadians(rotationAngle)));
		double unRotatedY = relativeX * Math.sin(Math.toRadians(rotationAngle)) + relativeY * Math.cos(Math.toRadians(rotationAngle));
		return Math.abs(unRotatedX) <= DEFAULT_SIDE/2 && Math.abs(unRotatedY) <= DEFAULT_SIDE/2;
	}

	/**
     * Draws this square in our panel.
     * @param g
     *           The 2D graphic in which this square will be drawn.
     */
	@Override
	public void draw(Graphics2D g) {
		// Creation of a new affine transformation to draw this square with a rotation.
		AffineTransform newTransform = new AffineTransform();
		newTransform.rotate(Math.toRadians(getRotationAngle()), (int) position.getX() + DEFAULT_SIDE/2, (int) position.getY() + DEFAULT_SIDE/2);
		AffineTransform oldTransform = g.getTransform();
		g.transform(newTransform);
		g.setColor(getColor());
		g.drawRect((int) position.getX(), (int) position.getY(), DEFAULT_SIDE, DEFAULT_SIDE);
		
		// Resetting the transformation (initial transformation).
		g.setTransform(oldTransform);
	}
	
	/**
     * Returns a clone of this square.
     * @return A Shape representing a new square instance of this square.
     */
	@Override
	public Shape clone() {
		Square square = new Square();
		square.setPosition(getPosition());
		square.setColor(getColor());
		square.setRotationAngle(getRotationAngle());
		return square;
	}
	
    /**
     *  Returns If the two squares are geometrically identical (without taking into account the colour).
     *  @param square
     *           Another square that we want to compare to this square.
     *  @return A boolean representing the equivalence between these two squares.
     */
	@Override
	public boolean equals(Shape square) {
		return getPosition().equals(square.getPosition()) && getRotationAngle() == square.getRotationAngle();
	}

}
