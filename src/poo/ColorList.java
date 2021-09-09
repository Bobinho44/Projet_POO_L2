package poo;

import java.awt.Color;

/**
 * ColorList is an enumeration bringing together the different colours of the Color class associated with their names (String)..
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */
public enum ColorList {
	BLACK(Color.BLACK, "Black"),
	BLUE(Color.BLUE, "Blue"),
	CYAN(Color.CYAN, "Cyan"),
	DARK_GRAY(Color.DARK_GRAY, "Dark gray"),
	GRAY(Color.GRAY, "Gray"),
	GREEN(Color.GREEN, "Green"),
	WHITE(Color.WHITE, "White"),
	MAGENTA(Color.MAGENTA, "Magenta"),
	ORANGE(Color.ORANGE, "Orange"),
	PINK(Color.PINK, "Pink"),
	RED(Color.RED, "Red"),
	YELLOW(Color.YELLOW, "Yellow");
	
	
    // The color of this colorList's value.
	private Color color; 
	
    // The name of this colorList's value.
	private String name;
	  
    private ColorList(Color color, String name) { 
        this.color = color; 
        this.name = name;
    }
    
	/**
	 * Getter: 
     * Returns the color of this colorList's value.
     * @return A Color representing the color of this colorList's value.
     */
	public Color getColor() { 
        return color; 
    } 
 
	/**
	 * Getter: 
     * Returns the name of this colorList's value.
     * @return A String representing the name of this colorList's value.
     */
    public String getName() {
    	return name;
    } 
}
