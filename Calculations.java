package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import DataTypes.Course;
import Main.Main;

import javax.swing.JLabel;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Calculations extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(int args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculations frame = new Calculations(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Calculations(int i) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 309, 128);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.darkGray);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		Course c = Main.courses.get(i);
		NumberFormat formatter = new DecimalFormat("#0.00");
		double g = round(c.getWeightedGrade(), 2);
		double mpg = round(maxPossibleGrade(c, g),2);
		JLabel lblNewLabel = new JLabel("Current Weighted Grade: " + g + "/" + c.getTotalWeightings());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 11, 273, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblMaxPossibleGrade = new JLabel("Max Possible Grade: " + mpg + "%.");
		lblMaxPossibleGrade.setForeground(Color.LIGHT_GRAY);
		lblMaxPossibleGrade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaxPossibleGrade.setBounds(10, 28, 255, 32);
		contentPane.add(lblMaxPossibleGrade);
		
		JLabel lblCurrentAveragedGrade = new JLabel("Current Averaged Grade:" + round((g/c.getTotalWeightings())*100, 2) +  "%.");
		lblCurrentAveragedGrade.setForeground(Color.LIGHT_GRAY);
		lblCurrentAveragedGrade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurrentAveragedGrade.setBounds(10, 44, 233, 32);
		contentPane.add(lblCurrentAveragedGrade);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	private double maxPossibleGrade(Course c, double wg) {
		double d = 100 - c.getTotalWeightings();
		return wg + d;
	}

}
