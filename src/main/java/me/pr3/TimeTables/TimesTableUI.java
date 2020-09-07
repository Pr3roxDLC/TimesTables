package me.pr3.TimeTables;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import org.apache.commons.lang3.StringUtils;



public class TimesTableUI extends JFrame implements Runnable {

	private JPanel contentPane;
	private Image dbImage;
	private Graphics dbg;
	private JSlider slider;
	private JLabel lblPoints;

	public int numberOfPoints = 0;
	public Point[] points = new Point[0];
	public TextPoint[] points2 = new TextPoint[0];
	public float mul = 2f;
	private JTextField textField;
	private JTextField textField_1;
	private JSlider slider_1;
	private JLabel lblMultiplier;

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
				
				Thread.sleep(30);
			}catch(InterruptedException e ) {
				e.printStackTrace();
			}

		}

	}


	/**
	 * Create the frame.
	 */
	public TimesTableUI() {
		setVisible(true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1781, 940);
		

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		slider = new JSlider();
		slider.setBounds(10, 11, 200, 26);
		contentPane.add(slider);
		slider.setMaximum(200);


		lblPoints = new JLabel("Points: ");
		lblPoints.setBounds(220, 11, 46, 14);
		contentPane.add(lblPoints);

		JButton btnNewButton = new JButton("Useless");
		btnNewButton.setDoubleBuffered(true);
		btnNewButton.setSelected(true);
		btnNewButton.setIgnoreRepaint(true);
		btnNewButton.setBounds(10, 112, 89, 23);
		contentPane.add(btnNewButton);
		
		slider_1 = new JSlider();
		slider_1.setBounds(10, 50, 200, 26);
		contentPane.add(slider_1);
		slider_1.setMaximum(400);
		
		lblMultiplier = new JLabel("Multiplier: ");
		lblMultiplier.setBounds(220, 50, 82, 14);
		contentPane.add(lblMultiplier);
		
		textField = new JTextField();
		textField.setBounds(276, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(276, 50, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);


		
	}


	public void onLoad() {




	}

	public void update() {
		
		mul = slider_1.getValue();

		points = new Point[slider.getValue()];
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
		if(!textField.isFocusOwner()) {
		textField.setText(Integer.toString(slider.getValue()));
		}else {
			if(StringUtils.isNumeric((textField.getText()))) {
			slider.setValue(Integer.parseInt(textField.getText()));
			}
		}
		if(!textField_1.isFocusOwner()) {
		textField_1.setText(Integer.toString(slider_1.getValue()));
		}else {
			
			if(StringUtils.isNumeric((textField_1.getText()))) {
				
				slider_1.setValue(Integer.parseInt(textField_1.getText()));
				
			}
			
		}
	}


	public void paint(Graphics g) {

		
	


		if(dbImage == null) {
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		}

		dbg.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		dbg.drawOval(this.getWidth()/2 - 200, this.getHeight()/2 - 200 , 400, 400);

		


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

			
			
		int connectTo =(int) (i*mul)%points.length;
			
		

				
				System.out.println("Drawn a Line from " + i + " to " + connectTo);
				dbg.drawLine(points[i].getX() + this.getWidth()/2 - 5, points[i].getY() + this.getHeight()/2 - 5, points[connectTo].getX() + this.getWidth()/2 - 5, points[connectTo].getY() + this.getHeight()/2 - 5);


				
				
		}

		g.drawImage(dbImage, 400, 32, null);
		
	}
	public JSlider getSlider() {
		return slider;
	}
	public JLabel getLblPoints() {
		return lblPoints;
	}
	public JSlider getSlider_1() {
		return slider_1;
	}
	public JLabel getLblMultiplier() {
		return lblMultiplier;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JTextField getTextField() {
		return textField;
	}
}
