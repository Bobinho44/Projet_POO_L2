package poo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The View class is a graphical representation of the Model allowing interactions with users.
 * 
 * @author Kylian GERARD and Eugénie SHEKA KASONGO
 * @version 1.0
 */

@SuppressWarnings("serial")
public class View extends JFrame implements MouseListener, KeyListener {
	
	// Height of the menu bar (at the top of the interface).
	private static final int MENU_BAR_HEIGHT = 60;
	
	// Minimum value of the sliders present in the menu bar.
	private static final int MINIMUM_SLIDER_VALUE = 0;
	
	// Maximum value of the sliders present in the menu bar.
	private static final int MAXIMUM_SLIDER_VALUE = 50;
	
	// Initial value of the sliders present in the menu bar.
	private static final int DEFAULT_SLIDER_VALUE = 25;
	
	// Width of the graphic interface (screen).
	public static int WIDTH;
	
	// Height of the graphic interface (screen).
	public static int HEIGHT;
	
	// Panel managing the graphical part of the application.
	private DrawingPanel drawingPanel;
	
	// Controller processing user requests and synchronises the model and the view.
	private Controller controller;
	
	// Colour selection button groups.
	// It is used to choose a colour by clicking on one of the buttons in the group, 
	// or by clicking on one of the drawn shapes (random colour).
	private ButtonGroup colorsButtonGroup;
	
	// Slider for changing the move speed.
	private JSlider	speedSlider;
	
    /**
     * Constructor which instantiates the view and creates the graphical interface 
     * with the different menus. Also sets up the various interactions with these menus..
     * @param title
     *           The application's name.
     */
	public View(String title) {
		super(title);
		build();
	}
	
	/**
	 * Create a link: View to Controller.
     * @param controller
     *           The model that processes user requests and synchronizes the model and the view.
	 * @see Controller
	 */
	public void linkController(Controller controller) {
		this.controller= controller;
	}
		
	/**
	 * Getter: 
     * Returns the colour selection button groups.
     * @return A ButtonGroup representing the colour selection button groups.
     */
	public ButtonGroup getColorsButtonGroup() {
		return colorsButtonGroup;
	}
	
	/**
	 * Getter: 
     * Returns the slider for changing the move speed.
     * @return A JSlider representing the slider for changing the move speed.
     */
	public JSlider getSpeedSlider() {
		return speedSlider;
	}
	
	/**
	 * Creating the graphic interface.
	 */
	private void build() {
		drawingPanel = new DrawingPanel();
		drawingPanel.addMouseListener(this);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().add(drawingPanel , BorderLayout.CENTER);
		buildContentPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		drawingPanel.addKeyListener(this);
		drawingPanel.setFocusTraversalKeysEnabled(false);
		drawingPanel.requestFocus();
	}
	
