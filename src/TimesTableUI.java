import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;



public class TimesTableUI extends JFrame implements Runnable {

	private JPanel contentPane;
	private Image dbImage;
	private Graphics dbg;
	private JSlider slider;
	private JLabel lblPoints;

	public int numberOfPoints = 0;
	public Point[] points = new Point[0];
	public TextPoint[] points2 = new TextPoint[0];
	public float mul = 2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Thread f = new Thread(new TimesTableUI());

		f.start();

	}






	public void run() {

		while(true) {

			onLoad();

			repaint();
			//System.out.println("[Frame]: "+ currentFrame++);
			try {

				update();

				Thread.sleep(10);
			}catch(InterruptedException e ) {
				e.printStackTrace();
			}

		}

	}


	/**
	 * Create the frame.
	 */
	public TimesTableUI() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 564);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		slider = new JSlider();
		slider.setBounds(10, 11, 200, 26);
		contentPane.add(slider);


		lblPoints = new JLabel("Points");
		lblPoints.setBounds(20, 48, 46, 14);
		contentPane.add(lblPoints);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 73, 89, 23);
		contentPane.add(btnNewButton);


		setVisible(true);
	}


	public void onLoad() {




	}

	public void update() {

		points = new Point[200];
		points2 = new TextPoint[points.length];

		for(int i = 0; i < points.length; i++) {

			double x = Math.sin(Math.toRadians((i / (float) points.length ) * 360)) * 200;
			double y = Math.cos(Math.toRadians((i / (float) points.length ) * 360)) * 200;

			points[i] = new Point((int)x, (int)y);

			double x2 = Math.sin(Math.toRadians((i / (float) points.length ) * 360)) * 250;
			double y2 = Math.cos(Math.toRadians((i / (float) points.length ) * 360)) * 250;

			points2[i] = new TextPoint((int)x2, (int)y2, Integer.toString(i));

			//System.out.println("Point " + i + " Created at: " + x + " / " + y);
		}


	}


	public void paint(Graphics g) {

	



		if(dbImage == null) {
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		}


		dbg.drawOval(this.getWidth()/2 - 200, this.getHeight()/2 - 200 , 400, 400);

		g.drawImage(dbImage, 8, 32, null);


		for(int i = 0; i < points.length; i++) {

			System.out.println(i);

			dbg.fillOval(

					points[i].getX() + this.getWidth()/2 - 5,
					points[i].getY() + this.getHeight()/2 - 5,
					5,
					5
					);

			dbg.drawString(
					points2[i].getText(),					
					points2[i].getX() + this.getWidth()/2 - 5,
					points2[i].getY() + this.getHeight()/2 - 5
					);

		}

		for(int i = 0; i < points.length; i++) {
			int j = (int) (i * mul);
			int connectTo = j;
			System.out.println("I is now: " + i);
			
			
			while(j * mul >= points.length) {
				System.out.println("Subtracing " + points.length + " from " + (int)(j*mul));
				j =- points.length; 
				connectTo = j;
			}
			
			connectTo = j;

				
				System.out.println("Drawn a Line from " + i + " to " + connectTo);
				dbg.drawLine(points[i].getX() + this.getWidth()/2 - 5, points[i].getY() + this.getHeight()/2 - 5, points[connectTo].getX() + this.getWidth()/2 - 5, points[connectTo].getY() + this.getHeight()/2 - 5);



		}

	}
	public JSlider getSlider() {
		return slider;
	}
	public JLabel getLblPoints() {
		return lblPoints;
	}
}
