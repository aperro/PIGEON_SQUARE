
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Interface extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private int mX, mY;
	private int oldX = -1, oldY = -1;


	private static final long serialVersionUID = 1L;
	private final int width = 1280;
	private final int heigth = 720;

	private BufferedImage foodImage;
	private BufferedImage staleFoodImage;
	private BufferedImage ramierImage;
	private BufferedImage colombinImage;
	private BufferedImage bisetImage;
	private BufferedImage bg;

	private JLabel backgroundImage;
	private JLayeredPane lp = new JLayeredPane();

	GameManager gameManager_;

	public Interface() {

		// CREATE THE GAME MANAGER (will manage all the calculations)

		gameManager_ = new GameManager();
		gameManager_.CreatePigeonList(2,3,4);

		// CREATE THE WINDOW

		//Display the window.
		this.setVisible(true);
		//4. Size the frame.
		this.setSize(1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Pigeons Square");
		addMouseListener(this);
		add(lp);

		// Get the path for pictures
		URL urlFreshFood = getClass().getResource("FreshFood.png");
		URL urlStaleFood = getClass().getResource("StaleFood.png");
		URL urlGrass = getClass().getResource("Grass.jpg");
		URL urlRamier = getClass().getResource("Ramier.png");
		URL urlColombin = getClass().getResource("Colombin.png");
		URL urlBiset = getClass().getResource("Biset.png");

		/*
		 * Initialize image for bg
		 */
		File fileGrass = new File(urlGrass.getPath());
		bg = null;
		try {
			bg = ImageIO.read(fileGrass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get food image
		File fileFood = new File(urlFreshFood.getPath());
		foodImage = null;
		try {
			foodImage = ImageIO.read(fileFood);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// get stale food image
		File fileStaleFood = new File(urlStaleFood.getPath());
		staleFoodImage = null;
		try {
			staleFoodImage = ImageIO.read(fileStaleFood);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// get pigeon images
		File fileRamier = new File(urlRamier.getPath());
		ramierImage = null;
		try {
			ramierImage = ImageIO.read(fileRamier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File fileColombin = new File(urlColombin.getPath());
		colombinImage = null;
		try {
			colombinImage = ImageIO.read(fileColombin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File fileBiset = new File(urlBiset.getPath());
		bisetImage = null;
		try {
			bisetImage = ImageIO.read(fileBiset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Show();
	}

	// Show all food
	public void Show()
	{
		lp.removeAll();//or remove(JComponent)
		/*
		 *  Background
		 */
		backgroundImage = new JLabel(new ImageIcon(bg));
		backgroundImage.setBounds(0, 0, width, heigth);
		lp.add(backgroundImage, new Integer(1));

		// Show the list of pigeon on the layer
		for(int i =0; i<gameManager_.getPigeonList().size(); i++) {
			Pigeon pigeonToShow = gameManager_.getPigeonList().get(i);
			String type = pigeonToShow.getClass().toString();
			JLabel pigeonLabel;
			switch (type)
			{
			case "class Ramier":
				pigeonLabel = new JLabel(new ImageIcon(ramierImage));
				break;
			case "class Colombin":
				pigeonLabel = new JLabel(new ImageIcon(colombinImage));
				break;
			default:
				pigeonLabel = new JLabel(new ImageIcon(bisetImage));
				break;
			}

			pigeonLabel.setBounds(pigeonToShow.getPosition().x-50, pigeonToShow.getPosition().y-50, 100, 100);
			lp.add(pigeonLabel, new Integer(2));
		}

		// Show the list of food on the layer
		for(int i =0; i<gameManager_.getFoodList().size(); i++) {
			Food foodToShow = gameManager_.getFoodList().get(i);
			JLabel foodLabel;
			if (foodToShow.checkFreshness())
			{
				foodLabel = new JLabel(new ImageIcon(foodImage));
			} else
			{
				foodLabel = new JLabel(new ImageIcon(staleFoodImage));
			}

			foodLabel.setBounds(foodToShow.getPosition().x-16, foodToShow.getPosition().y-12, 32, 24);

			lp.add(foodLabel, new Integer(2));
		}
		lp.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		gameManager_.AddFood(x, y);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse enter detected! Actual mouse position is: " + e.getX()+ "," + e.getY() + ".");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse exited detected! Actual mouse position is: " + e.getX()+ "," + e.getY() + ".");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void ActualizePigeonState() {
		gameManager_.DetectFoodAndMovePigeon();
	}
	
	public void AffraidPigeon() {
		gameManager_.AffraidPigeon();
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
				if(Math.random()*1000 <= 10.0f) {
					frame.AffraidPigeon();
				}
				frame.Show();
				frame.ActualizePigeonState();
			}
		});
		t.start();
	}

}