	/**
	 * Creating menus.
	 */
	private void buildContentPane() {
		setVisible(true);
		drawingPanel.setLayout(null);
		WIDTH = getWidth();
		HEIGHT = getHeight();
		JMenuBar menuBar = new JMenuBar();
		drawingPanel.add(menuBar);
		menuBar.setBounds(0,0, WIDTH, MENU_BAR_HEIGHT);
		
		// Creating the Shape menu.
		JMenu menuShape = new JMenu("Shape");
		createMenu("Shapes", menuShape);
		menuBar.add(menuShape);
		
		// Creating the Path menu.
		JMenu menuPath = new JMenu("Path");
		createMenu("Paths", menuPath);
		menuBar.add(menuPath);
		
		// Creating the color menu.
		JMenu menuColor = new JMenu("Color");
		colorsButtonGroup = new ButtonGroup();
		for (ColorList color : ColorList.values()) { 
			JCheckBoxMenuItem colorCheckBox = new JCheckBoxMenuItem(color.getName());
			colorCheckBox.setForeground(Color.LIGHT_GRAY);
			colorCheckBox.setBackground(color.getColor());
			colorCheckBox.setBorderPainted(true);
			colorCheckBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.setColor(color.getColor());
					
				}
			});
			 colorsButtonGroup.add(colorCheckBox);
			 menuColor.add(colorCheckBox);
			 if (color.getColor().equals(Color.red)) { colorCheckBox.setSelected(true); }
		}
		menuBar.add(menuColor);
		
		// Creating the path's movement direction menu.
		JToggleButton moveDirectionToggleButton = new JToggleButton("Sens normal  "); 
		moveDirectionToggleButton.addActionListener( new ActionListener() {
		
			@Override
		    public void actionPerformed(ActionEvent e) {
				int isInverse = moveDirectionToggleButton.getText().equals("Sens normal  ") ? -1 : 1;
				moveDirectionToggleButton.setText(isInverse == -1 ? "Sens inversé " : "Sens normal  ");
				controller.setMoveDirection(isInverse);
			}
		});
		menuBar.add(moveDirectionToggleButton);
		
		
		// Creating the trail menu.
		ButtonGroup trailsButtonGroup = new ButtonGroup();
		String[] trailsNameList = {"Complete trail", "No trail", "Comet trail"};
		for (int i = 0; i < trailsNameList.length; i++) {
			JCheckBoxMenuItem trailCheckBox = new JCheckBoxMenuItem(trailsNameList[i]);
			  trailCheckBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int trailIndex = 1;
					if (trailCheckBox.getText().equalsIgnoreCase("No trail")) { trailIndex = 2; }
					if (trailCheckBox.getText().equalsIgnoreCase("Comet trail")) { trailIndex = 3; }
					controller.setTrail(trailIndex);
					
				}
			});
			  trailsButtonGroup.add(trailCheckBox);
			  menuBar.add(trailCheckBox);
			  trailCheckBox.setSelected(true);
		}
		
		
		// Creating the speed modifier menu.
		speedSlider = createSlider("Speed modifier", menuBar);
		speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				controller.setSleepTime(speedSlider.getValue());
				
			}
		});
		
		
		// Creating the rotate modifier menu.
		JSlider sliderRotation = createSlider("Rotate modifier", menuBar);
		sliderRotation.setValue(0);
		sliderRotation.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				controller.setShapeAngleIncrement(sliderRotation.getValue());
				
			}
		});
		
		// Creating the quit menu.
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
						
			}
		});
		menuBar.add(quit);
	}
	
	/**
	 * Widespread creation of different sliders.
     * @param sliderName
     *           The name of the slider to be created.
     * @param menuBar
     *           The main menu bar with all other menus and buttons.
     * @return A JSlider representing the slider the slider we have just created.
	 */
	private JSlider createSlider(String sliderName, JMenuBar menuBar) {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, MINIMUM_SLIDER_VALUE, MAXIMUM_SLIDER_VALUE, DEFAULT_SLIDER_VALUE);
		slider.setMajorTickSpacing(10);
	    slider.setMinorTickSpacing(1);
	    slider.setPaintLabels(true);
	    
	    JPanel sliderPanel = new JPanel();
	    menuBar.add(sliderPanel);
	    sliderPanel.setLayout(new BorderLayout());
	    
	    JLabel sliderLabel = new JLabel((sliderName));
	    sliderLabel.setHorizontalAlignment(JTextField.CENTER);
	    sliderPanel.add(sliderLabel, BorderLayout.NORTH);
	    sliderPanel.add(slider, BorderLayout.SOUTH);
	  	return slider;
	   }

	/**
	 * Generalised creation of path and shape selection menus.
     * @param directory
     *           The name of the package (i.e. directory) in which the shapes and paths are placed.
     *           paths'package: "Paths" and shapes's package: "Shapes"
     * @param menu
     *           The main drop-down menu where the path and shape selection buttons will be placed.
	 */
	private void createMenu(String directory, JMenu menu) {
		File folder = new File(System.getProperty("user.dir") + "/src/" + directory);
		
		// All files in the package "Shapes" or "Paths".
		File[] filesList = folder.listFiles();
		ButtonGroup buttonGroup = new ButtonGroup();
		for (File file : filesList) {
			if (file.isFile()) {
			
				// Get file's name without extension.
				String radioButtonName = file.getName().replaceFirst("[.][^.]+$", "");
				JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(radioButtonName);
				radioButton.addActionListener(new ActionListener() {
				  
				@Override
				public void actionPerformed(ActionEvent e) {
					
						try {
							// Instantiate a Shape or a Path.
							Object selectedObject = Class.forName(directory + "." + radioButtonName).newInstance();
							if (directory.equalsIgnoreCase("Shapes")) {
								controller.setShape(selectedObject);
							}
							else if (directory.equalsIgnoreCase("Paths")) {
								controller.setPath(selectedObject);
							}
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {}
					}
			  	});
			  	buttonGroup.add(radioButton);
			  	menu.add(radioButton);
			}
		}
	}

	/**
     * Transmitting the information  to the drawingPanel to add the shape to the list of objects to be drawn.
     * @param drawable
     *           The shape you want to draw.
     * @see Shape
     * @see DrawingPanel
     */
	public void addDrawable(Shape drawable) {
		drawingPanel.addDrawable(drawable);
	}
	
	/**
     * Transmitting the information to the drawingPanel to delete the first shape in the drawables's list.
     * @see DrawingPanel
     */
	public void removeFirstDrawable() {
		drawingPanel.removeFirstDrawable();
	}

	/**
     * Transmitting the information to the drawingPanel to clear the drawables's list.
     * @see DrawingPanel
     */
	public void clearDrawable() {
		drawingPanel.clearDrawables();
	}
	
	/**
     * Transmitting the information to the drawingPanel to returns the number of shapes in the drawables's list.
     * @return An int representing the number of shapes in the drawables's list.
     * @see DrawingPanel
     */
	public int numberOfDrawable() {
		return drawingPanel.numberOfDrawables();
	}
	
	/**
     * Transmitting the information to the drawingPanel to returns if the 
     * position is included in one of the shapes in the drawables's list.
     * @param position
     *           The position of user mouse's click.
     * @return A boolean representing the inclusion of this position in one of the shapes in the drawables's list.
     * @see DrawingPanel
     */
	public boolean containMouse(Point position) {
		return drawingPanel.containMouse(position);
	}
	
	/**
	 * Transmit the information to the controller when a mouse button has been pressed.
     * @param e
     *           The mouse pressed event.
	 * @see Controller
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		controller.mouseClicked(e.getX(), e.getY());
	}

	/**
	 * Transmit the information to the controller when a key '+' or "-" has been pressed.
     * @param e
     *           The key pressed event.
	 * @see Controller
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == '+' || e.getKeyChar() == '-') {
			controller.keypadChangingSpeed(e.getKeyChar());
		}
	}
	
	/**
	 * Submitting a request to refresh the view to the drawingPanel.
	 * @see DrawingPanel
	 */
	public void update() {
		drawingPanel.repaint();
		drawingPanel.setFocusTraversalKeysEnabled(false);
		drawingPanel.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
