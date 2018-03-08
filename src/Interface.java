
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Timer;

public class Interface extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int width = 1280;
	private final int heigth = 720;
	
	private BufferedImage foodImage;
	private BufferedImage pigeonImage;
	private BufferedImage bg;

	private JLabel backgroundImage;
	private JLayeredPane lp = new JLayeredPane();

	GameManager gameManager_;

	public Interface() {

		// CREATE THE GAME MANAGER (will manage all the calculations)
		
		gameManager_ = new GameManager();
		gameManager_.CreatePigeonList(4);
				
		
		// CREATE THE WINDOW
		
		//Display the window.
		this.setVisible(true);
		//4. Size the frame.
		this.setSize(1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Pigeons Square");
		addMouseListener(this);
		add(lp);
		
		/*
		 * Initialize image for bg
		 */
		String path = "C:\\Users\\adipe\\Documents\\GitHub\\PIGEON_SQUARE\\src\\grass.jpg";
		File file = new File(path);
		bg = null;
		try {
			bg = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// get food image
		String pathFood = "C:\\Users\\adipe\\Documents\\GitHub\\PIGEON_SQUARE\\src\\Nourriture.png";
		File fileFood = new File(pathFood);
		foodImage = null;
		try {
			foodImage = ImageIO.read(fileFood);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// get pigeon image
		String pathPigeon = "C:\\Users\\adipe\\Documents\\GitHub\\PIGEON_SQUARE\\src\\pigeon.png";
		File filePigeon = new File(pathPigeon);
		pigeonImage = null;
		try {
			pigeonImage = ImageIO.read(filePigeon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Show();
		
	}
	
	// Show all food
	public void Show()
	{
		
		/*
		 *  Background
		 */
		backgroundImage = new JLabel(new ImageIcon(bg));
		backgroundImage.setBorder(BorderFactory.createTitledBorder("label"));
		backgroundImage.setBounds(0, 0, width, heigth);
		lp.add(backgroundImage, new Integer(1));
		
		// Show the list of pigeon on the layer
		for(int i =0; i<gameManager_.getPigeonList().size(); i++) {
			Pigeon pigeonToShow = gameManager_.getPigeonList().get(i);
			
			JLabel pigeonLabel = new JLabel(new ImageIcon(pigeonImage));
			
			pigeonLabel.setBounds(pigeonToShow.getPosition().x-50, pigeonToShow.getPosition().y-50, 100, 100);
			lp.add(pigeonLabel, new Integer(2));
		}
		
		// Show the list of food on the layer
		for(int i =0; i<gameManager_.getFoodList().size(); i++) {
			Food foodToShow = gameManager_.getFoodList().get(i);
			
			JLabel foodLabel = new JLabel(new ImageIcon(foodImage));
			
			foodLabel.setBounds(foodToShow.getPosition().x-16, foodToShow.getPosition().y-12, 32, 24);

			lp.add(foodLabel, new Integer(2));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		int x = e.getX();
		int y = e.getY();
		gameManager_.AddFood(x, y);
		

		//
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
	
	/*
	 * Main
	 */
	
	public static void main(String[] args) {
		Interface frame = new Interface();
		
		Timer t = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

		    	frame.Show();
			}
		});
		t.start();
	}

}
