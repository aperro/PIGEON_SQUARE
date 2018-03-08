
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Interface extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int width = 1280;
	private final int heigth = 720;
	
	private BufferedImage foodImage;

	private JLabel backgroundImage;
	private JLayeredPane lp = new JLayeredPane();

	public List<Food> food = new ArrayList<Food>();

	public Interface() {
		setTitle("Pigeons Square");
		addMouseListener(this);
		add(lp);
		
		/*
		 * Create Background
		 */
		String path = "C:\\Users\\adipe\\Documents\\GitHub\\PIGEON_SQUARE\\src\\grass.jpg";
		File file = new File(path);
		BufferedImage bg = null;
		try {
			bg = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backgroundImage = new JLabel(new ImageIcon(bg));
		backgroundImage.setBorder(BorderFactory.createTitledBorder("label"));
		backgroundImage.setBounds(0, 0, width, heigth);
		
		lp.add(backgroundImage, new Integer(1));
		
		String pathFood = "C:\\Users\\adipe\\Documents\\GitHub\\PIGEON_SQUARE\\src\\Nourriture.png";
		File fileFood = new File(pathFood);
		foodImage = null;
		try {
			foodImage = ImageIO.read(fileFood);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX()-9;
		int y = e.getY()-38;
		if (x < 0) x += 9;
		if (y < 0) y += 38;
		System.out.println("Mouse Clicked at X: " + x + " - Y: " + y);

		
		//////////////////////////////////////////////
		System.out.println("Mouse Clicked at X: " + x + " - Y: " + y);
		JLabel foodLabel = new JLabel(new ImageIcon(foodImage));
		foodLabel.setBounds(x-16, y-12, 32, 24);

		food.add(new Food((float) (10 + Math.random() * (15 - 10)), new Point(x,y)));

		lp.add(foodLabel, new Integer(2));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new Interface();
		//Display the window.
		frame.setVisible(true);
		//4. Size the frame.
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}




	/*
	 * Main
	 */
	public static void main(String[] args) {

		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

}
