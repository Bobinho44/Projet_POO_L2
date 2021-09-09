package poo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * DrawingPanel is the class representing the drawing panel where the drawing part of the application will be managed.
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
		
    // The list of shapes to be drawn.
	private ArrayList<Shape> drawables;
	
    /**
     * Constructor initiating the panel, its design and the drawables's list.
     */
	public DrawingPanel() {
		super();
		drawables = new ArrayList<Shape>();
		setBackground(Color.lightGray);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	/**
     * Adds the shape to the drawables's list to be drawn.
     * @param drawable
     *           The shape you want to draw.
     * @see Shape
     */
	public void addDrawable(Shape drawable) {
		// Checking the presence of this shape in the drawables's list
		// and deletion of this shape from the list if this is the case.
		// To be able to redraw this shape above the other.
		for (Shape shape : drawables) {
			if (shape.equals(drawable)) {
				drawables.remove(drawable);			
			}
		}
		drawables.add(drawable);
	}

	/**
     * Delete the first shape in the drawables's list. The first shape in this list is 
     * the oldest shape in this list (the first one added over the others).
     */
	public void removeFirstDrawable() {
		drawables.remove(0);
	}

	/**
     * Clear the drawables's list
     */
	public void clearDrawables() {
		drawables.clear();
	}
	
	/**
     * Returns the number of shapes in the drawables's list.
     * @return An int representing the number of shapes in the drawables's list.
     */
	public int numberOfDrawables() {
		return drawables.size();
	}
	
	/**
     * Returns if the position is included in one of the shapes in the drawables's list.
     * @param position
     *           The position of user mouse's click.
     * @return A boolean representing the inclusion of this position in one of the shapes in the drawables's list.
     */
	public boolean containMouse(Point position) {
		/*
		 * Prevents loop and drawables's list editing conflicts.
		 */
		Shape[] drawablesCopy = drawables.toArray(new Shape[drawables.size()]);
		for (Shape shape : drawablesCopy) {
			if (shape.containsMouse(position)) { 
				return true; 
			}
		}
		return false;
	}

	/**
	 * Redefinition of the drawing method.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// Prevents loop and drawables's list editing conflicts.
		Shape[] drawablesCopy = drawables.toArray(new Shape[drawables.size()]);
		for (Shape shape : drawablesCopy) {
			shape.draw(g2);
		}
	}
}
